import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseManager {
    private String url = "jdbc:mysql://localhost:3306/OOPGame";
    private String dbuser = "root";
    private String dbpass = "password";

    public Sprite getPlayer(String username){
        Sprite player = null;
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM players WHERE username LIKE ?");
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();

            if(results != null){
                conn.close();
                return player;
            }

            return player;
        } catch (Exception e){
            return player;
        }
    }



    public Weapon getWeapon(int weapon_id){
        try (Connection conn = DriverManager.getConnection(url, dbuser, dbpass)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM weapons WHERE weapon_id = ?");
            stmt.setInt(1, weapon_id);
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

}




