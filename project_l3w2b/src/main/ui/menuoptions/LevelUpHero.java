package ui.menuoptions;

import ui.MenuOption;
import ui.TowerRPG;

import javax.swing.*;

public class LevelUpHero extends MenuOption {

    public LevelUpHero(TowerRPG game, JComponent parent) {
        super(game, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Level Up Hero");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    protected void addListener() {

    }
}
