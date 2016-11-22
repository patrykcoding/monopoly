package monopoly.cells;

import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class RailRoadCell extends Cell {
    private int baseRent = 0;
    private int rent;
    public static String COLOR_GROUP = "RAILROAD";
    private int price;

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
        this.rent = baseRent;
    }
    
    public int getBaseRent() {
        return baseRent;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setRent(int rent) {
        if (baseRent == 0) {
            baseRent = rent;
        }
        this.rent = rent;
    }
    
    public int getRent() {
        return rent;
    }

    @Override
    public void playAction(MainController mainCtl) {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = mainCtl.getCurrentPlayer();
            if (player != currentPlayer) {
                mainCtl.payRentTo(player, getRent());
            }
        }
    }
}
