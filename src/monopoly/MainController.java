package monopoly;

import monopoly.enums.CardType;
import monopoly.gui.MonopolyGUI;
import monopoly.gameboards.GameBoardDefault;
import monopoly.cells.CardCell;
import java.util.List;
import monopoly.cells.JailCell;
import monopoly.enums.ColorGroup;
import monopoly.gui.PlayerPanel;

public class MainController {
    private final BoardController boardCtl;

    private final Dice dice;
    private GameBoard gameBoard;
    private MonopolyGUI gui;
    private final PropertyController propertyCtl;
    private int utilDiceRoll;
    
    public MainController() {
        gameBoard = new GameBoardDefault();
        boardCtl = new BoardController(gameBoard);
        propertyCtl = new PropertyController(boardCtl);
        dice = new Dice(2);
    }

    public void btnBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    public Card btnDrawCardClicked() {
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

    public void btnEndTurnClicked() {
        setAllButtonEnabled(false);
        getCurrentPlayer().getPosition().playAction(this);
        
        if (getCurrentPlayer().isBankrupt()) {
            gui.setBuyHouseEnabled(false);
            gui.setDrawCardEnabled(false);
            gui.setEndTurnEnabled(false);
            gui.setGetOutOfJailEnabled(false);
            gui.setPurchasePropertyEnabled(false);
            gui.setRollDiceEnabled(false);
            gui.setTradeEnabled(getTurn(),false);
            gui.update();
        } else {
            switchTurn();
            gui.update();
        }
    }

    public void btnGetOutOfJailClicked() {
        getOutOfJail();
        if (getCurrentPlayer().isBankrupt()) {
            gui.setBuyHouseEnabled(false);
            gui.setDrawCardEnabled(false);
            gui.setEndTurnEnabled(false);
            gui.setGetOutOfJailEnabled(false);
            gui.setPurchasePropertyEnabled(false);
            gui.setRollDiceEnabled(false);
            gui.setTradeEnabled(getTurn(),false);
        } else {
            gui.setRollDiceEnabled(true);
            gui.setBuyHouseEnabled(propertyCtl.canBuyHouse());
            gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
            gui.setTradeEnabled(getTurn(), true);
        }
    }

    public void btnPurchasePropertyClicked() {
        purchase();
        gui.setPurchasePropertyEnabled(false);
        gui.update();
    }
    
    public void btnRollDiceClicked(PlayerPanel panel) {
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

    public void btnTradeClicked() {
        TradeDialog dialog = gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal(this);
        if (deal != null) {
            RespondDialog rDialog = gui.openRespondDialog(deal);
            if (rDialog.getResponse()) {
                completeTrade(deal);
                gui.update();
            }
        }
    }
    
    public boolean canBuyHouse() {
        return propertyCtl.canBuyHouse();
    }

    public void completeTrade(TradeDeal deal) {
        propertyCtl.sellProperty(deal);
        propertyCtl.buyProperty(deal);
    }

    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }
    
    private void finishPlayerMove(Player player) {
        Cell cell = player.getPosition();
        int playerIndex = getPlayerIndex(player);
        if (cell instanceof CardCell) {
            gui.setDrawCardEnabled(true);
        } else {
            if (cell.isAvailable()) {
                int price = cell.getPrice();
                if (price <= player.getMoney() && price > 0) {
                    gui.enablePurchaseBtn(playerIndex);
                }
            }
            gui.enableEndTurnBtn(playerIndex);
        }
        gui.setTradeEnabled(boardCtl.getTurn(), false);
    }

    public Player getCurrentPlayer() {
        return boardCtl.getCurrentPlayer();
    }
    
    public Dice getDice() {
        return dice;
    }

    public MonopolyGUI getGUI() {
        return gui;
    }
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
    public List<ColorGroup> getMonopolies(Player player) {
        return propertyCtl.getMonopolies(player);
    }

    public int getNumberOfPlayers() {
        return boardCtl.getNumberOfPlayers();
    }
    
    private void getOutOfJail() {
        Player currentPlayer = boardCtl.getCurrentPlayer();
        currentPlayer.subtractMoney(JailCell.BAIL);
        if (currentPlayer.isBankrupt()) {
            currentPlayer.setMoney(0);
            giveAllProperties(currentPlayer, null);
        }
        currentPlayer.setInJail(false);
        gui.update();
    }

    public Player getPlayer(int index) {
        return boardCtl.getPlayer(index);
    }

    public int getPlayerIndex(Player player) {
        return boardCtl.getPlayerIndex(player);
    }

    public List<Player> getSellerList() {
        return propertyCtl.getSellerList();
    }
    
    public int getTurn() {
        return boardCtl.getTurn();
    }

    public int getUtilDiceRoll() {
        return this.utilDiceRoll;
    }
    
    public void giveAllProperties(Player fromPlayer, Player toPlayer) {
        propertyCtl.giveAllProperties(fromPlayer, toPlayer);
    }

    public void movePlayer(Player player, int diceValue) {
        int positionIndex = boardCtl.getCurrentPositionIndex(player);
        int newIndex = boardCtl.getNewPositionIndex(positionIndex, diceValue);
        
        boardCtl.movePlayer(player, diceValue);
        gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
        finishPlayerMove(player);

        gui.update();
    }
    
    public void payRentTo(Player owner, int rent) {
        propertyCtl.payRentTo(owner, rent);
    }
    
    public void purchase() {
        propertyCtl.purchase();
    }
    
    public void purchaseHouse(ColorGroup selectedMonopoly, int houses) {
        if (propertyCtl.purchaseHouse(selectedMonopoly, houses) <= 5)
            gui.update();
    }

    public void reset() {
        boardCtl.reset();
        if (gameBoard != null)
            gameBoard.removeCards();
    }
	
    public void sendToJail(Player player) {
        String currentPlayerName = getCurrentPlayer().getPosition().getName();
        int oldPosition = gameBoard.queryCellIndex(currentPlayerName);
        player.setPosition(gameBoard.queryCell("Jail"));
        player.setInJail(true);
        int jailIndex = gameBoard.queryCellIndex("Jail");
        gui.movePlayer(getPlayerIndex(player), oldPosition, jailIndex);
    }
    
    private void setAllButtonEnabled(boolean enabled) {
        gui.setRollDiceEnabled(enabled);
        gui.setPurchasePropertyEnabled(enabled);
        gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(boardCtl.getTurn(), enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
    }
    
    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
    }

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
        boardCtl.setGameBoard(board);
    }

    public void setNumberOfPlayers(int number) {
        boardCtl.setNumberOfPlayers(number);
    }

    public void startGame() {
        gui.startGame();
        gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
    }

    public void switchTurn() {
        boardCtl.switchTurn();
        
        if (!getCurrentPlayer().isInJail()) {
            gui.enablePlayerTurn(boardCtl.getTurn());
            gui.setBuyHouseEnabled(propertyCtl.canBuyHouse());
            gui.setTradeEnabled(boardCtl.getTurn(), true);
        } else {
            gui.setGetOutOfJailEnabled(true);
        }
    }

    public void utilRollDice() {
        this.utilDiceRoll = gui.showUtilDiceRoll();
    }
    
}
