package tests;

import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.Player;
import monopoly.TradeDeal;
import monopoly.cells.PropertyCell;

public class TradeDealTest extends TestCase {
    private MainController mainController;
    
    @Override
    public void setUp() {
        mainController = new MainController();
        mainController.reset();
        mainController.setNumberOfPlayers(2);
        mainController.getPlayer(0).setName("Buyer");
        mainController.getPlayer(1).setName("Seller");
    }

    public void testMakeMessage() {
        Player buyer = mainController.getPlayer(0);
        Player seller = mainController.getPlayer(1);
        int propertyPrice = 200;
        
        PropertyCell property = new PropertyCell();
        property.setName("Blue 1");
        property.setPrice(propertyPrice);
        property.setPlayer(seller);
        
        TradeDeal deal = new TradeDeal(property, buyer, propertyPrice);
        
        String message =
                "Buyer wishes to purchase Blue 1 from you for $200. " +
                "Do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }

}
