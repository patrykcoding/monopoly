package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardController {
    public static final int MAX_PLAYER = 8;
    private final ArrayList<Player> players = new ArrayList();
    private int initAmountOfMoney = 1500;
    private GameBoard gameBoard;
    private int turn = 0;
    
    private final ArrayList<Color> playerColors = new ArrayList<>(Arrays.asList(
                                    new Color(255, 249, 102),
                                    new Color(66, 134, 244),
                                    new Color(143, 99, 158),
                                    new Color(209, 155, 20),
                                    new Color(209, 96, 20),
                                    new Color(120, 230, 30),
                                    new Color(206, 57, 72),
                                    new Color(72, 196, 188)
                                    ));

    public BoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Player player = new Player();
            player.setMoney(initAmountOfMoney);
            player.setPosition(gameBoard.getCell(0));
            player.setColor(playerColors.get(i));
            players.add(player);
        }
    }
    
    public int getInitAmountOfMoney() {
        return initAmountOfMoney;
    }

    public void setInitAmountOfMoney(int money) {
        this.initAmountOfMoney = money;
    }
	
    public void movePlayer(Player player, int diceValue) {
        int positionIndex = getCurrentPositionIndex(player);
        int newIndex = getNewPositionIndex(positionIndex, diceValue);
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellSize()) {
            player.setMoney(player.getMoney() + 200);
        }
        player.setPosition(gameBoard.getCell(newIndex));
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
    }
    
    public Player getCurrentPlayer() {
        return getPlayer(turn);
    }
    
    public int getCurrentPlayerIndex() {
        return turn;
    }

    public int getCurrentPositionIndex(Player player) {
        Cell currentPosition = player.getPosition();
        return gameBoard.queryCellIndex(currentPosition.getName());
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

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getNewPositionIndex(int positionIndex, int diceValue) {
        return (positionIndex + diceValue) % gameBoard.getCellSize();    
    }
}
