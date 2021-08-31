package ui.menuoptions;

import ui.MenuOption;
import ui.TowerRPG;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveProgress extends MenuOption implements ActionListener {

    public SaveProgress(TowerRPG game, JComponent parent) {
        super(game, parent);
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a Save Progress button and invokes addToParent() on the
     *          parent passed to this method
     */
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Progress");
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
     * MODIFIES: TowerRPG
     * EFFECTS: initiates the saveProgress method to save progress
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.saveProgress();

        JOptionPane.showMessageDialog(null, "\nProgress Saved");
    }
}
