import java.util.Scanner;

public class Shop {
    private Scanner sc2=new Scanner(System.in);
    private Sprite player;
    private boolean running = true;



    //public Shop(Weapon player) {
        //this.player=player;
   // }
    public void shopMenu(){
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
                    break;
                default:
                    System.out.println("Invalid choice. Pick a number 1-3");
            }




        }
    }


    private void buyHealthPotion(){
        if(player.getCoins() >=10){
            player.setCoins(player.getCoins()-10);
            player.setHealthPotions(player.getHealthPotions()+1);
            System.out.println("You now have "+player.getHealthPotions());
        }
        else{
            System.out.println("You have not enough coins to buy health potions");
        }
    }
    public void startGame() {
        System.out.println("Welcome to the text-based game!");
        while (running) { // Keeps running until the player chooses to quit
            System.out.println("Press 'I' to open your inventory.");
            String input = sc2.nextLine().trim().toUpperCase(); // Reads and normalizes input

            if (input.equals("S")) {
                shopMenu(); // Opens inventory if 'I' is pressed
            }
        }
    }

}


