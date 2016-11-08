package monopoly.cells;

import monopoly.Cell;
import monopoly.GameMaster;
import monopoly.Player;

public class PropertyCell extends Cell {
    private String colorGroup;
    private int housePrice;
    private int numHouses;
    private int rent;
    private int sellPrice;

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

    public int getRent(GameMaster master) {
        int rentToCharge = rent;
        String [] monopolies = player.getMonopolies(master);
        for (String monopolie : monopolies) {
            if (monopolie.equals(colorGroup)) {
                rentToCharge = rent * 2;
            }
        }
        if (numHouses > 0) {
            rentToCharge = rent * (numHouses + 1);
        }
        return rentToCharge;
    }

    @Override
    public void playAction(GameMaster master) {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = master.getCurrentPlayer();
            if (player != currentPlayer) {
                currentPlayer.payRentTo(player, getRent(master));
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
        this.rent = rent;
    }
}
