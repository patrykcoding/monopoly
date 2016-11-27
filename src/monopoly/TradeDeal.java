package monopoly;

public class TradeDeal {
    private final int amount;
    private final Player buyer;
    private final Cell property;
    private final String propertyName;
    private final Player seller;
    
    public TradeDeal(Cell property, Player buyer, int amount) {
        this.propertyName = property.getName();
        this.seller = property.getOwner();
        this.buyer = buyer;
        this.amount = amount;
        this.property = property;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public Player getBuyer() {
        return this.buyer;
    }
    
    public Cell getProperty() {
        return property;
    }
    
    public String getPropertyName() {
        return propertyName;
    }

    public Player getSeller() {
        return this.seller;
    }
    
    public String makeMessage() {
        String message =
                this.buyer + " wishes to purchase " + propertyName +
                " from you for $" + this.amount +
                ". Do you wish to trade your property?";
        return message;
    }
}
