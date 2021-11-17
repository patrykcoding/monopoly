package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
import monopoly.enums.ColorGroup;

/**
 *
 * @author owner
 */
public class Player {
//The initial money for player is 1500$
    private final int INITIAL_MONEY = 1500;

    private boolean inJail;

    private int money;

    private String name;

    private boolean isOutOfGame;

    private Color playerColor;

    private Cell position;

    private List<PropertyCell> properties = new ArrayList<>();
    //the key of propertyColors is the name of the playerColor group.
 
    private final Map<ColorGroup, Integer> propertyColors = new HashMap<>();

    private List<RailRoadCell> railroads = new ArrayList<>();

    private List<UtilityCell> utilities = new ArrayList<>();
    
    /**
     *
     * @param position
     */
    public Player(Cell position) {
        this.position = position;
        inJail = false;
        isOutOfGame = false;
        playerColor = Color.GREEN;
        money = INITIAL_MONEY;
    }
    
    /**
     *Add money in the wallet of player
     * @param money
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     *Add property for player
     * @param property
     */
    public void addProperty(PropertyCell property) {
        properties.add(property);
        addPropertyColor(property.getColorGroup());
    }
    
    /**
     *Add property color for player by putting some variables
     * @param colorGroup
     */
    private void addPropertyColor(ColorGroup colorGroup) {
        propertyColors.put(colorGroup, getPropertyNumberForColor(colorGroup) + 1);
    }
    
    /**
     *Add RailRoad to the player
     * @param railroad
     */
    public void addRailRoad(RailRoadCell railroad) {
        railroads.add(railroad);
        addPropertyColor(ColorGroup.RAILROAD);
    }
    
    /**
     *Add utility to the player
     * @param utility
     */
    public void addUtility (UtilityCell utility) {
        utilities.add(utility);
        addPropertyColor(ColorGroup.UTILITY);
    }

    /**
     *
     * @param property
     * @return properties after checking 
     */
    public boolean checkProperty(String property) {
        return properties.stream().map((propertie) -> 
                (Cell) propertie).anyMatch((cell) -> 
                (cell.getName().equals(property)));
    }

    /**
     *
     * @return list of cell that consists of all properties,utilities and railroads
     */
    public List<Cell> getAllProperties() {
        List<Cell> list = new ArrayList<>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return list;
    }

    /**
     *
     * @return money of the player
     */
    public int getMoney() {
            return this.money;
    }

    /**
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     *
     * @return player's color
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     *
     * @return cell of player position
     */
    public Cell getPosition() {
        return this.position;
    }
	
    /**
     *
     * @param index
     * @return Property of the given index of the cell
     */
    public PropertyCell getProperty(int index) {
        return properties.get(index);
    }
    
    public List<PropertyCell> getPropertyCells() {
        return properties;
    }
    
    /**
     * 
     * @return HashMap of propertyColors which contain ColorGroup and its related integer.
     */
    public Map<ColorGroup, Integer> getPropertyColors() {
        return propertyColors;
    }
	
    /**
     *
     * @return size of properties. 
     */
    public int getPropertyCount() {
        return properties.size();
    }
    
    /**
     *
     * @param colorGroup
     * @return number for color
     */
    private int getPropertyNumberForColor(ColorGroup colorGroup) {
        Integer number = propertyColors.get(colorGroup);
        if (number != null) {
            return number;
        }
        return 0;
    }
    
    /**
     *
     * @return list of RailRoadCell.
     */
    public List<RailRoadCell> getRailRoadCells() {
        return railroads;
    }

    /**
     *
     * @return list of UtilityCell.
     */
    public List<UtilityCell> getUtilityCells() {
        return utilities;
    }
    
    /**
     *
     * @return true if money less than or equal to zero.
     */
    public boolean isBankrupt() {
        return money <= 0;
    }

    /**
     *
     * @return inJail status.
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     *
     * @return OutOfGame status.
     */
    public boolean isOutOfGame() {
        return isOutOfGame;
    }
    
    /**
     *
     * @return size of railroads.
     */
    public int numberOfRailroads() {
        return railroads.size();
    }

    /**
     *
     * @return size of utilities.
     */
    public int numberOfUtilities() {
        return utilities.size();
    }
    
    /**
     *Remove given property cell and its color.
     * @param property
     */
    public void removePropertyCell(PropertyCell property) {
        properties.remove(property);
        removePropertyColor(property.getColorGroup());
    }
    
    /**
     *Remove given colorGroup. 
     * @param colorGroup
     */
    private void removePropertyColor(ColorGroup colorGroup) {
        propertyColors.remove(colorGroup);
    }
    
    /**
     *Remove given RailroadCell.
     * @param railroad
     */
    public void removeRailroadCell(RailRoadCell railroad) {
        railroads.remove(railroad);
        removePropertyColor(ColorGroup.RAILROAD);
    }
    
    /**
     *Remove given utilityCell.
     * @param utility
     */
    public void removeUtilityCell(UtilityCell utility) {
        utilities.remove(utility);
        removePropertyColor(ColorGroup.UTILITY);
    }
    
    /**
     * Set Again all array lists of properties,railroads and utilities.
     */
    public void resetProperties() {
        properties = new ArrayList<>();
        railroads = new ArrayList<>();
        utilities = new ArrayList<>();
    }
    
    /**
     *
     * @param inJail
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     *
     * @param money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Set player's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *Set th out of game state.
    */
    public void setOutOfGame() {
        isOutOfGame = true;
    }
    
    /**
     *
     * @param color
     */
    public void setPlayerColor(Color color) {
        this.playerColor = color;
    }

    /**
     *
     * @param newPosition
     */
    public void setPosition(Cell newPosition) {
        this.position = newPosition;
    }
    
    /**
     *Reduce the money by the given amount of money.
     * @param money
     */
    public void subtractMoney(int money) {
        this.money -= money;
    }
  
    /**
     *
     * @return Player's Name.
     */
    @Override
    public String toString() {
        return name;
    }
}
