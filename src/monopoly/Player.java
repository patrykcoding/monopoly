package monopoly;

import java.awt.Color;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    //the key of propertyColors is the name of the playerColor group.
    private final Map<String, Integer> propertyColors = new HashMap<>();
    private boolean inJail;
    private int money;
    private String name;
    private Color playerColor;
    
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
        addPropertyColor(colorGroup);
    }
    
    public void addRailRoad(RailRoadCell railroad) {
        railroads.add(railroad);
        String colorGroup = RailRoadCell.COLOR_GROUP;
        addPropertyColor(colorGroup);
    }
    
    public void addProperty(PropertyCell property) {
        properties.add(property);
        String colorGroup = property.getColorGroup();
        addPropertyColor(colorGroup);
    }
    
    public void addPropertyColor(String colorGroup) {
        propertyColors.put(colorGroup, getPropertyNumberForColor(colorGroup) + 1);
    }
	
    public void setPlayerColor(Color color) {
        this.playerColor = color;
    }
    
    public Color getPlayerColor() {
        if (playerColor == null) {
            return Color.GREEN;
        }
        return playerColor;
    }

    public boolean checkProperty(String property) {
        return properties.stream().map((propertie) -> 
                (Cell) propertie).anyMatch((cell) -> 
                (cell.getName().equals(property)));
    }
	
    public ArrayList<PropertyCell> getPropertyCells() {
        return properties;
    }
    
    public ArrayList<Cell> getAllProperties() {
        ArrayList<Cell> list = new ArrayList<>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return list;
    }

    public int getMoney() {
            return this.money;
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
    
    public Map<String, Integer> getPropertyColors() {
        return propertyColors;
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
        Integer number = propertyColors.get(name);
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
    
    public void removePropertyCell(PropertyCell property) {
        properties.remove(property);
    }
    
    public void removeRailroadCell(RailRoadCell railroad) {
        railroads.remove(railroad);
    }
    
    public void removeUtilityCell(UtilityCell utility) {
        utilities.remove(utility);
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
