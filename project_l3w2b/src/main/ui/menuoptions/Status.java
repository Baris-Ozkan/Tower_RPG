package ui.menuoptions;

import model.Hero;
import ui.sound.Sound;
import ui.MenuOption;
import ui.TowerRPG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Status extends MenuOption implements ActionListener {

    Hero hero;
    Sound musicObj;

    public Status(TowerRPG game, JComponent parent, Hero hero) {
        super(game, parent);
        this.hero = hero;
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a Show Status button and invokes addToParent() on the
     *          parent passed to this method
     */
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Show Status");
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
     * EFFECTS: displays a pop-up windows with hero stat info and play sound effect show status
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String filepath = "./data/showStatus.wav";
        musicObj = new Sound(filepath);

        game.statusInfo();

        musicObj.playSound();
        JOptionPane.showMessageDialog(null, "Hero"
                + "\nHealth: " + hero.getStatus().get(0)
                + "\nAttack: " + hero.getStatus().get(1)
                + "\nDefense: " + hero.getStatus().get(2)
                + "\nLevel: " + hero.getStatus().get(3)
                + "\nExperience: " + hero.getStatus().get(4));
    }
}
