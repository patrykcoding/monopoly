
package monopoly;

import java.util.ArrayList;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;

public class PropertyController {
    private final BoardController boardCtl;
    
    public PropertyController(BoardController boardCtl) {
        this.boardCtl = boardCtl;
    }

    public ArrayList<Player> getSellerList() {
        ArrayList sellers = new ArrayList();
        boardCtl.getPlayers().stream().filter((player) -> 
                (player != boardCtl.getCurrentPlayer())).forEach((player) -> {
            sellers.add(player);
        });
        return sellers;
    }
    
    public int purchaseHouse(String selectedMonopoly, int houses) {
        Player currentPlayer = boardCtl.getCurrentPlayer();
        GameBoard gameBoard = boardCtl.getGameBoard();
        
        int newNumber = 0;
        int money = currentPlayer.getMoney();
        PropertyCell[] cells = gameBoard.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            for (PropertyCell cell : cells) {
                newNumber = cell.getNumHouses() + houses;
                if (newNumber <= 5) {
                    cell.setNumHouses(newNumber);
                    currentPlayer.subtractMoney(cell.getHousePrice() * houses);
                }
            }
        }
        return newNumber;
    }
    
    public void buyProperty(Cell property, int amount) {
        Player currentPlayer = boardCtl.getCurrentPlayer();
        property.setPlayer(currentPlayer);
        if (property instanceof PropertyCell) {
            currentPlayer.addProperty((PropertyCell) property);
        }
        if (property instanceof RailRoadCell) {
            currentPlayer.addRailRoad((RailRoadCell) property);
        }
        if (property instanceof UtilityCell) {
            currentPlayer.addUtility((UtilityCell) property);
        }
        currentPlayer.setMoney(currentPlayer.getMoney() - amount);
    }
    
    public void purchase() {
        Player currentPlayer = boardCtl.getCurrentPlayer();

        if (currentPlayer.getPosition().isAvailable()) {
            Cell cell = currentPlayer.getPosition();
            buyProperty(cell, cell.getPrice());
            cell.setAvailable(false);
        }
    }
}
