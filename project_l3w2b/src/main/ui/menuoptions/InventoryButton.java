package ui.menuoptions;

import model.Hero;
import ui.sound.Sound;
import ui.TowerRPG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class InventoryButton extends InventoryMenu implements ActionListener {

    private Hero hero;
    private JComboBox inventoryM;
    private Sound musicObj;

    public InventoryButton(TowerRPG game, JComponent parent, Hero hero, JComboBox jcomb) {
        super(game, parent, hero);
        this.hero = hero;
        inventoryM = jcomb;
    }

    /**
     * MODIFIES: TowerRPG
     * EFFECTS: uses the item that is selected on the combo-box menu and plays
     * use item sound
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String filepath = "./data/itemUsed.wav";

        musicObj = new Sound(filepath);


        for (int i = 0; i < hero.size(); i++) {
            if (hero.getItem(i).getName().equals(inventoryM.getSelectedItem())) {
                musicObj.playSound();
                JOptionPane.showMessageDialog(null, "Used "
                        + hero.getItem(i).getName());
                hero.useItem(hero.getItem(i), hero);
            }
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a Use Item button and invokes addToParent() on the
     * parent passed to this method
     */
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Use Item");
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
}

