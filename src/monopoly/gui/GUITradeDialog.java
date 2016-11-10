package monopoly.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Iterator;
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

public class GUITradeDialog extends JDialog implements TradeDialog {
    private JButton btnOK, btnCancel;
    private JComboBox cboSellers, cboProperties;

    private TradeDeal deal;
    private JTextField txtAmount;
    
    public GUITradeDialog(MainController mainCtl, Frame parent) {
        super(parent);
        
        super.setTitle("Trade Property");
        cboSellers = new JComboBox();
        cboProperties = new JComboBox();
        txtAmount = new JTextField();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        
        btnOK.setEnabled(false);
        
        buildSellersCombo(mainCtl);
        super.setModal(true);
             
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Sellers"));
        contentPane.add(cboSellers);
        contentPane.add(new JLabel("Properties"));
        contentPane.add(cboProperties);
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtAmount);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
        
        btnCancel.addActionListener((ActionEvent e) -> {
            GUITradeDialog.this.setVisible(false);
        });
        
        cboSellers.addItemListener((ItemEvent e) -> {
            Player player = (Player)e.getItem();
            updatePropertiesCombo(player);
        });
        
        btnOK.addActionListener((ActionEvent e) -> {
            int amount;
            try {
                amount = Integer.parseInt(txtAmount.getText());
            } catch(NumberFormatException nfe) {
                JOptionPane.showMessageDialog(GUITradeDialog.this,
                        "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cell cell = (Cell)cboProperties.getSelectedItem();
            if(cell == null) return;
            Player player = (Player)cboSellers.getSelectedItem();
            Player currentPlayer = mainCtl.getCurrentPlayer();
            if(currentPlayer.getMoney() > amount) {
                deal = new TradeDeal();
                deal.setAmount(amount);
                deal.setPropertyName(cell.getName());
                deal.setBuyer(currentPlayer);
                deal.setSeller(player);
            }
            this.setVisible(false);
        });
        
        super.pack();
    }

    private void buildSellersCombo(MainController mainCtl) {
        List sellers = mainCtl.getSellerList();
        for (Iterator iter = sellers.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            cboSellers.addItem(player);
        }
        if(sellers.size() > 0) {
            updatePropertiesCombo((Player)sellers.get(0));
        }
    }

    @Override
    public TradeDeal getTradeDeal(MainController mainCtl) {
        return deal;
    }

    private void updatePropertiesCombo(Player player) {
        cboProperties.removeAllItems();
        Cell[] cells = player.getAllProperties();
        btnOK.setEnabled(cells.length > 0);
        for (Cell cell : cells) {
            cboProperties.addItem(cell);
        }
    }

}
