package tech.grupo4.java.rachas.racha;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.exception.*;
import tech.grupo4.java.rachas.partida.*;
import tech.grupo4.java.rachas.model.*;
import tech.grupo4.java.rachas.repository.JogadorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RachaService {

    private final RachaRepository repository;
    private final JogadorRepository jogadorRepository;
    private final ModelMapper modelMapper;
    private final PartidaRepository partidaRepository;

    public List<RachaDto> listar() {
        return this.repository.findAll().stream()
                .filter(Racha::isDisponivel)
                .map(this::convertToRachaDto)
                .collect(Collectors.toList());
    }

    private RachaDto convertToRachaDto(Racha racha) {
        RachaDto dto = new RachaDto();
        dto.setUuid(racha.getUuid());
        dto.setLocalizacao(racha.getLocalizacao());
        dto.setClima(racha.getClima());
        dto.setData(racha.getData());
        dto.setQuantidadeMaxima(racha.getQuantidadeMaxima());
        dto.setQuantidadeAtual(racha.getQuantidadeAtual());
        dto.setDisponivel(racha.isDisponivel());
        dto.setEsporte(racha.getEsporte());
        dto.setAvaliacaoMinima(racha.getAvaliacaoMinima());
        dto.setDuracao(racha.getDuracao());
        dto.setDonoDaBola(racha.getDonoDaBola());

        dto.setJogadores(racha.getJogadores() != null ? racha.getJogadores().stream()
                .map(Jogador::getNome)
                .collect(Collectors.toList()) : new ArrayList<>());

        dto.setPartidas(racha.getPartidas() != null ? racha.getPartidas().stream()
                .map(Partida::getNumero)
                .collect(Collectors.toList()) : new ArrayList<>());

        return dto;
    }


    public List<RachaDto> consultarPorDonoDaBola(String username) {
        return this.repository.findByDonoDaBolaContainingIgnoreCase(username).stream()
                .map(this::convertToRachaDto)
                .toList();
    }

    private Function<Racha, RachaDto> mapToDto() {
        return todoItem -> this.modelMapper.map(todoItem, RachaDto.class);
    }

    public RachaDto buscarPorUuid(UUID uuid) {
        return this.repository.findByUuid(uuid)
                .map(this::convertToRachaDto)
                .orElseThrow(RachaNaoEncontradoException::new);
    }

    public List<RachaDto> buscarPorEsporte(String esporte) {
        return this.repository.findByEsporte(esporte).stream()
                .filter(Racha::isDisponivel)
                .map(this::convertToRachaDto)
                .toList();
    }

    public RachaDto adicionar(RachaRequest request) {
        Jogador donoDaBola = this.jogadorRepository.findByUsername(request.getDonoDaBola())
                .orElseThrow(() -> new IllegalStateException("Jogador não encontrado"));

        Racha todoItem = this.modelMapper.map(request, Racha.class);
        todoItem.setUuid(UUID.randomUUID());
        todoItem.setDisponivel(true);
        todoItem.setQuantidadeAtual(0);
        todoItem.setQuantidadeMaxima(10);
        Racha novoRacha = this.repository.save(todoItem);
        return this.modelMapper.map(novoRacha, RachaDto.class);
    }


    public void atualizar(UUID uuid, String username, RachaUpdateRequest request) {

        Racha existingRacha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);

        if (verificarDono(uuid, username)){
            this.modelMapper.map(request, existingRacha);
            this.repository.save(existingRacha);
        }

        else
            throw new PermissaoInvalidaException();
    }

    @Transactional
    public void marcarIndisponivel(UUID uuid, String username) {
        if (verificarDono(uuid, username))
            this.repository.marcarIndisponivel(uuid);

        else
            throw new PermissaoInvalidaException();
    }

    @Transactional
    public void excluir(UUID uuid, String username) {
        if (verificarDono(uuid, username))
            this.repository.deleteByUuid(uuid);

        else
            throw new PermissaoInvalidaException();
    }

    public void atribuirJogador(UUID uuid, String username2, String username) {
        Racha racha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);

        if (verificarDono(uuid, username2) && racha.isDisponivel()){
            novoJogador(username, racha);
        }

        else
            throw new PermissaoInvalidaException();
    }

    public void entrarJogador(UUID uuid, String username) {
        Racha racha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);

        if (racha.isDisponivel()){
            novoJogador(username, racha);
        }
    }

    public void atribuirPartida(UUID uuid, String username, int numero) {
        Racha racha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);

        if (verificarDono(uuid, username)){
            Partida partida = this.partidaRepository.findByNumero(numero).orElseThrow(PartidaNaoEncontradoException::new);
            partida.setRacha(racha);
            System.out.println(partida);
            racha.getPartidas().add(partida);
            System.out.println(racha.getPartidas().stream());
            this.repository.save(racha);
        }

        else
            throw new PermissaoInvalidaException();
    }

    private boolean verificarDono(UUID uuid, String username){
        Racha racha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);
        Jogador jogador = this.jogadorRepository.findByUsername(username).orElseThrow(JogadorNaoEncontradoException::new);
        return racha.getDonoDaBola().equals(jogador.getNome());
    }

    private void novoJogador(String username, Racha racha){
        Jogador jogador = this.jogadorRepository.findByUsername(username).orElseThrow(JogadorNaoEncontradoException::new);
        racha.getJogadores().add(jogador);
        racha.setQuantidadeAtual(racha.getQuantidadeAtual() + 1);
        if (racha.getQuantidadeAtual() >= racha.getQuantidadeMaxima()) {
            racha.setDisponivel(false);
        }
        this.repository.save(racha);
    }
}