import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BetTest {
    Bet bet;
    Time time;
    Tournament tournament;

    @BeforeEach
    void setUp() {
        time = new Time("322_AM", "Am", "Cachorro");
        tournament = new Tournament("NBA", 30);
        bet = new Bet(time, tournament, 1, 100.00);
    }

    @Test
    void getValorTest() {
        assertEquals(100.00, bet.getValor());
    }

    @Test
    void getTimeTest() {
        assertEquals("Am", bet.getTime().getNome());
    }

    @Test
    void getCampeonatoTest() {
        assertEquals("NBA", bet.getCampeonato().getName());
    }
}