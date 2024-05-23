package tech.grupo4.java.rachas.service.controller;

import java.util.List;
import java.util.Optional;

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

import tech.grupo4.java.rachas.partida.Partida;
import tech.grupo4.java.rachas.partida.PartidaRepository;
import tech.grupo4.java.rachas.partida.PartidaRequest;
import tech.grupo4.java.rachas.partida.PartidaService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
public class PartidaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PartidaService partidaService;

    @SpyBean
    private PartidaRepository partidaRepository;

    @Test
    @Order(1)
    public void listarTodos_sucesso() throws Exception {
        Partida partida = new Partida();
        partida.setId(1);
        partida.setNumero(1);
        partida.setTimeA("Time A");
        partida.setTimeB("Time B");
        partida.setDuracao("90 minutos");
        partida.setPlacar("2-1");

        Mockito.when(partidaRepository.findAll()).thenReturn(List.of(partida));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/partidas")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].numero").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].timeA").value("Time A")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].timeB").value("Time B")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].duracao").value("90 minutos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].placar").value("2-1")
        );

        Mockito.verify(partidaService, Mockito.times(1)).listarTodos();
    }

    @Test
    @Order(2)
    public void buscarPorNumero_sucesso() throws Exception {
        Partida partida = new Partida();
        partida.setId(1);
        partida.setNumero(1);
        partida.setTimeA("Time A");
        partida.setTimeB("Time B");
        partida.setDuracao("90 minutos");
        partida.setPlacar("2-1");

        Mockito.when(partidaRepository.findByNumero(1)).thenReturn(Optional.of(partida));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/partidas/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.numero").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.timeA").value("Time A")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.timeB").value("Time B")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.duracao").value("90 minutos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.placar").value("2-1")
        );

        Mockito.verify(partidaService, Mockito.times(1)).buscarPorNumero(1);
    }

    @Test
    @Order(3)
    public void buscarPorNumero_naoEncontrado() throws Exception {
        Mockito.when(partidaRepository.findByNumero(1)).thenReturn(Optional.empty());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/partidas/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

        Mockito.verify(partidaService, Mockito.times(1)).buscarPorNumero(1);
    }

    @Test
    @Order(4)
    public void adicionarPartida_sucesso() throws Exception {
        PartidaRequest partidaRequest = new PartidaRequest();
        partidaRequest.setNumero(1);
        partidaRequest.setTimeA("Time A");
        partidaRequest.setTimeB("Time B");
        partidaRequest.setDuracao("90 minutos");
        partidaRequest.setPlacar("2-1");

        Partida partida = new Partida();
        partida.setId(1);
        partida.setNumero(1);
        partida.setTimeA("Time A");
        partida.setTimeB("Time B");
        partida.setDuracao("90 minutos");
        partida.setPlacar("2-1");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/partidas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "numero": 1,
                                    "timeA": "Time A",
                                    "timeB": "Time B",
                                    "duracao": "90 minutos",
                                    "placar": "2-1"
                                }
                                """)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.numero").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.timeA").value("Time A")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.timeB").value("Time B")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.duracao").value("90 minutos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.placar").value("2-1")
        );

        Mockito.verify(partidaService, Mockito.times(1)).adicionarPartida(Mockito.any(PartidaRequest.class));
    }

    @Test
    @Order(5)
    public void excluir_sucesso() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/partidas/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        Mockito.verify(partidaService, Mockito.times(1)).excluir(1);
    }
}