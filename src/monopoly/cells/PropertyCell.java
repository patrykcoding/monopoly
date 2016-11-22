package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class PropertyCell extends Cell {
    private String colorGroup;
    private int housePrice;
    private int numHouses;
    private int rent;
    private int sellPrice;
    private int originalRent = 0;
    
    public String getColorGroup() {
        return colorGroup;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getNumHouses() {
        return numHouses;
    }
    
    @Override
    public int getPrice() {
        return sellPrice;
    }

    @Override
    public void playAction(MainController mainCtl) {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = mainCtl.getCurrentPlayer();
            if (player != currentPlayer) {
                mainCtl.payRentTo(player, rent);
            }
        }
    }

    public void setColorGroup(String colorGroup) {
        this.colorGroup = colorGroup;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public void setPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setRent(int rent) {
        if (originalRent == 0) {
            originalRent = rent;
        }
        this.rent = rent;
    }

    public int getRent() {
        return rent;
    }

    public int originalRent() {
        return originalRent;
    }
}