import java.util.HashSet;
import java.util.Objects;

/**
 * Organizes the tournament's related information.
 */
public class Tournament {

    /**
     * Name da tournament.
     */
    private String name;

    /**
     * Set of teams in the tournament.
     */
    private HashSet<Time> teams;

    /**
     * Limite de teams participantes permitidos na tournament.
     */
    private int LIMIT_PARTICIPANTS;

    /**
     * Constrói um campeonato a partir de seu name.
     * @param name Name do campeonato.
     * @param LIMIT_PARTICIPANTS Máximo de teams presentes no campeonato.
     */
    public Tournament(String name, int LIMIT_PARTICIPANTS) {
        this.name = name;
        this.LIMIT_PARTICIPANTS = LIMIT_PARTICIPANTS;
        this.teams = new HashSet<>();
    }

    /**
     * Adiciona team na tournament.
     * @param team Time a ser adicionado na tournament.
     * @return Se o team foi adicionado à tournament ou não.
     */
    public String addTeamInTheTournament(Time team){
        this.teams.add(team);
        team.adicionaCampeonato(this);
        return "TIME CADASTRADO COM SUCESSO!";
    }

    /**
     * Get the tournament's name.
     * @return Tournament's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checa se um team específico está participando do campeonato.
     * @param team Time que será procurado na lista de teams participates do campeonato.
     * @return Se o team desjado está participando do campeonato.
     */
    public String checkIfTeamIsInTheTournament(Time team){
        if (this.teams.contains(team)) return "THE TEAM IS IN THE TOURNAMENT!";
        return "THE TEAM IS NOT IN THE TOURNAMENT!";
    }

    /**
     * Verificador de igualdade para comparação entre objetos.
     * @param o Objeto a ser comparado com esse.
     * @return "Verdadeiro", caso os objetos sejam iguais, ou "Falso", caso os objetos sejam diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(name.toLowerCase(), that.name.toLowerCase());
    }

    /**
     * Muito usado por Java internamente em comparações.
     * @return Código identificador da tournament a partir de seu name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    /**
     * Pega o limite de teams permitidos na tournament.
     * @return Limite de teams permitidos na tournament.
     */
    public int getLIMITEDETIMES() {
        return this.LIMIT_PARTICIPANTS;
    }

    /**
     * Pega o conjunto de teams participantes da tournament.
     * @return Conjunto de teams participantes da tournament.
     */
    public HashSet<Time> getTeams() {
        return this.teams;
    }
}
