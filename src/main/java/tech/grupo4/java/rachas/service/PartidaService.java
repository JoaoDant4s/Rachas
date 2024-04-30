package tech.grupo4.java.rachas.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.grupo4.java.rachas.exception.*;
import tech.grupo4.java.rachas.model.Partida;
import tech.grupo4.java.rachas.model.dto.PartidaDto;
import tech.grupo4.java.rachas.model.dto.validation.PartidaRequest;
import tech.grupo4.java.rachas.repository.PartidaRepository;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository repository;
    private final ModelMapper modelMapper;

    public List<PartidaDto> listarTodos() {
        return this.repository.findAll().stream()
                .map(partida -> this.modelMapper.map(partida, PartidaDto.class))
                .toList();
    }

    public PartidaDto buscarPorNumero(int numero) {
        Partida partida = this.repository.findByNumero(numero)
                .orElseThrow(PartidaNaoEncontradoException::new);
        return this.modelMapper.map(partida, PartidaDto.class);
    }

    public PartidaDto adicionarPartida(PartidaRequest request) {
        Partida partida = modelMapper.map(request, Partida.class);
        Partida novoPartida = repository.save(partida);
        return modelMapper.map(novoPartida, PartidaDto.class);
    }

    @Transactional
    public void excluir(int numero) {
        this.repository.deleteByNumero(numero);
    }
}
