package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

/**
 * RailRoadCell class (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class RailRoadCell extends Cell {

    public static String COLOR_GROUP = "RAILROAD";
    
    private int baseRent = 0;

    private int price;

    private int rent;
    
    /**
     *
     * @return the amount of BaseRent
     */
    public int getBaseRent() {
        return baseRent;
    }

    /**
     *
     * @return the amount of rent
     */
    public int getRent() {
        return rent;
    }

    /**
     *
     * @param baseRent
     */
    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
        this.rent = baseRent;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *set the cost of rent , in the base if the rent is zero , it will be rent by any amount.
     * @param rent
     */
    public void setRent(int rent) {
        if (baseRent == 0) {
            baseRent = rent;
        }
        this.rent = rent;
    }
    
    /**
     *
     * @return price 
     */
    @Override
    public int getPrice() {
        return price;
    }
    
    /**
     * If the cell is not available and the player is not a current player , The player will pay Rent.
     * @param mainController
     */
    @Override
    public void playAction(MainController mainController) {
        Player currentPlayer;
        if (isAvailable())
            return;
        currentPlayer = mainController.getCurrentPlayer();
        if (player != currentPlayer)
            mainController.payRentTo(player, getRent());
    }
}
