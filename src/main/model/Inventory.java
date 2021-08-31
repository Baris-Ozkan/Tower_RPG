package model;

import java.util.ArrayList;
import java.util.List;

// List of items a hero holds
public class Inventory {
    private List<Item> heroInventory;


    // EFFECTS: Initializes the inventory the hero can use
    public Inventory() {
        heroInventory = new ArrayList<Item>();
    }

    /**
     * MODIFIES: this
     * EFFECTS: adds the acquired item into the Inventory
     */
    public void addItem(Item addThis) {
        heroInventory.add(addThis);
    }

    /**
     * MODIFIES: this
     * EFFECTS: removes the acquired item into the Inventory
     */
    public void removeItem(Item removeThis) {
        heroInventory.remove(removeThis);
    }



    public int size() {
        return heroInventory.size();
    }

    public Item getItem(int itemIndex) {
        return heroInventory.get(itemIndex);
    }
}