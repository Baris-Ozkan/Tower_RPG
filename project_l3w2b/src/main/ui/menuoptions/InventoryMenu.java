package ui.menuoptions;

import model.Hero;
import model.Inventory;
import ui.MenuOption;
import ui.TowerRPG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryMenu extends MenuOption implements ActionListener {
    JFrame mainInvent = new JFrame("Inventory");
    JPanel inventoryPanel;
    JLabel selectLabel;
    private JComboBox inventoryCombo;
    Hero hero;

    public InventoryMenu(TowerRPG game, JComponent parent, Hero hero) {
        super(game, parent);
        this.hero = hero;
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a Inventory Menu button and invokes addToParent() on the
     *          parent passed to this method
     */
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Inventory Menu");
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
     * EFFECTS: displays the set-up InventoryMenu
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        initializeItems();
        initializeFrame();

        System.out.println(inventoryCombo.getItemCount());

        inventoryPanel.add(inventoryCombo);
        inventoryPanel.add(selectLabel);
        InventoryButton itemUse = new InventoryButton(game, inventoryPanel, hero, inventoryCombo);

        mainInvent.add(inventoryPanel, BorderLayout.CENTER);

        mainInvent.setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS: gets the current items in inventory and puts them
     * into a combo-box menu
     */
    private void initializeItems() {
        inventoryCombo = new JComboBox();

        for (int i = 0; i < hero.size(); i++) {
            inventoryCombo.addItem(hero.getItem(i).getName());
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the InventoryMenu frame and sets it up
     */
    private void initializeFrame() {
        mainInvent.setMinimumSize(new Dimension(450,300));
        mainInvent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainInvent.setLocationRelativeTo(null);
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(0,1));
        selectLabel = new JLabel();
        selectLabel.setText("Select an item to use");
    }
}
