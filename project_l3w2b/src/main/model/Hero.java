package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//A character that can be created by the user to play the Tower RPG

public class Hero extends Inventory implements Writable {
    private int health;
    private int attack;
    private int defense;
    private int level;
    private int levelUpExp;
    private int experience;
    private int timeForLevelUp = 3;
    private int progressTN;
    private ArrayList status;

    /**
     * EFFECTS: Initializes the Hero character sheet with default values and an empty inventory
     */
    public Hero() {

        health = 6;
        attack = 6;
        defense = 6;

        level = 1;
        experience = 0;
        levelUpExp = 0;
    }

    /**
     * MODIFIES: this
     * EFFECTS:  Allocates starting points to different stats of the new hero
     * depending on the values entered by the user
     */
    public void createHero(Integer hp, Integer atck, Integer def) {
        health += hp;
        attack += atck;
        defense += def;
    }

    /**
     * MODIFIES: this, Monster
     * EFFECTS: If hero's attack > monster's defense, reduce the healthpoints of the monster on hero's turn according to
     * the difference between hero's attack and monster's defense. If monster's attack > hero's defense, reduce the
     * healthpoints of the hero on monster's turn according to the difference between monster's attack and hero's
     * defense. Continue until the hero or the monster dies. If battle is won add experience to the hero otherwise
     * end game.
     * The hero will always have strike priority.
     */
    public boolean combatWith(Monster enemy) {
        int monsExp = enemy.getHealth() - 5;
        int enemyHP = enemy.getHealth();
        int enemyATC = enemy.getAttack();
        int enemyDEF = enemy.getDefense();
        int differential1 = attack - enemyDEF;
        int differential2 = enemyATC - defense;

        for (int i = 0; health > 0 && enemyHP > 0; i++) {
            if (attack > enemyDEF) {
                enemyHP = enemyHP - differential1;
            }
            if (enemyATC > defense) {
                health = health - differential2;
            }
        }
        if (health > 0) {
            this.addExperience(monsExp);
            return true;
        } else {
            return false;
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: add winExperience to the hero. If hero experience >= timeToLevelUp levelUp the hero until
     * hero experience <= timeToLevelUp and put leftover points to experience.
     */
    public void addExperience(int monsExp) {
        experience += monsExp;
        for (int i = 0; experience >= timeForLevelUp; i++) {
            level += 1;
            levelUpExp += 1;
            experience = experience - timeForLevelUp;
        }
    }

    /**
     * EFFECTS: returns the amount of experience needed for the hero to reach a new level
     */
    public int progressTowardsNextLevel() {
        progressTN = timeForLevelUp - experience;
        return progressTN;
    }

    /**
     * REQUIRES: levelUpExp isn't <= 0
     * MODIFIES: this
     * EFFECTS: level up the hero and add new stats to the hero
     */
    public void levelUp(Integer statSelection) {
        switch (statSelection) {
            case 1:
                levelUpHealth();
                break;
            case 2:
                levelUpAttack();
                break;
            case 3:
                levelUpDefense();
                break;
        }

        levelUpExp = levelUpExp - 1;
    }

    /**
     * REQUIRES: called item is already in inventory
     * MODIFIES: this, Inventory
     * EFFECTS: Uses the item and reflects changes on hero depending on item type.
     * if item type is Potion then it is consumed and removed from the inventory.
     */
    public void useItem(Item useThis, Inventory myInvtry) {
        String s = useThis.getItemType();
        switch (s) {
            case "Weapon":
                attack += useThis.buff;
                useThis.buff = 0;
                break;
            case "Armor":
                defense += useThis.buff;
                useThis.buff = 0;
                break;
            case "Potion":
                health += useThis.buff;
                myInvtry.removeItem(useThis);
        }
    }

    /**
     * EFFECTS: Creates a list of hero stats and returns these stats as a status
     */
    public ArrayList getStatus() {
        status = new ArrayList<Integer>();

        status.add(getHealth());
        status.add(getAttack());
        status.add(getDefense());
        status.add(getLevel());
        status.add(getExperience());

        return status;
    }

    public void levelUpHealth() {
        health++;
    }

    public void levelUpAttack() {
        attack++;
    }

    public void levelUpDefense() {
        defense++;
    }

    public void changeHealth(Integer hp) {
        health = hp;
    }

    public void changeDefense(Integer def) {
        defense = def;
    }

    public void changeAttack(Integer atk) {
        attack = atk;
    }

    public void changeLevel(Integer lvl) {
        level = lvl;
    }

    public void changeExperience(Integer exp) {
        experience = exp;
    }

    public void changeLevelUpExp(Integer lvlupxp) {
        levelUpExp = lvlupxp;
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

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelUpExp() {
        return levelUpExp;
    }

    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        JSONObject jsonTotal = new JSONObject();

        json.put("Health", health);
        json.put("Attack", attack);
        json.put("Defense", defense);
        json.put("Level", level);
        json.put("Experience", experience);
        json.put("LevelUpExperience", levelUpExp);

        JSONArray jsonInventory = new JSONArray();

        for (int i = 0; i < this.size(); i++) {
            jsonInventory.put(this.getItem(i).returnItem(this.getItem(i)));
        }

        jsonTotal.put("Hero Stats", json);
        jsonTotal.put("Hero Inventory", jsonInventory);

        return jsonTotal;
    }
}
