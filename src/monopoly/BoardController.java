package monopoly;

import java.awt.Color;
import monopoly.gui.MonopolyGUI;
import monopoly.cells.CardCell;
import java.util.ArrayList;

public class BoardController {
    public static final int MAX_PLAYER = 8;
    private final ArrayList<Player> players = new ArrayList();
    private int initAmountOfMoney = 1500;
    private GameBoard gameBoard;
    private int turn = 0;
    private MonopolyGUI gui;
    
    private final Color[] playerColors = {
                                    new Color(255, 249, 102),
                                    new Color(66, 134, 244),
                                    new Color(143, 99, 158),
                                    new Color(209, 155, 20),
                                    new Color(209, 96, 20),
                                    new Color(120, 230, 30),
                                    new Color(206, 57, 72),
                                    new Color(72, 196, 188)
                                   };

    public BoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Player player = new Player();
            player.setMoney(initAmountOfMoney);
            player.setPosition(gameBoard.getCell(0));
            player.setColor(playerColors[i]);
            players.add(player);
        }
    }
    
    public int getInitAmountOfMoney() {
        return initAmountOfMoney;
    }

    public void setInitAmountOfMoney(int money) {
        this.initAmountOfMoney = money;
    }
 
    public void movePlayer(int playerIndex, int diceValue) {
        Player player = (Player)players.get(playerIndex);
        movePlayer(player, diceValue);
    }
	
    public void movePlayer(Player player, int diceValue) {
        Cell currentPosition = player.getPosition();
        int positionIndex = gameBoard.queryCellIndex(currentPosition.getName());
        int newIndex = (positionIndex + diceValue) % gameBoard.getCellSize();
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellSize()) {
            gui.showMessage("You receive $200");
            player.setMoney(player.getMoney() + 200);
        }
        player.setPosition(gameBoard.getCell(newIndex));
        gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
        playerMoved(player);
    }

    public void playerMoved(Player player) {
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
        gui.setTradeEnabled(turn, false);
    }
    
    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfSellers() {
        return players.size() - 1;
    }
    
    public void switchTurn() {
        turn = (turn + 1) % getNumberOfPlayers();
        if (!getCurrentPlayer().isInJail()) {
            gui.enablePlayerTurn(turn);
            gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse(gameBoard));
            gui.setTradeEnabled(turn, true);
        } else {
            gui.setGetOutOfJailEnabled(true);
        }
    }
    
    public Player getCurrentPlayer() {
        return getPlayer(turn);
    }
    
    public int getCurrentPlayerIndex() {
        return turn;
    }

    public void reset() {    
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        turn = 0;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
    }

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
