import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team team;
    Tournament tournament;
    AVBettingController sistema;

    @BeforeEach
    void setUp() {
        team = new Team("250_PB", "Nacional de Patos", "Canário");
        tournament = new Tournament("LP2", 20);
        team.addTeamInTournament(new Tournament("NBA", 30));
        sistema = new AVBettingController();
    }

    @Test
    void getCampeonatosTest() {
        assertFalse(team.getCampeonatos().isEmpty());
        assertEquals(1, team.getCampeonatos().size());
        team.addTeamInTournament(new Tournament("UCL", 32));
        assertEquals(2, team.getCampeonatos().size());
    }

    @Test
    void getIdTest() {
        assertEquals("250_PB", team.getId());
    }

    @Test
    void getMascoteTest() {
        assertEquals("Canário", team.getMascote());
    }

    @Test
    void addTeamInTournamentTest() {
        assertEquals("TIME INSCRITO NO CAMPEONATO COM SUCESSO!", team.addTeamInTournament(tournament));
    }

    @Test
    void addAlreadyRegisteredTeamInTournament() {
        assertEquals("CADASTRO NÃO REALIZADO. O TIME JÁ ESTAVA NO CAMPEONATO!", team.addTeamInTournament(new Tournament("NBA", 10)));
    }

    @Test
    void getNameTest() {
        assertEquals("Nacional de Patos", team.getName());
    }

    @Test
    void ToStringTest() {
        assertEquals("[250_PB] Nacional de Patos / Canário", team.toString());
    }
}