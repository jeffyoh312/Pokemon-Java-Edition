import java.util.*;

public class Battle {
    Pokemon player1;
    Pokemon player2;
    Pokemon wild1;
    Pokemon wild2;

    public Battle(Pokemon p1, Pokemon p2, Pokemon w1, Pokemon w2) {
        player1 = p1;
        player2 = p2;
        wild1 = w1;
        wild2 = w2;
    }

    public void start() {
        System.out.println("\n== BATTLE START ==");

        while (!battleOver()) {
            displayStatus();

            System.out.println("\n--- YOUR TURN ---");
            sleep();

            if (player1.getStatus()) {
                Pokemon target = wild1.getStatus() ? wild1 : (wild2.getStatus() ? wild2 : null);
                if (target != null) {
                    System.out.println(player1.getName() + " attacks " + target.getName() + "!");
                    player1.attack(target);
                    sleep();
                }
            }

            if (player2.getStatus()) {
                Pokemon target = wild2.getStatus() ? wild2 : (wild1.getStatus() ? wild1 : null);
                if (target != null) {
                    System.out.println(player2.getName() + " attacks " + target.getName() + "!");
                    player2.attack(target);
                    sleep();
                }
            }

            System.out.println("\n--- ENEMY TURN ---");
            sleep();

            if (wild1.getStatus()) {
                Pokemon target = player1.getStatus() ? player1 : (player2.getStatus() ? player2 : null);
                if (target != null) {
                    System.out.println(wild1.getName() + " attacks " + target.getName() + "!");
                    wild1.attack(target);
                    sleep();
                }
            }

            if (wild2.getStatus()) {
                Pokemon target = player2.getStatus() ? player2 : (player1.getStatus() ? player1 : null);
                if (target != null) {
                    System.out.println(wild2.getName() + " attacks " + target.getName() + "!");
                    wild2.attack(target);
                    sleep();
                }
            }

            System.out.println("\n=== END OF ROUND ===");
            sleep();
        }

        System.out.println("\n== BATTLE OVER ==");
        if (playerWon()) {
            System.out.println("You won the battle! YAYY\n");
        } else {
            System.out.println("You lost the battle...RIP!\n");
        }
    }

    private boolean battleOver() {
        boolean playerAlive = player1.getStatus() || player2.getStatus();
        boolean enemyAlive = wild1.getStatus() || wild2.getStatus();
        return !(playerAlive && enemyAlive);
    }

    private boolean playerWon() {
        return player1.getStatus() || player2.getStatus();
    }

    private void displayStatus() {
        System.out.println("\n--- YOUR TEAM :D ---");
        if (player1.getStatus()) player1.displayInfoBattle();
        if (player2.getStatus()) player2.displayInfoBattle();

        System.out.println("\n--- ENEMY TEAM >:) ---");
        if (wild1.getStatus()) wild1.displayInfoBattle();
        if (wild2.getStatus()) wild2.displayInfoBattle();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int calculateScore() {
        int score = 0;
        if (player1.getStatus()) score += player1.getHp();
        if (player2.getStatus()) score += player2.getHp();
        if (!wild1.getStatus()) score += 50;
        if (!wild2.getStatus()) score += 50;
        return score;
    }
}
