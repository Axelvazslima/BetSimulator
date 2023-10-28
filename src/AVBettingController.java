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
     * @param id Team's id.
     * @return Team com o name desejado.
     */
    public Team idStringToTeam(String id) {
        return this.teams.get(id);
    }

    /**
     * Pega o Tournament com o name desejado, caso ele exista.
     * @param name Tournament's name.
     * @return Tournament com o name desejado.
     */
    public Tournament nameToTournament(String name) {
        return this.tournaments.get(name);
    }

    /**
     * Adiciona um time a um campeonato, tendo uma saída personalizada para cada caso.
     * @param teamId Team's id a ser adicionado no campeonato
     * @param campeonatoNome Tournament's name que receberá um time.
     */
    public String addTeamInTournament(String teamId, String campeonatoNome){
        Team team = idStringToTeam(teamId);
        if(team == null) return "CADASTRO NÃO REALIZADO. TIME NÃO EXISTE!";

        Tournament tournament = nameToTournament(campeonatoNome);
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
    public String registerTeam(String id, String name, String mascot){
        if (id.isBlank()) return "YOU CAN'T CREATE A TEAM WITH NO ID!";
        if (name.isBlank()) return "YOU CAN'T CREATE A NAMELESS TEAM!";
        if (mascot.isBlank()) return "YOU CAN'T CREATE A TIME WITHOUT A MASCOT!";
        if (teams.containsKey(id)) return "THIS TEAM ALREADY EXISTS!";
        this.teams.put(id, new Team(id, name, mascot));
        return "TEAM SUCCESSFULLY REGISTERED!";
    }

    /**
     * Creates a tournament.
     * @param name Tournament's name.
     * @param LIMIT_PARTICIPANTS Limit of participants in the tournament.
     * @return If the tournament was created or not.
     */
    public String registerTournament(String name, int LIMIT_PARTICIPANTS){
        if (name.isBlank()) return "A TOURNAMENT'S NAME CAN NOT BE BLANK!";
        if (LIMIT_PARTICIPANTS <= 0) return "A TOURNAMENT MUST ACCEPT AT LEAST ONE TEAM!";
        if (tournaments.containsKey(name)) return "THIS TOURNAMENT ALREADY EXISTS!";
        tournaments.put(name, new Tournament(name, LIMIT_PARTICIPANTS));
        return "NEW TOURNAMENT REGISTERED!";
    }

    /**
     * Exception handler to converting a value into an integer number. If it's not a value, it turns -1.
     * @param number Number.
     * @return Integer number or -1, if the number is not valid.
     */
    public int exceptionHandlerIntConverter(String number){
        try{
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe){
            return -1;
        }
    }

    /**
     * Exception handler to converting a value into a rational number. If it's not a value, it turns -1.
     * @param number Number.
     * @return Rational number or -1, if the number is not valid.
     */
    public double exceptionHandlerDoubleConverter(String number){
        try{
            return Double.parseDouble(number);
        } catch (NumberFormatException nfe){
            return -1;
        }
    }

    /**
     * Get a placed list of bets.
     * @return List of bets.
     */
    public ArrayList<Bet> getBets() {
        return this.bets;
    }

    /**
     * Places a bet using a team, a tournament, a position and the value to be risked.
     * @param teamId Team's id.
     * @param tournamentName Tournament's name.
     * @param position Team's position in the tournament.
     * @param value Value / Money.
     * @return If the bet was placed or not.
     */
    public String placeBet(String teamId, String tournamentName, int position, Double value){
        Team team = idStringToTeam(teamId);
        Tournament tournament = nameToTournament(tournamentName);

        if (team == null) return "BET NOT PLACED. THE WANTED TEAM DOESN'T EXIST!";
        if (tournament == null) return "BET NOT PLACED. THE WANTED TOURNAMENT DOESN'T EXIST!";

        if (position > tournament.getLIMIT_PARTICIPANTS()) return "BET NOT PLACED. INVALID POSITION, THERE ARE NOT THIS AMOUNT OF TEAMS IN THE COMPETITION!";

        if (position < 1) return "BET NOT PLACED. YOUR POSITION MUST EQUALS OR BE GREATER THAN 1!";

        if (value <= 0) return "INPUT A VALID VALUE. IT MUST BE BIGGER THAN 0!";

        if (position == 1) team.upByOneTimesInFirst();
        this.bets.add(new Bet(team, tournament, position, value));
        return "BET SUCCESSFULLY PLACED!";
    }

    /**
     * Checks if a specific team is playing in the wanted tournament or not.
     * @param teamId Team's id.
     * @param tournamentName Tournament's name.
     * @return If the team is registered in the tournament or not.
     */
    public String checkIfTeamIsInASpecificTournament(String teamId, String tournamentName){
        Team team = idStringToTeam(teamId);
        if (team == null) return "THE TEAM DOESN'T EXIST!";

        Tournament tournament = nameToTournament(tournamentName);
        if (tournament == null) return "THE TOURNAMENT DOESN'T EXIST!";

        return tournament.checkIfTeamIsInTheTournament(team);
    }

    /**
     * Get the teams registered in the system.
     * @return Pair "name-value" of registered teams in the system.
     */
    public HashMap<String, Team> getTeams() {
        return this.teams;
    }

    /**
     * Get the status of the bet already formatted.
     * @return Already formatted bets.
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
        if (this.bets.isEmpty()) out = "THERE IS NO BET PLACED IN THE SYSTEM!";
        return out;
    }

    /**
     * Get the teams placed in the first position on bets at least once.
     * @return Teams placed as the winner of it all ate least once on bets.
     */
    public String timesInTheFirstPositionOnBets(){
        if (this.teams.isEmpty()) return "THERE IS NO TEAM REGISTERED IN THE SYSTEM!";

        StringBuilder sb = new StringBuilder();
        sb.append("POPULARITY ON BETS\n");
        int curIndex = 0;
        String out = null;
        for (Team team :
                this.teams.values()) {
            sb.append(team.getName()).append(" / ").append(team.getTimesInFirst());
            curIndex++;
            if (curIndex < teams.size()) sb.append("\n");
            out = sb.toString();
        }
        return out;
    }

    /**
     * Shows the wanted team's tournament.
     * @param team The wanted team.
     * @return The tournaments the team is registered in.
     */
    public String showTeamTournaments(Team team){
        StringBuilder sb = new StringBuilder();
        if (team == null) {
            sb.append("THE TEAM DOESN'T EXIST!");
        } else if (team.getTournaments().isEmpty()) {
            sb.append("THE TEAM IS NOT REGISTERED IN ANY TOURNAMENT!");
        } else {
            sb.append(team.getName()).append("'s TOURNAMENTS:");
            for (Tournament c:
                    team.getTournaments()) {
                sb.append(String.format("* %s - %d/%d\n", c.getName(), c.getTeams().size(), c.getLIMIT_PARTICIPANTS()));
            }
        }
        return sb.toString();
    }
}

