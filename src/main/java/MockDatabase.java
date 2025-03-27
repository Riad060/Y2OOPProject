import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MockDatabase {
    private static final String FILE_NAME = "player_data.txt";
    private static Map<String, Integer> playerHealth = new HashMap<>();
    private static Map<String, Integer> playerCoins = new HashMap<>();

    static {
        loadFromFile();
    }

    public static void savePlayer(String name, int health, int coins) {
        playerHealth.put(name, health);
        playerCoins.put(name, coins);
        saveToFile();
        System.out.println("✅ Player saved: " + name + " | Health: " + health + " | Coins: " + coins);
    }

    public static int loadPlayerHealth(String name) {
        loadFromFile();
        if (playerHealth.containsKey(name)) {
            System.out.println("✅ Loaded health for: " + name + " = " + playerHealth.get(name));
            return playerHealth.get(name);
        } else {
            System.out.println("❌ Player health not found.");
            return -1;
        }
    }

    public static int loadPlayerCoins(String name) {
        loadFromFile();
        return playerCoins.getOrDefault(name, 0);
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String name : playerHealth.keySet()) {
                int health = playerHealth.get(name);
                int coins = playerCoins.getOrDefault(name, 0);
                writer.println(name + "," + health + "," + coins);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving player data!");
        }
    }

    private static void loadFromFile() {
        playerHealth.clear();
        playerCoins.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    playerHealth.put(parts[0], Integer.parseInt(parts[1]));
                    playerCoins.put(parts[0], Integer.parseInt(parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ No previous save data found.");
        }
    }
}


