import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TournamentTest {
    Tournament tournament;

    @BeforeEach
    void setUp() {
        tournament = new Tournament("NBA", 30);
        tournament.addTeamInTheTournament(new Team("BUCKS", "Milwaukee Bucks", "Buck"));
    }

    @Test
    void addTeamInTheTournamentTest() {
        assertEquals("TIME CADASTRADO COM SUCESSO!", tournament.addTeamInTheTournament(new Team("NYK", "New York Knicks", "Empire State Building")));
    }

    @Test
    void getNameTest() {
        assertEquals("NBA", tournament.getName());
    }

    @Test
    void checkIfTeamIsInTheTournamentTest() {
        assertEquals("O TIME ESTÁ NO CAMPEONATO!", tournament.checkIfTeamIsInTheTournament(new Team("BUCKS", "Milwaukee Bucks", "Veado")));
    }

    @Test
    void checkIfNonExistingTeamIsInTheTournamentTest() {
        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", tournament.checkIfTeamIsInTheTournament(new Team("RMD", "Real Madrid", "Sr. Z")));
    }

    @Test
    void getLIMIT_PARTICIPANTSTest() {
        assertEquals(30, tournament.getLIMIT_PARTICIPANTS());
    }

    @Test
    void getTeamsTest() {
        assertEquals(1, tournament.getTeams().size());
    }
}