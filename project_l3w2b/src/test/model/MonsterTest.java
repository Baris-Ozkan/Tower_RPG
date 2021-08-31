package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {
    private Monster myMonster;

    @Test
    void testMonster() {
        myMonster = new Monster(10, 12, 8);
        assertEquals(10, myMonster.getHealth());
        assertEquals(12, myMonster.getAttack());
        assertEquals(8, myMonster.getDefense());
    }

}
