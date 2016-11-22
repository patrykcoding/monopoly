
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
    private final GameBoard gameBoard;
    
    public PropertyController(BoardController boardCtl) {
        this.boardCtl = boardCtl;
        this.gameBoard = boardCtl.getGameBoard();
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
        
        int newNumber = 0;
        int money = currentPlayer.getMoney();
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (properties.size() * (properties.get(0).getHousePrice() * houses)))) {
            for (PropertyCell property : properties) {
                newNumber = property.getNumHouses() + houses;
                if (newNumber <= 5) {
                    property.setNumHouses(newNumber);
                    currentPlayer.subtractMoney(property.getHousePrice() * houses);
                    updatePropertyRent(property);
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
            updateRailRoadRent((RailRoadCell)property);
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
        Cell property = deal.getProperty();
        
        property.setPlayer(null);
        if (property instanceof PropertyCell) {
            seller.removePropertyCell((PropertyCell)property);
            updatePropertyRent((PropertyCell) property);    
        }
        if (property instanceof RailRoadCell) {
            seller.removeRailroadCell((RailRoadCell)property);
            updateRailRoadRent((RailRoadCell)property);
        }
        if (property instanceof UtilityCell) {
            seller.removeUtilityCell((UtilityCell)property);
        }
        seller.addMoney(deal.getAmount());
    }
    
    public void updatePropertyRent(PropertyCell property) {
        int originalRent = property.getRent();
        int newRent;
        int numHouses = property.getNumHouses();
        Player owner = property.getOwner();
        
        if (owner == null) {
            property.setRent(property.originalRent());
        } else {
            List<String> monopolies = getMonopolies(owner);
            for (String monopolie : monopolies) {
                if (monopolie.equals(property.getColorGroup())) {
                    updateColorGroupRent(monopolie);
                }
            
                if (numHouses > 0) {
                    newRent = originalRent * (numHouses + 1);
                    property.setRent(newRent);
                }
            }
        }
    }
    
    public void updateColorGroupRent(String colorGroup) {
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(colorGroup);
        
        properties.stream().forEach((property) -> {
            property.setRent(property.originalRent() * 2);
        });
        
    }
    
    public void updateRailRoadRent(RailRoadCell railroad) {
        Player owner = railroad.getOwner();
        int basePrice = railroad.getBaseRent();
        
        if (owner == null) {
            railroad.setRent(basePrice);
        } else {
            int newRent = basePrice * (int)Math.pow(2, owner.numberOfRR() - 1);
            railroad.setRent(newRent);
        }
    }
}
