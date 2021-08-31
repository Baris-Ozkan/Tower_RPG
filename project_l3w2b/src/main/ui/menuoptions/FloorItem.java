package ui.menuoptions;

import model.Floor;
import model.Hero;
import ui.MenuOption;
import ui.TowerRPG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloorItem extends MenuOption implements ActionListener {

    Floor floorItems;
    JFrame floorItemF = new JFrame("Find an Item");
    JPanel floorItemP;
    JLabel floorItemL;
    JTextArea itemsArea;
    JTextField itemEnter;
    JPanel inputPanel;
    Hero hero;


    public FloorItem(TowerRPG game, JComponent parent, Floor floor, Hero hero) {
        super(game, parent);
        floorItems = floor;
        this.hero = hero;
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a Floor Item button and invokes addToParent() on the
     *          parent passed to this method
     */
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Find an Item");
        button = customizeButton(button);
        addToParent(parent);
    }

    /**
     * MODIFIES: this
     * EFFECTS:  constructs an ActionListener which is then added to this object's JButton
     */
    @Override
    protected void addListener() {
        button.addActionListener(this);
    }

    /**
     * MODIFIES: this
     * EFFECTS: displays the set-up Find an Item Frame
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        initializeFrame();
        initializeTowerItems();
        initializeInputText();


        floorItemP.add(itemsArea);
        floorItemP.add(floorItemL);
        floorItemP.add(itemEnter);
        inputPanel.add(itemEnter);

        FloorItemButton takeItem = new FloorItemButton(game, inputPanel, floorItems, hero, itemEnter);

        floorItemF.add(floorItemP, BorderLayout.CENTER);
        floorItemF.add(inputPanel, BorderLayout.SOUTH);

        floorItemF.setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the input text field that takes a string from user
     */
    private void initializeInputText() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,1));

        itemEnter = new JTextField();


    }

    /**
     * MODIFIES: this
     * EFFECTS: gets the current items in Tower and puts them
     * into a text field
     */
    private void initializeTowerItems() {
        itemsArea = new JTextArea("Tower Items:\n" + "\n", 20, 20);

        for (int i = 0; i < floorItems.size(); i++) {
            itemsArea.append(floorItems.getItem(i).getName() + "\n");
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the FindAnItem frame and sets it up
     */
    private void initializeFrame() {
        floorItemF.setMinimumSize(new Dimension(600,500));
        floorItemF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        floorItemF.setLocationRelativeTo(null);
        floorItemP = new JPanel();
        floorItemP.setLayout(new GridLayout(0,1));
        floorItemL = new JLabel();
        floorItemL.setText("Type the item to take");
    }
}
