package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FloorTest {
    private Floor myFloor;
    private Item myItem = new Item("Sword of Zondar", "Weapon", 5);

    @Test
    void testFloor() {
        myFloor = new Floor();
        assertEquals(0, myFloor.getFloorDifficultyModifier());
        assertEquals(1, myFloor.getFloorNumber());
        assertEquals(0, myFloor.size());
        myFloor.floorCleared();
        myFloor.addItem(myItem);
        assertEquals(1, myFloor.getFloorDifficultyModifier());
        assertEquals(2, myFloor.getFloorNumber());
        assertEquals(1, myFloor.size());
    }
}
