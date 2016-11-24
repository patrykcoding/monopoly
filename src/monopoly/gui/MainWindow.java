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

import monopoly.Cell;
import monopoly.Dice;
import monopoly.GameBoard;
import monopoly.MainController;
import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class MainWindow extends JFrame implements MonopolyGUI {
    private static final long serialVersionUID = 3146365872410925008L;
    private final MainController mainCtl;
    private final JPanel eastPanel = new JPanel();
    private final ArrayList<CellGUI> guiCells = new ArrayList<>();

    private final JPanel northPanel = new JPanel();
    private PlayerPanel[] playerPanels;
    private final JPanel southPanel = new JPanel();
    private final JPanel westPanel = new JPanel();

    public MainWindow(MainController mainCtl) {
        this.mainCtl = mainCtl;
        
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container c = super.getContentPane();
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

    private void addCells(JPanel panel, List<Cell> cells) {
        cells.stream().map((cell) -> new CellGUI(cell)).map((guiCell) -> {
            panel.add(guiCell);
            return guiCell;
        }).forEach((guiCell) -> {
            guiCells.add(guiCell);
        });
    }

    private void buildPlayerPanels() {
        JPanel infoPanel = new JPanel();
        int players = mainCtl.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[mainCtl.getNumberOfPlayers()];
        for (int i = 0; i < mainCtl.getNumberOfPlayers(); i++){
            playerPanels[i] = new PlayerPanel(mainCtl, mainCtl.getPlayer(i));
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
    public Dice getDiceRoll() {
        TestDiceRollDialog dialog = new TestDiceRollDialog(this);
        dialog.setVisible(true);
        return dialog.getDiceRoll();
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = mainCtl.getTurn();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = mainCtl.getTurn();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = mainCtl.getTurn();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }
	
    @Override
    public void movePlayer(int index, int from, int to) {
        CellGUI fromCell = queryCell(from);
        CellGUI toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(mainCtl, index);
    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        int sellerIdx = mainCtl.getPlayerIndex(deal.getSeller());
        RespondDialogGUI dialog = new RespondDialogGUI(playerPanels[sellerIdx]);
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public TradeDialog openTradeDialog() {
        TradeDialogGUI dialog = new TradeDialogGUI(mainCtl, this);
        dialog.setVisible(true);
        return dialog;
    }
	
    private CellGUI queryCell(int index) {
        Cell cell = mainCtl.getGameBoard().getCell(index);
            for (Object guiCell1 : guiCells) {
                CellGUI guiCell = (CellGUI) guiCell1;
                if (guiCell.getCell() == cell) {
                    return guiCell;
                }
            }
        return null;
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
        int currentPlayerIndex = mainCtl.getTurn();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        int currentPlayerIndex = mainCtl.getTurn();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = mainCtl.getTurn();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        int currentPlayerIndex = mainCtl.getTurn();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = mainCtl.getTurn();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
        int currentPlayerIndex = mainCtl.getTurn();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        playerPanels[index].setTradeEnabled(b);
    }
	
    public void setupGameBoard(GameBoard board) {
        Dimension dimension = GameBoardUtil.calculateDimension(board.getCellSize());
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
        BuyHouseDialog dialog = new BuyHouseDialog(mainCtl, currentPlayer);
        dialog.setVisible(true);
    }

    @Override
    public void showMessage(String msg, PlayerPanel panel) {
        JOptionPane.showMessageDialog(panel, msg);
    }

    @Override
    public int showUtilDiceRoll() {
        int currPlayerIdx = mainCtl.getPlayerIndex(mainCtl.getCurrentPlayer());
        return UtilDiceRoll.showDialog(mainCtl, playerPanels[currPlayerIdx]);
    }

    @Override
    public void startGame() {
        int numberOfPlayers = mainCtl.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }

    @Override
    public void update() {
        for (PlayerPanel playerPanel : playerPanels) {
            playerPanel.displayInfo();
        }
        
        guiCells.stream().map((guiCell) -> guiCell).forEach((cell) -> {
            cell.displayInfo();
        });
    }
}
