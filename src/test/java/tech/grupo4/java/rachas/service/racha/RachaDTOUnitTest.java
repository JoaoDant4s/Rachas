package tech.grupo4.java.rachas.service.racha;

import org.junit.jupiter.api.Test;
import tech.grupo4.java.rachas.racha.RachaDto;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RachaDTOUnitTest {

    @Test
    void testAddJogadores() {
        RachaDto rachaDto = new RachaDto();
        rachaDto.setJogadores(new ArrayList<>());
        String jogador = "Jogador1";
        rachaDto.getJogadores().add(jogador);
        assertEquals(1, rachaDto.getJogadores().size());
        assertTrue(rachaDto.getJogadores().contains(jogador));
    }

    @Test
    void testAddPartidas() {
        RachaDto rachaDto = new RachaDto();
        rachaDto.setPartidas(new ArrayList<>());
        int partida = 1;
        rachaDto.getPartidas().add(partida);
        assertEquals(1, rachaDto.getPartidas().size());
        assertTrue(rachaDto.getPartidas().contains(partida));
    }
}
