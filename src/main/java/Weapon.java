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
}

//}
