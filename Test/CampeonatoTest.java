import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CampeonatoTest {
    Campeonato campeonato;

    @BeforeEach
    void setUp() {
        campeonato = new Campeonato("NBA", 30);
        campeonato.adicionaTime(new Time("BUCKS", "Milwaukee Bucks", "Veado"));
    }

    @Test
    void adicionaTime() {
        assertEquals("TIME CADASTRADO COM SUCESSO!", campeonato.adicionaTime(new Time("NYK", "New York Knicks", "Empire State Building")));
    }

    @Test
    void getNome() {
        assertEquals("NBA", campeonato.getNome());
    }

    @Test
    void verificaTimeEmCampeonato() {
        assertEquals("O TIME ESTÁ NO CAMPEONATO!", campeonato.verificaTimeEmCampeonato(new Time("BUCKS", "Milwaukee Bucks", "Veado")));
    }

    @Test
    void verificaTimeInexistenteEmCampeonato() {
        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", campeonato.verificaTimeEmCampeonato(new Time("RMD", "Real Madrid", "Sr. Z")));
    }

    @Test
    void getLIMITEDETIMES() {
        assertEquals(30, campeonato.getLIMITEDETIMES());
    }

    @Test
    void getTimes() {
        assertEquals(1, campeonato.getTimes().size());
    }
}