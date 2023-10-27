/**
 * Responsável por organizar as apostas.
 */
public class Bet {

    /**
     * Team da aposta.
     */
    private Team team;

    /**
     * Tournament da aposta.
     */
    private Tournament tournament;

    /**
     * Colocação do team na tabela do tournament na aposta.
     */
    private int colocacao;

    /**
     * Valor monetário da aposta com casas decimais.
     */
    private Double valor;

    /**
     * Constrói uma aposta a partir de um Team, um Tournament, uma colocação e um valor monetário.
     * @param team Team.
     * @param tournament Tournament.
     * @param colocacao Colocação do team na tabela do tournament.
     * @param valor Valor monetário apostado.
     */
    public Bet(Team team, Tournament tournament, int colocacao, Double valor){
        this.team = team;
        this.tournament = tournament;
        this.colocacao = colocacao;
        this.valor = valor;
    }

    /**
     * Pega o valor apostado.
     * @return Valor monetáro apostado com duas casas decimais.
     */
    public Double getValue() {
        return valor;
    }

    /**
     * Pega o team sobre a qual a aposta é realizada.
     * @return Team presente na aposta.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Pega o tournament que sobre o qual a aposta é realizada.
     * @return Tournament presente na aposta.
     */
    public Tournament getTournament() {
        return tournament;
    }
}
