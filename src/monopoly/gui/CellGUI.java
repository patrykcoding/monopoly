package monopoly.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;
import monopoly.BoardController;

public class CellGUI extends JPanel {
    private static final long serialVersionUID = 2752137388247147409L;
    private final Cell cell;
    private JLabel lblInfo;
    private final JLabel[] lblPlayers = new JLabel[BoardController.MAX_PLAYER];
    
    public CellGUI(Cell cell) {
        this.cell = cell;
        super.setLayout(new OverlayLayout(this));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel pnlPlayer = new JPanel();
        pnlPlayer.setLayout(new GridLayout(2, 4, 0, 25));
        pnlPlayer.setOpaque(false);
        createPlayerLabels(pnlPlayer);
        super.add(pnlPlayer);
        super.setPreferredSize(new Dimension(100,100));
        addCellInfo();
        super.setToolTipText(InfoFormatter.cellToolTip(cell));
        super.doLayout();
    }
	
    private void addCellInfo() {
        lblInfo = new JLabel();
        displayInfo();
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new GridLayout(1, 1));
        pnlInfo.add(lblInfo);
        add(pnlInfo);
    }
	
    public void addPlayer(MainController mainCtl, int index) {
        Player player = mainCtl.getPlayer(index);
        lblPlayers[index].setOpaque(true);
        lblPlayers[index].setBackground(player.getPlayerColor());
    }

    private void createPlayerLabels(JPanel pnlPlayer) {
        for (int i = 0; i < BoardController.MAX_PLAYER; i++) {
            lblPlayers[i] = new JLabel();
            pnlPlayer.add(lblPlayers[i]);
            
        }
    }

    public void displayInfo() {
        lblInfo.setText(InfoFormatter.cellInfo(cell));
        this.setToolTipText(InfoFormatter.cellToolTip(cell));
        this.invalidate();
        this.repaint();
    }

    public Cell getCell() {
        return cell;
    }

    public void removePlayer(int index) {
        lblPlayers[index].setText("");
        lblPlayers[index].setOpaque(false);
        this.repaint();
    }
}
