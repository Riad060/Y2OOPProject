import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager db = new DatabaseManager();
        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();

        Sprite player = db.getPlayer(playerName);
        if(player == null){
            System.out.println("New player! Creating player with name " + playerName);
            db.savePlayer(playerName, 100, 0, player.getWeapon());
            player = new Sprite(playerName, 100 , db.getWeapon("Sword"));
        } else {
            System.out.println("Welcome back, " + playerName + "! Your health: " + player.getHealth());
            System.out.println("💰 You have " + player.getCoins() + " coins.");
        }

        // Create player and enemy
        enemy enemy = db.getRandomEnemy();
        Inventory in1 = new Inventory(player);
        Shop shop = new Shop(player); // link shop
        while(true) {

            System.out.println("A wild enemy appears!");
            enemy.setHealth(80);

            while (player.isAlive() && enemy.isAlive()) {
                System.out.println("\nPlayer Health: " + player.getHealth());
                System.out.println("Enemy Health: " + enemy.getHealth());
                System.out.println("Choose an action:");
                System.out.println("1. Attack1");
                System.out.println("2. Attack2");
                System.out.println("3. Run");
                System.out.println("4. Weapon Inventory");
                System.out.println("5. Open Shop");
                //System.out.println("6. Use HP Potion");

                int choice = scanner.nextInt();
                scanner.nextLine(); // clear newline
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
                        System.out.println("\nWeapon equipped: " + player.getWeapon().getName()
                                + " | Health: " + player.getHealth());
                        break;
                    case 5:
                        shop.shopMenu(); // 💰 opens the shop
                        break;
                    //case 6:
                        //player.useHealthPotion();
                        //break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }

                if (choice == 1 || choice == 2) {
                    if (enemy.isAlive()) {
                        enemy.attack(player, 1);
                    } else {
                        System.out.println("You defeated the enemy!");
                        player.addCoins(10);
                        System.out.println("💰 You earned 10 coins!");
                        break;
                    }

                    if (!player.isAlive()) {
                        System.out.println("You were defeated by the enemy.");
                        player.subtractCoins();
                        System.out.println("💀 You lost 10 coins.");
                        player.setHealth(100); // reset health
                        break;
                    }
                }
            }

            // Save final player state
            db.savePlayer(playerName, player.getHealth(), player.getCoins(), player.getWeapon());
        }
        //scanner.close();
    }
}
