package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardController {
    public static final int MAX_PLAYER = 8;
    
    private final ArrayList<Player> players = new ArrayList<>();
    private GameBoard gameBoard;
    private int playerTurnIdx = 0;
    
    /* Colors of the players on the gameBoard */
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

    /**
     * Constructor for the Monopoly BoardController.
     * 
     * @param gameBoard GameBoard representing a Monopoly board
     */
    public BoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    /**
     * Returns the current player who has their playerTurnIndex.
     * 
     * @return The player who has their playerTurnIndex
     */
    public Player getCurrentPlayer() {
        return getPlayer(playerTurnIdx);
    }

    /**
     * Gets the current position index on the gameBoard of the specified player.
     * 
     * @param player The player to get the position of
     * 
     * @return Returns the index on the gameBoard of the current player
     */
    public int getCurrentPositionIndex(Player player) {
        Cell currentPosition = player.getPosition();
        return gameBoard.queryCellIndex(currentPosition.getName());
    }

    /**
     * Gets the associated gameBoard of the boardController.
     * 
     * @return Returns a GameBoard representing a monopoly board
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
    /**
     * Gets a specified player based on their index value.
     * 
     * @param index The index of the player ('0' being Player 1)
     * 
     * @return Returns the player at the specified index
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * Gets the index of the specified player ('0' being Player 1)
     * 
     * @param player A player in the game of monopoly
     * 
     * @return Returns the index value of the player
     */
    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    /**
     * Calculates a players new position on the gameBoard by using a position
     * index and a dice value.
     * 
     * @param positionIndex Position index on the gameBoard for a player 
     * @param diceValue Amount to move the player forward by
     * 
     * @return Returns the index for the players new position
     */
    public int getNewPositionIndex(int positionIndex, int diceValue) {
        return (positionIndex + diceValue) % gameBoard.getCellSize();    
    }

    /**
     * Returns the number of players for the current game of monopoly on the
     * gameBoard.
     * 
     * @return Amounts of players
     */
    public int getNumberOfPlayers() {
        return players.size();
    }
    
    /**
     * Access method for the players list.
     * 
     * @return Returns the ArrayList of Players for the current Controller
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    /**
     * Returns the index of the player who's turn it currently is. ('0' is
     * Player 1)
     * 
     * @return Returns the current players index
     */
    public int getTurn() {
        return playerTurnIdx;
    }
	
    /**
     * Moves the specified player by the diceValue forward on the gameBoard. 
     * This will not set the players position, but instead just advance them 
     * from their current position.
     * 
     * @param player Player to move on the gameBoard
     * @param diceValue Amount to move the player by
     */
    public void movePlayer(Player player, int diceValue) {
        int positionIndex = getCurrentPositionIndex(player);
        int newIndex = getNewPositionIndex(positionIndex, diceValue);
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellSize()) {
            player.setMoney(player.getMoney() + 200);
        }
        player.setPosition(gameBoard.getCell(newIndex));
    }
    
    /**
     * Resets the controller by assigning all the current players back onto
     * the first cell of the gameBoard and resetting the playerTurnIdx. This
     * will not change the current state of the player (e.g. money, name, color,
     * etc)
     */
    public void reset() {    
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        playerTurnIdx = 0;
    }

    /**
     * Sets a new gameBoard to the controller.
     * 
     * @param board A GameBoard representing a Monopoly board
     */
    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }
    
    /**
     * Sets the number of players on the gameBoard. This will clear any previous
     * players on the gameBoard. Players are automatically assigned to start on 
     * the first cell on the gameBoard.
     * 
     * @param number Number of players
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
     * Switches to the next players playerTurnIndex. (Goes in order)
     */
    public void switchTurn() {
        playerTurnIdx = (playerTurnIdx + 1) % getNumberOfPlayers();
    }
}
