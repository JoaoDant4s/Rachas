package tech.grupo4.java.rachas.service.partida;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import tech.grupo4.java.rachas.exception.PartidaNaoEncontradoException;
import tech.grupo4.java.rachas.partida.Partida;
import tech.grupo4.java.rachas.partida.PartidaDto;
import tech.grupo4.java.rachas.partida.PartidaRepository;
import tech.grupo4.java.rachas.partida.PartidaRequest;
import tech.grupo4.java.rachas.partida.PartidaService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PartidaServiceUnitTest {

    @Mock
    private PartidaRepository partidaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PartidaService partidaService;

    private Partida partida;
    private PartidaRequest partidaRequest;
    private PartidaDto partidaDto;

    @BeforeEach
    public void setup() {
        partida = new Partida();
        partida.setId(1);
        partida.setNumero(1);
        partida.setTimeA("Time A");
        partida.setTimeB("Time B");
        partida.setDuracao("90 minutos");
        partida.setPlacar("2-1");

        partidaRequest = new PartidaRequest();
        partidaRequest.setNumero(1);
        partidaRequest.setTimeA("Time A");
        partidaRequest.setTimeB("Time B");
        partidaRequest.setDuracao("90 minutos");
        partidaRequest.setPlacar("2-1");

        partidaDto = new PartidaDto();
        partidaDto.setNumero(1);
        partidaDto.setTimeA("Time A");
        partidaDto.setTimeB("Time B");
        partidaDto.setDuracao("90 minutos");
        partidaDto.setPlacar("2-1");
    }

    @Test
    public void listarTodos_sucesso() {
        List<Partida> partidas = List.of(partida);

        Mockito.when(partidaRepository.findAll()).thenReturn(partidas);
        Mockito.when(modelMapper.map(partida, PartidaDto.class)).thenReturn(partidaDto);

        List<PartidaDto> result = partidaService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(partidaDto.getTimeA(), result.get(0).getTimeA());
        assertEquals(partidaDto.getTimeB(), result.get(0).getTimeB());
        assertEquals(partidaDto.getDuracao(), result.get(0).getDuracao());
        assertEquals(partidaDto.getPlacar(), result.get(0).getPlacar());
    }

    @Test
    public void buscarPorNumero_sucesso() {
        Mockito.when(partidaRepository.findByNumero(1)).thenReturn(Optional.of(partida));
        Mockito.when(modelMapper.map(partida, PartidaDto.class)).thenReturn(partidaDto);

        PartidaDto result = partidaService.buscarPorNumero(1);

        assertNotNull(result);
        assertEquals(partidaDto.getTimeA(), result.getTimeA());
        assertEquals(partidaDto.getTimeB(), result.getTimeB());
        assertEquals(partidaDto.getDuracao(), result.getDuracao());
        assertEquals(partidaDto.getPlacar(), result.getPlacar());
    }

    @Test
    public void buscarPorNumeroNaoEncontrado_deveLancarExcecao() {
        Mockito.when(partidaRepository.findByNumero(1)).thenReturn(Optional.empty());

        assertThrows(PartidaNaoEncontradoException.class, () -> partidaService.buscarPorNumero(1));
    }

    @Test
    public void adicionarPartida_sucesso() {
        Partida novaPartida = new Partida();
        novaPartida.setId(1);
        novaPartida.setNumero(1);
        novaPartida.setTimeA("Time A");
        novaPartida.setTimeB("Time B");
        novaPartida.setDuracao("90 minutos");
        novaPartida.setPlacar("2-1");

        Mockito.when(modelMapper.map(partidaRequest, Partida.class)).thenReturn(partida);
        Mockito.when(partidaRepository.save(partida)).thenReturn(novaPartida);
        Mockito.when(modelMapper.map(novaPartida, PartidaDto.class)).thenReturn(partidaDto);

        PartidaDto result = partidaService.adicionarPartida(partidaRequest);

        assertNotNull(result);
        assertEquals(partidaDto.getTimeA(), result.getTimeA());
        assertEquals(partidaDto.getTimeB(), result.getTimeB());
        assertEquals(partidaDto.getDuracao(), result.getDuracao());
        assertEquals(partidaDto.getPlacar(), result.getPlacar());
        Mockito.verify(partidaRepository, Mockito.times(1)).save(partida);
    }

    @Test
    public void excluir_sucesso() {
        Mockito.doNothing().when(partidaRepository).deleteByNumero(1);

        partidaService.excluir(1);

        Mockito.verify(partidaRepository, Mockito.times(1)).deleteByNumero(1);
    }
}
