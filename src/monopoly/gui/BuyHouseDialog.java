
package monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import monopoly.MainController;

import monopoly.Player;

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
    }

    private JButton buildCancelButton() {
        JButton btn = new JButton("Cancel");
        btn.addActionListener((ActionEvent e) -> {
            cancelClicked();
        });
        return btn;
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
        for (int i = 1; i <= 6; i++) {
            cboNumber.addItem(i);
        }
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
