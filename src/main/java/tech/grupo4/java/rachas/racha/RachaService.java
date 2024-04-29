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
import tech.grupo4.java.rachas.racha.Racha.PrioridadeEnum;
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
        dto.setPrioridade(racha.getPrioridade());

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
                .map(this::convertToRachaDto)
                .toList();
    }

    public RachaDto adicionar(RachaRequest request) {
        Racha todoItem = this.modelMapper.map(request, Racha.class);
        todoItem.setUuid(UUID.randomUUID());
        todoItem.setDisponivel(true);
        todoItem.setPrioridade(PrioridadeEnum.BAIXA);
        Racha novoRacha = this.repository.save(todoItem);
        return this.modelMapper.map(novoRacha, RachaDto.class);
    }

    public void atualizar(UUID uuid, RachaUpdateRequest request) {
        Racha existingRacha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);
        this.modelMapper.map(request, existingRacha);
        this.repository.save(existingRacha);
    }

    @Transactional
    public void marcarIndisponivel(UUID uuid) {
        this.repository.marcarIndisponivel(uuid);
    }

    @Transactional
    public void excluir(UUID uuid) {
        this.repository.deleteByUuid(uuid);
    }

    public void atribuirJogador(UUID uuid, String username) {
        Racha racha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);
        Jogador jogador = this.jogadorRepository.findByUsername(username).orElseThrow(JogadorNaoEncontradoException::new);
        racha.getJogadores().add(jogador);
        this.repository.save(racha);
    }

    public void atribuirPartida(UUID uuid, int numero) {
        Racha racha = this.repository.findByUuid(uuid).orElseThrow(RachaNaoEncontradoException::new);
        Partida partida = this.partidaRepository.findByNumero(numero).orElseThrow(PartidaNaoEncontradoException::new);
        partida.setRacha(racha);
        System.out.println(partida);
        racha.getPartidas().add(partida);
        System.out.println(racha.getPartidas().stream());
        this.repository.save(racha);
    }

}