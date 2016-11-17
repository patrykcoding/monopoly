package monopoly.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import monopoly.Cell;
import monopoly.MainController;
import monopoly.Player;
import monopoly.TradeDeal;
import monopoly.TradeDialog;

public class TradeDialogGUI extends JDialog implements TradeDialog {
    private static final long serialVersionUID = -7231996263338389498L;
    
    private JButton btnOK, btnCancel;
    private JComboBox<Player> cboSellers;
    private JComboBox<Cell> cboProperties;

    private TradeDeal deal;
    private JTextField txtAmount;
    
    public TradeDialogGUI(MainController mainCtl, Frame parent) {
        super(parent);
        super.setLocationRelativeTo(parent.getFocusOwner().getParent().getParent());
        super.setLocation(super.getX() - 125, super.getY() - 100);
        super.setTitle("Trade Property");
        cboSellers = new JComboBox<>();
        cboProperties = new JComboBox<>();
        txtAmount = new JTextField();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        
        btnOK.setEnabled(false);
        
        buildSellersCombo(mainCtl);
        super.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
             
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Property owner"));
        contentPane.add(cboSellers);
        contentPane.add(new JLabel("Property"));
        contentPane.add(cboProperties);
        contentPane.add(new JLabel("Offer price"));
        contentPane.add(txtAmount);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
        
        btnCancel.addActionListener((ActionEvent e) -> {
            TradeDialogGUI.this.setVisible(false);
        });
        
        cboSellers.addItemListener((ItemEvent e) -> {
            Player player = (Player)e.getItem();
            updatePropertiesCombo(player);
        });
        
        btnOK.addActionListener((ActionEvent e) -> {
            int amount;
            try {
                amount = Integer.parseInt(txtAmount.getText());
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
            Cell cell = (Cell) cboProperties.getSelectedItem();
            if(cell == null) return;
            Player currentPlayer = mainCtl.getCurrentPlayer();
            if(currentPlayer.getMoney() > amount) {
                deal = new TradeDeal(cell, currentPlayer, amount);
            }   TradeDialogGUI.this.setVisible(false);
        });
        
        super.pack();
    }

    private void buildSellersCombo(MainController mainCtl) {
        List<Player> sellers = mainCtl.getSellerList();
        sellers.stream().forEach((player) -> {
            cboSellers.addItem(player);
        });
        if(sellers.size() > 0) {
            updatePropertiesCombo(sellers.get(0));
        }
    }

    @Override
    public TradeDeal getTradeDeal(MainController mainCtl) {
        return deal;
    }

    private void updatePropertiesCombo(Player player) {
        cboProperties.removeAllItems();
        ArrayList<Cell> cells = player.getAllProperties();
        btnOK.setEnabled(cells.size() > 0);
        cells.stream().forEach((cell) -> {
            cboProperties.addItem(cell);
        });
    }

}
