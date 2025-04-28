import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseManager {
    private String url = "jdbc:mysql://localhost:3306/oopgame";
    private String dbuser = "root";
    private String dbpass = "password";


    public Sprite getPlayer(String username){
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM player WHERE user_name LIKE ?");
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();

            if(results.next()){
                Weapon currnet = getWeapon(results.getString("current_weapon"));
                if (currnet == null) {
                    //If somehow weapon is not found in the DB.
                    currnet = new Weapon(1, 1, 1, 1, "Fists");
                }
                int health = results.getInt("health");

                return new Sprite(username, health, currnet);
            }

            return null;
        } catch (Exception e){

            return null;
        }
    }

    public enemy getRandomEnemy(){
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM enemy ORDER BY RAND() LIMIT 1");
            ResultSet results = stmt.executeQuery();

            Weapon currnet = getWeapon(results.getString("current_weapon"));
            int health = results.getInt("health");
            String name = results.getString("name");
            return new enemy(name, health, currnet);

        } catch (Exception e){

            return new enemy("Enemy", 80, getRandomWeapon());
        }
    }




    public Weapon getWeapon(String weapon_id){
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM weapon WHERE name = ?");
            stmt.setString(1, weapon_id);
            ResultSet results = stmt.executeQuery();

            if (results.next()){
                String name = results.getString("name");
                int cost = results.getInt("cost");
                int Move1Max = results.getInt("move1Max");
                int Move1Min = results.getInt("move1Min");
                int Move2Max = results.getInt("move2Max");
                int Move2Min = results.getInt("move2Min");

                return new Weapon(Move1Min, Move1Max, Move2Min, Move2Max, name);
            }
            return null;

        } catch (Exception e){
            return null;
        }
    }

    public Weapon getRandomWeapon(){
        Weapon defaultWeapon = new Weapon(1, 1, 1, 1, "NotReal");
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM weapon ORDER BY RAND() LIMIT 1");
            ResultSet results = stmt.executeQuery();

            if (results.next()){
                String name = results.getString("name");
                int cost = results.getInt("cost");
                int Move1Max = results.getInt("move1Max");
                int Move1Min = results.getInt("move1Min");
                int Move2Max = results.getInt("move2Max");
                int Move2Min = results.getInt("move2Min");

                return new Weapon(Move1Min, Move1Max, Move2Min, Move2Max, name);
            }

            return defaultWeapon;

        } catch (Exception e){
            return defaultWeapon;
        }
    }


    public int savePlayer(String player, int health, int coins, Weapon current_weapon){
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("REPLACE INTO player VALUES (?, ?, ?, ?)");
            stmt.setString(1, player);
            stmt.setInt(2, health);
            stmt.setInt(3, coins);
            stmt.setString(4, current_weapon.getName());
            stmt.executeUpdate();

            return 1;

        } catch (Exception e){
            return 0;
        }
    }

}




