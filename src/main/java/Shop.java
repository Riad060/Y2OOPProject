import java.util.Scanner;

public class Shop {
    private Scanner sc2 = new Scanner(System.in);
    private Sprite player;

    public Shop(Sprite player) {
        this.player = player;
    }

    public void shopMenu() {
        boolean inShop = true;
        while (inShop) {
            System.out.println("\n\uD83D\uDED2 You have entered the shop!");
            System.out.println("1. Buy HP Potion (10 coins)");
            System.out.println("2. Upgrade Weapon (20 coins)");
            System.out.println("3. Leave Shop");
            System.out.println("\uD83D\uDCB0 You have " + player.getCoins() + " coins.");

            String choice = sc2.nextLine();
            switch (choice) {
                case "1":
                    buyHealthPotion();
                    break;
                case "2":
                    upgradeWeapon();
                    break;
                case "3":
                    System.out.println("\uD83D\uDEAA You have left the shop.");
                    inShop = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Pick a number 1-3.");
            }
        }
    }

    private void buyHealthPotion() {
        if (player.getCoins() >= 10) {
            player.setCoins(player.getCoins() - 10);
            player.setHealthPotions(player.getHealthPotions()+1);
            System.out.println("✅ Bought 1 HP potion. You now have " + player.getHealthPotions() + ".");
            //player.useHealthPotion(); // instantly use potion
        } else {
            System.out.println("❌ Not enough coins to buy health potions.");
        }
    }

    private void upgradeWeapon() {
        if (player.getCoins() >= 20) {
            player.setCoins(player.getCoins() - 20);
            Weapon weapon = player.getWeapon();
            weapon.upgrade();
            System.out.println("\uD83D\uDD27 Weapon upgraded!");
        } else {
            System.out.println("❌ Not enough coins to upgrade weapon.");
        }
    }
}





