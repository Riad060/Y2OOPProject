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

    public boolean isAlive() {
        return health > 0;
    }
}
