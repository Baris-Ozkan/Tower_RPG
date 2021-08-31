package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// A floor with a chance of containing a random item that adjusts its difficulty each time its cleared

public class Floor extends Inventory implements Writable {
    int floorNumber;
    int floorDifficultyModifier;


    /**
     * EFFECTS: Initializes a floor that increases its difficulty whenever it's cleared
     */
    public Floor() {
        floorNumber = 1;
        floorDifficultyModifier = 0;
    }

    /**
     * MODIFIES: this
     * EFFECTS: advances the floor by increases the difficulty modifier and floor number
     */
    public void floorCleared() {
        floorNumber += 1;
        floorDifficultyModifier += 1;
    }

    public void changeFloorNumber(Integer fn) {
        floorNumber = fn;
    }

    public void changeFloorDifficultyModifier(Integer fdm) {
        floorDifficultyModifier = fdm;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getFloorDifficultyModifier() {
        return floorDifficultyModifier;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONObject jsonTotal = new JSONObject();

        json.put("Floor Number", floorNumber);
        json.put("Floor Difficulty Modifier", floorDifficultyModifier);

        JSONArray jsonInventory = new JSONArray();

        for (int i = 0; i < this.size(); i++) {
            jsonInventory.put(this.getItem(i).returnItem(this.getItem(i)));
        }

        jsonTotal.put("Floor Data", json);
        jsonTotal.put("Floor Inventory", jsonInventory);

        return jsonTotal;
    }
}
