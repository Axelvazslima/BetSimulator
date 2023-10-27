import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sistema responsável pela lógica e funcionamento da plataforma de bets MrBet.
 * Base de todo o programa.
 */
public class AVBettingController {

    /**
     * Mapa de teams.
     */
    private HashMap<String, Time> teams;

    /**
     * Mapa de tournaments.
     */
    private HashMap<String, Tournament> tournaments;

    /**
     * Lista de bets.
     */
    private ArrayList<Bet> bets;

    /**
     * Constrói o sistema de bets da plataforma Mr. Bet.
     */
    public AVBettingController() {
        teams = new HashMap<>();
        tournaments = new HashMap<>();
        bets = new ArrayList<>();
    }

    /**
     * Pega o Time com o name desejado, caso ele exista.
     * @param id Código identificador do Time.
     * @return Time com o name desejado.
     */
    public Time idStringToTeam(String id) {
        return teams.get(id);
    }

    /**
     * Pega o Tournament com o name desejado, caso ele exista.
     * @param name Nome do Tournament.
     * @return Tournament com o name desejado.
     */
    public Tournament stringNomeParaCampeonato(String name) {
        return tournaments.get(name);
    }

    /**
     * Adiciona um time a um campeonato, tendo uma saída personalizada para cada caso.
     * @param teamId Código identificador do Time a ser adicionado no campeonato
     * @param campeonatoNome Nome do Tournament que receberá um time.
     */
    public String addTeamInTournament(String teamId, String campeonatoNome){
        Time time = idStringToTeam(teamId);
        if(time == null) return "CADASTRO NÃO REALIZADO. TIME NÃO EXISTE!";

        Tournament tournament = stringNomeParaCampeonato(campeonatoNome);
        if (tournament == null) return "CADASTRO NÃO REALIZADO. CAMPEONATO NÃO EXISTE!";

        if (tournament.getTeams().size() >= tournament.getLIMITEDETIMES()) return "CADASTRO NÃO REALIZADO. TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";

        if (tournament.getTeams().contains(time)) return "CADASTRO NÃO REALIZADO. TIME JÁ PRESENTE NO CAMPEONATO!";

        return tournament.addTeamInTheTournament(time);
    }

    /**
     * Cria time e o adiciona no conjunto de teams.
     * @param id Identificador do Time.
     * @param name Nome do Time.
     * @param mascot Mascote do Time.
     * @return Se a operação foi bem sucedida ou não.
     */
    public String registerTeam (String id, String name, String mascot){
        if (id.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM UM IDENTIFICADOR TEXTUAL!";
        if (name.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM NOME!";
        if (mascot.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM MASCOTE!";
        if (teams.containsKey(id)) return "TIME JÁ EXISTE!";
        teams.put(id, new Time(id, name, mascot));
        return "INCLUSÃO REALIZADA!";
    }

    /**
     * Cria um campeonato.
     * @param name Nome do campeonato.
     * @param LIMITEDETIMES Limite de teams que podem ser inseridos no campeonato.
     * @return Se o campeonato foi criado ou não.
     */
    public String registerTournament(String name, int LIMITEDETIMES){
        if (name.length() == 0) return "O NOME NÃO PODE SER VAZIO!";
        if (LIMITEDETIMES <= 0) return "O CAMPEONATO DEVE ACEITAR NO MÍNIMO UM TIME. INSIRA UM NÚMERO INTEIRO VÁLIDO!";
        if (tournaments.containsKey(name)) return "CAMPEONATO JÁ EXISTE.";
        tournaments.put(name, new Tournament(name, LIMITEDETIMES));
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
     * Pega a lista de bets do programa.
     * @return Lista de bets.
     */
    public ArrayList<Bet> getBets() {
        return this.bets;
    }

    /**
     * Realiza uma aposta a partir de um Time, uma Competição, sua colocação na tabela e um value monetário.
     * @param teamId Código identificador do Time.
     * @param tournamentName Nome do Tournament.
     * @param position Colocação no time na tabela do Tournament.
     * @param value Valor monetário.
     * @return Bet registrada.
     */
    public String placeBet(String teamId, String tournamentName, int position, Double value){
        if (!(this.teams.containsKey(teamId))) return "APOSTA NÃO REGISTRADA. TIME NÃO EXISTE!";
        if (!(this.tournaments.containsKey(tournamentName))) return "APOSTA NÃO REGISTRADA. CAMPEONATO NÃO EXISTE";

        Time time = idStringToTeam(teamId);
        Tournament tournament = stringNomeParaCampeonato(tournamentName);

        if (position > tournament.getLIMITEDETIMES()) return "APOSTA NÃO REGISTRADA. POSIÇÃO INVÁLIDA. NÃO HÁ TIMES SUFICIENTES.";

        if (position < 1) return "APOSTA NÃO REGISTRADA. INSIRA UMA COLOCAÇÃO VÁLIDA. DEVE SER UM NÚMERO INTEIRO A PARTIR DE 1.";

        if (value <= 0) return "INSIRA UM VALOR VÁLIDO. O VALOR DA APOSTA DEVE SER UM NÚMERO MAIOR QUE 0.";

        if (position == 1) time.incrementaVezesEmPrimeiro();
        this.bets.add(new Bet(time, tournament, position, value));
        return "APOSTA REGISTRADA!";
    }

    /**
     * Verifica se um time está participando de determinado campeonato.
     * @param teamId Código identificador do time desejado.
     * @param tournamentName Nome do campeonato em questão.
     * @return Se o time participa ou não do campeonato.
     */
    public String checkIfTeamIsInASpecificTournament(String teamId, String tournamentName){
        Time time = idStringToTeam(teamId);
        if (time == null) return "TIME NÃO EXISTE!";

        Tournament tournament = stringNomeParaCampeonato(tournamentName);
        if (tournament == null) return "CAMPEONATO NÃO EXISTE!";

        return tournament.checkIfTeamIsInTheTournament(time);
    }

    /**
     * Pega os teams cadastrados no sistema.
     * @return Par "name-value" de teams cadastrados no sistema.
     */
    public HashMap<String, Time> getTeams() {
        return teams;
    }

    /**
     * Pega o status das bets formatado.
     * @return Status das bets já formatado.
     */
    public String betStatus(){
        StringBuilder sb = new StringBuilder();
        String out = null;

        int i = 0;
        for (Bet a :
                this.bets) {
            i++;
            out = String.format("\n%d. [%s] %s / %s\n%s\n%d/%d\nR$ %.2f\n", i, a.getTime().getId(), a.getTime().getNome(), a.getTime().getMascote(), a.getCampeonato().getName(), a.getCampeonato().getTeams().size(), a.getCampeonato().getLIMITEDETIMES(), a.getValor());
            sb.append(out);
        }
        if (this.bets.isEmpty()) out = "NÃO HÁ NENHUMA APOSTA REGISTRADA.";
        return out;
    }

    public String timesInTheFirstPositionOnBets(){
        StringBuilder sb = new StringBuilder();
        sb.append("Popularidade em bets\n");
        int curIndex = 0;
        String out = null;
        for (Time time:
                this.teams.values()) {
            sb.append(time.getNome()).append(" / ").append(time.getVezesEmPrimeiro());
            curIndex++;
            if (curIndex < teams.size()) sb.append("\n");
            out = sb.toString();
        }
        if (this.teams.isEmpty()) return "NÃO HÁ NENHUM TIME CADASTRADO NO SISTEMA";
        return out;
    }

    public String getTeam(Time time){
        if (time == null) {
            return "TIME NÃO EXISTE!";
        } else {
            return time.toString();
        }
    }

    public String showTeamTournaments(Time time){
        StringBuilder sb = new StringBuilder();
        if (time == null) {
            sb.append("TIME NÃO EXISTE.");
        } else if (time.getCampeonatos().isEmpty()) {
            sb.append("O TIME NÃO ESTÁ INSCRITO EM NENHUM CAMPEONATO!");
        } else {
            sb.append(String.format("Campeonatos do %s:\n", time.getNome()));
            for (Tournament c:
                    time.getCampeonatos()) {
                sb.append(String.format("* %s - %d/%d\n", c.getName(), c.getTeams().size(), c.getLIMITEDETIMES()));
            }
        }
        return sb.toString();
    }
}

