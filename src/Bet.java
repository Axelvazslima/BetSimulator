/**
 * Responsável por organizar as apostas.
 */
public class Bet {

    /**
     * Time da aposta.
     */
    private Time time;

    /**
     * Tournament da aposta.
     */
    private Tournament tournament;

    /**
     * Colocação do time na tabela do tournament na aposta.
     */
    private int colocacao;

    /**
     * Valor monetário da aposta com casas decimais.
     */
    private Double valor;

    /**
     * Constrói uma aposta a partir de um Time, um Tournament, uma colocação e um valor monetário.
     * @param time Time.
     * @param tournament Tournament.
     * @param colocacao Colocação do time na tabela do tournament.
     * @param valor Valor monetário apostado.
     */
    public Bet(Time time, Tournament tournament, int colocacao, Double valor){
        this.time = time;
        this.tournament = tournament;
        this.colocacao = colocacao;
        this.valor = valor;
    }

    /**
     * Pega o valor apostado.
     * @return Valor monetáro apostado com duas casas decimais.
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Pega o time sobre a qual a aposta é realizada.
     * @return Time presente na aposta.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Pega o tournament que sobre o qual a aposta é realizada.
     * @return Tournament presente na aposta.
     */
    public Tournament getCampeonato() {
        return tournament;
    }
}
