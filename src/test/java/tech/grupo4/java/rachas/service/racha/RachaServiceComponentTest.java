package tech.grupo4.java.rachas.service.racha;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import tech.grupo4.java.rachas.exception.RachaNaoEncontradoException;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.racha.Racha;
import tech.grupo4.java.rachas.racha.RachaRepository;
import tech.grupo4.java.rachas.racha.RachaService;
import tech.grupo4.java.rachas.racha.RachaUpdateRequest;
import tech.grupo4.java.rachas.repository.JogadorRepository;

public class RachaServiceComponentTest {

    @Mock
    private RachaRepository rachaRepository;

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RachaService rachaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void update_Racha_shouldUpdateSuccessfully() {
        UUID rachaUuid = UUID.randomUUID();
        String jogadorUsername = "jogador_teste";

        Racha existingRacha = new Racha();
        existingRacha.setUuid(rachaUuid);
        existingRacha.setDonoDaBola(jogadorUsername);

        RachaUpdateRequest updateRequest = new RachaUpdateRequest();
        updateRequest.setLocalizacao("Local Atualizado");
        updateRequest.setClima("Clima Atualizado");
        updateRequest.setData("Data Atualizada");
        updateRequest.setQuantidadeMaxima(15);
        updateRequest.setQuantidadeAtual(5);
        updateRequest.setEsporte("Esporte Atualizado");
        updateRequest.setAvaliacaoMinima(2);
        updateRequest.setDuracao("Duracao Atualizada");
        updateRequest.setDonoDaBola(jogadorUsername);
        updateRequest.setUuid(rachaUuid);
        updateRequest.setDisponivel(false);

        when(rachaRepository.findByUuid(rachaUuid)).thenReturn(Optional.of(existingRacha));
        when(jogadorRepository.findByUsername(jogadorUsername)).thenReturn(Optional.of(new Jogador()));

        rachaService.atualizar(rachaUuid, jogadorUsername, updateRequest);

        verify(rachaRepository, times(1)).save(existingRacha);
    }

    @Test
    public void buscarPorNumeroNaoEncontrado_deveLancarExcecao() {
        UUID invalidUuid = UUID.randomUUID();
        when(rachaRepository.findByUuid(invalidUuid)).thenReturn(Optional.empty());

        assertThrows(RachaNaoEncontradoException.class, () -> rachaService.buscarPorUuid(invalidUuid));
    }
}
