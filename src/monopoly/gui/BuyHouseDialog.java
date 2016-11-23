
package monopoly.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import monopoly.GameBoard;
import monopoly.MainController;

import monopoly.Player;
import monopoly.cells.PropertyCell;

public class BuyHouseDialog extends JDialog {
    private static final long serialVersionUID = -8707274599957567230L;
    
    private JComboBox<String> cboMonopoly;
    private JComboBox<Integer> cboNumber;

    private final Player player;
    private final MainController mainCtl;
    
    public BuyHouseDialog(MainController mainCtl, Player player) {
        this.mainCtl = mainCtl;
        this.player = player;
        
        Container c = super.getContentPane();
        c.setLayout(new GridLayout(3, 2));
        c.add(new JLabel("Select monopoly"));
        c.add(buildMonopolyComboBox());
        c.add(new JLabel("Number of houses"));
        c.add(buildNumberComboBox());
        c.add(buildOKButton());
        c.add(buildCancelButton());
        c.doLayout();
        super.pack();
        
        cboMonopoly.addActionListener((ActionEvent e) -> {
            String monopoly = (String)cboMonopoly.getSelectedItem();
            updateHousesCboBox(monopoly);
        });
    }

    private JButton buildCancelButton() {
        JButton btn = new JButton("Cancel");
        btn.addActionListener((ActionEvent e) -> {
            cancelClicked();
        });
        return btn;
    }

    private void updateHousesCboBox(String monopoly) {
        cboNumber.removeAllItems();
        GameBoard gameBoard = mainCtl.getGameBoard();
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(monopoly);
        int houses = properties.get(0).getNumHouses();
        int maxBuy = 5 - houses;
        for (int i = 1; i <= maxBuy; i++)
            cboNumber.addItem(i);
    }

    private JComboBox<String> buildMonopolyComboBox() {
        cboMonopoly = new JComboBox<>();
        List<String> monopolies = mainCtl.getMonopolies(player);
        monopolies.stream().forEach((monopoly) -> {
            cboMonopoly.addItem(monopoly);
        });
        return cboMonopoly;
    }

    private JComboBox<Integer> buildNumberComboBox() {
        cboNumber = new JComboBox<>();
        String monopoly = cboMonopoly.getItemAt(0);
        GameBoard gameBoard = mainCtl.getGameBoard();
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(monopoly);
        int houses = properties.get(0).getNumHouses();
        int maxBuy = 5 - houses;
        for (int i = 1; i <= maxBuy; i++)
            cboNumber.addItem(i);
        
        return cboNumber;
    }

    private JButton buildOKButton() {
        JButton btn = new JButton("OK");
        btn.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        return btn;
    }

    private void cancelClicked() {
        this.dispose();
    }

    private void okClicked() {
        String monopoly = (String)cboMonopoly.getSelectedItem();
        int number = cboNumber.getSelectedIndex() + 1;
        mainCtl.purchaseHouse(monopoly, number);
        this.dispose();
    }
}
