public class Sprite {
    String name;
    int health;
    Weapon weapon;

    public Sprite(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public void attack(Sprite enemy, int move) {
        int damage = (move == 1) ? weapon.getMove1Damage() : weapon.getMove2Damage();
        enemy.health -= damage;
        System.out.println(name + " attacks " + enemy.name + " for " + damage + " damage.");
    }
    private int healthPotions = 3;
    private int Coins=30;
    public int getCoins() {
        return Coins;
    }

    public void setCoins(int coins) {
        this.Coins = coins;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public void setHealthPotions(int healthPotions) {
        this.healthPotions = healthPotions;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
