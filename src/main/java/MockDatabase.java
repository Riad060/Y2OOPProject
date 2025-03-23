import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MockDatabase {
    private static final String FILE_NAME = "player_data.txt";
    private static Map<String, Integer> playerData = new HashMap<>();

    static {
        loadFromFile();
    }

    public static void savePlayer(String name, int health) {
        playerData.put(name, health);
        saveToFile();
        System.out.println("✅ Player saved: " + name + " | Health: " + health);
    }

    public static int loadPlayer(String name) {
        loadFromFile();  // Ensure we load from the file
        if (playerData.containsKey(name)) {
            System.out.println("✅ Player found: " + name + " | Health: " + playerData.get(name));
            return playerData.get(name);
        } else {
            System.out.println("❌ Player not found. Creating new profile.");
            return -1;
        }
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : playerData.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving player data!");
        }
    }

    private static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    playerData.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ No previous save data found.");
        }
    }
}

