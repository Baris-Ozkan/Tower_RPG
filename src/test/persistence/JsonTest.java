package persistence;

import static org.junit.jupiter.api.Assertions.*;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.Floor;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Hero;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Hero testHero = new Hero();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistantFile() {
        JsonReader reader = new JsonReader("non-existantpath");

        try {
            Hero testHero = reader.readHero();
            fail("exception expected");

        } catch (IOException e) {
            // should pass
        }

    }

    @Test
    void testWriterDefaultFile() {
        try {
            Hero testHero = new Hero();
            Floor testFloor = new Floor();
            JsonWriter writer = new JsonWriter("./data/testWriterHero.json");
            writer.open();
            writer.write(testHero, testFloor);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterHero.json");
            testHero = reader.readHero();
            testFloor = reader.readFloor();

            assertEquals(6, testHero.getHealth());
            assertEquals(0, testHero.size());
            assertEquals(1, testFloor.getFloorNumber());
            assertEquals(0, testFloor.getFloorDifficultyModifier());

        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }
    }

    @Test
    void testWriterGeneralHeroAndFloor() {

        try {
            Hero testHero = new Hero();
            Item testItem = new Item("Sword of Zondar", "Weapon", 5);
            Item testItem2 = new Item("Cloak of Einar", "Armor", 8);
            Floor testFloor = new Floor();

            JsonWriter writer = new JsonWriter("./data/testWriterHeroAndFloor.json");
            writer.open();
            testHero.levelUp(1);
            testHero.levelUp(2);
            testHero.addItem(testItem);
            testHero.addItem(testItem2);
            testFloor.addItem(testItem);
            testFloor.floorCleared();
            writer.write(testHero,testFloor);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterHeroAndFloor.json");
            testHero = reader.readHero();
            testFloor = reader.readFloor();

            assertEquals("Sword of Zondar", testHero.getItem(0).getName());
            assertEquals(5, testHero.getItem(0).getBuff());
            assertEquals(7, testHero.getHealth());
            assertEquals("Sword of Zondar", testFloor.getItem(0).getName());
            assertEquals(2, testFloor.getFloorNumber());
            assertEquals(1, testFloor.getFloorDifficultyModifier());

        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }
    }

}
