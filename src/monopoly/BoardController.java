package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Maximum number of players in game is 8.
 * Board Controller is a color that directs and regulates the player.
 * @author owner
 */
public class BoardController {

    public static final int MAX_PLAYER = 8;
    
    private GameBoard gameBoard;
    /* Colors of the players on the gameBoard */


    private final List<Color> playerColors = new ArrayList<>(Arrays.asList(
            new Color(255, 249, 102), /* Player 1 */
            new Color(66, 134, 244),  /* Player 2 */
            new Color(143, 99, 158),  /* Player 3 */
            new Color(209, 155, 20),  /* Player 4 */
            new Color(209, 96, 20),   /* Player 5 */
            new Color(120, 230, 30),  /* Player 6 */
            new Color(206, 57, 72),   /* Player 7 */
            new Color(72, 196, 188)   /* Player 8 */
    ));

    
    private int outOfGamePlayers = 0;

    private int playerTurnIndex = 0;

    private final List<Player> players = new ArrayList<>();

    /**
     *
     * @param gameBoard
     */
    public BoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    /**
     *
     * @return current player index
     */
    public Player getCurrentPlayer() {
        return getPlayer(playerTurnIndex);
    }

    /**
     *
     * @param player
     * @return current position index of player in game board
     */
    public int getCurrentPositionIndex(Player player) {
        Cell currentPosition = player.getPosition();
        return gameBoard.queryCellIndex(currentPosition.getName());
    }

    /**
     *
     * @return game board after getting the position index of the player
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     *
     * @param positionIndex
     * @param diceValue
     * @return the new position of index after doing dice value
     */
    public int getNewPositionIndex(int positionIndex, int diceValue) {
        return (positionIndex + diceValue) % gameBoard.getCellSize();    
    }

    /**
     *
     * @return numbers of players
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     *
     * @return numbers of out of game players.
     */
    public int getOutOfGamePlayersNumber() {
        return outOfGamePlayers;
    }
    
    /**
     *
     * @param index
     * @return player of the index
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }
    
    /**
     *
     * @param player
     * @return index of new player.
     */
    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }
    
    /**
     *
     * @return list of all players
     */
    public List<Player> getPlayers() {
        return players;
    }
    
    /**
     *
     * @return turn index of player
     */
    public int getTurn() {
        return playerTurnIndex;
    }
	
    /**
     *
     * @param player
     * @param diceValue
     */
    public void movePlayer(Player player, int diceValue) {
        int positionIndex = getCurrentPositionIndex(player);
        int newIndex = getNewPositionIndex(positionIndex, diceValue);
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellSize())
            player.setMoney(player.getMoney() + 200);
        player.setPosition(gameBoard.getCell(newIndex));
    }
    
    /**
     * Game over/out of game player and remove.
     */
    public void removePlayer() {
        outOfGamePlayers++;
    }
   
    /**
     * Set again player to position 0
     */
    public void reset() {    
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        playerTurnIndex = 0;
    }
    
    /**
     * set board of the game  
     * @param board
     */
    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }
    
    /**
     * set number of players who input this game and add them in board and get each of them a color
     * @param number
     */
    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Player player = new Player(gameBoard.getCell(0));
            player.setPlayerColor(playerColors.get(i));
            players.add(player);
        }
    }
    
    /**
     * Calculate the new player turn index
     */
    public void switchTurn() {
        playerTurnIndex = (playerTurnIndex + 1) % getNumberOfPlayers();
    }
}
