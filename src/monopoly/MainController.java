package monopoly;

import java.util.List;
import monopoly.cells.CardCell;
import monopoly.cells.JailCell;
import monopoly.enums.CardType;
import monopoly.enums.ColorGroup;
import monopoly.gameboards.GameBoardDefault;
import monopoly.gui.MonopolyGUI;
import monopoly.gui.PlayerPanel;

/**
 *
 * @author owner
 */
public class MainController {

    private final BoardController boardController;

    private final Dice dice;

    private GameBoard gameBoard;

    private MonopolyGUI gui;

    private final PropertyController propertyController;

    private int utilityDiceRoll;
    
    public MainController() {
        gameBoard = new GameBoardDefault();
        boardController = new BoardController(gameBoard);
        propertyController = new PropertyController(boardController);
        dice = new Dice(2);
    }
    
    /**
   * Button click to buy house if player need
   */
    public void buttonBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    /**
     *Draw card based on cell type.
     * @return card based on the current player's cell type.
     */
    public Card buttonDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell)getCurrentPlayer().getPosition();
        Card card;
        if (cell.getType() == CardType.CC) {
            card = getGameBoard().drawCCCard();
            card.applyAction(this);
        } else {
            card = getGameBoard().drawChanceCard();
            card.applyAction(this);
        }
        gui.setEndTurnEnabled(true);
        return card;
    }

    /**
     * click Update button if player want to end turn
     */
    public void buttonEndTurnClicked() {
        setAllButtonEnabled(false);
        getCurrentPlayer().getPosition().playAction(this);
        
        if (getCurrentPlayer().isBankrupt()) {
            getCurrentPlayer().setOutOfGame();
            boardController.removePlayer();
        }
        switchTurn();
        gui.update();
    }

   
    public void buttonGetOutOfJailClicked() {
        getOutOfJail();
        if (getCurrentPlayer().isBankrupt()) {
            setAllButtonEnabled(false);
            getCurrentPlayer().setOutOfGame();
            int positionIndex = boardController.getCurrentPositionIndex(getCurrentPlayer());
            gui.removePlayer(getPlayerIndex(getCurrentPlayer()), positionIndex);
            boardController.removePlayer();
            switchTurn();
            gui.update();
        } else {
            gui.setRollDiceEnabled(true);
            gui.setBuyHouseEnabled(propertyController.canBuyHouse());
            gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
            gui.setTradeEnabled(getTurn(), true);
        }
    }

    
    public void buttonPurchasePropertyClicked() {
        purchase();
        gui.setPurchasePropertyEnabled(false);
        gui.update();
    }
    
    /**
     *
     * @param panel
     */
    public void buttonRollDiceClicked(PlayerPanel panel) {
        dice.roll();
        if ((dice.getTotal()) > 0) {
            Player player = getCurrentPlayer();
            gui.setRollDiceEnabled(false);
            StringBuilder msg = new StringBuilder();
            msg.append("You rolled ")
                    .append(dice.getSingleDice(0))
                    .append(" and ")
                    .append(dice.getSingleDice(1));
            gui.showMessage(msg.toString(), panel);
            movePlayer(player, dice.getTotal());
            gui.setBuyHouseEnabled(false);
        }
    }

    
    public void buttonTradeClicked() {
        TradeDialog dialog = gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal(this);
        if (deal != null) {
            RespondDialog respondDialog = gui.openRespondDialog(deal);
            if (respondDialog.getResponse()) {
                completeTrade(deal);
                gui.update();
            }
        }
    }
    
    /**
     *
     * @return
     */
    public boolean canBuyHouse() {
        return propertyController.canBuyHouse();
    }

    /**
     *
     * @param deal
     */
    public void completeTrade(TradeDeal deal) {
        propertyController.sellProperty(deal);
        propertyController.buyProperty(deal);
    }

    /**
     *
     * @return
     */
    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    /**
     *
     * @return
     */
    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }
    
    /**
     *
     * @param player
     */
    private void finishPlayerMove(Player player) {
        Cell cell = player.getPosition();
        int playerIndex = getPlayerIndex(player);
        if (cell instanceof CardCell) {
            gui.setDrawCardEnabled(true);
        } else {
            if (cell.isAvailable()) {
                int price = cell.getPrice();
                if (price <= player.getMoney() && price > 0)
                    gui.enablePurchaseButton(playerIndex);
            }
            gui.enableEndTurnButton(playerIndex);
        }
        gui.setTradeEnabled(boardController.getTurn(), false);
    }

    /**
     *
     * @return
     */
    public Player getCurrentPlayer() {
        return boardController.getCurrentPlayer();
    }
    
    /**
     *
     * @return
     */
    public Dice getDice() {
        return dice;
    }

    /**
     *
     * @return
     */
    public MonopolyGUI getGUI() {
        return gui;
    }
    
    /**
     *
     * @return
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
    /**
     *
     * @param player
     * @return
     */
    public List<ColorGroup> getMonopolies(Player player) {
        return propertyController.getMonopolies(player);
    }

    /**
     *
     * @return
     */
    public int getNumberOfPlayers() {
        return boardController.getNumberOfPlayers();
    }
    
    private void getOutOfJail() {
        Player currentPlayer = boardController.getCurrentPlayer();
        currentPlayer.subtractMoney(JailCell.BAIL);
        if (currentPlayer.isBankrupt()) {
            currentPlayer.setMoney(0);
            giveAllProperties(currentPlayer, null);
        }
        currentPlayer.setInJail(false);
        gui.update();
    }

    /**
     *
     * @param index
     * @return
     */
    public Player getPlayer(int index) {
        return boardController.getPlayer(index);
    }

    /**
     *
     * @param player
     * @return
     */
    public int getPlayerIndex(Player player) {
        return boardController.getPlayerIndex(player);
    }

    /**
     *
     * @return
     */
    public List<Player> getSellerList() {
        return propertyController.getSellerList();
    }
    
    /**
     *
     * @return
     */
    public int getTurn() {
        return boardController.getTurn();
    }

    /**
     *
     * @return
     */
    public int getUtilityDiceRoll() {
        return this.utilityDiceRoll;
    }
    
    /**
     *
     * @param fromPlayer
     * @param toPlayer
     */
    public void giveAllProperties(Player fromPlayer, Player toPlayer) {
        propertyController.giveAllProperties(fromPlayer, toPlayer);
    }

    /**
     *
     * @param player
     * @param diceValue
     */
    public void movePlayer(Player player, int diceValue) {
        int positionIndex = boardController.getCurrentPositionIndex(player);
        int newIndex = boardController.getNewPositionIndex(positionIndex, diceValue);
        
        boardController.movePlayer(player, diceValue);
        gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
        finishPlayerMove(player);

        gui.update();
    }
    
    /**
     *
     * @param owner
     * @param rent
     */
    public void payRentTo(Player owner, int rent) {
        propertyController.payRentTo(owner, rent);
    }
    
   
    public void purchase() {
        propertyController.purchase();
    }
    
    /**
     *
     * @param selectedMonopoly
     * @param houses
     */
    public void purchaseHouse(ColorGroup selectedMonopoly, int houses) {
        if (propertyController.purchaseHouse(selectedMonopoly, houses) <= 5)
            gui.update();
    }

    public void reset() {
        boardController.reset();
        if (gameBoard != null)
            gameBoard.removeCards();
    }
	
    /**
     *
     * @param player
     */
    public void sendToJail(Player player) {
        String currentPlayerName = getCurrentPlayer().getPosition().getName();
        int oldPosition = gameBoard.queryCellIndex(currentPlayerName);
        player.setPosition(gameBoard.queryCell("Jail"));
        player.setInJail(true);
        int jailIndex = gameBoard.queryCellIndex("Jail");
        gui.movePlayer(getPlayerIndex(player), oldPosition, jailIndex);
    }
    
    /**
     *
     * @param enabled
     */
    private void setAllButtonEnabled(boolean enabled) {
        gui.setRollDiceEnabled(enabled);
        gui.setPurchasePropertyEnabled(enabled);
        gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(boardController.getTurn(), enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
    }
    
    /**
     *
     * @param gui
     */
    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
    }

    /**
     *
     * @param board
     */
    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
        boardController.setGameBoard(board);
    }

    /**
     *
     * @param number
     */
    public void setNumberOfPlayers(int number) {
        boardController.setNumberOfPlayers(number);
    }

    
    public void startGame() {
        gui.startGame();
        gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
    }

    public void switchTurn() {
        boardController.switchTurn();
        
        if (getCurrentPlayer().isOutOfGame()) {
            switchTurn();
            return;
        }
        if (boardController.getOutOfGamePlayersNumber() + 1 >= boardController.getNumberOfPlayers()) {
            setAllButtonEnabled(false);
            return;
        }
        if (!getCurrentPlayer().isInJail()) {
            gui.enablePlayerTurn(boardController.getTurn());
            gui.setBuyHouseEnabled(propertyController.canBuyHouse());
            gui.setTradeEnabled(boardController.getTurn(), true);
        } else {
            gui.setGetOutOfJailEnabled(true);
        }
    }

    public void utilityRollDice() {
        this.utilityDiceRoll = gui.showUtilityDiceRoll();
    }
    
}
