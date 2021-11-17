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

/**
 *
 * @author owner
 */
public class GameBoard {

    private final List<Cell> cells = new ArrayList<>();

    private final List<Card> chanceCards = new ArrayList<>();

    private final List<Card> communityChestCards = new ArrayList<>();
    //the key of propertyColors is the name of the color group.

    private final Map<ColorGroup, Integer> propertyColors = new HashMap<>();

    /**
     * Start the game , to go first cell
     */
    public GameBoard() {
        Cell go = new GoCell();
        addCell(go);
    }

    /**
     * Add new card to its correct group based on type.
     * @param card
     */
    public void addCard(Card card) {
        if (card.getCardType() == CardType.CC)
            communityChestCards.add(card);
        else
            chanceCards.add(card);
    }

    /**
     *
     * @param cell
     */
    public final void addCell(Cell cell) {
        cells.add(cell);
    }
	
    /**
     *Add cell if the property of cell is the same 
     * @param cell
     */
    public void addCell(PropertyCell cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        propertyColors.put(cell.getColorGroup(), propertyNumber + 1);
        cells.add(cell);
    }

    /**
     *Get last added card and draw it 
     * @return CC card
     */
    public Card drawCCCard() {
        Card card = communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     * Get last added card and draw it 
     * @return ChanceCard
     */
    public Card drawChanceCard() {
        Card card = chanceCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     *
     * @param index
     * @return cell of the getting index.
     */
    public Cell getCell(int index) {
        return cells.get(index);
    }
	
    /**
     *
     * @return size of set
     */
    public int getCellSize() {
        return cells.size();
    }
	
    /**
     *
     * @param color
     * @return
     */
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
	
    /**
     *
     * @param colorGroup
     * @return number of color 
     */
    public int getPropertyNumberForColor(ColorGroup colorGroup) {
        Integer number = propertyColors.get(colorGroup);
        if (number != null)
            return number;
        return 0;
    }

    /**
     *
     * @param string
     * @return cell if founded
     */
    public Cell queryCell(String string) {
        for (Cell cell : cells) {
            if (cell.getName().equals(string))
                return cell;
        }
        return null;
    }
	
    /**
     *
     * @param string
     * @return index of cell if founded.
     */
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
    
    /**
     * BYFNAT El cards.
     */
    public final void shuffleCards() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }
}
