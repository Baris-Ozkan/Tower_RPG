package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import ui.TowerRPG;

// code of this class were inspired by the Tool class in the project SimpleDrawingPlayer
public abstract class MenuOption {
    protected JButton button;
    protected TowerRPG game;
    private boolean active;

    public MenuOption(TowerRPG game, JComponent parent) {
        this.game = game;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);

        return button;
    }

    protected abstract void createButton(JComponent parent);

    protected abstract void addListener();

    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
