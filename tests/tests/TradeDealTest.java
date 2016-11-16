package tests;

import junit.framework.TestCase;
import monopoly.MainController;
import monopoly.Player;
import monopoly.TradeDeal;
import monopoly.cells.PropertyCell;

public class TradeDealTest extends TestCase {
    private MainController mainCtl;
    
    @Override
    public void setUp() {
        mainCtl = new MainController();
        mainCtl.reset();
        mainCtl.setNumberOfPlayers(2);
        mainCtl.getPlayer(0).setName("Buyer");
        mainCtl.getPlayer(1).setName("Seller");
    }

    public void testMakeMessage() {
        Player buyer = mainCtl.getPlayer(0);
        Player seller = mainCtl.getPlayer(1);
        int propertyPrice = 200;
        
        PropertyCell property = new PropertyCell();
        property.setName("Blue 1");
        property.setPrice(propertyPrice);
        property.setPlayer(seller);
        
        TradeDeal deal = new TradeDeal(property, buyer, propertyPrice);
        
        String message = "ATTENTION: Seller\n" + 
                "Buyer wishes to purchase Blue 1 from you for $200. " +
                "Do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }

}
