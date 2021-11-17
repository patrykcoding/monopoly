package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;
import monopoly.enums.ColorGroup;

/**
 * PropertyCell (subclass) inherits the attributes and methods from the Cell class (superclass)
 * @author owner
 */
public class PropertyCell extends Cell {

    private ColorGroup colorGroup;

    private int housePrice;

    private int numHouses;

    private int originalRent = 0;

    private int rent;

    private int sellPrice;
    
    /**
     *
     * @return color of the group
     */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     *
     * @return price of the house
     */
    public int getHousePrice() {
        return housePrice;
    }

    /**
     *
     * @return number of the house
     */
    public int getNumHouses() {
        return numHouses;
    }
    
    /**
     *
     * @return cost of rent
     */
    public int getRent() {
        return rent;
    }
    
    /**
     *
     * @return cos of original Rent
     */
    public int originalRent() {
        return originalRent;
    }

    /**
     *
     * @param colorGroup
     */
    public void setColorGroup(ColorGroup colorGroup) {
        this.colorGroup = colorGroup;
    }

    /**
     *
     * @param housePrice
     */
    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    /**
     *
     * @param numHouses
     */
    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    /**
     *
     * @param sellPrice
     */
    public void setPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * set the cost of rent , in the basics if the the original rent  is zero , the new rent will be new by any amount.
     * @param rent
     */
    public void setRent(int rent) {
        if (originalRent == 0)
            originalRent = rent;
        this.rent = rent;
    }
    
    /**
     *
     * @return price of selling 
     */
    @Override
    public int getPrice() {
        return sellPrice;
    }

    /**
     * If the cell is not available and the player is not a current player , The player will pay Rent.
     * @param mainController 
     */
    @Override
    public void playAction(MainController mainController) {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = mainController.getCurrentPlayer();
            if (player != currentPlayer) {
                mainController.payRentTo(player, rent);
            }
        }
    }
}