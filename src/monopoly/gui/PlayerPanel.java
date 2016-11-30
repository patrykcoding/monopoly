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

    private final JButton buyHouseButton;
    private final JButton drawCardButton;
    private final JButton endTurnButton;
    private final JButton getOutOfJailButton;
    private final JLabel moneyLabel;
    private final JLabel nameLabel;
    private final Player player;
    private final JTextArea propertyText;
    private final JButton purchasePropertyButton;
    private final JButton rollDiceButton;
    private final JButton tradeButton;

    public PlayerPanel(MainController mainController, Player player) {
        JPanel actionPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        rollDiceButton = new JButton("Roll Dice");
        purchasePropertyButton = new JButton("Purchase Property");
        endTurnButton = new JButton("End Turn");
        buyHouseButton = new JButton("Buy House");
        getOutOfJailButton = new JButton("Get Out of Jail");
        drawCardButton = new JButton("Draw Card");
        tradeButton = new JButton("Trade");
        this.player = player;
        nameLabel = new JLabel();
        moneyLabel = new JLabel();
        propertyText = new JTextArea(); 
        JScrollPane scroll = new JScrollPane(propertyText, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        propertyText.setEnabled(false);
        propertyText.setDisabledTextColor(Color.black);

        JPanel namePanel = new JPanel();
        JPanel propertiesPanel = new JPanel();

        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(namePanel, BorderLayout.NORTH);
        infoPanel.add(propertiesPanel, BorderLayout.CENTER);

        propertiesPanel.setLayout(new OverlayLayout(propertiesPanel));
        namePanel.setBackground(player.getPlayerColor());        

        namePanel.add(nameLabel);
        namePanel.add(moneyLabel);
        propertiesPanel.add(scroll);

        actionPanel.setLayout(new GridLayout(3, 3));
        actionPanel.add(buyHouseButton);
        actionPanel.add(rollDiceButton);
        actionPanel.add(purchasePropertyButton);
        actionPanel.add(getOutOfJailButton);
        actionPanel.add(endTurnButton);
        actionPanel.add(drawCardButton);
        actionPanel.add(tradeButton);

        actionPanel.doLayout();
        infoPanel.doLayout();
        namePanel.doLayout();
        propertiesPanel.doLayout();
        super.doLayout();

        super.setLayout(new BorderLayout());
        super.add(infoPanel, BorderLayout.CENTER);
        super.add(actionPanel, BorderLayout.SOUTH);

        rollDiceButton.setEnabled(false);
        purchasePropertyButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        buyHouseButton.setEnabled(false);
        getOutOfJailButton.setEnabled(false);
        drawCardButton.setEnabled(false);
        tradeButton.setEnabled(false);

        super.setBorder(new BevelBorder(BevelBorder.RAISED));

        rollDiceButton.addActionListener((ActionEvent e) -> {
            mainController.buttonRollDiceClicked(PlayerPanel.this);
        });

        endTurnButton.addActionListener((ActionEvent e) -> {
            mainController.buttonEndTurnClicked();
        });

        purchasePropertyButton.addActionListener((ActionEvent e) -> {
            mainController.buttonPurchasePropertyClicked();
        });

        buyHouseButton.addActionListener((ActionEvent e) -> {
            mainController.buttonBuyHouseClicked();
        });

        getOutOfJailButton.addActionListener((ActionEvent e) -> {
            mainController.buttonGetOutOfJailClicked();
        });

        drawCardButton.addActionListener((ActionEvent e) -> {
            Card card = mainController.buttonDrawCardClicked();
            JOptionPane.showMessageDialog(PlayerPanel.this, card);
            displayInfo();
        });

        tradeButton.addActionListener((ActionEvent e) -> {
            mainController.buttonTradeClicked();
        });
    }

    public final void displayInfo() {
        nameLabel.setText(player.getName());
        moneyLabel.setText("$ " + player.getMoney());
        StringBuilder buf = new StringBuilder();
        List<Cell> cells = player.getAllProperties();
        cells.stream().forEach((cell) -> {
            buf.append(cell).append("\n");
        });
        propertyText.setText(buf.toString());
    }

    public boolean isDrawCardButtonEnabled() {
        return drawCardButton.isEnabled();
    }

    public boolean isEndTurnButtonEnabled() {
        return endTurnButton.isEnabled();
    }
    
    public boolean isGetOutOfJailButtonEnabled() {
        return getOutOfJailButton.isEnabled();
    }
    
    public boolean isTradeButtonEnabled() {
        return tradeButton.isEnabled();
    }

    public void setBuyHouseEnabled(boolean enabled) {
        buyHouseButton.setEnabled(enabled);
    }

    public void setDrawCardEnabled(boolean enabled) {
        drawCardButton.setEnabled(enabled);
    }

    public void setEndTurnEnabled(boolean enabled) {
        endTurnButton.setEnabled(enabled);
    }

    public void setGetOutOfJailEnabled(boolean enabled) {
        getOutOfJailButton.setEnabled(enabled);
    }

    public void setPurchasePropertyEnabled(boolean enabled) {
        purchasePropertyButton.setEnabled(enabled);
    }

    public void setRollDiceEnabled(boolean enabled) {
        rollDiceButton.setEnabled(enabled);
    }

    public void setTradeEnabled(boolean b) {
        tradeButton.setEnabled(b);
    }
}