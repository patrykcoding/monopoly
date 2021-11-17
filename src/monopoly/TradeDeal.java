package monopoly;

/**
 *
 * @author owner
 */
public class TradeDeal {

    private final int amount;

    private final Player buyer;

    private final Cell property;

    private final String propertyName;

    private final Player seller;
    
    /**
     *
     * @param property
     * @param buyer
     * @param amount
     */
    public TradeDeal(Cell property, Player buyer, int amount) {
        this.propertyName = property.getName();
        this.seller = property.getOwner();
        this.buyer = buyer;
        this.amount = amount;
        this.property = property;
    }
    
    /**
     *
     * @return amount of the buy
     */
    public int getAmount() {
        return amount;
    }
    
    /**
     *
     * @return Player who buy
     */
    public Player getBuyer() {
        return this.buyer;
    }
    
    /**
     *
     * @return property of cell
     */
    public Cell getProperty() {
        return property;
    }
    
    /**
     *
     * @return Name of the property
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     *
     * @return player who sell.
     */
    public Player getSeller() {
        return this.seller;
    }
    
    /**
     *
     * @return wishes message to player who buy the property.
     */
    public String makeMessage() {
        String message =
                this.buyer + " wishes to purchase " + propertyName +
                " from you for $" + this.amount +
                ". Do you wish to trade your property?";
        return message;
    }
}
