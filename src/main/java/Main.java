import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Weapon.Inventory in1 = new Weapon.Inventory();
        in1.start();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();

        // Load player from the mock database
        int playerHealth = MockDatabase.loadPlayer(playerName);
        if (playerHealth == -1) {
            System.out.println("New player! Setting default health to 100.");
            playerHealth = 100;
            MockDatabase.savePlayer(playerName, playerHealth);
        } else {
            System.out.println("Welcome back, " + playerName + "! Your health: " + playerHealth);
        }

        // Create player and enemy
        Weapon sword = new Weapon(5, 15, 10, 20, "Sword");
        Sprite player = new Sprite(playerName, playerHealth, sword);
        Sprite enemy = new Sprite("Enemy", 80, sword);

        System.out.println("A wild enemy appears!");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nPlayer Health: " + player.health);
            System.out.println("Enemy Health: " + enemy.health);
            System.out.println("Choose an action:");
            System.out.println("1. Attack1");
            System.out.println("2. Attack2");
            System.out.println("3. Run");
            System.out.println("4. Weapon.Inventory");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    player.attack(enemy, 1);
                    break;
                case 2:
                    player.attack(enemy, 2);
                    break;
                case 3:
                    System.out.println("You ran away!");
                    return;
                case 4:
                    in1.OpenInventory();

                    System.out.println("\nWeapon equipped: " + player.weapon.name + " | Health: " + player.health);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            if (choice == 1 || choice == 2) {
                if (enemy.isAlive()) {
                    enemy.attack(player, 1);
                } else {
                    System.out.println("You defeated the enemy!");
                    break;
                }

                if (!player.isAlive()) {
                    System.out.println("You were defeated by the enemy.");
                }
            }
        }

        // Save player profile after combat
        MockDatabase.savePlayer(playerName, player.health);

        scanner.close();
    }
}
