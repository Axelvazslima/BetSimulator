import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sistema responsável pela lógica e funcionamento da plataforma de apostas MrBet.
 * Base de todo o programa.
 */
public class MrBetSistema {

    /**
     * Mapa de times.
     */
    private HashMap<String, Time> times;

    /**
     * Mapa de campeonatos.
     */
    private HashMap<String, Campeonato> campeonatos;

    /**
     * Lista de apostas.
     */
    private ArrayList<Aposta> apostas;

    /**
     * Constrói o sistema de apostas da plataforma Mr. Bet.
     */
    public MrBetSistema() {
        times = new HashMap<>();
        campeonatos = new HashMap<>();
        apostas = new ArrayList<>();
    }

    /**
     * Pega o Time com o nome desejado, caso ele exista.
     * @param id Código identificador do Time.
     * @return Time com o nome desejado.
     */
    public Time stringIdParaTime(String id) {
        return times.get(id);
    }

    /**
     * Pega o Campeonato com o nome desejado, caso ele exista.
     * @param nome Nome do Campeonato.
     * @return Campeonato com o nome desejado.
     */
    public Campeonato stringNomeParaCampeonato(String nome) {
        return campeonatos.get(nome);
    }

    /**
     * Adiciona um time a um campeonato, tendo uma saída personalizada para cada caso.
     * @param idTime Código identificador do Time a ser adicionado no campeonato
     * @param campeonatoNome Nome do Campeonato que receberá um time.
     */
    public String adicionaTimeEmCampeonato (String idTime, String campeonatoNome){
        Time time = stringIdParaTime(idTime);
        if(time == null) return "CADASTRO NÃO REALIZADO. TIME NÃO EXISTE!";

        Campeonato campeonato = stringNomeParaCampeonato(campeonatoNome);
        if (campeonato == null) return "CADASTRO NÃO REALIZADO. CAMPEONATO NÃO EXISTE!";

        if (campeonato.getTimes().size() >= campeonato.getLIMITEDETIMES()) return "CADASTRO NÃO REALIZADO. TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";

        if (campeonato.getTimes().contains(time)) return "CADASTRO NÃO REALIZADO. TIME JÁ PRESENTE NO CAMPEONATO!";

        return campeonato.adicionaTime(time);
    }

    /**
     * Cria time e o adiciona no conjunto de times.
     * @param id Identificador do Time.
     * @param nome Nome do Time.
     * @param mascote Mascote do Time.
     * @return Se a operação foi bem sucedida ou não.
     */
    public String adicionaTime (String id, String nome, String mascote){
        if (id.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM UM IDENTIFICADOR TEXTUAL!";
        if (nome.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM NOME!";
        if (mascote.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM MASCOTE!";
        if (times.containsKey(id)) return "TIME JÁ EXISTE!";
        times.put(id, new Time(id, nome, mascote));
        return "INCLUSÃO REALIZADA!";
    }

    /**
     * Cria um campeonato.
     * @param nome Nome do campeonato.
     * @param LIMITEDETIMES Limite de times que podem ser inseridos no campeonato.
     * @return Se o campeonato foi criado ou não.
     */
    public String adicionaCampeonato(String nome, int LIMITEDETIMES){
        if (nome.length() == 0) return "O NOME NÃO PODE SER VAZIO!";
        if (LIMITEDETIMES <= 0) return "O CAMPEONATO DEVE ACEITAR NO MÍNIMO UM TIME. INSIRA UM NÚMERO INTEIRO VÁLIDO!";
        if (campeonatos.containsKey(nome)) return "CAMPEONATO JÁ EXISTE.";
        campeonatos.put(nome, new Campeonato(nome, LIMITEDETIMES));
        return "CAMPEONATO CRIADO.";
    }

    /**
     * Tratamento de exceção para conversão de dado em inteiro.
     * @param number Número.
     * @return Número inteiro ou -1, caso a conversão não seja possível.
     */
    public int exceptionHandlerIntConverter(String number){
        try{
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe){
            return -1;
        }
    }

    /**
     * Tratamento de exceção para conversão de dado em número racional.
     * @param number Número.
     * @return Número racional ou -1, caso a conversão não seja possível.
     */
    public double exceptionHandlerDoubleConverter(String number){
        try{
            return Double.parseDouble(number);
        } catch (NumberFormatException nfe){
            return -1;
        }
    }

    /**
     * Pega a lista de apostas do programa.
     * @return Lista de apostas.
     */
    public ArrayList<Aposta> getApostas() {
        return this.apostas;
    }

    /**
     * Realiza uma aposta a partir de um Time, uma Competição, sua colocação na tabela e um valor monetário.
     * @param idTime Código identificador do Time.
     * @param nomeCampeonato Nome do Campeonato.
     * @param colocacao Colocação no time na tabela do Campeonato.
     * @param valor Valor monetário.
     * @return Aposta registrada.
     */
    public String adicionaAposta(String idTime, String nomeCampeonato, int colocacao, Double valor){
        if (!(this.times.containsKey(idTime))) return "APOSTA NÃO REGISTRADA. TIME NÃO EXISTE!";
        if (!(this.campeonatos.containsKey(nomeCampeonato))) return "APOSTA NÃO REGISTRADA. CAMPEONATO NÃO EXISTE";

        Time time = stringIdParaTime(idTime);
        Campeonato campeonato = stringNomeParaCampeonato(nomeCampeonato);

        if (colocacao > campeonato.getLIMITEDETIMES()) return "APOSTA NÃO REGISTRADA. POSIÇÃO INVÁLIDA. NÃO HÁ TIMES SUFICIENTES.";

        if (colocacao < 1) return "APOSTA NÃO REGISTRADA. INSIRA UMA COLOCAÇÃO VÁLIDA. DEVE SER UM NÚMERO INTEIRO A PARTIR DE 1.";

        if (valor <= 0) return "INSIRA UM VALOR VÁLIDO. O VALOR DA APOSTA DEVE SER UM NÚMERO MAIOR QUE 0.";

        if (colocacao == 1) time.incrementaVezesEmPrimeiro();
        this.apostas.add(new Aposta(time, campeonato, colocacao, valor));
        return "APOSTA REGISTRADA!";
    }

    /**
     * Verifica se um time está participando de determinado campeonato.
     * @param idTime Código identificador do time desejado.
     * @param nomeCampeonato Nome do campeonato em questão.
     * @return Se o time participa ou não do campeonato.
     */
    public String verificaTimeEmCampeonato(String idTime, String nomeCampeonato){
        Time time = stringIdParaTime(idTime);
        if (time == null) return "TIME NÃO EXISTE!";

        Campeonato campeonato = stringNomeParaCampeonato(nomeCampeonato);
        if (campeonato == null) return "CAMPEONATO NÃO EXISTE!";

        return campeonato.verificaTimeEmCampeonato(time);
    }

    /**
     * Pega os times cadastrados no sistema.
     * @return Par "nome-valor" de times cadastrados no sistema.
     */
    public HashMap<String, Time> getTimes() {
        return times;
    }

    /**
     * Pega o status das apostas formatado.
     * @return Status das apostas já formatado.
     */
    public String statusDasApostas(){
        StringBuilder sb = new StringBuilder();
        String out = null;

        int i = 0;
        for (Aposta a :
                this.apostas) {
            i++;
            out = String.format("\n%d. [%s] %s / %s\n%s\n%d/%d\nR$ %.2f\n", i, a.getTime().getId(), a.getTime().getNome(), a.getTime().getMascote(), a.getCampeonato().getNome(), a.getCampeonato().getTimes().size(), a.getCampeonato().getLIMITEDETIMES(), a.getValor());
            sb.append(out);
        }
        if (this.apostas.isEmpty()) out = "NÃO HÁ NENHUMA APOSTA REGISTRADA.";
        return out;
    }

    public String vezesEmPrimeiro(){
        StringBuilder sb = new StringBuilder();
        sb.append("Popularidade em apostas\n");
        int curIndex = 0;
        String out = null;
        for (Time time:
                this.times.values()) {
            sb.append(time.getNome()).append(" / ").append(time.getVezesEmPrimeiro());
            curIndex++;
            if (curIndex < times.size()) sb.append("\n");
            out = sb.toString();
        }
        if (this.times.isEmpty()) return "NÃO HÁ NENHUM TIME CADASTRADO NO SISTEMA";
        return out;
    }

    public String recuperarTime(Time time){
        if (time == null) {
            return "TIME NÃO EXISTE!";
        } else {
            return time.toString();
        }
    }

    public String exibirCampeonatosDoTime(Time time){
        StringBuilder sb = new StringBuilder();
        if (time == null) {
            sb.append("TIME NÃO EXISTE.");
        } else if (time.getCampeonatos().isEmpty()) {
            sb.append("O TIME NÃO ESTÁ INSCRITO EM NENHUM CAMPEONATO!");
        } else {
            sb.append(String.format("Campeonatos do %s:\n", time.getNome()));
            for (Campeonato c:
                    time.getCampeonatos()) {
                sb.append(String.format("* %s - %d/%d\n", c.getNome(), c.getTimes().size(), c.getLIMITEDETIMES()));
            }
        }
        return sb.toString();
    }
}

