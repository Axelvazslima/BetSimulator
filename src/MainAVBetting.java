import java.util.Scanner;

/**
 * Final betting system class.
 * The user interface.
 */
public class MainAVBetting {
    public static void main(String[] args) {
        AVBettingController controller = new AVBettingController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Starting the program...");

        while (true){
            String usersInput = usersInterface(sc);
            command(controller, sc, usersInput);
        }
    }

    /**
     * Prints the user's interface.
     * @param sc Scanner for the user's input.
     * @return User's input.
     */
    private static String usersInterface(Scanner sc){
        System.out.println("""
                
                (M)Register team
                (R)Get team's information
                (.)Register tournament
                (B)Include team in Tournament or check if it's there
                (E)Print tournaments that a given team plays
                (T)Try your luck or bets' status
                (H)History
                (!)Shut it down!
                """);
        System.out.print("\nINPUT A CHAR: ");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Interface's logic based off user's input.
     * @param controller Betting platform controller.
     * @param sc Scanner for user's input.
     * @param usersInput User's input.
     */
    private static void command(AVBettingController controller, Scanner sc, String usersInput){
        switch (usersInput){
            case "M" -> registerTeam(sc, controller);
            case "R" -> getTeamsInformation(sc, controller);
            case "." -> registerTournament(sc, controller);
            case "B" -> interfaceCheckIfTeamIsInASpecificTournamentOrAddTeamInTournament(sc, controller, interfaceCheckIfTeamsIsInATournamentOrAddsItInATournament(sc));
            case "E" -> showTeamTournaments(sc, controller);
            case "T" -> betOrShowBetsStatus(sc, controller, interfaceBetOrBetsStatus(sc));
            case "H" -> usersChoiceHistory(sc, controller, interfaceHistory(sc));
            case "!" -> shutDown();
            default -> System.err.println("INPUT A VALID CHARACTER!");
        }
    }

    /**
     * Adds a team in a list of teams.
     * @param sc Scanner for user's input.
     * @param controller Gambling platform controller.
     */
    private static void registerTeam(Scanner sc, AVBettingController controller){
        System.out.print("\nTeam's id: ");
        String id = sc.nextLine().toUpperCase();
        System.out.print("\nNome: ");
        String name = sc.nextLine();
        System.out.print("\nMascote: ");
        String mascot = sc.nextLine();
        System.out.println(controller.registerTeam(id, name, mascot));
    }

    /**
     * Creates a tournament.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     */
    private static void registerTournament(Scanner sc, AVBettingController controller){
        System.out.print("\nTournament's name: ");
        String name = sc.nextLine();
        System.out.print("\nNúmero máximo permitido de times participantes: ");
        int LIMIT_PARTICIPANTS = controller.exceptionHandlerIntConverter(sc.nextLine());
        System.out.println(controller.registerTournament(name, LIMIT_PARTICIPANTS));
    }

    /**
     * Shows the team's information from its id.
     * @param sc Scanner for user's input.
     * @param controller Gambling platform controller.
     */
    private static void getTeamsInformation(Scanner sc, AVBettingController controller){
        System.out.print("\nTeam's id: ");
        String name = sc.nextLine().toUpperCase();
        Time team = controller.idStringToTeam(name);
        System.out.println(controller.getTeam(team));
    }

    /**
     * Prints interface to include team in a tournament or check if a team is there.
     * @param sc Scanner for user's input.
     * @return Entrada do usuário.
     */
    private static String interfaceCheckIfTeamsIsInATournamentOrAddsItInATournament(Scanner sc){
        System.out.println("(I)Add team in a tournament\n(V)Check if a team is in a specific tournament");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Interface that works based on a user input.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     * @param usersInput Escolha do usuário.
     */
    private static void interfaceCheckIfTeamIsInASpecificTournamentOrAddTeamInTournament(Scanner sc, AVBettingController controller, String usersInput){
        switch (usersInput){
            case "I" -> addTeamInATournament(sc, controller);
            case "V" -> checkIfTeamIsInASpecificTournament(sc, controller);
            default -> System.err.println("INPUT A VALID CHARACTER!");
        }
    }

    /**
     * Register a team in a tournament.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     */
    private static void addTeamInATournament(Scanner sc, AVBettingController controller){
        System.out.print("\nTeam's id: ");
        String team = sc.nextLine().toUpperCase();
        System.out.print("\nTournament's name: ");
        String tournament = sc.nextLine();
        System.out.println(controller.addTeamInTournament(team, tournament));
    }

    /**
     * Tells if a specific team is in the desired tournament.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     */
    private static void checkIfTeamIsInASpecificTournament(Scanner sc, AVBettingController controller){
        System.out.print("\nTeam's id: ");
        String team = sc.nextLine().toUpperCase();
        System.out.print("\nTournament's name: ");
        String tournament = sc.nextLine();
        System.out.println(controller.checkIfTeamIsInASpecificTournament(team, tournament));
    }

    /**
     * Prints the tournaments that the wanted team is currently registered.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     */
    private static void showTeamTournaments(Scanner sc, AVBettingController controller){
        System.out.print("\nTeam's id: ");
        String id = sc.nextLine().toUpperCase();
        Time team = controller.idStringToTeam(id);
        System.out.println(controller.showTeamTournaments(team));
    }

    /**
     * Prints a goodbye message and then closes the program.
     */
    private static void shutDown(){
        System.out.println("\nThanks for trusting us! See you soon...\n");
        System.exit(0);
    }

    /**
     * User's interface for placing bets or checking the status of bets.
     * @param sc Scanner for user's input.
     * @return User's input
     */
    private static String interfaceBetOrBetsStatus(Scanner sc){
        System.out.println("(A)Bet\n(S)Bets' status");
        return sc.nextLine().toUpperCase();
    }

    /**
     * User's input to see if they want to place a bet or check the status of bets.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     * @param usersInput User's input.
     */
    private static void betOrShowBetsStatus(Scanner sc, AVBettingController controller, String usersInput){
        switch (usersInput){
            case "A" -> bet(sc, controller);
            case "S" -> betsStatus(controller);
            default -> System.out.println("INPUT A VALID CHARACTER!");
        }
    }

    /**
     * User's bet.
     * @param sc Scanner for user's input.
     * @param controller Betting platform controller.
     */
    private static void bet(Scanner sc, AVBettingController controller){
        System.out.print("\nTeam's id: ");
        String team = sc.nextLine().toUpperCase();

        System.out.print("\nTournament's name: ");
        String tournament = sc.nextLine();

        System.out.print("\nPosition: ");
        int position = controller.exceptionHandlerIntConverter(sc.nextLine());

        System.out.print("\nHow much do you want to bet: ");
        double betValue = controller.exceptionHandlerDoubleConverter(sc.nextLine());

        System.out.println(controller.placeBet(team, tournament, position, betValue));
    }

    /**
     * Get information about the bets.
     * @param controller Betting platform controller.
     */
    private static void betsStatus(AVBettingController controller){
        System.out.println(controller.betStatus());
    }

    /**
     * Interface for the user to see the platform history.
     * @param sc Scanner for user's input.
     * @return User's input from scanner.
     */
    private static String interfaceHistory(Scanner sc){
        System.out.println("(P)See the team that was most-betted to win it all by users");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Interface that gets an user input and works based on what they want.
     * @param sc Scanner for user's input.
     * @param controller Gambling platform controller.
     * @param usersInput User's choice based off his input.
     */
    private static void usersChoiceHistory(Scanner sc, AVBettingController controller, String usersInput){
        switch (usersInput){
            case "P" -> timesInFirstPosition(controller);
            default -> System.out.println("INPUT A VALID CHARACTER!");
        }
    }

    /**
     * Prints the system's most betted team to win it all most of the time.
     * @param controller Gambling platform controller.
     */
    private static void timesInFirstPosition(AVBettingController controller){
        System.out.println(controller.timesInTheFirstPositionOnBets());
    }
}