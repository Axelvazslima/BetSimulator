import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CampeonatoTest {
    Tournament tournament;

    @BeforeEach
    void setUp() {
        tournament = new Tournament("NBA", 30);
        tournament.adicionaTime(new Time("BUCKS", "Milwaukee Bucks", "Veado"));
    }

    @Test
    void adicionaTime() {
        assertEquals("TIME CADASTRADO COM SUCESSO!", tournament.adicionaTime(new Time("NYK", "New York Knicks", "Empire State Building")));
    }

    @Test
    void getNome() {
        assertEquals("NBA", tournament.getName());
    }

    @Test
    void verificaTimeEmCampeonato() {
        assertEquals("O TIME ESTÁ NO CAMPEONATO!", tournament.verificaTimeEmCampeonato(new Time("BUCKS", "Milwaukee Bucks", "Veado")));
    }

    @Test
    void verificaTimeInexistenteEmCampeonato() {
        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", tournament.verificaTimeEmCampeonato(new Time("RMD", "Real Madrid", "Sr. Z")));
    }

    @Test
    void getLIMITEDETIMES() {
        assertEquals(30, tournament.getLIMITEDETIMES());
    }

    @Test
    void getTimes() {
        assertEquals(1, tournament.getTeams().size());
    }
}