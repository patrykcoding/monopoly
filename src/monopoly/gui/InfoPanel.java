package monopoly.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import monopoly.MainController;

public class InfoPanel extends JPanel {
    public void displayInfo(MainController mainCtl) {
        setLayout(new GridLayout(1, mainCtl.getNumberOfPlayers()));
        for (int i = 0; i < mainCtl.getNumberOfPlayers(); i++){
            PlayerPanel panel = new PlayerPanel(mainCtl, mainCtl.getPlayer(i));
            add(panel);
            panel.displayInfo();
        }
    }
}
