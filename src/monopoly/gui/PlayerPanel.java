package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;

import monopoly.Card;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;

public class PlayerPanel extends JPanel {
    private static final long serialVersionUID = -86435279085524828L;

    private final JButton btnBuyHouse;
    private final JButton btnDrawCard;
    private final JButton btnEndTurn;
    private final JButton btnGetOutOfJail;
    private final JButton btnPurchaseProperty;
    private final JButton btnRollDice;
    private final JButton btnTrade;
    
    private final JLabel lblMoney;
    private final JLabel lblName;
    
    private final Player player;
    
    private final JTextArea txtProperty;

    
    public PlayerPanel(MainController mainCtl, Player player) {
        JPanel pnlAction = new JPanel();
        JPanel pnlInfo = new JPanel();
        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        this.player = player;
        lblName = new JLabel();
        lblMoney = new JLabel();
        txtProperty = new JTextArea(); 
        JScrollPane scroll = new JScrollPane(txtProperty, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        txtProperty.setEnabled(false);
        txtProperty.setDisabledTextColor(Color.black);

        JPanel pnlName = new JPanel();
        JPanel pnlProperties = new JPanel();

        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.add(pnlName, BorderLayout.NORTH);
        pnlInfo.add(pnlProperties, BorderLayout.CENTER);

        pnlProperties.setLayout(new OverlayLayout(pnlProperties));
        pnlName.setBackground(player.getPlayerColor());        

        pnlName.add(lblName);
        pnlName.add(lblMoney);
        pnlProperties.add(scroll);

        pnlAction.setLayout(new GridLayout(3, 3));
        pnlAction.add(btnBuyHouse);
        pnlAction.add(btnRollDice);
        pnlAction.add(btnPurchaseProperty);
        pnlAction.add(btnGetOutOfJail);
        pnlAction.add(btnEndTurn);
        pnlAction.add(btnDrawCard);
        pnlAction.add(btnTrade);

        pnlAction.doLayout();
        pnlInfo.doLayout();
        pnlName.doLayout();
        pnlProperties.doLayout();
        super.doLayout();

        super.setLayout(new BorderLayout());
        super.add(pnlInfo, BorderLayout.CENTER);
        super.add(pnlAction, BorderLayout.SOUTH);

        btnRollDice.setEnabled(false);
        btnPurchaseProperty.setEnabled(false);
        btnEndTurn.setEnabled(false);
        btnBuyHouse.setEnabled(false);
        btnGetOutOfJail.setEnabled(false);
        btnDrawCard.setEnabled(false);
        btnTrade.setEnabled(false);

        super.setBorder(new BevelBorder(BevelBorder.RAISED));

        btnRollDice.addActionListener((ActionEvent e) -> {
            mainCtl.btnRollDiceClicked(PlayerPanel.this);
        });

        btnEndTurn.addActionListener((ActionEvent e) -> {
            mainCtl.btnEndTurnClicked();
        });

        btnPurchaseProperty.addActionListener((ActionEvent e) -> {
            mainCtl.btnPurchasePropertyClicked();
        });

        btnBuyHouse.addActionListener((ActionEvent e) -> {
            mainCtl.btnBuyHouseClicked();
        });

        btnGetOutOfJail.addActionListener((ActionEvent e) -> {
            mainCtl.btnGetOutOfJailClicked();
        });

        btnDrawCard.addActionListener((ActionEvent e) -> {
            Card card = mainCtl.btnDrawCardClicked();
            JOptionPane.showMessageDialog(PlayerPanel.this, card);
            displayInfo();
        });

        btnTrade.addActionListener((ActionEvent e) -> {
            mainCtl.btnTradeClicked();
        });
    }

    public final void displayInfo() {
        lblName.setText(player.getName());
        lblMoney.setText("$ " + player.getMoney());
        StringBuilder buf = new StringBuilder();
        List<Cell> cells = player.getAllProperties();
        cells.stream().forEach((cell) -> {
            buf.append(cell).append("\n");
        });
        txtProperty.setText(buf.toString());
    }
    
    public boolean isBuyHouseButtonEnabled() {
        return btnBuyHouse.isEnabled();
    }

    public boolean isDrawCardButtonEnabled() {
        return btnDrawCard.isEnabled();
    }

    public boolean isEndTurnButtonEnabled() {
        return btnEndTurn.isEnabled();
    }
    
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJail.isEnabled();
    }
    
    public boolean isPurchasePropertyButtonEnabled() {
        return btnPurchaseProperty.isEnabled();
    }
    
    public boolean isRollDiceButtonEnabled() {
        return btnRollDice.isEnabled();
    }

    public boolean isTradeButtonEnabled() {
        return btnTrade.isEnabled();
    }

    public void setBuyHouseEnabled(boolean b) {
        btnBuyHouse.setEnabled(b);
    }

    public void setDrawCardEnabled(boolean b) {
        btnDrawCard.setEnabled(b);
    }

    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurn.setEnabled(enabled);
    }

    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJail.setEnabled(b);
    }

    public void setPurchasePropertyEnabled(boolean enabled) {
        btnPurchaseProperty.setEnabled(enabled);
    }

    public void setRollDiceEnabled(boolean enabled) {
        btnRollDice.setEnabled(enabled);
    }

    public void setTradeEnabled(boolean b) {
        btnTrade.setEnabled(b);
    }
}