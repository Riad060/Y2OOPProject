import java.util.Random;
import java.util.Scanner;

public class Weapon {
    private int move1MinDamage, move1MaxDamage, move2MinDamage, move2MaxDamage;
    public String name;

    public Weapon(int move1MinDamage, int move1MaxDamage, int move2MinDamage, int move2MaxDamage, String name) {
        this.move1MinDamage = move1MinDamage;
        this.move1MaxDamage = move1MaxDamage;
        this.move2MinDamage = move2MinDamage;
        this.move2MaxDamage = move2MaxDamage;
        this.name = name;
    }

    public Weapon(String bow, int move1MaxDamage) {
    }

    public int getMove1Damage() {
        return new Random().nextInt((move1MaxDamage - move1MinDamage) + 1) + move1MinDamage;
    }

    public int getMove2Damage() {
        return new Random().nextInt((move2MaxDamage - move2MinDamage) + 1) + move2MinDamage;
    }

    public static class Inventory {
        private int health = 100;
        private int maxHealth = 100;
        private int healthPotions = 3;
        private String currentWeapon = "Sword";
        private boolean running = true;//used for the quit option in Inventory

        private Scanner sc = new Scanner(System.in);

        //need to change this when adding into main code
        public void start() {
            System.out.println("Game has started");
            while (running) {//will have to change to after a battle? or somethijng
                System.out.println("Press 'I' to open the inventory");
                String input = sc.nextLine();

                if (input.equals("I")) {
                    OpenInventory();
                }
            }

        }

        public void OpenInventory() {
            while (true) {
                System.out.println(" Inventory ");
                System.out.println("1. Heal (" + healthPotions + " Potions)");
                System.out.println("2. Switch Weapons (Currently equipped " + currentWeapon + ")");
                System.out.println("3. Exit");
                System.out.println("Enter your choice 1-3");

                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        heal();
                        break;
                    case "2":
                        switchWeapon();
                        break;
                    case "3":
                        quitGame();
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        }

        //uses health potions to heal, will have to decide how the user actually gets the potions
        private void heal() {
            if (healthPotions > 0) {
                if (health < maxHealth) {
                    health = Math.min(health + 30, maxHealth);
                    healthPotions--;
                    System.out.println("You used a potion,New health is " + health + " hp of" + maxHealth);
                } else {
                    System.out.println("Health is alresdy full");

                }
            }
            if (healthPotions == 0) {
                System.out.println("You have used all your potions");
            }

        }

        //will have to change around to use the database weapons didnt know how to set it up so
        //I just harddcoded some weapons for meantime
        private void switchWeapon() {
            System.out.println("Choose equipped weapon: 1=Sword,2=Axe");
            String weaponChoice = sc.nextLine();

            switch (weaponChoice) {
                case "1":
                    currentWeapon = "Sword";
                    break;
                case "2":
                    currentWeapon = "Axe";
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("You equipped " + currentWeapon);
        }

        private void quitGame() {
            System.out.println("You have quit the game");
            running = false;
        }


    }
}
