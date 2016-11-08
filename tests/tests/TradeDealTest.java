package tests;

import junit.framework.TestCase;
import monopoly.GameMaster;
import monopoly.Player;
import monopoly.TradeDeal;

public class TradeDealTest extends TestCase {
    private GameMaster gameMaster;
    
    @Override
    public void setUp() {
        gameMaster = new GameMaster();
        gameMaster.reset();
        gameMaster.setNumberOfPlayers(2);
        gameMaster.getPlayer(0).setName("Buyer");
        gameMaster.getPlayer(1).setName("Seller");
    }

    public void testMakeMessage() {
        TradeDeal deal = new TradeDeal();
        Player buyer = gameMaster.getPlayer(0);
        Player seller = gameMaster.getPlayer(1);
        
        deal.setBuyer(buyer);
        deal.setSeller(seller);
        deal.setAmount(200);
        deal.setPropertyName("Blue 1");

        String message = "ATTENTION: Seller\n" + 
                "Buyer wishes to purchase Blue 1 from you for $200. " +
                "Do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }

}
