package monopoly;

public class TradeDeal {
    private int amount;
    private Player seller;
    private Player buyer;
    private String propertyName;
    private final Cell property;
    
    public TradeDeal(Cell property, Player buyer, int amount) {
        this.propertyName = property.getName();
        this.seller = property.getPlayer();
        this.buyer = buyer;
        this.amount = amount;
        this.property = property;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public Cell getProperty() {
        return property;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public String makeMessage() {
        String message = 
                this.buyer + " wishes to purchase " + propertyName + 
                " from you for $" + this.amount + 
                ". Do you wish to trade your property?";
        return message;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    public void setBuyer(Player buyer) {
        this.buyer = buyer;
    }
    
    public void setSeller(Player seller) {
        this.seller = seller;
    }
    
    public Player getSeller() {
        return this.seller;
    }

    public Player getBuyer() {
        return this.buyer;
    }
}
