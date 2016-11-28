package monopoly.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import monopoly.BoardController;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class CellGUI extends JPanel {
    private static final long serialVersionUID = 2752137388247147409L;
    private final Cell cell;
    private JLabel infoLabel;
    private final JLabel[] playersLabel = new JLabel[BoardController.MAX_PLAYER];
    
    public CellGUI(Cell cell) {
        this.cell = cell;
        super.setLayout(new OverlayLayout(this));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 4, 0, 25));
        playerPanel.setOpaque(false);
        createPlayerLabels(playerPanel);
        super.add(playerPanel);
        super.setPreferredSize(new Dimension(100,100));
        addCellInfo();
        super.setToolTipText(InfoFormatter.cellToolTip(cell));
        super.doLayout();
    }
	
    private void addCellInfo() {
        infoLabel = new JLabel();
        displayInfo();
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 1));
        infoPanel.add(infoLabel);
        add(infoPanel);
    }
	
    public void addPlayer(MainController mainController, int index) {
        Player player = mainController.getPlayer(index);
        playersLabel[index].setOpaque(true);
        playersLabel[index].setBackground(player.getPlayerColor());
    }

    private void createPlayerLabels(JPanel playerPanel) {
        for (int i = 0; i < BoardController.MAX_PLAYER; i++) {
            playersLabel[i] = new JLabel();
            playerPanel.add(playersLabel[i]);
            
        }
    }

    public void displayInfo() {
        infoLabel.setText(InfoFormatter.cellInfo(cell));
        this.setToolTipText(InfoFormatter.cellToolTip(cell));
        this.invalidate();
        this.repaint();
    }

    public Cell getCell() {
        return cell;
    }

    public void removePlayer(int index) {
        playersLabel[index].setText("");
        playersLabel[index].setOpaque(false);
        this.repaint();
    }
}
