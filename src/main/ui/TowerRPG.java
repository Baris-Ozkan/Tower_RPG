package ui;

import model.*;
import persistence.*;
import ui.sound.Sound;
import ui.menuoptions.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

// TowerRPG application
// GUI method designs were inspired by the project SimpleDrawingPlayer

public class TowerRPG extends JFrame {
    private int mainWidth = 1000;
    private int mainHeight = 700;
    private static final String JSON_STORE = "./data/TowerRPG.json";
    private Scanner input;
    private Hero gameHero;
    int decision;
    int heroDefault = 6;
    int increaseDifficulty1;
    int increaseDifficulty2;
    int increaseDifficulty3;
    int increaseDifficulty4;
    int chance;
    int randomI;
    int hp = 0;
    int atk = 0;
    int def = 0;
    boolean confirm = false;
    Random r1;
    Random r2;
    Random r3;
    Random r4;
    boolean keepGoing = true;
    Item sword2;
    Item armor1;
    Item armor2;
    Item potion2;
    public static Inventory starterItems;
    Monster chosenMonster;
    Monster monster1;
    Monster monster2;
    Monster monster3;
    Monster monster4;
    MonsterList floorMonsterList;
    Item floorItem;
    Floor towerFloor;
    String command = null;
    Sound musicObj;

    // method below was inspired by the TellerApp method in TellerApp file
    // EFFECTS: runs the TowerRPG application
    public TowerRPG() {
        runTowerRPG();
    }

    // code below was inspired by the runTeller method in TellerApp file
    // MODIFIES:this
    // EFFECTS: processes user input
    private void runTowerRPG() {

        startMenu();
        initiateStarterItems();

        initializeGUI();                  // Comment out to play text-based only, stable with full features

        command = input.next();
        if (command.equals("N")) {
            loadSave();
            menuLoop();
        } else if (command.equals("Y")) {

            newHero();
            menuLoop();
        } else {
            System.out.println("Goodbye...");
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: create the Sound object and loop given file
     */
    private void loopSound() {
        String filepath = "./data/Tower16bit.wav";

        musicObj = new Sound(filepath);
        musicObj.playSoundContinuous();
    }

    /**
     * MODIFIES: this
     * EFFECTS: initiates creating a new hero
     */
//    private void createHeroGUI() {
//        initiateStarterItems();
//
//        JFrame frame = new JFrame("New Hero");
//        frame.setMinimumSize(new Dimension(450,300));
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//
//        JLabel label = new JLabel();
//        label.setText("Current Inventory");
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(0,1));
//        JPanel panel2 = new JPanel();
//
//
//        for (int i = 0; i < starterItems.size(); i++) {
//            JCheckBox ckbox = new JCheckBox(starterItems.getItem(i).getName());
//            panel.add(ckbox);
//        }
//
//
//        Button button = new Button("Take Item");
//        panel.add(button, BorderLayout.SOUTH);
//        panel2.add(label);
//        frame.add(panel, BorderLayout.CENTER);
//        frame.add(panel2, BorderLayout.EAST);
//
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a popup window to ask the user to load a previous save or not
     */
    private void loadHeroGUI() {

        int a = JOptionPane.showConfirmDialog(this,
                "Do you want to load a previously saved Hero?",
                "Load Hero?", JOptionPane.YES_NO_OPTION);

        if (a == JOptionPane.YES_OPTION) {
            loadSave();
            JOptionPane.showMessageDialog(this, "Successfully Loaded Latest Save");
        } else if (a == JOptionPane.NO_OPTION) {
            //
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates the main frame menu options
     */
    private void createMenuGUI() {
        this.setTitle("TowerRPG");
        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(0,1));
        menuArea.setSize(new Dimension(0,0));
        add(menuArea, BorderLayout.CENTER);

        CombatMenu ex = new CombatMenu(this, menuArea);
        SaveProgress ex1 = new SaveProgress(this, menuArea);
        FloorItem ex2 = new FloorItem(this, menuArea, towerFloor, gameHero);
        Status ex3 = new Status(this, menuArea, gameHero);
        InventoryMenu ex4 = new InventoryMenu(this, menuArea, gameHero);
        LevelUpHero ex5 = new LevelUpHero(this, menuArea);
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the GUI components of the game
     */
    private void initializeGUI() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(mainWidth, mainHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadHeroGUI();
        loopSound();

        createMenuGUI();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS: operates the menu loop of TowerRPG
     */
    private void menuLoop() {
        while (keepGoing) {
            floorInitiate();
            floorMenu();
            command = input.next();
            floorMenuSelect(command);
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: begins new hero creation procedures
     */
    private void newHero() {
        gameHero.changeHealth(6);
        gameHero.changeDefense(6);
        gameHero.changeAttack(6);
        createHero();
        initiateStarterItems();
        chooseStarterItems();
        towerItems();
    }

    /**
     * MODIFIES: this
     * EFFECTS: loads a previously saved Hero and Hero Progress if there is one
     */
    private void loadSave() {
        JsonReader reader = new JsonReader(JSON_STORE);

        try {
            gameHero = reader.readHero();
            towerFloor = reader.readFloor();

            System.out.println("\nSuccessfully loaded previous save");
        } catch (IOException e) {
            System.out.println("\nUnable to read from file " + JSON_STORE);
        }

    }

    /**
     * MODIFIES: this
     * EFFECTS: Displays the items located on the floor and allows the user to acquire them if they wish so
     */
    private void acquireItems() {
        String s;

        if (floorItemAndChance()) {
            System.out.println("This item was found on this floor: ");
            System.out.println("\n" + floorItem.getName());
            System.out.println("\nDo you wish to acquire it?");
            System.out.println("\tYes - Y " + "\tNo - N");

            s = input.next();

            if (s.equals("Y")) {
                System.out.println("\tAcquiring item...");
                for (int i = 0; i < gameHero.size(); i++) {
                    if (gameHero.getItem(i) == floorItem) {
                        gameHero.removeItem(gameHero.getItem(i));
                    }
                }
                gameHero.addItem(floorItem);
            } else {
                System.out.println("\tAlright then...");
            }
        } else {
            System.out.println("No Items were found on this floor...");
        }
    }

    // EFFECTS: Print floor clear screen and adjusts floor and difficutly tokens
    private void floorClear() {
        if (gameHero.getHealth() > 0 && keepGoing) {
            System.out.println("\nCongratulations you have cleared the floor");
            System.out.println("\nHero Status: ");
            statusInfo();
            towerFloor.floorCleared();
        }
    }

    // EFFECTS: Print combat loss screen
    private void combatLossInfo() {
        System.out.println("\nBattle lost. The hero has fallen...");
        System.out.println("\nGame Over");
        statusInfo();
        System.out.println("You have survived until floor " + towerFloor.getFloorNumber());
    }

    // EFFECTS: Print combat win screen
    private void combatWinInfo() {
        System.out.println("\nBattle Won!");
        System.out.println("\nNew hero status: ");
        statusInfo();
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the combat menu of the floor
     */
    private void combatMenu() {
        String s;

        System.out.println("\nAre you ready to fight the monsters of this floor? (Type 1, 2 or 3)");
        System.out.println("\tYes - Y " + "\tNo - N");

        s = input.next();

        if (s.equals("Y")) {
            while (floorMonsterList.monsterListSize() > 0 && keepGoing) {
                chooseTarget();

                if (gameHero.combatWith(chosenMonster)) {
                    combatWinInfo();
                    floorMonsterList.removeMonster(chosenMonster);
                } else {
                    combatLossInfo();
                    keepGoing = false;
                }
            }
            floorClear();
        } else {
            System.out.println("\nBetter get prepared");
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: displays the monsters in the current floor and
     * allows the player to choose a target to combat with
     */
    private void chooseTarget() {
        System.out.println("Which monster do you want to battle first?" + "\n");
        for (int i = 1; i <= floorMonsterList.monsterListSize(); i++) {
            System.out.print("Monster" + i + " ");
        }
        decision = input.nextInt();
        chosenMonster = floorMonsterList.getMonster(decision - 1);

        System.out.println("\nBeginning combat with Monster" + decision);
    }

    /**
     * MODIFIES: this
     * EFFECTS: uses an item from the inventory
     */
    private void useItem() {
        int item;

        System.out.println("\nEnter the index of the item you want to use:");
        item = input.nextInt();
        for (int i = 0; i < gameHero.size(); i++) {
            if (item == i) {
                System.out.println("\nUsing item...");
                gameHero.useItem(gameHero.getItem(i), gameHero);
            }
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: saves current progress of TowerRPG and Hero
     */
    public void saveProgress() {
        try {
            JsonWriter writer = new JsonWriter(JSON_STORE);
            writer.open();
            writer.write(gameHero, towerFloor);
            writer.close();

            System.out.println("\nProgress saved");
        } catch (FileNotFoundException e) {
            System.out.println("File not found at" + JSON_STORE);
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: processes user command on floor menu selection
     */
    private void floorMenuSelect(String command) {
        if (command.equals("c")) {
            combatMenu();
        } else if (command.equals("v")) {
            saveProgress();
        } else if (command.equals("f")) {
            acquireItems();
        } else if (command.equals("s")) {
            statusInfo();
        } else if (command.equals("i")) {
            displayInventory();
        } else if (command.equals("u")) {
            useItem();
        } else if (command.equals("e")) {
            expNeededForNextLevel();
        } else if (command.equals("l")) {
            levelUpMenu();
        } else if (command.equals("q")) {
            System.out.println("Quitting TowerRPG, Goodbye...");
            keepGoing = false;
        }
    }

    /**
     * EFFECTS: displays the options for the floor
     */
    private void floorMenu() {
        System.out.println("\nWelcome to floor " + towerFloor.getFloorNumber());
        System.out.println("\n Choose an Option:");
        System.out.println("\tv -> save progress so far");
        System.out.println("\tc -> combat monsters");
        System.out.println("\tf -> find items in the floor");
        System.out.println("\ts -> show status");
        System.out.println("\ti -> show inventory");
        System.out.println("\tu -> use an item");
        System.out.println("\te -> show experience needed for next level");
        System.out.println("\tl -> level up Hero");
        System.out.println("\tq -> quit");
    }

    /**
     * MODIFIES: Hero
     * EFFECTS: allows player to level up the Hero as many times as the Hero's level Up points
     */
    private void levelUpMenu() {
        if (!(gameHero.getLevelUpExp() == 0)) {
            System.out.println("You can level up the hero " + gameHero.getLevelUpExp() + " times");
            while (gameHero.getLevelUpExp() > 0) {
                statusInfo();
                System.out.println("Select the stat to level up, Health - 1, Attack - 2, Defense - 3");
                levelUpSelection();
            }
        } else {
            System.out.println("You don't have enough experience to level up");
        }
    }

    /**
     * MODIFIES: Hero
     * EFFECTS: gets player input to level up the desired stat by 1
     */
    private void levelUpSelection() {
        int statSelection;
        statSelection = input.nextInt();

        gameHero.levelUp(statSelection);
    }

    /**
     * EFFECTS: Prints the current inventory of hero
     */
    private void displayInventory() {
        System.out.println("Inventory: ");
        for (int i = 0; i < gameHero.size(); i++) {
            System.out.println((gameHero.getItem(i)).getName() + " ");
        }
    }

    /**
     * EFFECTS: Prints the amount of experience needed for the hero to reach a new level
     */
    private void expNeededForNextLevel() {
        System.out.println("\nExperience needed for next level is " + gameHero.progressTowardsNextLevel());
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the floor with monsters and items based on current progress on the tower
     */
    private void floorInitiate() {
        difficultyModifier();
        floorMonsterList = new MonsterList();

        monster1 = new Monster(6 + increaseDifficulty1, 5 + increaseDifficulty1, 4 + increaseDifficulty1);
        monster2 = new Monster(6 + increaseDifficulty2, 5 + increaseDifficulty2, 4 + increaseDifficulty2);
        monster3 = new Monster(6 + increaseDifficulty3, 5 + increaseDifficulty3, 4 + increaseDifficulty3);
        monster4 = new Monster(6 + increaseDifficulty4, 5 + increaseDifficulty4, 4 + increaseDifficulty4);

        if (towerFloor.getFloorDifficultyModifier() <= 3) {
            floorMonsterList.addMonster(monster1);
            floorMonsterList.addMonster(monster2);

        } else if (towerFloor.getFloorDifficultyModifier() <= 6) {
            floorMonsterList.addMonster(monster1);
            floorMonsterList.addMonster(monster2);
            floorMonsterList.addMonster(monster3);

        } else {
            floorMonsterList.addMonster(monster1);
            floorMonsterList.addMonster(monster2);
            floorMonsterList.addMonster(monster3);
            floorMonsterList.addMonster(monster4);
        }
        floorChance();
    }

    /**
     * MODIFIES: this
     * EFFECTS: modifies difficulty tokens according to Hero's progress so far
     */
    private void difficultyModifier() {
        r1 = new Random();
        r2 = new Random();
        r3 = new Random();
        r4 = new Random();
        increaseDifficulty1 = r1.nextInt(6 + towerFloor.getFloorDifficultyModifier());
        increaseDifficulty2 = r2.nextInt(6 + towerFloor.getFloorDifficultyModifier());
        increaseDifficulty3 = r3.nextInt(6 + towerFloor.getFloorDifficultyModifier());
        increaseDifficulty4 = r4.nextInt(6 + towerFloor.getFloorDifficultyModifier());
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the chance of an item for the floor
     */
    public void floorChance() {
        if (towerFloor.size() > 0) {
            Random r = new Random();
            chance = r.nextInt(101);
            Random randomItem = new Random();
            randomI = randomItem.nextInt(towerFloor.size());
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes current floor item based on a chance of 30%, if successful
     * a random item from the towerInventory is determined as the floor item and returns true
     * otherwise no item is determined and returns false
     */
    public boolean floorItemAndChance() {
        if (towerFloor.size() > 0) {
            if (chance <= 50) {
                floorItem = towerFloor.getItem(randomI);
                towerFloor.removeItem(towerFloor.getItem(randomI));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes towerItems
     */
    private void towerItems() {

        Item item1 = new Item("Robe of Avalon", "Armor", 7);
        Item item2 = new Item("Sword of Zondar", "Weapon", 5);;
        Item item3 = new Item("Legendary Healing Potion", "Potion", 10);
        Item item4 = new Item("Greater Healing Potion", "Potion", 4);
        Item item5 = new Item("Frostmourne", "Weapon", 15);
        Item item6 = new Item("Sulfuras", "Weapon", 15);
        Item item7 = new Item("King's Plate", "Armor", 10);
        Item item8 = new Item("Greater Healing Potion", "Potion", 4);
        Item item9 = new Item("Greater Healing Potion", "Potion", 4);
        Item item10 = new Item("Indomitable Will", "Armor", 15);

        towerFloor.addItem(item1);
        towerFloor.addItem(item2);
        towerFloor.addItem(item3);
        towerFloor.addItem(item4);
        towerFloor.addItem(item5);
        towerFloor.addItem(item6);
        towerFloor.addItem(item7);
        towerFloor.addItem(item8);
        towerFloor.addItem(item9);
        towerFloor.addItem(item10);
    }

    /**
     * EFFECTS: Prints the status of hero
     */
    public void statusInfo() {
        System.out.print("\nHero" + "\nHealth: " + gameHero.getStatus().get(0));
        System.out.print(" Attack: " + gameHero.getStatus().get(1));
        System.out.print(" Defense: " + gameHero.getStatus().get(2));
        System.out.print(" Level: " + gameHero.getStatus().get(3));
        System.out.print(" Experience: " + gameHero.getStatus().get(4));
        System.out.println("");
    }

    /**
     * MODIFIES: this
     * EFFECTS: initiates starter items
     */
    private void initiateStarterItems() {
        sword2 = new Item("Great Sword","Weapon", 3);
        armor1 = new Item("Leather Mail","Armor", 1);
        armor2 = new Item("Chain Mail","Armor", 2);
        potion2 = new Item("Lesser Healing Potion","Potion", 2);

        starterItems = new Inventory();

        starterItems.addItem(sword2);
        starterItems.addItem(armor1);
        starterItems.addItem(armor2);
        starterItems.addItem(potion2);
    }

    /**
     * MODIFIES: this
     * EFFECTS: adjusts inventories according to user choice
     */
    private void itemSelection() {
        String selectedItem;

        System.out.println("\nItem Choice: ");
        selectedItem = input.nextLine();

        switch (selectedItem) {
            case "Great Sword":
                gameHero.addItem(sword2);
                starterItems.removeItem(sword2);
                break;
            case "Leather Mail":
                gameHero.addItem(armor1);
                starterItems.removeItem(armor1);
                break;
            case "Chain Mail":
                gameHero.addItem(armor2);
                starterItems.removeItem(armor2);
                break;
            case "Lesser Healing Potion":
                gameHero.addItem(potion2);
                starterItems.removeItem(potion2);
                break;
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: starts the process for the selection of 2 starter items among the starter items inventory
     */
    private void chooseStarterItems() {
        int x = 0;
        boolean confirm = false;
        System.out.println("Please select 2 starter items to put into your inventory");

        while (x <= 2) {
            System.out.println("\nRemaining Items: ");
            for (int i = 0; i < starterItems.size(); i++) {
                System.out.print((starterItems.getItem(i)).getName() + "   ");
            }

            itemSelection();
            x += 1;
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the hero character;
     */
    private void initiateChar() {
        gameHero = new Hero();
        input = new Scanner(System.in);
    }

    /**
     * EFFECTS: displays the starting menu for players
     */
    private void startMenu() {
        initiateChar();
        System.out.println("Welcome to TowerRPG");
        System.out.println("\nCreate a new hero?");
        System.out.println("\nYes - Y \nLoad a Previously Saved Hero - N");

        towerFloor = new Floor();
    }

    /**
     * MODIFIES: this
     * EFFECTS: Allows user to create a new hero, if final results are not to user's liking
     *  they have a chance to re-do
     */
    private void createHero() {

        while (!confirm) {
            System.out.println("You have 5 points available, assign them to your stats");
            System.out.println("\nDefault Stats: " + "\nHealth: " + heroDefault + " Attack: " + heroDefault
                    + " Defense " + heroDefault);
            System.out.print("Points into Health = ");
            hp = input.nextInt();
            System.out.print("Points into Attack = ");
            atk = input.nextInt();
            System.out.print("Points into Defense = ");
            def = input.nextInt();

            System.out.println("\nFinal Stats: " + "\nHealth: " + (gameHero.getHealth() + hp) + " Attack: "
                    + (gameHero.getAttack() + atk) + " Defense " + (gameHero.getDefense() + def));

            System.out.println("\nDo you want to confirm this Hero?");
            System.out.println("\nYes - Y,     No - N");

            if (input.next().equals("Y")) {
                gameHero.createHero(hp, atk, def);
                confirm = true;
            } else {
                hp = 0;
                atk = 0;
                def = 0;
            }
        }
    }

    /**
     * Unfinished alternative hero creation method. Might be used later.

    private void createHeroAlt() {
        System.out.println("You have 5 points available allocate to your stats");
        int hp = 0;
        int atk = 0;
        int def = 0;

        for (int i = 0; i <= 5; i++) {
            System.out.println("\nHero Health: " + (heroDefault + hp) + " Attack: " + (heroDefault + atk)
            + " Defense: " + (heroDefault + def));
            System.out.println("\nWhich stat do you want to improve?");
            switch (input.next()) {
                case "Health":
                    hp += 1;
                    break;
                case "Attack" :
                    atk += 1;
                    break;
                case "Defense" :
                    def += 1;
                    break;
            }
        }
    } */
}

