package monopoly;

import monopoly.cells.PropertyCell;
import monopoly.cells.GoCell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    private final ArrayList<Cell> cells = new ArrayList<>();
    private final ArrayList<Card> chanceCards = new ArrayList<>();
    //the key of propertyColors is the name of the color group.
    private final Map<String, Integer> propertyColors = new HashMap<>();
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

    public final void addCell(Cell cell) {
        cells.add(cell);
    }
	
    public void addCell(PropertyCell cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        propertyColors.put(cell.getColorGroup(), propertyNumber + 1);
        cells.add(cell);
    }

    public Card drawCCCard() {
        Card card = communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = chanceCards.remove(0);
        addCard(card);
        return card;
    }

    public Cell getCell(int newIndex) {
        return cells.get(newIndex);
    }
	
    public int getCellSize() {
        return cells.size();
    }
	
    public PropertyCell[] getPropertiesInMonopoly(String color) {
        PropertyCell[] monopolyCells = 
        new PropertyCell[getPropertyNumberForColor(color)];
        int counter = 0;
        for (Object cell : cells) {
            if (cell instanceof PropertyCell) {
                PropertyCell pc = (PropertyCell)cell;
                if (pc.getColorGroup().equals(color))
                    monopolyCells[counter++] = pc;
            }
        }
        return monopolyCells;
    }
	
    public int getPropertyNumberForColor(String name) {
        Integer number = propertyColors.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    public Cell queryCell(String string) {
        for (Cell cell : cells) {
            if (cell.getName().equals(string)) {
                return cell;
            }
        }
        return null;
    }
	
    public int queryCellIndex(String string){
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getName().equals(string))
                return i;
        }
        return -1;
    }

    public void removeCards() {
        communityChestCards.clear();
    }
    
    public final void shuffleCards() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }
}
