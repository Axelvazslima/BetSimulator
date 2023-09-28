import java.util.Scanner;

/**
 * Classe final da plataforma de apostas.
 * O que fica "visível" para o usuário.
 */
public class MainMrBet {
    public static void main(String[] args) {
        MrBetSistema sistema = new MrBetSistema();
        Scanner sc = new Scanner(System.in);

        System.out.println("Iniciando o Menu principal...");

        while (true){
            String escolha = menu(sc);
            comando(sistema, sc, escolha);
        }
    }

    /**
     * Impressão do menu para o usuário.
     * @param sc Scanner para entrada do usuário.
     * @return Entrada digitada pelo usuário.
     */
    private static String menu(Scanner sc){
        System.out.println("""
                
                (M)Minha inclusão de times
                (R)Recuperar time
                (.)Adicionar campeonato
                (B)Bora incluir time em campeonato e Verificar se time está em campeonato
                (E)Exibir campeonatos que o time participa
                (T)Tentar a sorte e status
                (H)Histórico
                (!)Já pode fechar o programa!
                """);
        System.out.print("\nINSIRA UM COMANDO: ");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Lógica do menu a partir de entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     * @param sc Scanner para entrada do usuário.
     * @param escolha Escolha do usuário por Scanner de entrada.
     */
    private static void comando(MrBetSistema sistema, Scanner sc, String escolha){
        switch (escolha){
            case "M" -> adicionarTime(sc, sistema);
            case "R" -> recuperarTime(sc, sistema);
            case "." -> adicionarCampeonato(sc, sistema);
            case "B" -> escolhaVerificarTimeOuIncluirTimeCampeonato(sc, sistema, menuVerificarTimeOuIncluirTimeCampeonato(sc));
            case "E" -> exibirCampeonatosDoTime(sc, sistema);
            case "T" -> escolhaApostarOuStatusDasApostas(sc, sistema, menuApostarOuStatusDasApostas(sc));
            case "H" -> escolhaHistorico(sc, sistema, menuHistorico(sc));
            case "!" -> encerrarPrograma();
            default -> System.err.println("INSIRA UM CARACTERE VÁLIDO.");
        }
    }

    /**
     * Adiciona um time na lista de times.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema de apostas.
     */
    private static void adicionarTime(Scanner sc, MrBetSistema sistema){
        System.out.print("\nCódigo identificador do time: ");
        String id = sc.nextLine().toUpperCase();
        System.out.print("\nNome: ");
        String nome = sc.nextLine();
        System.out.print("\nMascote: ");
        String mascote = sc.nextLine();
        System.out.println(sistema.adicionaTime(id, nome, mascote));
    }

    /**
     * Cria um Campeonato.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     */
    private static void adicionarCampeonato(Scanner sc, MrBetSistema sistema){
        System.out.print("\nNome do campeonato: ");
        String nome = sc.nextLine();
        System.out.print("\nNúmero máximo permitido de times participantes: ");
        int LIMITEDETIMES = sistema.exceptionHandlerIntConverter(sc.nextLine());
        System.out.println(sistema.adicionaCampeonato(nome, LIMITEDETIMES));
    }

    /**
     * Mostra as informações do time desejado pelo usuário a partir de seu código.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema de apostas.
     */
    private static void recuperarTime(Scanner sc, MrBetSistema sistema){
        System.out.print("\nCódigo identificador do time: ");
        String nome = sc.nextLine().toUpperCase();
        Time time = sistema.stringIdParaTime(nome);
        System.out.println(sistema.recuperarTime(time));
    }

    /**
     * Imprime menu para incluir time em campeonato ou verifica se time está em campeonato.
     * @param sc Scanner para entrada do usuário.
     * @return Entrada do usuário.
     */
    private static String menuVerificarTimeOuIncluirTimeCampeonato(Scanner sc){
        System.out.println("(I) Incluir time em campeonato\n(V) Verificar se time está em campeonato");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Lógica que opera a parir da escolha do usuário, podendo incluir time em campeonato ou verificar se o time está em algum campeoanto.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     * @param escolha Escolha do usuário.
     */
    private static void escolhaVerificarTimeOuIncluirTimeCampeonato(Scanner sc, MrBetSistema sistema, String escolha){
        switch (escolha){
            case "I" -> incluirTimeEmCampeonato(sc, sistema);
            case "V" -> verificarSeTimeEstaEmCampeonato(sc, sistema);
            default -> System.err.println("INSIRA UM CARACTERE VÁLIDO.");
        }
    }

    /**
     * Inscreve um Time em um Campeonato.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     */
    private static void incluirTimeEmCampeonato(Scanner sc, MrBetSistema sistema){
        System.out.print("\nCódigo identificador do time: ");
        String time = sc.nextLine().toUpperCase();
        System.out.print("\nNome do campeonato: ");
        String campeonato = sc.nextLine();
        System.out.println(sistema.adicionaTimeEmCampeonato(time, campeonato));
    }

    /**
     * Diz se um time específico está no campeonato desejado.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     */
    private static void verificarSeTimeEstaEmCampeonato(Scanner sc, MrBetSistema sistema){
        System.out.print("\nCódigo identificador do time: ");
        String time = sc.nextLine().toUpperCase();
        System.out.print("\nNome do campeonato: ");
        String campeonato = sc.nextLine();
        System.out.println(sistema.verificaTimeEmCampeonato(time, campeonato));
    }

    /**
     * Diz todos os campeonatos que o time desejado está participando.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     */
    private static void exibirCampeonatosDoTime(Scanner sc, MrBetSistema sistema){
        System.out.print("\nCódigo identificador do time: ");
        String id = sc.nextLine().toUpperCase();
        Time time = sistema.stringIdParaTime(id);
        System.out.println(sistema.exibirCampeonatosDoTime(time));
    }

    /**
     * Imprime uma mensagem para o usuário e encerra o programa.
     */
    private static void encerrarPrograma(){
        System.out.println("\nPor hoje é só pessoal!\n");
        System.exit(0);
    }

    /**
     * Imprime o menu com opção de apostar ou de ver os status das apostas e recebe entrada do usuário.
     * @param sc Scanner para entrada do usuário.
     * @return Entrada do usuário.
     */
    private static String menuApostarOuStatusDasApostas(Scanner sc){
        System.out.println("(A)Apostar\n(S)Status das Apostas");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Entrada do usuário para saber se ele quer os status das apostas ou apostar.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     * @param escolha Escolha do usuário a partir de sua entrada.
     */
    private static void escolhaApostarOuStatusDasApostas(Scanner sc, MrBetSistema sistema, String escolha){
        switch (escolha){
            case "A" -> apostar(sc, sistema);
            case "S" -> statusDasApostas(sistema);
            default -> System.out.println("INSIRA UM CARACTERE VÁLIDO.");
        }
    }

    /**
     * Aposta do usuário.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema da plataforma de apostas.
     */
    private static void apostar(Scanner sc, MrBetSistema sistema){
        System.out.print("\nCódigo identificador do time: ");
        String time = sc.nextLine().toUpperCase();

        System.out.print("\nNome do campeonato: ");
        String campeonato = sc.nextLine();

        System.out.print("\nColocação: ");
        int colocacao = sistema.exceptionHandlerIntConverter(sc.nextLine());

        System.out.print("\nValor da aposta: ");
        double valorAposta = sistema.exceptionHandlerDoubleConverter(sc.nextLine());

        System.out.println(sistema.adicionaAposta(time, campeonato, colocacao, valorAposta));
    }

    /**
     * Pega as informações sobre as apostas realizadas.
     * @param sistema Sistema da plataforma de apostas.
     */
    private static void statusDasApostas(MrBetSistema sistema){
        System.out.println(sistema.statusDasApostas());
    }

    /**
     * Menu de histórico para o usuário.
     * @param sc Scanner para entrada do usuário.
     * @return Escolha do usuário via Scanner de entrada.
     */
    private static String menuHistorico(Scanner sc){
        System.out.println("(P)Ver vezes que cada time apareceu em primeiro");
        return sc.nextLine().toUpperCase();
    }

    /**
     * Menu responsável pela lógica de fazer o que o usuário quer baseado em sua entrada.
     * @param sc Scanner para entrada do usuário.
     * @param sistema Sistema do Mr. Bet.
     * @param escolha Escolha do usuário obtida via Scanner de entrada.
     */
    private static void escolhaHistorico(Scanner sc, MrBetSistema sistema, String escolha){
        switch (escolha){
            case "P" -> vezesEmPrimeiro(sistema);
            default -> System.out.println("INSIRA UM CARACTERE VÁLIDO.");
        }
    }

    /**
     * Parte do bônus.
     * Imprime os times que mais aparecram em primeiro lugar nas apostas.
     * @param sistema Sistema do Mr. Bet.
     */
    private static void vezesEmPrimeiro(MrBetSistema sistema){
        System.out.println(sistema.vezesEmPrimeiro());
    }
}