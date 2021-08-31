package model;

import java.util.ArrayList;
import java.util.List;

// A monster the hero has to fight to clear the level
public class Monster {
    private int health;
    private int attack;
    private int defense;


    // EFFECTS: Initializes a monster with given values
    public Monster(Integer hp, Integer atck, Integer def) {
        health += hp;
        attack += atck;
        defense += def;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
