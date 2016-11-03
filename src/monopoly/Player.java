package monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player {
    //the key of colorGroups is the name of the color group.
    private final Map<String, Integer> colorGroups = new HashMap<>();
    private boolean inJail;
    private int money;
    private String name;

    private Cell position;
    private ArrayList<PropertyCell> properties = new ArrayList<>();
    private ArrayList<RailRoadCell> railroads = new ArrayList<>();
    private ArrayList<UtilityCell> utilities = new ArrayList<>();

    public Player() {
        GameBoard gb = GameMaster.instance().getGameBoard();
        inJail = false;
        if (gb != null) {
            position = gb.queryCell("Go");
        }
    }

    public void buyProperty(Cell property, int amount) {
        property.setPlayer(this);
        if (property instanceof PropertyCell) {
            PropertyCell cell = (PropertyCell)property;
            properties.add(cell);
            colorGroups.put(
                    cell.getColorGroup(),
                    getPropertyNumberForColor(cell.getColorGroup()) + 1
            );
        }
        if (property instanceof RailRoadCell) {
            railroads.add((RailRoadCell) property);
            colorGroups.put(
                    RailRoadCell.COLOR_GROUP, 
                    getPropertyNumberForColor(RailRoadCell.COLOR_GROUP) + 1
            );
        }
        if (property instanceof UtilityCell) {
            utilities.add((UtilityCell) property);
            colorGroups.put(
                    UtilityCell.COLOR_GROUP, 
                    getPropertyNumberForColor(UtilityCell.COLOR_GROUP) + 1
            );
        }
        setMoney(getMoney() - amount);
    }
	
    public boolean canBuyHouse() {
        return (getMonopolies().length != 0);
    }

    public boolean checkProperty(String property) {
        return properties.stream().map((propertie) -> 
                (Cell) propertie).anyMatch((cell) -> 
                        (cell.getName().equals(property)));
    }
	
    public void exchangeProperty(Player player) {
        for (int i = 0; i < getPropertyNumber(); i++ ) {
            PropertyCell cell = getProperty(i);
            cell.setPlayer(player);
            if (player == null) {
                cell.setAvailable(true);
                cell.setNumHouses(0);
            } else {
                player.properties.add(cell);
                colorGroups.put(
                        cell.getColorGroup(), 
                        getPropertyNumberForColor(cell.getColorGroup()) + 1
                );
            }
        }
        properties.clear();
    }
    
    public Cell[] getAllProperties() {
        ArrayList list = new ArrayList();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return (Cell[])list.toArray(new Cell[list.size()]);
    }

    public int getMoney() {
            return this.money;
    }

    public String[] getMonopolies() {
        ArrayList monopolies = new ArrayList();
        Set colors = colorGroups.keySet();
        
        for (int i = 0; i < colors.size(); i++) {
            String color = (String) colors.toArray()[i];
            if (!color.equals(RailRoadCell.COLOR_GROUP) && !color.equals(UtilityCell.COLOR_GROUP)) {
                Integer num = colorGroups.get(color);
                GameBoard gameBoard = GameMaster.instance().getGameBoard();
                if (num == gameBoard.getPropertyNumberForColor(color)) {
                    monopolies.add(color);
                }
            }
        }
        return (String[])monopolies.toArray(new String[monopolies.size()]);
    }

    public String getName() {
        return name;
    }

    public void getOutOfJail() {
        money -= JailCell.BAIL;
        if (isBankrupt()) {
            money = 0;
            exchangeProperty(null);
        }
        inJail = false;
        GameMaster.instance().updateGUI();
    }

    public Cell getPosition() {
        return this.position;
    }
	
    public PropertyCell getProperty(int index) {
        return (PropertyCell)properties.get(index);
    }
	
    public int getPropertyNumber() {
        return properties.size();
    }

    private int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    public boolean isBankrupt() {
        return money <= 0;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int numberOfRR() {
        return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
    }

    public int numberOfUtil() {
        return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
    }
	
    public void payRentTo(Player owner, int rentValue) {
        if (money < rentValue) {
            owner.money += money;
            money -= rentValue;
        } else {
            money -= rentValue;
            owner.money +=rentValue;
        }
        
        if (isBankrupt()) {
            money = 0;
            exchangeProperty(owner);
        }
    }
	
    public void purchase() {
        if (getPosition().isAvailable()) {
            Cell c = getPosition();
            c.setAvailable(false);
            if (c instanceof PropertyCell) {
                PropertyCell cell = (PropertyCell)c;
                purchaseProperty(cell);
            }
            if (c instanceof RailRoadCell) {
                RailRoadCell cell = (RailRoadCell)c;
                purchaseRailRoad(cell);
            }
            if (c instanceof UtilityCell) {
                UtilityCell cell = (UtilityCell)c;
                purchaseUtility(cell);
            }
        }
    }
	
    public void purchaseHouse(String selectedMonopoly, int houses) {
        GameBoard gb = GameMaster.instance().getGameBoard();
        PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            for (PropertyCell cell : cells) {
                int newNumber = cell.getNumHouses() + houses;
                if (newNumber <= 5) {
                    cell.setNumHouses(newNumber);
                    this.setMoney(money - (cell.getHousePrice() * houses));
                    GameMaster.instance().updateGUI();
                }
            }
        }
    }
	
    private void purchaseProperty(PropertyCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    private void purchaseRailRoad(RailRoadCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    private void purchaseUtility(UtilityCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    public void sellProperty(Cell property, int amount) {
        property.setPlayer(null);
        if (property instanceof PropertyCell) {
            properties.remove((PropertyCell)property);
        }
        if (property instanceof RailRoadCell) {
            railroads.remove((RailRoadCell)property);
        }
        if (property instanceof UtilityCell) {
            utilities.remove((UtilityCell)property);
        }
        setMoney(getMoney() + amount);
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Cell newPosition) {
        this.position = newPosition;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public void resetProperty() {
    	properties = new ArrayList<>();
    	railroads = new ArrayList<>();
    	utilities = new ArrayList<>();
    }
}
