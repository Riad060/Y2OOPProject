public class enemy {
    private String name;
    private int health;
    private Weapon weapon;

    public enemy(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    // Attack method now takes Sprite as the parameter
    public void attack(Sprite sprite, int move) {
        int damage;
        if (move == 1) {
            damage = weapon.getMove1Damage();
        } else {
            damage = weapon.getMove2Damage();
        }

        sprite.setHealth(sprite.getHealth() - damage); // Adjust the player's health
        System.out.println(name + " attacks " + sprite.getName() + " for " + damage + " damage.");
    }

    public boolean isAlive() {
        return health > 0;
    }
}