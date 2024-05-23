package tech.grupo4.java.rachas.service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.model.JogadorRequest;
import tech.grupo4.java.rachas.model.dto.LoginDto;
import tech.grupo4.java.rachas.service.JwtService;
import tech.grupo4.java.rachas.service.JogadorService;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JogadorService jogadorService;

    @MockBean
    private AuthenticationManager authenticationManager;

    private LoginDto loginDto;
    private Jogador jogador;
    private JogadorRequest jogadorRequest;

    @BeforeEach
    public void setUp() {
        loginDto = new LoginDto("testUser", "testPassword");
        jogador = new Jogador();
        jogador.setUsername("testUser");
        jogador.setPassword("testPassword");

        jogadorRequest = new JogadorRequest();
        jogadorRequest.setUsername("testUser");
        jogadorRequest.setPassword("testPassword");
        jogadorRequest.setNome("Test User");

        jogadorService.adicionarJogador(jogadorRequest);
    }
    
    @Test
    public void login_sucesso() throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());

        Mockito.when(jogadorService.getByUsernameEntity(loginDto.username())).thenReturn(jogador);
        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenReturn(authentication);
       // Mockito.when(jwtService.createToken(jogador)).thenReturn("mockedToken");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "testUser",
                                    "password": "testPassword"
                                }
                                """)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(jogadorService, Mockito.times(1)).getByUsernameEntity(loginDto.username());
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(Mockito.any(Authentication.class));
        //Mockito.verify(jwtService, Mockito.times(1)).createToken(jogador);
    }

    @Test
    public void login_falha_autenticacao() throws Exception {
        Mockito.when(jogadorService.getByUsernameEntity(loginDto.username())).thenReturn(jogador);
        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
        .thenThrow(new BadCredentialsException("Invalid credentials"));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "testUser",
                                    "password": "testPasswor"
                                }
                                """)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());

        Mockito.verify(jogadorService, Mockito.times(1)).getByUsernameEntity(loginDto.username());
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(Mockito.any(Authentication.class));
        //Mockito.verify(jwtService, Mockito.never()).createToken(jogador);
    }
}
