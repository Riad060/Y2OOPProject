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
    void TestgetWeaponFAIL(){
        assertNull(Database.getWeapon(1002));
    }

    @Test
    void TestgetWeaponPASS(){
        assertNotNull(Database.getWeapon(1));
    }
}
