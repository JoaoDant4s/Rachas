package tech.grupo4.java.rachas.service.racha;

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
import tech.grupo4.java.rachas.racha.RachaService;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
public class RachaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private RachaService rachaService;

    @Test
    @Order(1)
    public void create_RachaWithoutOwner_shouldThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rachas")
                        .content("""
                                {
                                    "clima": "teste",
                                    "localizacao": "teste",
                                    "data": "teste",
                                    "duracao": "teste"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );

        Mockito.verify(rachaService, Mockito.never()).adicionar(Mockito.any());
    }

    @Test
    @Order(2)
    public void update_Racha_shouldUpdateSuccessfully() throws Exception {

        String jogadorUsername = "jogador_teste";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jogadores")
                        .content("""
                                {
                                    "nome": "Jogador Teste",
                                    "username": "jogador_teste",
                                    "password": "Senha@123",
                                    "avaliacao": 5.0
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );


        String rachaJson = mockMvc.perform(
                MockMvcRequestBuilders.post("/rachas")
                        .content("""
                                {
                                    "localizacao": "Local Teste",
                                    "clima": "Clima Teste",
                                    "data": "Data Teste",
                                    "quantidadeMaxima": 10,
                                    "quantidadeAtual": 0,
                                    "esporte": "Esporte Teste",
                                    "avaliacaoMinima": 1,
                                    "duracao": "Duracao Teste",
                                    "donoDaBola": "jogador_teste"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andReturn().getResponse().getContentAsString();


        UUID rachaUuid = UUID.fromString(rachaJson.split("\"uuid\":\"")[1].split("\"")[0]);


        String updatedLocation = "Local Atualizado";
        String updateRequestJson = """
                {
                    "localizacao": "Local Atualizado",
                    "clima": "Clima Atualizado",
                    "data": "Data Atualizada",
                    "quantidadeMaxima": 15,
                    "quantidadeAtual": 5,
                    "esporte": "Esporte Atualizado",
                    "avaliacaoMinima": 2,
                    "duracao": "Duracao Atualizada",
                    "donoDaBola": "jogador_teste",
                    "uuid": "%s",
                    "disponivel": false
                }
                """.formatted(rachaUuid);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/rachas/" + rachaUuid + "/" + jogadorUsername)
                        .content(updateRequestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );


        Mockito.verify(rachaService, Mockito.times(1)).atualizar(Mockito.eq(rachaUuid), Mockito.eq(jogadorUsername), Mockito.any());
    }


}

