import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    Time time;
    Tournament tournament;
    AVBettingController sistema;

    @BeforeEach
    void setUp() {
        time = new Time("250_PB", "Nacional de Patos", "Canário");
        tournament = new Tournament("LP2", 20);
        time.adicionaCampeonato(new Tournament("NBA", 30));
        sistema = new AVBettingController();
    }

    @Test
    void getCampeonatosTest() {
        assertFalse(time.getCampeonatos().isEmpty());
        assertEquals(1, time.getCampeonatos().size());
        time.adicionaCampeonato(new Tournament("UCL", 32));
        assertEquals(2, time.getCampeonatos().size());
    }

    @Test
    void getIdTest() {
        assertEquals("250_PB", time.getId());
    }

    @Test
    void getMascoteTest() {
        assertEquals("Canário", time.getMascote());
    }

    @Test
    void adicionaCampeonatoTest() {
        assertEquals("TIME INSCRITO NO CAMPEONATO COM SUCESSO!", time.adicionaCampeonato(tournament));
    }

    @Test
    void adicionaCampeonatoJaPresenteTest() {
        assertEquals("CADASTRO NÃO REALIZADO. O TIME JÁ ESTAVA NO CAMPEONATO!", time.adicionaCampeonato(new Tournament("NBA", 10)));
    }

    @Test
    void getNomeTest() {
        assertEquals("Nacional de Patos", time.getNome());
    }

    @Test
    void testToStringTest() {
        assertEquals("[250_PB] Nacional de Patos / Canário", time.toString());
    }
}