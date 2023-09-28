import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApostaTest {
    Aposta aposta;
    Time time;
    Campeonato campeonato;

    @BeforeEach
    void setUp() {
        time = new Time("322_AM", "Am", "Cachorro");
        campeonato = new Campeonato("NBA", 30);
        aposta = new Aposta(time, campeonato, 1, 100.00);
    }

    @Test
    void getValorTest() {
        assertEquals(100.00, aposta.getValor());
    }

    @Test
    void getTimeTest() {
        assertEquals("Am", aposta.getTime().getNome());
    }

    @Test
    void getCampeonatoTest() {
        assertEquals("NBA", aposta.getCampeonato().getNome());
    }
}