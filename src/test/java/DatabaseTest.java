import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DatabaseTest {
    DatabaseManager Database;

    @BeforeEach
    void setUp(){
        Database = new DatabaseManager();
    }

    @Test
    void Test_getWeaponFAIL(){
        assertNull(Database.getWeapon(1002));
    }

    @Test
    void Test_getWeaponPASS(){
        assertNotNull(Database.getWeapon(1));
    }

    @Test
    void Test_getPlayernFAIL(){
        assertNull(Database.getPlayer("NotReal"));
    }

    @Test
    void Test_getPlayerPASS(){
        assertNotNull(Database.getPlayer("John"));
    }

}
