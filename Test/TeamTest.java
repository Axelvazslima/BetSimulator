import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team team;
    Tournament tournament;
    AVBettingController controller;

    @BeforeEach
    void setUp() {
            team = new Team("BVB_09", "Borussia Dortmund", "Bee");
        tournament = new Tournament("LP2", 20);
        team.addTeamInTournament(new Tournament("NBA", 30));
        controller = new AVBettingController();
    }

    @Test
    void getTournamentsTest() {
        assertFalse(team.getTournaments().isEmpty());
        assertEquals(1, team.getTournaments().size());
        team.addTeamInTournament(new Tournament("UCL", 32));
        assertEquals(2, team.getTournaments().size());
    }

    @Test
    void getIdTest() {
        assertEquals("BVB_09", team.getId());
    }

    @Test
    void getMascotTest() {
        assertEquals("Bee", team.getMascot());
    }

    @Test
    void addTeamInTournamentTest() {
        assertEquals("TEAM SUCCESSFULLY REGISTERED IN THE TOURNAMENT!", team.addTeamInTournament(tournament));
    }

    @Test
    void addAlreadyRegisteredTeamInTournament() {
        assertEquals("THE REGISTRATION COULD NOT BE COMPLETED. THE TEAM WAS ALREADY IN THE TOURNAMENT!", team.addTeamInTournament(new Tournament("NBA", 10)));
    }

    @Test
    void getNameTest() {
            assertEquals("Borussia Dortmund", team.getName());
    }

    @Test
    void ToStringTest() {
            assertEquals("[BVB_09] Borussia Dortmund / Bee", team.toString());
    }
}