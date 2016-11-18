package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import monopoly.MainController;

public class UtilDiceRoll extends JDialog {
    private static final long serialVersionUID = -2985807932807855607L;
    private final JButton btnDice = new JButton("Roll the Dice!");
    private final JButton btnOK = new JButton("OK");
    private int diceValue;
    private final JLabel lblPrompt = new JLabel();
    private final MainController mainCtl;
    
 
    public UtilDiceRoll(MainController mainCtl) {
        this.mainCtl = mainCtl;
        super.setModal(true);
        btnOK.setEnabled(false);
        lblPrompt.setText("Please roll the dice to determine your utility bill.");
        Container contentPane = super.getContentPane();
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnDice);
        pnlButtons.add(btnOK);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(lblPrompt, BorderLayout.CENTER);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        btnDice.addActionListener((ActionEvent arg0) -> {
            rollDice();
        });
        btnOK.addActionListener((ActionEvent arg0) -> {
            okClicked();
        });
        this.pack();
    }

    public static int showDialog(MainController mainCtl) {
        UtilDiceRoll dialog = new UtilDiceRoll(mainCtl);
        dialog.setVisible(true);
        return dialog.diceValue;
    }
    
    public final void okClicked(){
        this.dispose();
    }

    public final void rollDice() {
        int[] diceRoll = mainCtl.rollDice();
        this.diceValue = diceRoll[0] + diceRoll[1];
        lblPrompt.setText("You rolled " + diceValue);
        btnDice.setEnabled(false);
        btnOK.setEnabled(true);
    }
}
