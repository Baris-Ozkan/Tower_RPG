package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    private Hero myHero;
    private Monster fightThis;

    @BeforeEach
    void runBefore() {
        myHero = new Hero();
    }

    @Test
    void testCreateHero() {
        myHero.createHero(0, 3,2);
        assertEquals(6, myHero.getHealth());
        assertEquals(9, myHero.getAttack());
        assertEquals(8, myHero.getDefense());
        Item newit= new Item("zor", "Weapon", 5);
        assertEquals(0, myHero.size());
        myHero.addItem(newit);
        assertEquals("zor", myHero.getItem(0).getName());
        assertEquals(1, myHero.size());
    }

    @Test
    void testAddExperience() {
        myHero.addExperience(7);
        assertEquals(3, myHero.getLevel());
        assertEquals(1, myHero.getExperience());
        myHero.addExperience(1);
        assertEquals(3, myHero.getLevel());
        assertEquals(2, myHero.getExperience());
    }

    @Test
    void testLevelUp() {
        myHero.levelUp(1);
        assertEquals(7, myHero.getHealth());
        assertEquals(6, myHero.getAttack());
        assertEquals(6, myHero.getDefense());

        myHero.levelUp(2);
        assertEquals(7, myHero.getHealth());
        assertEquals(7, myHero.getAttack());
        assertEquals(6, myHero.getDefense());

        myHero.levelUp(3);
        assertEquals(7, myHero.getHealth());
        assertEquals(7, myHero.getAttack());
        assertEquals(7, myHero.getDefense());

        // levelup with illegal value, should have no effect
        myHero.levelUp(4);
        assertEquals(7, myHero.getHealth());
        assertEquals(7, myHero.getAttack());
        assertEquals(7, myHero.getDefense());
    }

    @Test
    void testProgressTowardsNextLevel() {
        myHero.addExperience(2);
        int progressTN = myHero.progressTowardsNextLevel();
        assertEquals(1, progressTN);
   }

   @Test
   void testGetStatus() {
       ArrayList testList = new ArrayList();
       testList = myHero.getStatus();

       assertEquals(5, testList.size());
       assertEquals(6, testList.get(0));
       assertEquals(6, testList.get(1));
       assertEquals(6, testList.get(2));
       assertEquals(1, testList.get(3));
       assertEquals(0, testList.get(4));
   }

    @Test
    void testCombatWithWin() {
        fightThis = new Monster(8, 5, 5);
        myHero.combatWith(fightThis);
        assertEquals(6, myHero.getHealth());
        assertEquals(0, myHero.getExperience());
        assertEquals(2, myHero.getLevel());
        assertEquals(1, myHero.getLevelUpExp());
    }

    @Test
    void testCombatWithLose() {
        fightThis = new Monster(30, 9, 6);
        myHero.combatWith(fightThis);
        assertFalse(myHero.combatWith(fightThis));
        assertTrue(myHero.getHealth() <=0);
    }

    @Test
    void testUseItemWeapon() {
        Item useThis = new Item("Sword of Zandor", "Weapon", 2);
        Inventory heroInvent = new Inventory();
        heroInvent.addItem(useThis);
        myHero.useItem(useThis, heroInvent);
        assertEquals(8, myHero.getAttack());
        assertEquals(1,  heroInvent.size());
        assertEquals(0, useThis.getBuff());
    }

    @Test
    void testUseItemArmor() {
        Item useThis = new Item("Plate of Gordon", "Armor", 5);
        Inventory heroInvent = new Inventory();
        heroInvent.addItem(useThis);
        myHero.useItem(useThis, heroInvent);
        assertEquals(11, myHero.getDefense());
        assertEquals(1,  heroInvent.size());
        assertEquals(0, useThis.getBuff());
    }

    @Test
    void testUseItemPotion() {
        Item useThis = new Item ("Potion of Health", "Potion", 2);
        Inventory heroInvent = new Inventory();
        heroInvent.addItem(useThis);
        myHero.useItem(useThis, heroInvent);
        assertEquals(8, myHero.getHealth());
        assertEquals(0,  heroInvent.size());
    }

    @Test
    void testUseIllegalItem() {
        Item useThis = new Item ("Potion of Health", "Potionsq22we", 2);
        Inventory heroInvent = new Inventory();
        heroInvent.addItem(useThis);
        myHero.useItem(useThis, heroInvent);
        assertEquals(6, myHero.getHealth());
        assertEquals(1,  heroInvent.size());
    }
}