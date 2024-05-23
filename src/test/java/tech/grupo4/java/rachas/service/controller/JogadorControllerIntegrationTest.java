package tech.grupo4.java.rachas.service.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tech.grupo4.java.rachas.service.JogadorService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
public class JogadorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private JogadorService jogadorService;

    @Test
    @Order(1)
    public void create_JogadorWithoutUsername_shouldThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jogadores")
                        .content("""
                                {
                                    "nome": "123456",
                                    "password": "teste",
                                    "avaliacao": 5.0
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );

        Mockito.verify(jogadorService, Mockito.never()).adicionarJogador(Mockito.any());
    }

    @Test
    @Order(1)
    public void create_JogadorWithoutPassword_shouldThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jogadores")
                        .content("""
                                {
                                    "username": "teste",
                                    "nome": "123456",
                                    "avaliacao": 5.0
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );

        Mockito.verify(jogadorService, Mockito.never()).adicionarJogador(Mockito.any());
    }
}
