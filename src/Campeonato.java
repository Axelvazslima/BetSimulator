import java.util.HashSet;
import java.util.Objects;

/**
 * Classe responsável pela organização das informações relacionadas ao campeonato.
 */
public class Campeonato {

    /**
     * Nome da competição.
     */
    private String nome;

    /**
     * Conjunto de times participantes da competição.
     */
    private HashSet<Time> times;

    /**
     * Limite de times participantes permitidos na competição.
     */
    private int LIMITEDETIMES;

    /**
     * Constrói um campeonato a partir de seu nome.
     * @param nome Nome do campeonato.
     * @param LIMITEDETIMES Máximo de times presentes no campeonato.
     */
    public Campeonato(String nome, int LIMITEDETIMES) {
        this.nome = nome;
        this.LIMITEDETIMES = LIMITEDETIMES;
        this.times = new HashSet<>();
    }

    /**
     * Adiciona time na competição.
     * @param time Time a ser adicionado na competição.
     * @return Se o time foi adicionado à competição ou não.
     */
    public String adicionaTime(Time time){
        this.times.add(time);
        time.adicionaCampeonato(this);
        return "TIME CADASTRADO COM SUCESSO!";
    }

    /**
     * Pega o nome da competição.
     * @return Nome da competição.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Checa se um time específico está participando do campeonato.
     * @param time Time que será procurado na lista de times participates do campeonato.
     * @return Se o time desjado está participando do campeonato.
     */
    public String verificaTimeEmCampeonato(Time time){
        if (this.times.contains(time)) return "O TIME ESTÁ NO CAMPEONATO!";
        return "O TIME NÃO ESTÁ NO CAMPEONATO!";
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
        Campeonato that = (Campeonato) o;
        return Objects.equals(nome.toLowerCase(), that.nome.toLowerCase());
    }

    /**
     * Muito usado por Java internamente em comparações.
     * @return Código identificador da competição a partir de seu nome.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    /**
     * Pega o limite de times permitidos na competição.
     * @return Limite de times permitidos na competição.
     */
    public int getLIMITEDETIMES() {
        return this.LIMITEDETIMES;
    }

    /**
     * Pega o conjunto de times participantes da competição.
     * @return Conjunto de times participantes da competição.
     */
    public HashSet<Time> getTimes() {
        return this.times;
    }
}
