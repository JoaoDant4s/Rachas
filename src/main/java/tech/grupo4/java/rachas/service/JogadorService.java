package tech.grupo4.java.rachas.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.exception.JogadorNaoEncontradoException;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.model.JogadorRequest;
import tech.grupo4.java.rachas.model.dto.JogadorDto;
import tech.grupo4.java.rachas.repository.JogadorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public List<JogadorDto> listarTodos() {
        return this.repository.findAll().stream()
                .map(jogador -> this.modelMapper.map(jogador, JogadorDto.class))
                .toList();
    }

    public JogadorDto buscarPorLogin(String username) {
        Jogador jogador = this.repository.findByUsername(username)
                .orElseThrow(JogadorNaoEncontradoException::new);
        return this.modelMapper.map(jogador, JogadorDto.class);
    }

    public JogadorDto adicionarJogador(JogadorRequest request) {
        Jogador jogador = modelMapper.map(request, Jogador.class);
        jogador.setPassword(passwordEncoder.encode(jogador.getPassword()));
        Jogador novoJogador = repository.save(jogador);
        return modelMapper.map(novoJogador, JogadorDto.class);
    }

    @Transactional
    public void excluir(String username) {
        this.repository.deleteByUsername(username);
    }

    public Jogador getByUsernameEntity(String username) {
        return this.repository.findByUsername(username)
                .orElseThrow(JogadorNaoEncontradoException::new);
    }
}