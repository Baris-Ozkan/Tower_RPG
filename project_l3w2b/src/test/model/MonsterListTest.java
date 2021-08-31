package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonsterListTest {
    private MonsterList monsterList;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;

    @BeforeEach
    void runBefore(){
        monsterList = new MonsterList();
        monster1 = new Monster(5,6,10);
        monster2 = new Monster(10,10,12);
        monster3 = new Monster(1,1,1);
    }

    @Test
    void testMonsterList() {
        monsterList.addMonster(monster1);
        monsterList.addMonster(monster2);
        monsterList.addMonster(monster3);

        assertEquals(3, monsterList.monsterListSize());
        assertEquals(10, monsterList.getMonster(0).getDefense());
        assertEquals(6, monsterList.getMonster(0).getAttack());
        assertEquals(5, monsterList.getMonster(0).getHealth());

        monsterList.removeMonster(monsterList.getMonster(2));
        assertEquals(2, monsterList.monsterListSize());
        assertEquals(10, monsterList.getMonster(1).getHealth());
    }
}
