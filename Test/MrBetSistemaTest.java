import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MrBetSistemaTest {
    MrBetSistema sistema;

    @BeforeEach
    void setUp() {
        sistema = new MrBetSistema();
        sistema.adicionaTime("007_JB", "Exemplo", "Aston Martin");
        sistema.adicionaCampeonato("NBA", 30);
        sistema.adicionaAposta("007_JB", "NBA", 1, 1000.00);
    }

    @Test
    void stringIdParaTimeTest() {
        assertEquals("007_JB", sistema.stringIdParaTime("007_JB").getId());
        assertEquals("Exemplo", sistema.stringIdParaTime("007_JB").getNome());
        assertEquals("Aston Martin", sistema.stringIdParaTime("007_JB").getMascote());
    }

    @Test
    void stringIdParaTime() {
        assertNull(sistema.stringIdParaTime("Sinatra"));
    }

    @Test
    void stringNomeParaCampeonatoTest() {
        assertEquals("NBA", sistema.stringNomeParaCampeonato("NBA").getNome());
        assertEquals(30, sistema.stringNomeParaCampeonato("NBA").getLIMITEDETIMES());
    }

    @Test
    void adicionaTimeEmCampeonatoTest() {
        assertNull(sistema.stringNomeParaCampeonato("UCL"));
    }

    @Test
    void adicionaTimeNovoTest() {
        assertNull(sistema.stringIdParaTime("322_AM"));
        sistema.adicionaTime("322_AM", "Am", "Cachorro");
        assertNotNull(sistema.stringIdParaTime("322_AM"));
        assertEquals(sistema.stringIdParaTime("322_AM").getNome(), "Am");
        assertNotNull(sistema.stringIdParaTime("007_JB"));
    }

    @Test
    void adicionaTimeJaExistenteTest() {
        assertEquals("TIME JÁ EXISTE!", sistema.adicionaTime("007_JB", "A", "Aston Martin"));
        assertEquals("Exemplo", sistema.stringIdParaTime("007_JB").getNome());
    }

    @Test
    void adicionaTimeIdVazio() {
        assertEquals("NÃO É POSSÍVEL CRIAR UM TIME SEM UM IDENTIFICADOR TEXTUAL!", sistema.adicionaTime("", "Frank Sinatra", "Ema"));
    }

    @Test
    void adicionaTimeNomeVazioTest() {
        assertEquals("NÃO É POSSÍVEL CRIAR UM TIME SEM NOME!", sistema.adicionaTime("007_JB", "", "Aston Martin"));
    }

    @Test
    void adicionaTimeSemMascote() {
        assertEquals("NÃO É POSSÍVEL CRIAR UM TIME SEM MASCOTE!", sistema.adicionaTime("322_AB", "Nome", ""));
    }

    @Test
    void adicionaCampeonatoTest() {
        assertEquals("CAMPEONATO CRIADO.", sistema.adicionaCampeonato("EUROLEAGUE", 10));
    }

    @Test
    void adicionaCampeonatoNomeVazioTest() {
        assertEquals("O NOME NÃO PODE SER VAZIO!", sistema.adicionaCampeonato("", 10));
    }

    @Test
    void adicionaCampeonatoMenosQueMinimoDeTimesPermitidosTest() {
        assertEquals("O CAMPEONATO DEVE ACEITAR NO MÍNIMO UM TIME.", sistema.adicionaCampeonato("Avestruz", 0));
        assertEquals("O CAMPEONATO DEVE ACEITAR NO MÍNIMO UM TIME.", sistema.adicionaCampeonato("Avestruz", -1));
    }

    @Test
    void adicionaCampeonatoJaExistenteTest() {
        assertEquals("CAMPEONATO JÁ EXISTE.", sistema.adicionaCampeonato("NBA", 10));
    }

    @Test
    void getApostasTest() {
        assertNotNull(sistema.getApostas());
    }

    @Test
    void adicionaApostaTest() {
        assertEquals("APOSTA REGISTRADA!", sistema.adicionaAposta("007_JB", "NBA", 1, 100.00));
    }

    @Test
    void adicionaApostaTimeInvalidoTest() {
        assertEquals("APOSTA NÃO REGISTRADA. TIME NÃO EXISTE!", sistema.adicionaAposta("Borussia Dortmund", "NBA", 10, 100.00));
        assertEquals("APOSTA NÃO REGISTRADA. TIME NÃO EXISTE!", sistema.adicionaAposta("", "NBA", 10, 100.00));
    }

    @Test
    void adicionaApostaCampeonatoInvalidoTest() {
        assertEquals("APOSTA NÃO REGISTRADA. CAMPEONATO NÃO EXISTE", sistema.adicionaAposta("007_JB", "UCL", 1, 100.00));
        assertEquals("APOSTA NÃO REGISTRADA. CAMPEONATO NÃO EXISTE", sistema.adicionaAposta("007_JB", "", 1, 100.00));
    }

    @Test
    void adicionaApostaColocacaoMaiorQueOPermitidoTest() {
        assertEquals("APOSTA NÃO REGISTRADA. POSIÇÃO INVÁLIDA. NÃO HÁ TIMES SUFICIENTES.", sistema.adicionaAposta("007_JB", "NBA", 100, 1000.00));
    }

    @Test
    void adicionaApostaColocacaoMenorQueOPermitidoTest() {
        assertEquals("APOSTA NÃO REGISTRADA. COLOCAÇÃO INVÁLIDA. A TABELA COMEÇA EM 1!", sistema.adicionaAposta("007_JB", "NBA", 0, 1000.00));
        assertEquals("APOSTA NÃO REGISTRADA. COLOCAÇÃO INVÁLIDA. A TABELA COMEÇA EM 1!", sistema.adicionaAposta("007_JB", "NBA", -1, 1000.00));
    }

    @Test
    void adicionaApostaValorMenorQueOPermitidoTest() {
        assertEquals("APOSTA NÃO REGISTRADA. O VALOR APOSTADO DEVE SER MAIOR QUE 0!", sistema.adicionaAposta("007_JB", "NBA", 1, 0.00));
        assertEquals("APOSTA NÃO REGISTRADA. O VALOR APOSTADO DEVE SER MAIOR QUE 0!", sistema.adicionaAposta("007_JB", "NBA", 1, -1.00));
    }

    @Test
    void adicionaApostaColocacaoInvalidaMenorQueUmTest(){
        assertEquals("APOSTA NÃO REGISTRADA. COLOCAÇÃO INVÁLIDA. A TABELA COMEÇA EM 1!", sistema.adicionaAposta("007_JB", "NBA", 0, 100.00));
    }

    @Test
    void adicionaApostaColocacaoInvalidaMaiorQuePermitidoTest(){
        assertEquals("APOSTA NÃO REGISTRADA. POSIÇÃO INVÁLIDA. NÃO HÁ TIMES SUFICIENTES.", sistema.adicionaAposta("007_JB", "NBA", 31, 100.00));
    }

    @Test
    void adicionaApostaValorInvalidoMenorQueMinimoTest(){
        assertEquals("APOSTA NÃO REGISTRADA. O VALOR APOSTADO DEVE SER MAIOR QUE 0!", sistema.adicionaAposta("007_JB", "NBA", 1, 0.0));
        assertEquals("APOSTA NÃO REGISTRADA. O VALOR APOSTADO DEVE SER MAIOR QUE 0!", sistema.adicionaAposta("007_JB", "NBA", 1, -1.0));
    }

    @Test
    void verificaTimeEmCampeonatoTimeNaoParticipaTest() {
        assertEquals(sistema.verificaTimeEmCampeonato("007_JB", "NBA"), "O TIME NÃO ESTÁ NO CAMPEONATO!");
    }

    @Test
    void verificaTimeEmCampeonatoTimeParticipaTest(){
        sistema.adicionaTimeEmCampeonato("007_JB", "NBA");
        assertEquals(sistema.verificaTimeEmCampeonato("007_JB", "NBA"), "O TIME ESTÁ NO CAMPEONATO!");
    }
}