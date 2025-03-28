
// Base class for all items
public class item {
    protected String name;

    public item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void use() {
        System.out.println("Using " + name);
    }
}
import java.util.Random;

public class Weapon extends item {
    private int move1MinDamage, move1MaxDamage, move2MinDamage, move2MaxDamage;

    public Weapon(int move1MinDamage, int move1MaxDamage, int move2MinDamage, int move2MaxDamage, String name) {
        super(name); // Call parent constructor
        this.move1MinDamage = move1MinDamage;
        this.move1MaxDamage = move1MaxDamage;
        this.move2MinDamage = move2MinDamage;
        this.move2MaxDamage = move2MaxDamage;
    }

    public int getMove1Damage() {
        return new Random().nextInt((move1MaxDamage - move1MinDamage) + 1) + move1MinDamage;
    }

    public int getMove2Damage() {
        return new Random().nextInt((move2MaxDamage - move2MinDamage) + 1) + move2MinDamage;
    }

    @Override
    public void use() {
        System.out.println("Equipping " + name);
    }
}
public class Potion extends item {
    private int healAmount;

    public Potion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public void use() {
        System.out.println("Drinking " + name + " and healing for " + healAmount + " HP.");
    }
}
