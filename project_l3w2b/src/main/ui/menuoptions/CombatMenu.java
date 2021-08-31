package ui.menuoptions;

import ui.MenuOption;
import ui.TowerRPG;

import javax.swing.*;

public class CombatMenu extends MenuOption {


    public CombatMenu(TowerRPG game, JComponent parent) {
        super(game, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Combat Monsters");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    protected void addListener() {

    }
}
