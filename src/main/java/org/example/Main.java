package org.example;

import java.util.Random;
import java.util.Scanner;
import ie.atu.study.MockDatabase;



class Weapon {
    private int move1MinDamage, move1MaxDamage, move2MinDamage, move2MaxDamage;
    String name;

    public Weapon(int move1MinDamage, int move1MaxDamage, int move2MinDamage, int move2MaxDamage , String name) {
        this.move1MinDamage = move1MinDamage;
        this.move1MaxDamage = move1MaxDamage;
        this.move2MinDamage = move2MinDamage;
        this.move2MaxDamage = move2MaxDamage;
        this.name = name;
    }

    public int getMove1Damage() {
        return new Random().nextInt((move1MaxDamage - move1MinDamage) + 1) + move1MinDamage;
    }

    public int getMove2Damage() {
        return new Random().nextInt((move2MaxDamage - move2MinDamage) + 1) + move2MinDamage;
    }

    public static class Inventory {
        public int health=100;
        private int maxHealth=100;
        private int healthPotions=3;
        private String currentWeapon="Sword";
        private boolean running =true;//used for the quit option in Inventory

        private Scanner sc = new Scanner(System.in);

        //need to change this when adding into main code

        public void OpenInventory(){
            while(true){
            System.out.println(" Inventory ");
            System.out.println("1. Heal ("+healthPotions+" Potions)");
            System.out.println("2. Switch Weapons (Currently equipped "+currentWeapon+")");
            System.out.println("3. Exit");
            System.out.println("Enter your choice 1-3");

            String choice = sc.nextLine();

            switch(choice){
                case "1":
                    heal();
                    break;
                case "2":
                    switchWeapon();
                    break;
                case "3":
                    //quitGame();
                    return;
               default:
                   System.out.println("Invalid choice");
                   break;
            }
            }
            }
        //uses health potions to heal, will have to decide how the user actually gets the potions
        private void heal(){
            if(healthPotions>0){
                if(health<maxHealth){
                    health=Math.min(health +30,maxHealth);
                    healthPotions--;
                    System.out.println("You used a potion,New health is "+health+" hp of"+maxHealth);
                }else{
                    System.out.println("Health is alresdy full");

                }
            }
            if(healthPotions==0){
                    System.out.println("You have used all your potions");
                }

        }
        //will have to change around to use the database weapons didnt know how to set it up so
        //I just harddcoded some weapons for meantime
        private void switchWeapon(){
            System.out.println("Choose equipped weapon: 1=Sword,2=Axe");
            String weaponChoice=sc.nextLine();

            switch(weaponChoice){
                case "1":
                    currentWeapon="Sword";
                    break;
                    case "2":
                        currentWeapon="Axe";
                        break;
                    default:
                        System.out.println("Invalid choice");
            }
            System.out.println("You equipped "+currentWeapon);
        }
        private void quitGame(){
            System.out.println("You have quit the game");
            running=false;
        }





    }
}

class Sprite {
    String name;
    Weapon weapon;
    Weapon.Inventory inventory;

    public Sprite(String name, Weapon.Inventory inventory, Weapon weapon) {
        this.name = name;
        this.inventory = inventory;
        this.weapon = weapon;
    }

    public void attack(Sprite enemy, int move) {
        int damage = (move == 1) ? weapon.getMove1Damage() : weapon.getMove2Damage();
        enemy.inventory.health -= damage;
        System.out.println(name + " attacks " + enemy.name + " for " + damage + " damage.");
    }

    public boolean isAlive() {
        return inventory.health > 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Weapon.Inventory in1 = new Weapon.Inventory(); // Shared inventory instance

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();

        // Load player health
        int playerHealth = MockDatabase.loadPlayer(playerName);
        if (playerHealth == -1) {
            System.out.println("New player! Setting default health to 100.");
            in1.health = 100;
            MockDatabase.savePlayer(playerName, in1.health);
        } else {
            in1.health = playerHealth;
            System.out.println("Welcome back, " + playerName + "! Your health: " + in1.health);
        }

        // Create player and enemy with shared inventory
        Weapon sword = new Weapon(5, 15, 10, 20, "Sword");
        Sprite player = new Sprite(playerName, in1, sword); // Share inventory
        Sprite enemy = new Sprite("Enemy", new Weapon.Inventory(), sword); // Enemy has its own inventory

        System.out.println("A wild enemy appears!");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nPlayer Health: " + in1.health);
            System.out.println("Enemy Health: " + enemy.inventory.health);
            System.out.println("Choose an action:");
            System.out.println("1. Attack1");
            System.out.println("2. Attack2");
            System.out.println("3. Run");
            System.out.println("4. Open Inventory");

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
                    in1.OpenInventory(); // Player heals inside inventory
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

        // Save health after battle
        MockDatabase.savePlayer(playerName, in1.health);
        scanner.close();
    }
}