import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private ArrayList<Pokemon> collection = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public void addPokemon(Pokemon p) {
        collection.add(p);
        System.out.println(p.getName() + " added to your collection!");
    }

    public Pokemon choosePokemon() {
        return choosePokemon(null); // default to no exclusion
    }

    public Pokemon choosePokemon(Pokemon exclude) {
        ArrayList<Pokemon> available = new ArrayList<>();
        System.out.println("\n--- Your Pokemon Collection ---");
        for (Pokemon p : collection) {
            if (exclude == null || !p.equals(exclude)) {
                available.add(p);
            }
        }

        for (int i = 0; i < available.size(); i++) {
            System.out.print(i + ": ");
            available.get(i).displayInfo();
        }

        System.out.print("Choose Pokemon by index: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 0 && choice < available.size()) {
            return available.get(choice);
        } else {
            System.out.println("Invalid choice, using first available Pokemon.");
            return available.get(0);
        }
    }

    public void viewCollection() {
        System.out.println("\n--- Your Pokemon Collection ---");
        for (int i = 0; i < collection.size(); i++) {
            System.out.print(i + ": ");
            collection.get(i).displayInfo();
        }
    }
    
    public void healAllPokemon() {
        for (Pokemon p : collection) {
            p.restore();
        }
        System.out.println(" All your Pokemon have been fully healed! :)");
    }
    
        public ArrayList<Pokemon> getCollection() {
            return collection;
        }

    public void tryCatch(Pokemon wild) {
        System.out.println("Trying to catch " + wild.getName() + "...");
        for (Pokeball ball : Pokeball.values()) {
            if (!ball.isOutOfStock()) {
                if (ball.tryCatch(wild)) {
                    System.out.println("You caught " + wild.getName() + "!");
                    addPokemon(wild);
                    System.out.println();
                    return;
                } else {
                    System.out.println("Failed to catch " + wild.getName() + " with " + ball.getName() + ".");
                }
            }
        }
        System.out.println("Out of Pokeballs or failed to catch " + wild.getName());
    }

    public String getName() {
        return name;
    }
}
