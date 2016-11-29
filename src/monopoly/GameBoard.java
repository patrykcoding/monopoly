package monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import monopoly.cells.GoCell;
import monopoly.cells.PropertyCell;
import monopoly.enums.CardType;
import monopoly.enums.ColorGroup;

public class GameBoard {

    private final List<Cell> cells = new ArrayList<>();
    private final List<Card> chanceCards = new ArrayList<>();
    private final List<Card> communityChestCards = new ArrayList<>();
    //the key of propertyColors is the name of the color group.
    private final Map<ColorGroup, Integer> propertyColors = new HashMap<>();

    public GameBoard() {
        Cell go = new GoCell();
        addCell(go);
    }

    public void addCard(Card card) {
        if (card.getCardType() == CardType.CC)
            communityChestCards.add(card);
        else
            chanceCards.add(card);
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

    public Cell getCell(int index) {
        return cells.get(index);
    }
	
    public int getCellSize() {
        return cells.size();
    }
	
    public List<PropertyCell> getPropertiesInMonopoly(ColorGroup color) {
        List<PropertyCell> monopolyCells = new ArrayList<>();
        cells.stream().filter((cell) 
                            -> (cell instanceof PropertyCell)).map((cell)
                            -> (PropertyCell)cell).filter((pc) 
                            -> (pc.getColorGroup().equals(color))).forEach((pc) -> {
                                monopolyCells.add(pc);
                            });
        return monopolyCells;
    }
	
    public int getPropertyNumberForColor(ColorGroup colorGroup) {
        Integer number = propertyColors.get(colorGroup);
        if (number != null)
            return number;
        return 0;
    }

    public Cell queryCell(String string) {
        for (Cell cell : cells) {
            if (cell.getName().equals(string))
                return cell;
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
