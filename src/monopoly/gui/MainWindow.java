package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import monopoly.*;
import monopoly.Cell;
import monopoly.GameBoard;
import monopoly.GameMaster;
import monopoly.Player;

public class MainWindow extends JFrame implements MonopolyGUI {
    
    private final GameMaster master;
    JPanel eastPanel = new JPanel();
    ArrayList guiCells = new ArrayList();

    JPanel northPanel = new JPanel();
    PlayerPanel[] playerPanels;
    JPanel southPanel = new JPanel();
    JPanel westPanel = new JPanel();

    public MainWindow(GameMaster master) {
        this.master = master;
        
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container c = super.getContentPane();
        //setSize(800, 600);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        super.setSize(d);
        c.add(northPanel, BorderLayout.NORTH);
        c.add(southPanel, BorderLayout.SOUTH);
        c.add(eastPanel, BorderLayout.EAST);
        c.add(westPanel, BorderLayout.WEST);

        super.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }
        });
    }

    private void addCells(JPanel panel, List cells) {
        for (Object cell1 : cells) {
            GUICell cell = new GUICell((Cell) cell1);
            panel.add(cell);
            guiCells.add(cell);
        }
    }

    private void buildPlayerPanels() {
        JPanel infoPanel = new JPanel();
        int players = master.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[master.getNumberOfPlayers()];
        for (int i = 0; i < master.getNumberOfPlayers(); i++){
            playerPanels[i] = new PlayerPanel(master, master.getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }

    @Override
    public void enableEndTurnBtn(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);
    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    @Override
    public int[] getDiceRoll() {
        TestDiceRollDialog dialog = new TestDiceRollDialog(this);
        dialog.setVisible(true);
        return dialog.getDiceRoll();
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }
	
    @Override
    public void movePlayer(int index, int from, int to) {
        GUICell fromCell = queryCell(from);
        GUICell toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(master, index);
    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        GUIRespondDialog dialog = new GUIRespondDialog();
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public TradeDialog openTradeDialog() {
        GUITradeDialog dialog = new GUITradeDialog(master, this);
        dialog.setVisible(true);
        return dialog;
    }
	
    private GUICell queryCell(int index) {
        Cell cell = master.getGameBoard().getCell(index);
            for (Object guiCell1 : guiCells) {
                GUICell guiCell = (GUICell) guiCell1;
                if (guiCell.getCell() == cell) {
                    return guiCell;
                }
            }
        return null;
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
        int currentPlayerIndex = master.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        playerPanels[index].setTradeEnabled(b);
    }
	
    public void setupGameBoard(GameBoard board) {
        Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
        northPanel.setLayout(new GridLayout(1, dimension.width + 2));
        southPanel.setLayout(new GridLayout(1, dimension.width + 2));
        westPanel.setLayout(new GridLayout(dimension.height, 1));
        eastPanel.setLayout(new GridLayout(dimension.height, 1));
        addCells(northPanel, GameBoardUtil.getNorthCells(board));
        addCells(southPanel, GameBoardUtil.getSouthCells(board));
        addCells(eastPanel, GameBoardUtil.getEastCells(board));
        addCells(westPanel, GameBoardUtil.getWestCells(board));
        buildPlayerPanels();
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        BuyHouseDialog dialog = new BuyHouseDialog(master, currentPlayer);
        dialog.setVisible(true);
    }

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

        @Override
    public int showUtilDiceRoll() {
        return UtilDiceRoll.showDialog(master);
    }

        @Override
    public void startGame() {
        int numberOfPlayers = master.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
                movePlayer(i, 0, 0);
        }
    }

        @Override
    public void update() {
        for (PlayerPanel playerPanel : playerPanels) {
            playerPanel.displayInfo();
        }
        for (Object guiCell : guiCells) {
            GUICell cell = (GUICell) guiCell;
            cell.displayInfo();
        }
    }
}
