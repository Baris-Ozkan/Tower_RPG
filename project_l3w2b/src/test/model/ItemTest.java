package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item myItem;

    @Test
    void testItem() {
        myItem = new Item("Sword of Zondar", "Weapon", 2);
        assertEquals("Sword of Zondar", myItem.getName());
        assertEquals("Weapon", myItem.getItemType());
        assertEquals(2, myItem.getBuff());
    }
}
