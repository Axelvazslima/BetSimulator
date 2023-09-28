/**
 * Responsável por organizar as apostas.
 */
public class Aposta {

    /**
     * Time da aposta.
     */
    private Time time;

    /**
     * Campeonato da aposta.
     */
    private Campeonato campeonato;

    /**
     * Colocação do time na tabela do campeonato na aposta.
     */
    private int colocacao;

    /**
     * Valor monetário da aposta com casas decimais.
     */
    private Double valor;

    /**
     * Constrói uma aposta a partir de um Time, um Campeonato, uma colocação e um valor monetário.
     * @param time Time.
     * @param campeonato Campeonato.
     * @param colocacao Colocação do time na tabela do campeonato.
     * @param valor Valor monetário apostado.
     */
    public Aposta(Time time, Campeonato campeonato, int colocacao, Double valor){
        this.time = time;
        this.campeonato = campeonato;
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
     * Pega o campeonato que sobre o qual a aposta é realizada.
     * @return Campeonato presente na aposta.
     */
    public Campeonato getCampeonato() {
        return campeonato;
    }
}
