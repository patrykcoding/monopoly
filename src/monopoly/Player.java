package monopoly;

import java.awt.Color;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
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
    private Color color;
    
    private Cell position;
    private ArrayList<PropertyCell> properties = new ArrayList<>();
    private ArrayList<RailRoadCell> railroads = new ArrayList<>();
    private ArrayList<UtilityCell> utilities = new ArrayList<>();
    
    public Player() {
        inJail = false;
    }
    
    public void addUtility (UtilityCell utility) {
        utilities.add(utility);
        String colorGroup = UtilityCell.COLOR_GROUP;
        addColorGroup(colorGroup);
    }
    
    public void addRailRoad(RailRoadCell railroad) {
        railroads.add(railroad);
        String colorGroup = RailRoadCell.COLOR_GROUP;
        addColorGroup(colorGroup);
    }
    
    public void addProperty(PropertyCell property) {
        properties.add(property);
        String colorGroup = property.getColorGroup();
        addColorGroup(colorGroup);
    }
    
    public void addColorGroup(String colorGroup) {
        colorGroups.put(colorGroup, getPropertyNumberForColor(colorGroup) + 1);
    }
	
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        if (color == null) {
            return Color.GREEN;
        }
        return color;
    }
    
    public boolean canBuyHouse(GameBoard gameBoard) {
        return (getMonopolies(gameBoard).length != 0);
    }

    public boolean checkProperty(String property) {
        return properties.stream().map((propertie) -> 
                (Cell) propertie).anyMatch((cell) -> 
                (cell.getName().equals(property)));
    }
	
    public void exchangeProperty(Player player) {
        properties.stream().map((property) -> {
            property.setPlayer(player);
            return property;
        }).forEach((property) -> {
            if (player == null) {
                property.setAvailable(true);
                property.setNumHouses(0);
            } else {
                player.addProperty(property);
            }
        });
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

    public String[] getMonopolies(GameBoard gameBoard) {
        ArrayList<String> monopolies = new ArrayList<>();
        Set colors = colorGroups.keySet();
        
        for (int i = 0; i < colors.size(); i++) {
            String colorGroup = colors.toArray()[i].toString();
            if (!colorGroup.equals(RailRoadCell.COLOR_GROUP) && !colorGroup.equals(UtilityCell.COLOR_GROUP)) {
                Integer num = colorGroups.get(colorGroup);
                if (num == gameBoard.getPropertyNumberForColor(colorGroup)) {
                    monopolies.add(colorGroup);
                }
            }
        }
        return monopolies.toArray(new String[monopolies.size()]);
    }

    public String getName() {
        return name;
    }
    
    public void subtractMoney(int money) {
        this.money -= money;
    }

    public void addMoney(int money) {
        this.money += money;
    }
    
    public Cell getPosition() {
        return this.position;
    }
	
    public PropertyCell getProperty(int index) {
        return properties.get(index);
    }
	
    public int getPropertyCount() {
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
    
    public void resetProperties() {
    	properties = new ArrayList<>();
    	railroads = new ArrayList<>();
    	utilities = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return name;
    }
}
