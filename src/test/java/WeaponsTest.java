import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    private Weapon weapon;

    @BeforeEach
    void setUp() {
        // This runs before EVERY test method
        weapon = new Weapon(5, 10, 8, 15, "Spear");
    }

    @Test
    void testMove1DamageInRange() {
        for (int i = 0; i < 100; i++) {
            int dmg = weapon.getMove1Damage();
            assertTrue(dmg >= 5 && dmg <= 10);
        }
    }

    @Test
    void testMove2DamageInRange() {
        for (int i = 0; i < 100; i++) {
            int dmg = weapon.getMove2Damage();
            assertTrue(dmg >= 8 && dmg <= 15);
        }
    }

    @Test
    void testUpgrade() {
        weapon.upgrade(); // Mutates the internal state

        for (int i = 0; i < 100; i++) {
            int dmg1 = weapon.getMove1Damage();
            int dmg2 = weapon.getMove2Damage();
            assertTrue(dmg1 >= 7 && dmg1 <= 14);
            assertTrue(dmg2 >= 11 && dmg2 <= 20);
        }
    }

    @Test
    void testName() {
        assertEquals("Spear", weapon.getName());
    }
}
