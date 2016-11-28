package monopoly.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class TradeDialogGUI extends JDialog implements TradeDialog {
    private static final long serialVersionUID = -7231996263338389498L;
    private JButton cancelButton;
    private JButton okButton;
    private JComboBox<Cell> propertiesCombobox;
    private final int comboboxBorderSize = 3;
    private JComboBox<Player> sellersCombobox;
    private TradeDeal deal;
    private JTextField amountText;
    
    public TradeDialogGUI(MainController mainController, Frame parent) {
        super(parent);
        int xOffset = 125;
        int yOffset = 100;
        super.setLocationRelativeTo(parent.getFocusOwner().getParent().getParent());
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);
        super.setTitle("Trade Property");
        sellersCombobox = new JComboBox<>();
        propertiesCombobox = new JComboBox<>();
        amountText = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setEnabled(false);
        
        buildSellersComboBox(mainController);
        super.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
             
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Property owner"));
        contentPane.add(sellersCombobox);
        contentPane.add(new JLabel("Property"));
        contentPane.add(propertiesCombobox);
        contentPane.add(new JLabel("Offer price"));
        contentPane.add(amountText);
        contentPane.add(okButton);
        contentPane.add(cancelButton);

        cancelButton.addActionListener((ActionEvent e) -> {
            TradeDialogGUI.this.setVisible(false);
        });
        
        sellersCombobox.addItemListener((ItemEvent e) -> {
            Player player = (Player)e.getItem();
            updatePropertiesComboBox(player);
        });
        
        sellersCombobox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = -5460014450312978883L;
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component ret = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected || cellHasFocus) {
                    Player p = (Player)value;
                    list.setSelectionBackground(p.getPlayerColor());
                    list.setSelectionForeground(Color.black);
                }
                return ret;
            }
        });

        PopupMenuListener listener = new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                Color defaultColor = new Color(238, 238, 238);
                sellersCombobox.setBorder(new LineBorder(defaultColor, comboboxBorderSize));
                sellersCombobox.setBackground(defaultColor);
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                Player player = (Player)sellersCombobox.getSelectedItem();
                sellersCombobox.setBorder(new LineBorder(player.getPlayerColor(), comboboxBorderSize));
                sellersCombobox.setBackground(player.getPlayerColor());
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        };

        sellersCombobox.addPopupMenuListener(listener);
        
        okButton.addActionListener((ActionEvent e) -> {
            int amount;
            try {
                amount = Integer.parseInt(amountText.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(TradeDialogGUI.this,
                        "Amount should be an integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount < 0) {
                JOptionPane.showMessageDialog(TradeDialogGUI.this,
                        "Amount should not be negative", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cell cell = (Cell) propertiesCombobox.getSelectedItem();
            if(cell == null) return;
            Player currentPlayer = mainController.getCurrentPlayer();
            if(currentPlayer.getMoney() > amount) {
                deal = new TradeDeal(cell, currentPlayer, amount);
            } else {
                JOptionPane.showMessageDialog(TradeDialogGUI.this,
                        "You don't have enough money to make this trade", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            TradeDialogGUI.this.setVisible(false);
        });
        
        super.pack();
    }

    private void buildSellersComboBox(MainController mainCtl) {
        List<Player> sellers = mainCtl.getSellerList();
        sellers.stream().forEach((player) -> {
            sellersCombobox.addItem(player);
        });
        if(sellers.size() > 0) {
            Player topSeller = sellers.get(0);
            updatePropertiesComboBox(topSeller);
            sellersCombobox.setBackground(topSeller.getPlayerColor());
            sellersCombobox.setBorder(new LineBorder(topSeller.getPlayerColor(), comboboxBorderSize));
        }
    }

    private void updatePropertiesComboBox(Player player) {
        propertiesCombobox.removeAllItems();
        List<Cell> cells = player.getAllProperties();
        okButton.setEnabled(cells.size() > 0);
        cells.stream().forEach((cell) -> {
            propertiesCombobox.addItem(cell);
        });
    }

    @Override
    public TradeDeal getTradeDeal(MainController mainCtl) {
        return deal;
    }
}
