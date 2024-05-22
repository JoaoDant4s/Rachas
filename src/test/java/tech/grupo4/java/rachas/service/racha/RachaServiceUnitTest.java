package tech.grupo4.java.rachas.service.racha;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.grupo4.java.rachas.exception.JogadorNaoEncontradoException;
import tech.grupo4.java.rachas.exception.PartidaNaoEncontradoException;
import tech.grupo4.java.rachas.exception.RachaNaoEncontradoException;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.racha.RachaRepository;
import tech.grupo4.java.rachas.racha.RachaRequest;
import tech.grupo4.java.rachas.racha.RachaService;
import tech.grupo4.java.rachas.repository.JogadorRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RachaServiceUnitTest {
    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private RachaRepository rachaRepository;

    @InjectMocks
    private RachaService rachaService;

    private Jogador jogador;

    @BeforeEach
    public void setup() {
        jogador = new Jogador();
        jogador.setUsername("unit-test");
    }

    // Criar racha para jogador inexistente, deve ocorrer um erro.
    @Test
    @Order(1)
    public void create_jogadorNotExists_mustThrowsException() {
        var jogador = new Jogador();
        jogador.setUsername("invalid");

        Mockito.when(
                jogadorRepository.findByUsername(jogador.getUsername())
        ).thenReturn(Optional.empty());

        RachaRequest teste = new RachaRequest();
        teste.setLocalizacao("teste");
        teste.setEsporte("teste");
        teste.setDuracao("teste");
        teste.setData("teste");
        teste.setClima("teste");
        teste.setQuantidadeAtual(1);
        teste.setAvaliacaoMinima(1);
        teste.setQuantidadeMaxima(15);
        teste.setDonoDaBola(jogador.getUsername());

        Assertions.assertThrows(
                JogadorNaoEncontradoException.class,
                () -> rachaService.adicionar(teste)
        );
    }

    @Test
    public void buscarPorUUIDNaoEncontrado_deveLancarExcecao() {
        UUID invalidUuid = UUID.randomUUID();
        Mockito.when(rachaRepository.findByUuid(invalidUuid)).thenReturn(Optional.empty());

        assertThrows(RachaNaoEncontradoException.class, () -> rachaService.buscarPorUuid(invalidUuid));
    }
}
