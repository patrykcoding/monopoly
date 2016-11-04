package monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    private final ArrayList<Cell> cells = new ArrayList<>();
    private final ArrayList<Card> chanceCards = new ArrayList<>();
    //the key of colorGroups is the name of the color group.
    private final Map<String, Integer> colorGroups = new HashMap<>();
    private final ArrayList<Card> communityChestCards = new ArrayList<>();

    public GameBoard() {
        Cell go = new GoCell();
        addCell(go);
    }

    public void addCard(Card card) {
        if (card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
    public final void shuffleCards() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }
    public final void addCell(Cell cell) {
        cells.add(cell);
    }
	
    public void addCell(PropertyCell cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        colorGroups.put(cell.getColorGroup(), propertyNumber + 1);
        cells.add(cell);
    }

    public Card drawCCCard() {
        Card card = communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

    public Cell getCell(int newIndex) {
        return (Cell)cells.get(newIndex);
    }
	
    public int getCellNumber() {
        return cells.size();
    }
	
    public PropertyCell[] getPropertiesInMonopoly(String color) {
        PropertyCell[] monopolyCells = 
        new PropertyCell[getPropertyNumberForColor(color)];
        int counter = 0;
        for (int i = 0; i < getCellNumber(); i++) {
            Cell c = getCell(i);
            if (c instanceof PropertyCell) {
                PropertyCell pc = (PropertyCell)c;
                if (pc.getColorGroup().equals(color)) {
                    monopolyCells[counter] = pc;
                    counter++;
                }
            }
        }
        return monopolyCells;
    }
	
    public int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    public Cell queryCell(String string) {
        for (Object cell : cells) {
            Cell temp = (Cell) cell;
            if (temp.getName().equals(string)) {
                return temp;
            }
        }
        return null;
    }
	
    public int queryCellIndex(String string){
        for (int i = 0; i < cells.size(); i++) {
            Cell temp = (Cell) cells.get(i); 
            if (temp.getName().equals(string)) {
                return i;
            }
        }
        return -1;
    }

    public void removeCards() {
        communityChestCards.clear();
    }
}
