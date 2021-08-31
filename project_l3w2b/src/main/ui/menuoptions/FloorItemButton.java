package ui.menuoptions;

import model.Floor;
import model.Hero;
import ui.TowerRPG;
import ui.sound.Sound;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloorItemButton extends FloorItem implements ActionListener {

    JTextField takeThis;
    Floor thisFloor;
    private Sound musicObj;

    public FloorItemButton(TowerRPG game, JComponent parent, Floor floor, Hero hero, JTextField itemInput) {
        super(game, parent, floor, hero);
        this.takeThis = itemInput;
        thisFloor = floor;
    }


    /**
     * MODIFIES: TowerRPG
     * EFFECTS: takes the item typed into the text field and adds it to the inventory and plays
     * use take sound
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String filepath = "./data/takeItem.wav";
        musicObj = new Sound(filepath);

        for (int i = 0; i < thisFloor.size(); i++) {
            if (thisFloor.getItem(i).getName().equals(takeThis.getText())) {

                musicObj.playSound();
                hero.addItem(thisFloor.getItem(i));
                JOptionPane.showMessageDialog(null, "Took "
                        + thisFloor.getItem(i).getName());
                thisFloor.removeItem(thisFloor.getItem(i));
            }
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a Take Item button and invokes addToParent() on the
     * parent passed to this method
     */
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Take Item");
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

