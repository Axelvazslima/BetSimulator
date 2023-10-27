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
    private HashMap<String, Team> teams;

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
     * Pega o Team com o name desejado, caso ele exista.
     * @param id Código identificador do Team.
     * @return Team com o name desejado.
     */
    public Team idStringToTeam(String id) {
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
     * @param teamId Código identificador do Team a ser adicionado no campeonato
     * @param campeonatoNome Nome do Tournament que receberá um time.
     */
    public String addTeamInTournament(String teamId, String campeonatoNome){
        Team team = idStringToTeam(teamId);
        if(team == null) return "CADASTRO NÃO REALIZADO. TIME NÃO EXISTE!";

        Tournament tournament = stringNomeParaCampeonato(campeonatoNome);
        if (tournament == null) return "CADASTRO NÃO REALIZADO. CAMPEONATO NÃO EXISTE!";

        if (tournament.getTeams().size() >= tournament.getLIMIT_PARTICIPANTS()) return "CADASTRO NÃO REALIZADO. TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";

        if (tournament.getTeams().contains(team)) return "CADASTRO NÃO REALIZADO. TIME JÁ PRESENTE NO CAMPEONATO!";

        return tournament.addTeamInTheTournament(team);
    }

    /**
     * Cria time e o adiciona no conjunto de teams.
     * @param id Identificador do Team.
     * @param name Nome do Team.
     * @param mascot Mascote do Team.
     * @return Se a operação foi bem sucedida ou não.
     */
    public String registerTeam (String id, String name, String mascot){
        if (id.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM UM IDENTIFICADOR TEXTUAL!";
        if (name.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM NOME!";
        if (mascot.length() == 0) return "NÃO É POSSÍVEL CRIAR UM TIME SEM MASCOTE!";
        if (teams.containsKey(id)) return "TIME JÁ EXISTE!";
        teams.put(id, new Team(id, name, mascot));
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
     * Realiza uma aposta a partir de um Team, uma Competição, sua colocação na tabela e um value monetário.
     * @param teamId Código identificador do Team.
     * @param tournamentName Nome do Tournament.
     * @param position Colocação no time na tabela do Tournament.
     * @param value Valor monetário.
     * @return Bet registrada.
     */
    public String placeBet(String teamId, String tournamentName, int position, Double value){
        if (!(this.teams.containsKey(teamId))) return "APOSTA NÃO REGISTRADA. TIME NÃO EXISTE!";
        if (!(this.tournaments.containsKey(tournamentName))) return "APOSTA NÃO REGISTRADA. CAMPEONATO NÃO EXISTE";

        Team team = idStringToTeam(teamId);
        Tournament tournament = stringNomeParaCampeonato(tournamentName);

        if (position > tournament.getLIMIT_PARTICIPANTS()) return "APOSTA NÃO REGISTRADA. POSIÇÃO INVÁLIDA. NÃO HÁ TIMES SUFICIENTES.";

        if (position < 1) return "APOSTA NÃO REGISTRADA. INSIRA UMA COLOCAÇÃO VÁLIDA. DEVE SER UM NÚMERO INTEIRO A PARTIR DE 1.";

        if (value <= 0) return "INSIRA UM VALOR VÁLIDO. O VALOR DA APOSTA DEVE SER UM NÚMERO MAIOR QUE 0.";

        if (position == 1) team.upByOneTimesInFirst();
        this.bets.add(new Bet(team, tournament, position, value));
        return "APOSTA REGISTRADA!";
    }

    /**
     * Verifica se um time está participando de determinado campeonato.
     * @param teamId Código identificador do time desejado.
     * @param tournamentName Nome do campeonato em questão.
     * @return Se o time participa ou não do campeonato.
     */
    public String checkIfTeamIsInASpecificTournament(String teamId, String tournamentName){
        Team team = idStringToTeam(teamId);
        if (team == null) return "TIME NÃO EXISTE!";

        Tournament tournament = stringNomeParaCampeonato(tournamentName);
        if (tournament == null) return "CAMPEONATO NÃO EXISTE!";

        return tournament.checkIfTeamIsInTheTournament(team);
    }

    /**
     * Pega os teams cadastrados no sistema.
     * @return Par "name-value" de teams cadastrados no sistema.
     */
    public HashMap<String, Team> getTeams() {
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
            out = String.format("\n%d. [%s] %s / %s\n%s\n%d/%d\nR$ %.2f\n", i, a.getTeam().getId(), a.getTeam().getName(), a.getTeam().getMascot(), a.getTournament().getName(), a.getTournament().getTeams().size(), a.getTournament().getLIMIT_PARTICIPANTS(), a.getValue());
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
        for (Team team :
                this.teams.values()) {
            sb.append(team.getName()).append(" / ").append(team.getTimesInFirst());
            curIndex++;
            if (curIndex < teams.size()) sb.append("\n");
            out = sb.toString();
        }
        if (this.teams.isEmpty()) return "NÃO HÁ NENHUM TIME CADASTRADO NO SISTEMA";
        return out;
    }

    public String getTeam(Team team){
        if (team == null) {
            return "TIME NÃO EXISTE!";
        } else {
            return team.toString();
        }
    }

    public String showTeamTournaments(Team team){
        StringBuilder sb = new StringBuilder();
        if (team == null) {
            sb.append("TIME NÃO EXISTE.");
        } else if (team.getTournaments().isEmpty()) {
            sb.append("O TIME NÃO ESTÁ INSCRITO EM NENHUM CAMPEONATO!");
        } else {
            sb.append(String.format("Campeonatos do %s:\n", team.getName()));
            for (Tournament c:
                    team.getTournaments()) {
                sb.append(String.format("* %s - %d/%d\n", c.getName(), c.getTeams().size(), c.getLIMIT_PARTICIPANTS()));
            }
        }
        return sb.toString();
    }
}

