package model;

import java.util.ArrayList;
import java.util.List;

// A list of monsters that the hero must fight to clear the floor
public class MonsterList {
    private List<Monster> floorMonsters;

    // EFFECTS: Initializes a list of monsters for a floor
    public MonsterList() {
        floorMonsters = new ArrayList<Monster>();
    }

    /**
     * MODIFIES: this
     * EFFECTS: adds the monster to the monster list
     */
    public void addMonster(Monster addThis) {
        floorMonsters.add(addThis);
    }

    /**
     * MODIFIES: this
     * EFFECTS: removes the monster from the monster list
     */
    public void removeMonster(Monster removeThis) {
        floorMonsters.remove(removeThis);
    }

    public Monster getMonster(int index) {
        return floorMonsters.get(index);
    }

    public int monsterListSize() {
        return floorMonsters.size();
    }
}
