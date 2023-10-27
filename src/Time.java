import java.util.HashSet;
import java.util.Objects;

/**
 * Classe responsável por agrupar as características de um time.
 */
public class Time {

    /**
     * Identificador no formato: "CODIGONUMERICO-ESTADO"
     */
    private final String id;

    /**
     * Nome da equipe.
     */
    private final String nome;

    /**
     * Mascote do time.
     */
    private final String mascote;

    /**
     * Conjunto de tournaments que o time participa.
     */
    private HashSet<Tournament> tournaments;

    private int vezesEmPrimeiro;

    /**
     * Constrói um time a partir de um nome, identificador e um mascote.
     * @param id Identificador textual.
     * @param nome Nome da equipe.
     * @param mascote Mascote da equipe.
     */
    public Time(String id, String nome, String mascote){
            this.id = id;
            this.nome = nome;
            this.mascote = mascote;
            this.tournaments = new HashSet<>();
            vezesEmPrimeiro = 0;
    }

    /**
     * @return Conjunto de times que o time está participando.
     */
    public HashSet<Tournament> getCampeonatos() {
        return tournaments;
    }

    /**
     * Pega o código da equipe.
     * @return Código do Time.
     */
    public String getId() {
        return id;
    }

    /**
     * Pega o mascote da equipe.
     * @return Mascote do Time.
     */
    public String getMascote() {
        return mascote;
    }

    /**
     * Inscreve um time em um Tournament.
     * @param tournament Tournament no qual o Time será inscrito.
     * @return Mensagem dizendo se a inscrição foi bem sucedida.
     */
    public String adicionaCampeonato(Tournament tournament) {
        if (this.tournaments.contains(tournament)){
            return "CADASTRO NÃO REALIZADO. O TIME JÁ ESTAVA NO CAMPEONATO!";
        } else {
            this.tournaments.add(tournament);
            return "TIME INSCRITO NO CAMPEONATO COM SUCESSO!";
        }
    }

    /**
     * Verifica igualdade entre objetos.
     * @param o Objeto a ser comparado.
     * @return Se os objeto são iguais ou não.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(id, time.id);
    }

    /**
     * Código usado por Java em comparações.
     * @return Código identificador do objeto a partir da id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id.toUpperCase());
    }

    /**
     * Incremanta, mais um, a quantidade de vezes que o time é visto como favorito da competição por apostadores.
     */
    public void incrementaVezesEmPrimeiro(){
        this.vezesEmPrimeiro++;
    }

    /**
     * Pega a quantidade de vezes que o time apareceu em primeiro.
     * @return Quantas vezes, um inteiro, o time apareceu em primeiro.
     */
    public int getVezesEmPrimeiro() {
        return vezesEmPrimeiro;
    }

    /**
     * @return Nome da Equipe.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Representação textual do Time.
     * @return Representação textual do Time.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append("[").append(this.id).append("] ").append(this.nome).append(" / ").append(this.mascote).toString();
    }
}
