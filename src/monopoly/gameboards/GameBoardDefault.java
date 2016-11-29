package monopoly.gameboards;

import monopoly.GameBoard;
import monopoly.cards.JailCard;
import monopoly.cards.MoneyCard;
import monopoly.cards.MovePlayerCard;
import monopoly.cells.CardCell;
import monopoly.cells.FreeParkingCell;
import monopoly.cells.GoToJailCell;
import monopoly.cells.JailCell;
import monopoly.cells.PropertyCell;
import monopoly.cells.RailRoadCell;
import monopoly.cells.UtilityCell;
import monopoly.enums.CardType;
import monopoly.enums.ColorGroup;

public class GameBoardDefault extends GameBoard {
    public GameBoardDefault() {
        super();
        
        int rrBaseRent = 25;
        int rrPrice = 200;
        
        PropertyCell dp1 = new PropertyCell();
        CardCell cc1 = new CardCell(CardType.CC, "Community Chest 1");
        PropertyCell dp2 = new PropertyCell();
        PropertyCell dp3 = new PropertyCell();
        RailRoadCell rr1 = new RailRoadCell();
        PropertyCell lb1 = new PropertyCell();
        CardCell c1 = new CardCell(CardType.CHANCE, "Chance 1");
        PropertyCell lb2 = new PropertyCell();
        PropertyCell lb3 = new PropertyCell();
        JailCell jail = new JailCell();
        PropertyCell p1 = new PropertyCell();
        UtilityCell u1 = new UtilityCell();
        PropertyCell p2 = new PropertyCell();
        PropertyCell p3 = new PropertyCell();
        RailRoadCell rr2 = new RailRoadCell();
        PropertyCell o1 = new PropertyCell();
        CardCell cc2 = new CardCell(CardType.CC, "Community Chest 2");
        PropertyCell o2 = new PropertyCell();
        PropertyCell o3 = new PropertyCell();
        FreeParkingCell fp = new FreeParkingCell();
        PropertyCell r1 = new PropertyCell();
        CardCell c2 = new CardCell(CardType.CHANCE, "Chance 2");
        PropertyCell r2 = new PropertyCell();
        PropertyCell r3 = new PropertyCell();
        RailRoadCell rr3 = new RailRoadCell();
        PropertyCell y1 = new PropertyCell();
        PropertyCell y2 = new PropertyCell();
        UtilityCell u2 = new UtilityCell();
        PropertyCell y3 = new PropertyCell();
        GoToJailCell goToJail = new GoToJailCell();
        PropertyCell g1 = new PropertyCell();
        PropertyCell g2 = new PropertyCell();
        CardCell cc3 = new CardCell(CardType.CC, "Community Chest 3");
        PropertyCell g3 = new PropertyCell();
        RailRoadCell rr4 = new RailRoadCell();
        CardCell c3 = new CardCell(CardType.CHANCE, "Chance 3");
        
        PropertyCell db1 = new PropertyCell();
        PropertyCell db2 = new PropertyCell();
        PropertyCell db3 = new PropertyCell();
        
        dp1.setPrice(60);
        dp1.setColorGroup(ColorGroup.PURPLE);
        dp1.setHousePrice(50);
        dp1.setName("Mediterranean Avenue");
        dp1.setRent(2);
        
        dp2.setPrice(60);
        dp2.setColorGroup(ColorGroup.PURPLE);
        dp2.setHousePrice(50);
        dp2.setName("Baltic Avenue");
        dp2.setRent(4);
        
        dp3.setPrice(60);
        dp3.setColorGroup(ColorGroup.PURPLE);
        dp3.setHousePrice(50);
        dp3.setName("Sarah Avenue");
        dp3.setRent(4);
        
        lb1.setPrice(100);
        lb1.setColorGroup(ColorGroup.TEAL);
        lb1.setHousePrice(50);
        lb1.setName("Oriental Avenue");
        lb1.setRent(6);

        lb2.setPrice(100);
        lb2.setColorGroup(ColorGroup.TEAL);
        lb2.setHousePrice(50);
        lb2.setName("Vermont Avenue");
        lb2.setRent(6);

        lb3.setPrice(120);
        lb3.setColorGroup(ColorGroup.TEAL);
        lb3.setHousePrice(50);
        lb3.setName("Connecticut Avenue");
        lb3.setRent(8);
        
        p1.setPrice(140);
        p1.setColorGroup(ColorGroup.FUCHSIA);
        p1.setHousePrice(100);
        p1.setName("St. Charles Place");
        p1.setRent(10);
        
        p2.setPrice(140);
        p2.setColorGroup(ColorGroup.FUCHSIA);
        p2.setHousePrice(100);
        p2.setName("States Avenue");
        p2.setRent(10);
        
        p3.setPrice(160);
        p3.setColorGroup(ColorGroup.FUCHSIA);
        p3.setHousePrice(100);
        p3.setName("Virginia Avenue");
        p3.setRent(12);
        
        o1.setPrice(180);
        o1.setColorGroup(ColorGroup.MAROON);
        o1.setHousePrice(100);
        o1.setName("St. James Avenue");
        o1.setRent(14);
        
        o2.setPrice(180);
        o2.setColorGroup(ColorGroup.MAROON);
        o2.setHousePrice(100);
        o2.setName("Tennessee Avenue");
        o2.setRent(14);
        
        o3.setPrice(200);
        o3.setColorGroup(ColorGroup.MAROON);
        o3.setHousePrice(100);
        o3.setName("New York Avenue");
        o3.setRent(16);
        
        r1.setPrice(220);
        r1.setColorGroup(ColorGroup.RED);
        r1.setHousePrice(150);
        r1.setName("Kentucky Avenue");
        r1.setRent(18);
        
        r2.setPrice(220);
        r2.setColorGroup(ColorGroup.RED);
        r2.setHousePrice(150);
        r2.setName("Indiana Avenue");
        r2.setRent(18);
        
        r3.setPrice(240);
        r3.setColorGroup(ColorGroup.RED);
        r3.setHousePrice(150);
        r3.setName("Illinois Avenue");
        r3.setRent(20);
        
        y1.setPrice(260);
        y1.setColorGroup(ColorGroup.ORANGE);
        y1.setHousePrice(150);
        y1.setName("Atlantic Avenue");
        y1.setRent(22);
        
        y2.setPrice(260);
        y2.setColorGroup(ColorGroup.ORANGE);
        y2.setHousePrice(150);
        y2.setName("Ventnor Avenue");
        y2.setRent(22);
        
        y3.setPrice(280);
        y3.setColorGroup(ColorGroup.ORANGE);
        y3.setHousePrice(150);
        y3.setName("Marvin Gardens");
        y3.setRent(24);
        
        g1.setPrice(300);
        g1.setColorGroup(ColorGroup.GREEN);
        g1.setHousePrice(200);
        g1.setName("Pacific Avenue");
        g1.setRent(26);
        
        g2.setPrice(300);
        g2.setColorGroup(ColorGroup.GREEN);
        g2.setHousePrice(200);
        g2.setName("North Carolina Avenue");
        g2.setRent(26);
        
        g3.setPrice(320);
        g3.setColorGroup(ColorGroup.GREEN);
        g3.setHousePrice(200);
        g3.setName("Pennsylvania Avenue");
        g3.setRent(28);
        
        db1.setPrice(350);
        db1.setColorGroup(ColorGroup.BLUE);
        db1.setHousePrice(200);
        db1.setName("Park Place");
        db1.setRent(35);
        
        db2.setPrice(350);
        db2.setColorGroup(ColorGroup.BLUE);
        db2.setHousePrice(200);
        db2.setName("Dright Place");
        db2.setRent(35);
        
        db3.setPrice(400);
        db3.setColorGroup(ColorGroup.BLUE);
        db3.setHousePrice(200);
        db3.setName("Boardwalk");
        db3.setRent(50);
        
        rr1.setName("Reading Railroad");
        rr1.setBaseRent(rrBaseRent);
        rr1.setPrice(rrPrice);
 
        rr2.setName("Pennsylvania Railroad");
        rr2.setBaseRent(rrBaseRent);
        rr2.setPrice(rrPrice);
        
        rr3.setName("B. & O. RailRoad");
        rr3.setBaseRent(rrBaseRent);
        rr3.setPrice(rrPrice);
        
        rr4.setName("Short Line");
        rr4.setBaseRent(rrBaseRent);
        rr4.setPrice(rrPrice);
        
        UtilityCell.setPrice(150);
        
        u1.setName("Electric Company");
        u2.setName("Water Works");
        
        super.addCell(dp1);
        super.addCell(cc1);
        super.addCell(dp2);
        super.addCell(dp3);
        super.addCell(rr1);
        super.addCell(lb1);
        super.addCell(c1);
        super.addCell(lb2);
        super.addCell(lb3);
        super.addCell(jail);
        super.addCell(p1);
        super.addCell(u1);
        super.addCell(p2);
        super.addCell(p3);
        super.addCell(rr2);
        super.addCell(o1);
        super.addCell(cc2);
        super.addCell(o2);
        super.addCell(o3);
        super.addCell(fp);
        super.addCell(r1);
        super.addCell(c2);
        super.addCell(r2);
        super.addCell(r3);
        super.addCell(rr3);
        super.addCell(y1);
        super.addCell(y2);
        super.addCell(u2);
        super.addCell(y3);
        super.addCell(goToJail);
        super.addCell(g1);
        super.addCell(g2);
        super.addCell(cc3);
        super.addCell(g3);
        super.addCell(rr4);
        super.addCell(c3);
        super.addCell(db1);
        super.addCell(db2);
        super.addCell(db3);
        
        super.addCard(new MoneyCard("Win $50", 50, CardType.CC));
        super.addCard(new MoneyCard("Win $20", 20, CardType.CC));
        super.addCard(new MoneyCard("Win $10", 10, CardType.CC));
        super.addCard(new MoneyCard("Lose $100", -100, CardType.CC));
        super.addCard(new MoneyCard("Lose $50", -50, CardType.CC));
        super.addCard(new JailCard(CardType.CC));
        super.addCard(new MovePlayerCard("St. Charles Place", CardType.CC));
        super.addCard(new MovePlayerCard("Boardwalk", CardType.CC));

        super.addCard(new MoneyCard("Win $50", 50, CardType.CHANCE));
        super.addCard(new MoneyCard("Win $20", 20, CardType.CHANCE));
        super.addCard(new MoneyCard("Win $10", 10, CardType.CHANCE));
        super.addCard(new MoneyCard("Lose $100", -100, CardType.CHANCE));
        super.addCard(new MoneyCard("Lose $50", -50, CardType.CHANCE));
        super.addCard(new JailCard(CardType.CHANCE));
        super.addCard(new MovePlayerCard("Illinois Avenue", CardType.CHANCE));
        
        super.shuffleCards();
    }
}
