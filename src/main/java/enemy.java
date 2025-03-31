public class enemy {
    private String name = "";
    private int health = 80;

    public enemy(String name, int health, Weapon weapon){
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

    private Weapon weapon;

}
