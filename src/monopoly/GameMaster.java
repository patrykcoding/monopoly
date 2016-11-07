package monopoly;

import monopoly.gui.MonopolyGUI;
import monopoly.gameboards.GameBoardDefault;
import monopoly.cells.CardCell;
import java.util.ArrayList;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;

public class GameMaster {

    private final Die[] dice;
    private GameBoard gameBoard;
    private MonopolyGUI gui;
    private int utilDiceRoll;
    private boolean testMode;
    private final PlayerController playerController;

    public GameMaster() {
        gameBoard = new GameBoardDefault();
        this.playerController = new PlayerController(gameBoard);
        dice = new Die[]{new Die(), new Die()};
    }

    public void btnBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    public Card btnDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell)getCurrentPlayer().getPosition();
        Card card;
        if (cell.getType() == Card.TYPE_CC) {
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
            gui.setTradeEnabled(getCurrentPlayerIndex(),false);
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
            gui.setTradeEnabled(getCurrentPlayerIndex(),false);
        } else {
            gui.setRollDiceEnabled(true);
            gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse(this));
            gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
        }
    }

    public void btnPurchasePropertyClicked() {
        Player player = getCurrentPlayer();
        player.purchase();
        gui.setPurchasePropertyEnabled(false);
        gui.update();
    }
    
    public void btnRollDiceClicked() {
        int[] rolls = rollDice();
        if ((rolls[0]+rolls[1]) > 0) {
            Player player = getCurrentPlayer();
            gui.setRollDiceEnabled(false);
            StringBuilder msg = new StringBuilder();
            msg.append(player.getName())
                    .append(", you rolled ")
                    .append(rolls[0])
                    .append(" and ")
                    .append(rolls[1]);
            gui.showMessage(msg.toString());
            movePlayer(player, rolls[0] + rolls[1]);
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

    public void completeTrade(TradeDeal deal) {
        Player seller = deal.getSeller();
        Cell property = gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }

    public Player getCurrentPlayer() {
        return playerController.getCurrentPlayer();
    }
    
    public int getCurrentPlayerIndex() {
        return playerController.getCurrentPlayerIndex();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public MonopolyGUI getGUI() {
        return gui;
    }

    public int getInitAmountOfMoney() {
        return playerController.getInitAmountOfMoney();
    }

    public int getNumberOfPlayers() {
        return playerController.getNumberOfPlayers();
    }

    public int getNumberOfSellers() {
        return playerController.getNumberOfSellers();
    }

    public Player getPlayer(int index) {
        return playerController.getPlayer(index);
    }

    public int getPlayerIndex(Player player) {
        return playerController.getPlayerIndex(player);
    }

    public ArrayList<Player> getSellerList() {
        return playerController.getSellerList();
    }

    public int getUtilDiceRoll() {
        return this.utilDiceRoll;
    }

    public void movePlayer(int playerIndex, int diceValue) {
        playerController.movePlayer(playerIndex, diceValue);
    }
	
    public void movePlayer(Player player, int diceValue) {
        playerController.movePlayer(player, diceValue);
        gui.update();
    }

    public void playerMoved(Player player) {
        playerController.playerMoved(player);
    }

    public void reset() {
        playerController.reset();
        if (gameBoard != null) {
            gameBoard.removeCards();
        }
    }
	
    public int[] rollDice() {
        if (testMode) {
            return gui.getDiceRoll();
        } else {
            return new int[]{ dice[0].getRoll(),  dice[1].getRoll() };
        }
    }
	
    public void sendToJail(Player player) {
        int oldPosition = gameBoard.queryCellIndex(getCurrentPlayer().getPosition().getName());
        player.setPosition(gameBoard.queryCell("Jail"));
        player.setInJail(true);
        int jailIndex = gameBoard.queryCellIndex("Jail");
        gui.movePlayer(
                getPlayerIndex(player),
                oldPosition,
                jailIndex
        );
    }
    
    private void setAllButtonEnabled(boolean enabled) {
        gui.setRollDiceEnabled(enabled);
        gui.setPurchasePropertyEnabled(enabled);
        gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(playerController.getTurn(), enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
    }

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
        playerController.setGameBoard(board);
    }

    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
        playerController.setGUI(gui);
    }

    public void setInitAmountOfMoney(int money) {
        playerController.setInitAmountOfMoney(money);
    }

    public void setNumberOfPlayers(int number) {
        playerController.setNumberOfPlayers(number);
    }

    public void setUtilDiceRoll(int diceRoll) {
        this.utilDiceRoll = diceRoll;
    }

    public void startGame() {
        gui.startGame();
        gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
    }

    public void switchTurn() {
        playerController.switchTurn(this);
    }

    public void utilRollDice() {
        this.utilDiceRoll = gui.showUtilDiceRoll();
    }

    public void setTestMode(boolean b) {
        testMode = b;
    }
    
    public int getTurn() {
        return playerController.getTurn();
    }
    
    public void getOutOfJail() {
        Player currentPlayer = playerController.getCurrentPlayer();
        currentPlayer.subtractMoney(JailCell.BAIL);
        if (currentPlayer.isBankrupt()) {
            currentPlayer.setMoney(0);
            currentPlayer.exchangeProperty(null);
        }
        currentPlayer.setInJail(false);
        gui.update();
    }
    
    public void purchaseHouse(String selectedMonopoly, int houses) {
        Player currentPlayer = playerController.getCurrentPlayer();
        int money = currentPlayer.getMoney();
        PropertyCell[] cells = gameBoard.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            for (PropertyCell cell : cells) {
                int newNumber = cell.getNumHouses() + houses;
                if (newNumber <= 5) {
                    cell.setNumHouses(newNumber);
                    currentPlayer.subtractMoney(cell.getHousePrice() * houses);
                    gui.update();
                }
            }
        }
    }
}
