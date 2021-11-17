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

    /**
     * add the list of the given cells into the given panel
     * @param panel
     * @param cells
     */
    private void addCells(JPanel panel, List<Cell> cells) {
        cells.stream().map((cell) -> new CellGUI(cell)).map((guiCell) -> {
                                        panel.add(guiCell);
                                        return guiCell;
                                    }).forEach((guiCell) -> {
                                        guiCells.add(guiCell);
                                    });
    }

    /**
     * Build all player panels and add them to infoPanel
     */
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

    /**
     * get the cell in the given index then return the related GUI if found.
     * @param index
     * @return CellGUI of the given index
     */
    private CellGUI queryCell(int index) {
        Cell cell = mainController.getGameBoard().getCell(index);
            for (Object guiCell1 : guiCells) {
                CellGUI guiCell = (CellGUI) guiCell1;
                if (guiCell.getCell() == cell)
                    return guiCell;
            }
        return null;
    }

    /**
     * set up the gameBoard by:
     * 1- setting the layout of all the panels
     * 2- adding the cells of all the panels
     * 3- build the player panels
     * @param board
     */
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

    /**
     * set the endTurnEnabled by true for the player in the given index
     * @param playerIndex
     */
    @Override
    public void enableEndTurnButton(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    /**
     * enable the player turn by setting the rollDiceEnabled with true value for the player in the given index
     * @param playerIndex
     */
    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);
    }

    /**
     * enable the player purchaseButton by setting the PurchasePropertyEnabled with true value for the player in the given index
     * @param playerIndex
     */
    @Override
    public void enablePurchaseButton(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    /**
     * @return true if the DrawCardButton is enabled for the current player and false otherwise.
     */
    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    /**
     * @return true if the EndTurnButton is enabled for the current player and false otherwise.
     */
    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    /**
     * @return true if the GetOutOfJailButton is enabled for the current player and false otherwise.
     */
    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    /**
     * @param i
     * @return true if the TradeButton is enabled for the current player and false otherwise.
     */
    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }

    /**
     * move the player in the given index from the cell located in from param to the cell located in to param
     * by removing the player from the 'from' index and adding him/her in 'to' index
     * @param index
     * @param from
     * @param to
     */
    @Override
    public void movePlayer(int index, int from, int to) {
        CellGUI fromCell = queryCell(from);
        CellGUI toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(mainController, index);
    }

    /**
     * @param deal
     * @return a respondDialog with the given deal
     */
    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        int sellerIdx = mainController.getPlayerIndex(deal.getSeller());
        RespondDialogGUI dialog = new RespondDialogGUI(playerPanels[sellerIdx]);
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }

    /**
     * @return TradeDialog
     */
    @Override
    public TradeDialog openTradeDialog() {
        TradeDialogGUI dialog = new TradeDialogGUI(mainController, this);
        dialog.setVisible(true);
        return dialog;
    }

    /**
     * remove the player in the given index from the cell in the 'from' param
     * @param index
     * @param from
     */
    @Override
    public void removePlayer(int index, int from) {
        CellGUI cell = queryCell(from);
        cell.removePlayer(index);
    }

    /**
     * set BuyHouseEnabled in the playerPanel of the currentPlayerTurn with the value of enabled param
     * @param enabled
     */
    @Override
    public void setBuyHouseEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(enabled);
    }

    /**
     * set DrawCardEnabled in the playerPanel of the currentPlayerTurn with the value of enabled param
     * @param enabled
     */
    @Override
    public void setDrawCardEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(enabled);
    }

    /**
     * set EndTurnEnabled in the playerPanel of the currentPlayerTurn with the value of enabled param
     * @param enabled
     */
    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    /**
     * set GetOutOfJailEnabled in the playerPanel of the currentPlayerTurn with the value of enabled param
     * @param enabled
     */
    @Override
    public void setGetOutOfJailEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(enabled);
    }

    /**
     * set PurchasePropertyEnabled in the playerPanel of the currentPlayerTurn with the value of enabled param
     * @param enabled
     */
    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    /**
     * set RollDiceEnabled in the playerPanel of the currentPlayerTurn with the value of enabled param
     * @param enabled
     */
    @Override
    public void setRollDiceEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(enabled);
    }

    /**
     * set setTradeEnabled in the playerPanel of the player in the given index with the value of enabled param
     * @param index
     * @param enabled
     */
    @Override
    public void setTradeEnabled(int index, boolean enabled) {
        playerPanels[index].setTradeEnabled(enabled);
    }

    /**
     * show the dialog of buyHouseDialog of the given currentPlayer by making it visible
     * @param currentPlayer
     */
    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        int currentPlayerIndex = mainController.getPlayerIndex(currentPlayer);
        PlayerPanel panel = playerPanels[currentPlayerIndex];
        BuyHouseDialog dialog = new BuyHouseDialog(mainController, currentPlayer, panel);
        dialog.setVisible(true);
    }

    /**
     * show the given message to the given PlayerPanel
     * @param message
     * @param panel
     */
    @Override
    public void showMessage(String message, PlayerPanel panel) {
        JOptionPane.showMessageDialog(panel, message);
    }

    /**
     *
     * @return the dice value of utilityDice of the currentPlayer
     */
    @Override
    public int showUtilityDiceRoll() {
        int currentPlayerIndex = mainController.getPlayerIndex(mainController.getCurrentPlayer());
        return UtilityDiceRoll.showDialog(playerPanels[currentPlayerIndex]);
    }

    /**
     * start the game by moving all the players into the start cell
     */
    @Override
    public void startGame() {
        int numberOfPlayers = mainController.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }

    /**
     *display info for all playerPanel and for each guiCell
     */
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
