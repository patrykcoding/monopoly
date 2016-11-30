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
import monopoly.GameBoard;
import monopoly.MainController;
import monopoly.Player;
import monopoly.RespondDialog;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class MainWindow extends JFrame implements MonopolyGUI {
    private static final long serialVersionUID = 3146365872410925008L;
    private final JPanel eastPanel = new JPanel();
    private final ArrayList<CellGUI> guiCells = new ArrayList<>();
    private final MainController mainController;
    private final JPanel northPanel = new JPanel();
    private PlayerPanel[] playerPanels;
    private final JPanel southPanel = new JPanel();
    private final JPanel westPanel = new JPanel();

    public MainWindow(MainController mainCtl) {
        this.mainController = mainCtl;
        
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container container = super.getContentPane();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        super.setSize(dimension);
        container.add(northPanel, BorderLayout.NORTH);
        container.add(southPanel, BorderLayout.SOUTH);
        container.add(eastPanel, BorderLayout.EAST);
        container.add(westPanel, BorderLayout.WEST);

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
        int players = mainController.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[mainController.getNumberOfPlayers()];
        for (int i = 0; i < mainController.getNumberOfPlayers(); i++){
            playerPanels[i] = new PlayerPanel(mainController, mainController.getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }
	
    private CellGUI queryCell(int index) {
        Cell cell = mainController.getGameBoard().getCell(index);
            for (Object guiCell1 : guiCells) {
                CellGUI guiCell = (CellGUI) guiCell1;
                if (guiCell.getCell() == cell)
                    return guiCell;
            }
        return null;
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
    public void enableEndTurnButton(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);
    }

    @Override
    public void enablePurchaseButton(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
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
        toCell.addPlayer(mainController, index);
    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        int sellerIdx = mainController.getPlayerIndex(deal.getSeller());
        RespondDialogGUI dialog = new RespondDialogGUI(playerPanels[sellerIdx]);
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public TradeDialog openTradeDialog() {
        TradeDialogGUI dialog = new TradeDialogGUI(mainController, this);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public void removePlayer(int index, int from) {
        CellGUI cell = queryCell(from);
        cell.removePlayer(index);
    }
    
    @Override
    public void setBuyHouseEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(enabled);
    }

    @Override
    public void setDrawCardEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(enabled);
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(enabled);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(enabled);
    }

    @Override
    public void setTradeEnabled(int index, boolean enabled) {
        playerPanels[index].setTradeEnabled(enabled);
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        int currentPlayerIndex = mainController.getPlayerIndex(currentPlayer);
        PlayerPanel panel = playerPanels[currentPlayerIndex];
        BuyHouseDialog dialog = new BuyHouseDialog(mainController, currentPlayer, panel);
        dialog.setVisible(true);
    }

    @Override
    public void showMessage(String message, PlayerPanel panel) {
        JOptionPane.showMessageDialog(panel, message);
    }

    @Override
    public int showUtilityDiceRoll() {
        int currentPlayerIndex = mainController.getPlayerIndex(mainController.getCurrentPlayer());
        return UtilityDiceRoll.showDialog(playerPanels[currentPlayerIndex]);
    }

    @Override
    public void startGame() {
        int numberOfPlayers = mainController.getNumberOfPlayers();
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
