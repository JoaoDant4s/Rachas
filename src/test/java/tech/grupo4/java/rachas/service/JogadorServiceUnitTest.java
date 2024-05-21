package tech.grupo4.java.rachas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.model.JogadorRequest;
import tech.grupo4.java.rachas.model.dto.JogadorDto;
import tech.grupo4.java.rachas.repository.JogadorRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JogadorServiceUnitTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private JogadorService jogadorService;

    private Jogador jogador;
    private JogadorRequest jogadorRequest;

    @BeforeEach
    public void setup() {
        jogador = new Jogador();
        jogador.setUsername("testUser");
        jogador.setPassword("testPass");

        jogadorRequest = new JogadorRequest();
        jogadorRequest.setUsername("testUser");
        jogadorRequest.setPassword("testPass");
    }

    @Test
    // criar jogador deve ter sucesso
    public void adicionarJogador_sucesso() {
        String encodedPassword = "encodedPass";
        Jogador savedJogador = new Jogador();
        savedJogador.setUsername("testUser");
        savedJogador.setPassword(encodedPassword);

        Mockito.when(modelMapper.map(jogadorRequest, Jogador.class)).thenReturn(jogador);
        Mockito.when(passwordEncoder.encode(jogador.getPassword())).thenReturn(encodedPassword);
        Mockito.when(jogadorRepository.save(jogador)).thenReturn(savedJogador);
        Mockito.when(modelMapper.map(savedJogador, JogadorDto.class)).thenReturn(new JogadorDto());

        JogadorDto result = jogadorService.adicionarJogador(jogadorRequest);

        assertNotNull(result);
        Mockito.verify(jogadorRepository, Mockito.times(1)).save(jogador);
        assertEquals(encodedPassword, jogador.getPassword());
    }
}
