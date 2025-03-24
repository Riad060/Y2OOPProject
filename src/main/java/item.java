
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

