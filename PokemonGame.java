import java.util.Random;
import java.util.Scanner;

public class PokemonGame {
    static Scanner input = new Scanner(System.in);
    static Player player;
    static ScoreManager scoreManager = new ScoreManager();
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.print("Enter your name: ");
        String playerName = input.nextLine();
        player = new Player(playerName);

        System.out.println("Welcome " + player.getName() + " to Pokemon Java Edition");

        player.addPokemon(PokemonFactory.generateRandomPokemon());
        player.addPokemon(PokemonFactory.generateRandomPokemon());

        boolean playing = true;
        while (playing) {
            showMenu();
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> startBattle();
                case 2 -> player.viewCollection();
                case 3 -> scoreManager.displayTopScores();
                case 4 -> showPokeballInventory();
                case 5 -> visitDoctor();
                case 6 -> {
                    System.out.println("Exiting game...");
                    playing = false;
                }
                case 7 -> showCurrentScore();
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Start Battle");
        System.out.println("2. View My Pokemon");
        System.out.println("3. View Top Scores");
        System.out.println("4. View Pokeball Inventory");
        System.out.println("5. Go to Doctor (Heal Team)");
        System.out.println("6. Exit");
        System.out.println("7. Check Current Score");
        System.out.print("Choose an option: ");
    }

    public static void startBattle() {
        Pokemon wild1 = PokemonFactory.generateRandomPokemon();
        Pokemon wild2 = PokemonFactory.generateRandomPokemon();

        System.out.println("\nTwo wild Pokemon appeared!");
        wild1.displayInfo();
        wild2.displayInfo();

        System.out.println("\nChoose your first Pokemon:");
        Pokemon p1 = player.choosePokemon();

        System.out.println("Choose your second Pokemon:");
        Pokemon p2 = player.choosePokemon(p1);

        Battle battle = new Battle(p1, p2, wild1, wild2);
        battle.start();

        if (wild1.isDefeated()) player.tryCatch(wild1);
        if (wild2.isDefeated()) player.tryCatch(wild2);

        int score = battle.calculateScore();
        if (score > 0) {
            scoreManager.addToScore(player.getName(), score);
            System.out.println("⭐ You earned " + score + " score points!");
        }

        if (p1.getStatus() || p2.getStatus()) {
            rewardPokeballs();
        }
    }

    public static void showPokeballInventory() {
        System.out.println("\n--- Pokeball Inventory ---");
        for (Pokeball b : Pokeball.values()) {
            System.out.printf("%s: %d remaining\n", b.getName(), b.getQuantity());
        }
        System.out.println();
    }

    public static void rewardPokeballs() {
        System.out.println("\n You found some Pokeballs after the battle!");
        boolean receivedAny = false;

        for (Pokeball b : Pokeball.values()) {
            int gained = rand.nextInt(2); // 0 or 1
            if (gained > 0) {
                b.addQuantity(gained);
                System.out.printf("+%d %s\n", gained, b.getName());
                receivedAny = true;
            }
        }

        if (!receivedAny) {
            System.out.println("No Pokeballs found this time :(");
        }

        System.out.println();
    }

    public static void visitDoctor() {
        int cost = 20;
        int currentScore = scoreManager.getScore(player.getName());
        boolean allFainted = player.getCollection().stream().allMatch(p -> !p.getStatus());

        if (currentScore >= cost) {
            scoreManager.addToScore(player.getName(), -cost); // Correct deduction
            player.healAllPokemon();
            System.out.println(" The Doctor healed all your Pokemon for " + cost + " points.");
        } else if (allFainted) {
            player.healAllPokemon();
            System.out.println("All your Pokemon were fainted. The Doctor healed them for free! :D");
        } else {
            System.out.println(" Not enough score to heal your team. (Need " + cost + ") </3");
        }
    }

    public static void showCurrentScore() {
        int currentScore = scoreManager.getScore(player.getName());
        System.out.println("\n Your current score: " + currentScore);
    }
}
