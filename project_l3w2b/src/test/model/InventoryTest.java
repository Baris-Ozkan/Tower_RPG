package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory invent1;
    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    void runBefore() {
        invent1 = new Inventory();
        item1 = new Item("Long Sword", "Weapon", 3);
        item2 = new Item("Chain Plate", "Armor", 2);
        item3 = new Item("Health Potion", "Potion", 5);
    }

    @Test
    void testInventory() {
        invent1.addItem(item1);
        invent1.addItem(item2);
        invent1.addItem(item3);

        assertEquals(3, invent1.size());
        assertEquals("Long Sword", invent1.getItem(0).getName());
        assertEquals("Weapon", invent1.getItem(0).getItemType());
        assertEquals(3, invent1.getItem(0).getBuff());
        assertEquals("Chain Plate", invent1.getItem(1).getName());
        assertEquals("Armor", invent1.getItem(1).getItemType());
        assertEquals(2, invent1.getItem(1).getBuff());
        assertEquals("Health Potion", invent1.getItem(2).getName());
        assertEquals("Potion", invent1.getItem(2).getItemType());
        assertEquals(5, invent1.getItem(2).getBuff());
        invent1.removeItem(item3);
        assertEquals(2, invent1.size());
        invent1.removeItem(item1);
        assertEquals(1, invent1.size());
    }
}
