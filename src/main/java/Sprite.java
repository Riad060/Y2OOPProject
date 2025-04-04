public class Sprite {
    private String name;
    private int health;
    private Weapon weapon;
    private int coins;
    private int healthPotions;

    public Sprite(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.coins = 0;
        this.healthPotions = 0;
    }

    public void attack(enemy enemy, int move) {
        int damage;
        if (move == 1) {
            damage = weapon.getMove1Damage();
        } else {
            damage = weapon.getMove2Damage();
        }
        enemy.setHealth(enemy.getHealth()-damage); ;
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage.");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public void setHealthPotions(int healthPotions) {
        this.healthPotions = healthPotions;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void subtractCoins() {
        coins -= 10;
        if (coins < 0) coins = 0;
    }

    public void subtractCoins(int amount) {
        coins -= amount;
        if (coins < 0) coins = 0;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public void useHealthPotion() {
        if (healthPotions > 0) {
            health = 100;
            healthPotions--;
            System.out.println("🧪 Used an HP potion. Your health is fully restored!");
        } else {
            System.out.println("❌ You have no HP potions.");
        }
    }
}