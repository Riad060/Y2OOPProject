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
