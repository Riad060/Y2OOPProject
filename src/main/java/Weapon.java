import java.util.Random;

public class Weapon {
    private int move1MinDamage, move1MaxDamage;
    private int move2MinDamage, move2MaxDamage;
    public String name;

    public Weapon(int move1MinDamage, int move1MaxDamage, int move2MinDamage, int move2MaxDamage, String name) {
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

    // Upgrade weapon damage
    public void upgrade() {
        move1MinDamage += 2;
        move1MaxDamage += 4;
        move2MinDamage += 3;
        move2MaxDamage += 5;
        System.out.println("⚔️ Your weapon has been upgraded! New damage ranges:");
        System.out.println("Move 1: " + move1MinDamage + " - " + move1MaxDamage);
        System.out.println("Move 2: " + move2MinDamage + " - " + move2MaxDamage);
    }

    // Optional: Getters if needed
    public String getName() {
        return name;
    }

    public String getMove1MinDamage() {
        return null;
    }

    public String getMove1MaxDamage() {
        return null;
    }

    public void increaseDamage(int i) {
    }
}

