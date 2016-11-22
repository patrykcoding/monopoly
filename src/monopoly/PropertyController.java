
package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;

public class PropertyController {
    private final BoardController boardCtl;
    
    public PropertyController(BoardController boardCtl) {
        this.boardCtl = boardCtl;
    }

    public List<Player> getSellerList() {
        List<Player> sellers = new ArrayList<>();
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
    
    public void buyProperty(TradeDeal deal) {
        Cell property = deal.getProperty();
        Player buyer = deal.getBuyer();
        property.setPlayer(buyer);
        
        if (property instanceof PropertyCell) {
            buyer.addProperty((PropertyCell) property);
            updatePropertyRent((PropertyCell) property);
        }
        if (property instanceof RailRoadCell) {
            buyer.addRailRoad((RailRoadCell) property);
        }
        if (property instanceof UtilityCell) {
            buyer.addUtility((UtilityCell) property);
        }
        buyer.subtractMoney(deal.getAmount());
    }
    
    public void purchase() {
        Player currentPlayer = boardCtl.getCurrentPlayer();

        if (currentPlayer.getPosition().isAvailable()) {
            Cell cell = currentPlayer.getPosition();
            TradeDeal deal = new TradeDeal(cell, currentPlayer, cell.getPrice());
            buyProperty(deal);
            cell.setAvailable(false);
        }
    }
    
    public List<String> getMonopolies(Player player) {
        Map<String, Integer> propertyColors = player.getPropertyColors();
        GameBoard gameBoard = boardCtl.getGameBoard();
        List<String> monopolies = new ArrayList<>();
        Set<String> colors = propertyColors.keySet();
        
        for (int i = 0; i < colors.size(); i++) {
            String propertyColor = colors.toArray()[i].toString();
            if (!propertyColor.equals(RailRoadCell.COLOR_GROUP) && !propertyColor.equals(UtilityCell.COLOR_GROUP)) {
                Integer num = propertyColors.get(propertyColor);
                if (num == gameBoard.getPropertyNumberForColor(propertyColor)) {
                    monopolies.add(propertyColor);
                }
            }
        }
        return monopolies;
    }
    
    public boolean canBuyHouse() {
        return (!getMonopolies(boardCtl.getCurrentPlayer()).isEmpty());
    }
    
    public void giveAllProperties(Player fromPlayer, Player toPlayer) {
        List<PropertyCell> properties = fromPlayer.getPropertyCells();
        properties.stream().map((property) -> {
            property.setPlayer(toPlayer);
            return property;
        }).forEach((property) -> {
            if (toPlayer == null) {
                property.setAvailable(true);
                property.setNumHouses(0);
            } else {
                toPlayer.addProperty(property);
            }
        });
        properties.clear();
    }
	
    public void payRentTo(Player owner, int rentValue) {
        Player currentPlayer = boardCtl.getCurrentPlayer();
        int playerMoney = currentPlayer.getMoney();
        
        if (playerMoney < rentValue) {
            owner.addMoney(playerMoney);
            currentPlayer.subtractMoney(rentValue);
        } else {
            currentPlayer.subtractMoney(rentValue);
            owner.addMoney(rentValue);
        }
        
        if (currentPlayer.isBankrupt()) {
            currentPlayer.setMoney(0);
            giveAllProperties(currentPlayer, owner);
        }
    }
    
    public void sellProperty(TradeDeal deal) {
        Player seller = deal.getSeller();
        Cell property = boardCtl.getGameBoard().queryCell(deal.getPropertyName());
        
        property.setPlayer(null);
        if (property instanceof PropertyCell) {
            seller.removePropertyCell((PropertyCell)property);
            updatePropertyRent((PropertyCell) property);    
        }
        if (property instanceof RailRoadCell) {
            seller.removeRailroadCell((RailRoadCell)property);
        }
        if (property instanceof UtilityCell) {
            seller.removeUtilityCell((UtilityCell)property);
        }
        seller.addMoney(deal.getAmount());
    }
    
    public void updatePropertyRent(PropertyCell property) {
        int rent = property.getRent();
        int newRent = property.getRent();
        int numHouses = property.getNumHouses();
        Player owner = property.getOwner();
        
        if (owner == null) {
            property.setRent(property.originalRent());
        } else {
            List<String> monopolies = getMonopolies(owner);
            for (String monopolie : monopolies) {
                if (monopolie.equals(property.getColorGroup())) {
                    newRent = rent * 2;
                }
            }
            if (numHouses > 0) {
                newRent = rent * (numHouses + 1);
            }
            property.setRent(newRent);
        }
    }
}
