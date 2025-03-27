import java.util.Scanner;

public class Inventory {
    private int health = 100;
    private int maxHealth = 100;
    private int healthPotions = 3;
    private String currentWeapon = "Sword";
    private boolean running = true;//used for the quit option in Inventory

    private Sprite player;
    // Constructor to pass player reference
    public Inventory(Sprite player) {
        this.player = player;
    }




    private Scanner sc = new Scanner(System.in);
    private Scanner sc2 = new Scanner(System.in);

    //need to change this when adding into main code
    public void start() {
        System.out.println("Game has started");
        while (running) {//will have to change to after a battle? or somethijng
            System.out.println("Press 'I' to open the inventory");
            System.out.println("Press 'S' to open the shop");
            String input = sc.nextLine();

            if (input.equals("I")) {
                OpenInventory();
            } else if (input.equals("S")) {
                OpenShop();
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
        if (player.getHealthPotions() > 0) {
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
    public void OpenShop() {
        while(true){
            System.out.println("You have entered the shop");
            System.out.println("1. Hp potion Cost: 10 coins");
            System.out.println("2. Upgrade Weapon: 20 coins");
            System.out.println("3. Leave Shop");
            System.out.println("You have "+player.getCoins()+"Coins");
            String choice=sc2.nextLine();
            switch(choice){
                case "1":
                    buyHealthPotion();
                    break;
                case "2":
                    //upgradeWeapon();
                    break;
                case "3":
                    System.out.println("You have left the shop");
                    return;
                default:
                    System.out.println("Invalid choice. Pick a number 1-3");
            }
        }
    }

    private void buyHealthPotion() {
        if (player.getCoins() >= 10) {
            player.setCoins(player.getCoins() - 10);
            player.setHealthPotions(player.getHealthPotions() + 1);
            System.out.println("You now have " + player.getHealthPotions());
        } else {
            System.out.println("You have not enough coins to buy health potions");
        }

    }


}