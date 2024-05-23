package tech.grupo4.java.rachas.service.partida;
import org.junit.jupiter.api.Test;
import tech.grupo4.java.rachas.partida.PartidaDto;

import static org.assertj.core.api.Assertions.assertThat;
public class PartidaDTOUnitTest {
    @Test
    void testPartidaDtoBuilder() {
        PartidaDto partidaDto = PartidaDto.builder()
                .numero(1)
                .timeA("Time A")
                .timeB("Time B")
                .duracao("90 minutos")
                .placar("2-1")
                .build();

        assertThat(partidaDto.getNumero()).isEqualTo(1);
        assertThat(partidaDto.getTimeA()).isEqualTo("Time A");
        assertThat(partidaDto.getTimeB()).isEqualTo("Time B");
        assertThat(partidaDto.getDuracao()).isEqualTo("90 minutos");
        assertThat(partidaDto.getPlacar()).isEqualTo("2-1");
    }
}
