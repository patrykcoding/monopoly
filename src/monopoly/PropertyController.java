package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
import monopoly.enums.ColorGroup;

public class PropertyController {
    private final BoardController boardController;
    private final GameBoard gameBoard;
    
    public PropertyController(BoardController boardController) {
        this.boardController = boardController;
        this.gameBoard = boardController.getGameBoard();
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
    
    public boolean canBuyHouse() {
        return (!getMonopolies(boardController.getCurrentPlayer()).isEmpty());
    }
    
    private void doublePropertyRent(ColorGroup colorGroup) {
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(colorGroup);
        
        properties.stream().forEach((property) -> {
            property.setRent(property.originalRent() * 2);
        });
    }
    
    public List<ColorGroup> getMonopolies(Player player) {
        Map<ColorGroup, Integer> propertyColors = player.getPropertyColors();
        List<ColorGroup> monopolies = new ArrayList<>();
        Set<ColorGroup> colors = propertyColors.keySet();
        
        for (int i = 0; i < colors.size(); i++) {
            ColorGroup propertyColor = (ColorGroup) colors.toArray()[i];
            if (!propertyColor.equals(ColorGroup.RAILROAD) && !propertyColor.equals(ColorGroup.UTILITY)) {
                Integer num = propertyColors.get(propertyColor);
                if (num == gameBoard.getPropertyNumberForColor(propertyColor)) {
                    monopolies.add(propertyColor);
                }
            }
        }
        return monopolies;
    }
    
    public List<Player> getSellerList() {
        List<Player> sellers = new ArrayList<>();
        boardController.getPlayers().stream().filter((player) ->
                (player != boardController.getCurrentPlayer())).forEach((player) -> {
                    sellers.add(player);
                });
        return sellers;
    }
    
    public void giveAllProperties(Player fromPlayer, Player toPlayer) {
        List<PropertyCell> properties = fromPlayer.getPropertyCells();
        List<RailRoadCell> railroads = fromPlayer.getRailRoadCells();
        List<UtilityCell> utilities = fromPlayer.getUtilityCells();
        
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
        railroads.stream().map((railroad) -> {
            railroad.setPlayer(toPlayer);
            return railroad;
        }).forEach((railroad) -> {
            if (toPlayer == null) {
                railroad.setAvailable(true);
            } else {
                toPlayer.addRailRoad(railroad);
            }
        });
        railroads.clear();
        utilities.stream().map((utility) -> {
            utility.setPlayer(toPlayer);
            return utility;
        }).forEach((utility) -> {
            if (toPlayer == null) {
                utility.setAvailable(true);
            } else {
                toPlayer.addUtility(utility);
            }
        });
        utilities.clear();
    }
	
    public void payRentTo(Player owner, int rentValue) {
        Player currentPlayer = boardController.getCurrentPlayer();
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
    
    public void purchase() {
        Player currentPlayer = boardController.getCurrentPlayer();
        
        if (currentPlayer.getPosition().isAvailable()) {
            Cell cell = currentPlayer.getPosition();
            TradeDeal deal = new TradeDeal(cell, currentPlayer, cell.getPrice());
            buyProperty(deal);
            cell.setAvailable(false);
        }
    }
    
    public int purchaseHouse(ColorGroup selectedMonopoly, int houses) {
        Player currentPlayer = boardController.getCurrentPlayer();
        
        int numOfHouses = 0;
        int money = currentPlayer.getMoney();
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (properties.size() * (properties.get(0).getHousePrice() * houses)))) {
            for (PropertyCell property : properties) {
                numOfHouses = property.getNumHouses() + houses;
                if (numOfHouses <= 5) {
                    property.setNumHouses(numOfHouses);
                    currentPlayer.subtractMoney(property.getHousePrice() * houses);
                    updatePropertyRent(property);
                }
            }
        }
        return numOfHouses;
    }
    
    private void resetPropertyRent(ColorGroup colorGroup) {
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(colorGroup);
        
        properties.stream().forEach((property) -> {
            property.setRent(property.originalRent());
        });
    }
    
    public void sellProperty(TradeDeal deal) {
        Player seller = deal.getSeller();
        Cell property = deal.getProperty();
        
        if (property instanceof PropertyCell) {
            seller.removePropertyCell((PropertyCell)property);
            updatePropertyRent((PropertyCell)property);    
        } else if (property instanceof RailRoadCell) {
            seller.removeRailroadCell((RailRoadCell)property);
            updateRailRoadRent((RailRoadCell)property);
        } else if (property instanceof UtilityCell) {
            seller.removeUtilityCell((UtilityCell)property);
        }
        seller.addMoney(deal.getAmount());
    }
    
    private void updatePropertyRent(PropertyCell property) {
        int previousRent = property.getRent();
        int numHouses = property.getNumHouses();
        int newRent;
        Player owner = property.getOwner();
        
        resetPropertyRent(property.getColorGroup());
        if (owner == null)
            return;
        List<ColorGroup> monopolies = getMonopolies(owner);
        for (ColorGroup monopoly : monopolies) {
            if (monopoly.equals(property.getColorGroup()))
                doublePropertyRent(monopoly);            
            if (numHouses > 0) {
                newRent = previousRent * (numHouses + 1);
                property.setRent(newRent);
            }
        }
    }

    private void updateRailRoadRent(RailRoadCell railroad) {
        Player owner = railroad.getOwner();
        int basePrice = railroad.getBaseRent();
        
        /* Reset to basePrice */
        railroad.setRent(basePrice);
        if (owner == null)
            return;
        List<RailRoadCell> railRoads = owner.getRailRoadCells();
        int newRent = basePrice * (int)Math.pow(2, owner.numberOfRailroads() - 1);
            
        railRoads.stream().forEach((playersRailroad) -> {
            playersRailroad.setRent(newRent);
        });
    }
}
