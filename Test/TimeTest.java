import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    Time time;
    Campeonato campeonato;
    MrBetSistema sistema;

    @BeforeEach
    void setUp() {
        time = new Time("250_PB", "Nacional de Patos", "Canário");
        campeonato = new Campeonato("LP2", 20);
        time.adicionaCampeonato(new Campeonato("NBA", 30));
        sistema = new MrBetSistema();
    }

    @Test
    void getCampeonatosTest() {
        assertFalse(time.getCampeonatos().isEmpty());
        assertEquals(1, time.getCampeonatos().size());
        time.adicionaCampeonato(new Campeonato("UCL", 32));
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
        assertEquals("TIME INSCRITO NO CAMPEONATO COM SUCESSO!", time.adicionaCampeonato(campeonato));
    }

    @Test
    void adicionaCampeonatoJaPresenteTest() {
        assertEquals("CADASTRO NÃO REALIZADO. O TIME JÁ ESTAVA NO CAMPEONATO!", time.adicionaCampeonato(new Campeonato("NBA", 10)));
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