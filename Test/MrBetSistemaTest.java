import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MrBetSistemaTest {
    AVBettingController controller;

    @BeforeEach
    void setUp() {
        this.controller = new AVBettingController();
        this.controller.registerTeam("007_JB", "Example", "Aston Martin");
        this.controller.registerTournament("NBA", 30);
        this.controller.placeBet("007_JB", "NBA", 1, 1000.00);
    }

    @Test
    void stringIdParaTimeTest() {
        assertEquals("007_JB", this.controller.idStringToTeam("007_JB").getId());
        assertEquals("Example", this.controller.idStringToTeam("007_JB").getName());
        assertEquals("Aston Martin", this.controller.idStringToTeam("007_JB").getMascot());
    }

    @Test
    void stringIdParaTime() {
        assertNull(this.controller.idStringToTeam("Sinatra"));
    }

    @Test
    void stringNomeParaCampeonatoTest() {
        assertEquals("NBA", this.controller.nameToTournament("NBA").getName());
        assertEquals(30, this.controller.nameToTournament("NBA").getLIMIT_PARTICIPANTS());
    }

    @Test
    void adicionaTimeEmCampeonatoTest() {
        assertNull(this.controller.nameToTournament("UCL"));
    }

    @Test
    void registerTeam() {
        assertNull(this.controller.idStringToTeam("322_AM"));
        this.controller.registerTeam("322_AM", "Am", "Cachorro");
        assertNotNull(this.controller.idStringToTeam("322_AM"));
        assertEquals(this.controller.idStringToTeam("322_AM").getName(), "Am");
        assertNotNull(this.controller.idStringToTeam("007_JB"));
    }

    @Test
    void registerAlreadyExistingTeamTest() {
        assertEquals("THIS TEAM ALREADY EXISTS!", this.controller.registerTeam("007_JB", "A", "Aston Martin"));
        assertEquals("Example", this.controller.idStringToTeam("007_JB").getName());
    }

    @Test
    void registerTeamWithNoIdTest() {
        assertEquals("YOU CAN'T CREATE A TEAM WITH NO ID!", this.controller.registerTeam("", "Frank Sinatra", "Ema"));
    }

    @Test
    void registerNamelessTeamTest() {
        assertEquals("YOU CAN'T CREATE A NAMELESS TEAM!", this.controller.registerTeam("007_JB", "", "Aston Martin"));
    }

    @Test
    void registerTeamWithoutAMascotTest() {
        assertEquals("YOU CAN'T CREATE A TIME WITHOUT A MASCOT!", this.controller.registerTeam("322_AB", "Name", ""));
    }

    @Test
    void registerTournamentTest() {
        assertEquals("NEW TOURNAMENT REGISTERED!", this.controller.registerTournament("EUROLEAGUE", 10));
    }

    @Test
    void registerANamelessTournamentTest() {
        assertEquals("A TOURNAMENT'S NAME CAN NOT BE BLANK!", this.controller.registerTournament("", 10));
    }

    @Test
    void registerTournamentWithNoTeamsAcceptanceTest() {
        assertEquals("A TOURNAMENT MUST ACCEPT AT LEAST ONE TEAM!", this.controller.registerTournament("Summer League", 0));
        assertEquals("A TOURNAMENT MUST ACCEPT AT LEAST ONE TEAM!", this.controller.registerTournament("Summer League", -1));
    }

    @Test
    void registerAlreadyExistingTournamentTest() {
        assertEquals("THIS TOURNAMENT ALREADY EXISTS!", this.controller.registerTournament("NBA", 10));
    }

    @Test
    void getBetsTest() {
        assertNotNull(this.controller.getBets());
    }

    @Test
    void placeBetTest() {
        assertEquals("BET SUCCESSFULLY PLACED!", this.controller.placeBet("007_JB", "NBA", 1, 100.00));
    }

    @Test
    void placeBetInvalidTeamTest() {
        assertEquals("BET NOT PLACED. THE WANTED TEAM DOESN'T EXIST!", this.controller.placeBet("Borussia Dortmund", "NBA", 10, 100.00));
        assertEquals("BET NOT PLACED. THE WANTED TEAM DOESN'T EXIST!", this.controller.placeBet("", "NBA", 10, 100.00));
    }

    @Test
    void placeBetInvalidTournamentTest() {
        assertEquals("BET NOT PLACED. THE WANTED TOURNAMENT DOESN'T EXIST!", this.controller.placeBet("007_JB", "UCL", 1, 100.00));
        assertEquals("BET NOT PLACED. THE WANTED TOURNAMENT DOESN'T EXIST!", this.controller.placeBet("007_JB", "", 1, 100.00));
    }

    @Test
    void placeBetInvalidPositionTest() {
        assertEquals("BET NOT PLACED. INVALID POSITION, THERE ARE NOT THIS AMOUNT OF TEAMS IN THE COMPETITION!", this.controller.placeBet("007_JB", "NBA", 100, 1000.00));
    }

    @Test
    void placeBetPositionLessThanOneTest() {
        assertEquals("BET NOT PLACED. YOUR POSITION MUST EQUALS OR BE GREATER THAN 1!", this.controller.placeBet("007_JB", "NBA", 0, 1000.00));
        assertEquals("BET NOT PLACED. YOUR POSITION MUST EQUALS OR BE GREATER THAN 1!", this.controller.placeBet("007_JB", "NBA", -1, 1000.00));
    }

    @Test
    void placeBetInvalidValueTest() {
        assertEquals("INPUT A VALID VALUE. IT MUST BE BIGGER THAN 0!", this.controller.placeBet("007_JB", "NBA", 1, 0.00));
        assertEquals("INPUT A VALID VALUE. IT MUST BE BIGGER THAN 0!", this.controller.placeBet("007_JB", "NBA", 1, -1.00));
    }

    @Test
    void checkIfTeamIsInASpecificTournamentTest(){
        this.controller.addTeamInTournament("007_JB", "NBA");
        assertEquals(this.controller.checkIfTeamIsInASpecificTournament("007_JB", "NBA"), "THE TEAM IS IN THE TOURNAMENT!");
    }

    @Test
    void checkIfTeamIsInASpecificTournamentAndItIsNotTest() {
        assertEquals(this.controller.checkIfTeamIsInASpecificTournament("007_JB", "NBA"), "THE TEAM IS NOT IN THE TOURNAMENT!");
    }
}