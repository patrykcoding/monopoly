package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardController {
    public static final int MAX_PLAYER = 8;
    private final ArrayList<Player> players = new ArrayList<>();
    private GameBoard gameBoard;
    private int turn = 0;
    
    private final ArrayList<Color> playerColors = new ArrayList<>(Arrays.asList(
                                    new Color(255, 249, 102), /* Player 1 */
                                    new Color(66, 134, 244),  /* Player 2 */
                                    new Color(143, 99, 158),  /* Player 3 */
                                    new Color(209, 155, 20),  /* Player 4 */
                                    new Color(209, 96, 20),   /* Player 5 */
                                    new Color(120, 230, 30),  /* Player 6 */
                                    new Color(206, 57, 72),   /* Player 7 */
                                    new Color(72, 196, 188)   /* Player 8 */
                                    ));

    public BoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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

    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    public int getNewPositionIndex(int positionIndex, int diceValue) {
        return (positionIndex + diceValue) % gameBoard.getCellSize();    
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfSellers() {
        return players.size() - 1;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public int getTurn() {
        return turn;
    }
	
    public void movePlayer(Player player, int diceValue) {
        int positionIndex = getCurrentPositionIndex(player);
        int newIndex = getNewPositionIndex(positionIndex, diceValue);
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellSize()) {
            player.setMoney(player.getMoney() + 200);
        }
        player.setPosition(gameBoard.getCell(newIndex));
    }
    
    public void reset() {    
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        turn = 0;
    }
    
    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Player player = new Player(gameBoard.getCell(0));
            player.setPlayerColor(playerColors.get(i));
            players.add(player);
        }
    }
    
    public void switchTurn() {
        turn = (turn + 1) % getNumberOfPlayers();
    }

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }
}
