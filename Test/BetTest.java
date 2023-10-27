import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BetTest {
    Bet bet;
    Team team;
    Tournament tournament;

    @BeforeEach
    void setUp() {
        team = new Team("322_AM", "Am", "Dog");
        tournament = new Tournament("NBA", 30);
        bet = new Bet(team, tournament, 1, 100.00);
    }

    @Test
    void getValueTest() {
        assertEquals(100.00, bet.getValue());
    }

    @Test
    void getTeamTest() {
        assertEquals("Am", bet.getTeam().getName());
    }

    @Test
    void getTournamentTest() {
        assertEquals("NBA", bet.getTournament().getName());
    }
}