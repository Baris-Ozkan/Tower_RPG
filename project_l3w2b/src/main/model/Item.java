package model;

import java.util.ArrayList;
import java.util.List;

// an Item the hero can put into the inventory to use later to gain stat changes
public class Item {

    String itemName;
    String itemType;
    int buff;

    // EFFECTS: Initializes the item with certain stat changes
    public Item(String name, String type, int stat) {
        itemName = name;
        itemType = type;
        buff = stat;
    }

    // EFFECTS: returns the given item info as strings
    public List<String> returnItem(Item returnThis) {
        List item1 = new ArrayList<String>();
        item1.add(itemName);
        item1.add(itemType);
        item1.add(Integer.toString(buff));

        return item1;
    }

    public String getName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public int getBuff() {
        return buff;
    }
}

