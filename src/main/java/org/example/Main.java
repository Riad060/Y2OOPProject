import java.util.Random;
import java.util.Scanner;
class Weapon {

        private int move1MinDamage;
        private int move1MaxDamage;
        private int move2MinDamage;
        private int move2MaxDamage;
        String name;

        public Weapon(int move1MinDamage, int move1MaxDamage, int move2MinDamage, int move2MaxDamage , String name) {
            this.move1MinDamage = move1MinDamage;
            this.move1MaxDamage = move1MaxDamage;
            this.move2MinDamage = move2MinDamage;
            this.move2MaxDamage = move2MaxDamage;
            this.name = name;
        }

        public int getMove1Damage() {
            Random rand = new Random();
            return rand.nextInt((move1MaxDamage - move1MinDamage) + 1) + move1MinDamage;
        }

        public int getMove2Damage() {
            Random rand = new Random();
            return rand.nextInt((move2MaxDamage - move2MinDamage) + 1) + move2MinDamage;
        }
    }
class Sprite {
    String name;
    int health;
    Weapon weapon;

    public Sprite(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public void attack(Sprite enemy, int move) {
        int damage;
        if(move == 1) {
            damage = weapon.getMove1Damage();
        }
        else{
            damage  = weapon.getMove2Damage();
        }
        enemy.health -= damage;
        System.out.println(name + " attacks " + enemy.name + " for " + damage + " damage.");
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Weapon sword = new Weapon(5, 15, 10, 20, "Sword");
        Sprite player = new Sprite("Player", 100, sword);
        //System.out.println("to enter inventory enter [i] ");

        Sprite enemy = new Sprite("Enemy", 80, sword);

        System.out.println("A wild enemy appears!");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nPlayer Health: " + player.health);
            System.out.println("Enemy Health: " + enemy.health);
            System.out.println("Choose an action:");
            System.out.println("1. Attack1");
            System.out.println("2. Attack2");
            System.out.println("3. Run");
            System.out.println("4. Inventory");

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
                    break;
                case 4:
                    System.out.println("\nWeapon equipped: " + player.weapon.name + " | Health: " + player.health);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            if(choice == 1 || choice == 2) {
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

        scanner.close();
    }
}