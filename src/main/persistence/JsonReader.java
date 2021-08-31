package persistence;

import jdk.nashorn.internal.parser.JSONParser;
import model.Floor;
import model.Hero;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Hero and Hero Progress from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Hero from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Hero readHero() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHero(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Hero from JSON object and returns it
    private Hero parseHero(JSONObject jsonObject) {
        Hero h = new Hero();
        addHero(h, jsonObject);
        return h;
    }

    // MODIFIES: h
    // EFFECTS: parses Hero Inventory and Hero Stats from JSON object and modifies hero accordingly
    private void addHero(Hero h, JSONObject jsonObject) {
        JSONObject jsonHero = jsonObject.getJSONObject("Hero");

        addHeroInventory(h, jsonHero);
        addHeroStats(h,jsonHero);
    }

    // MODIFIES: h
    // EFFECTS: parses items from Hero Inventory JSON array
    private void addHeroInventory(Hero h, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Hero Inventory");

        for (Object json : jsonArray) {
            JSONArray nextItem = (JSONArray) json;
            addItem(h, nextItem);
        }
    }

    // MODIFIES: h
    // EFFECTS: parses item info and adds the item to h
    private void addItem(Hero h, JSONArray jsonObject) {

        String name = jsonObject.getString(0);
        String type = jsonObject.getString(1);
        int buff = Integer.parseInt(jsonObject.getString(2));

        Item item = new Item(name, type, buff);
        h.addItem(item);
    }

    // MODIFIES: h
    // EFFECTS: parses Hero Stats and gives h according read values
    private void addHeroStats(Hero h, JSONObject jsonObject) {
        JSONObject stats = (JSONObject) jsonObject.get("Hero Stats");

        int health = stats.getInt("Health");
        h.changeHealth(health);

        int attack = stats.getInt("Attack");
        h.changeAttack(attack);

        int defense = stats.getInt("Defense");
        h.changeDefense(defense);

        int level = stats.getInt("Level");
        h.changeLevel(level);

        int experience = stats.getInt("Experience");
        h.changeExperience(experience);

        int levelUpExperience = stats.getInt("LevelUpExperience");
        h.changeLevelUpExp(levelUpExperience);
    }

    // EFFECTS: reads Hero Progress from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Floor readFloor() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFloor(jsonObject);
    }

    // EFFECTS: parses Hero Progress from JSON object and returns it
    private Floor parseFloor(JSONObject jsonObject) {
        Floor f = new Floor();
        addFloor(f, jsonObject);
        return f;
    }

    // MODIFIES: f
    // EFFECTS: parses Floor Inventory and Floor Data from JSON object and modifies hero progress accordingly
    private void addFloor(Floor f, JSONObject jsonObject) {
        JSONObject jsonHero = jsonObject.getJSONObject("Hero Progress");

        addFloorInventory(f, jsonHero);
        addFloorData(f,jsonHero);
    }

    // MODIFIES: f
    // EFFECTS: parses items from Floor Inventory JSON array
    private void addFloorInventory(Floor f, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Floor Inventory");

        for (Object json : jsonArray) {
            JSONArray nextItem = (JSONArray) json;
            addFloorItem(f, nextItem);
        }
    }

    // MODIFIES: f
    // EFFECTS: parses item info and adds the item to floor
    private void addFloorItem(Floor f, JSONArray jsonObject) {
        String name = jsonObject.getString(0);
        String type = jsonObject.getString(1);
        int buff = Integer.parseInt(jsonObject.getString(2));

        Item item = new Item(name, type, buff);
        f.addItem(item);
    }

    // MODIFIES: f
    // EFFECTS: parses Floor Data and gives f according read values
    private void addFloorData(Floor f, JSONObject jsonObject) {
        JSONObject floorData = (JSONObject) jsonObject.get("Floor Data");

        int floorNumber = floorData.getInt("Floor Number");
        f.changeFloorNumber(floorNumber);

        int floorDifficultyModifier = floorData.getInt("Floor Difficulty Modifier");
        f.changeFloorDifficultyModifier(floorDifficultyModifier);

    }
}
