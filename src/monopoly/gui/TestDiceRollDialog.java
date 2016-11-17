package monopoly.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import monopoly.Dice;


public class TestDiceRollDialog extends JDialog {
    private static final long serialVersionUID = 8547740090678848552L;
    private JButton btnOK, btnCancel;
    private JTextField txtDiceRoll;
    private Dice dice;
    
    public TestDiceRollDialog(Frame parent) {
        super(parent);
        
        super.setTitle("Dice Roll Dialog");
        txtDiceRoll = new JTextField(2);
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        dice = new Dice(2);
        
        super.setModal(true);
             
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtDiceRoll);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
        
        btnCancel.addActionListener((ActionEvent e) -> {
            TestDiceRollDialog.this.setVisible(false);
            dice.setDice(0, 0);
            dice.setDice(1, 0);
        });
        
        btnOK.addActionListener((ActionEvent e) -> {
            int amount;
            try {
                amount = Integer.parseInt(txtDiceRoll.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(TestDiceRollDialog.this,
                        "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount > 0) {
                dice = new Dice(2);
                if ((amount % 2) == 0) {
                    dice.setDice(0, amount / 2);
                    dice.setDice(1, amount / 2);
                } else {
                    dice.setDice(0, amount / 2);
                    dice.setDice(1, (amount / 2) + 1);
                }
            }
            this.setVisible(false);
        });
        
        super.pack();
    }

    public Dice getDice() {
        return dice;
    }
}
