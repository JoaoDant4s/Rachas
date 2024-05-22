package tech.grupo4.java.rachas.service;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tech.grupo4.java.rachas.racha.RachaService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
}
