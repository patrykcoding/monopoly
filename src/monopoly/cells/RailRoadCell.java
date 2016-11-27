package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class RailRoadCell extends Cell {
    public static String COLOR_GROUP = "RAILROAD";
    private int baseRent = 0;
    private int price;
    private int rent;
    
    public int getBaseRent() {
        return baseRent;
    }

    
    public int getRent() {
        return rent;
    }

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
        this.rent = baseRent;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRent(int rent) {
        if (baseRent == 0) {
            baseRent = rent;
        }
        this.rent = rent;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
    
    @Override
    public void playAction(MainController mainCtl) {
        Player currentPlayer;
        if (isAvailable())
            return;
        currentPlayer = mainCtl.getCurrentPlayer();
        if (player != currentPlayer)
            mainCtl.payRentTo(player, getRent());
    }
}
