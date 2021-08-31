package persistence;

import model.Floor;
import model.Hero;
import org.json.JSONObject;

import java.io.*;

// code of this class is inspired by JsonRealizationDemo
// represents a writer that writes the JSON representation of the Hero and Floor model
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String saveHere;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        saveHere = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(saveHere));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Hero and Floor to file
    public void write(Hero saveHero, Floor saveFloor) {
        JSONObject json = saveHero.toJson();
        JSONObject jsonFloor = saveFloor.toJson();
        JSONObject jsonTotal = new JSONObject();

        jsonTotal.put("Hero", json);
        jsonTotal.put("Hero Progress", jsonFloor);
        saveToFile(jsonTotal.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
