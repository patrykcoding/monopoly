package monopoly;

/**
 *
 * @author owner
 */
public abstract class Cell {

    
    private boolean available = true;

    private String name;

    protected Player player;

    /**
     *
     * @return name of cell.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Player who have the turn to play.
     */
    public Player getOwner() {
        return player;
    }
	
    /**
     *Get price of cell.
     * @return the price.
     */
    public int getPrice() {
        return 0;
    }

    /**
     *
     * @return true if cell is available
     */
    public boolean isAvailable() {
        return available;
    }
	
    /**
     *will be implemented by the child classes. 
     * @param mainCtl
     */
    public void playAction(MainController mainCtl) {};

    /**
     *Set the cell availability
     * @param available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
	
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     *
     * @return name of cell.
     */
    @Override
    public String toString() {
        return name;
    }
}
