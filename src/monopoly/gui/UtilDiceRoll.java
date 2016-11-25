package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import monopoly.Dice;

import monopoly.MainController;

public class UtilDiceRoll extends JDialog {
    private static final long serialVersionUID = -2985807932807855607L;
    private final JButton btnDice = new JButton("Roll the Dice!");
    private final JButton btnOK = new JButton("OK");
    private int diceValue;
    private final JLabel lblPrompt = new JLabel();
    private final MainController mainCtl;
    
 
    public UtilDiceRoll(MainController mainCtl, PlayerPanel panel) {
        this.mainCtl = mainCtl;
        super.setModal(true);
        btnOK.setEnabled(false);
        btnOK.setVisible(false);
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
            btnDice.setVisible(false);
            btnOK.setVisible(true);
        });
        btnOK.addActionListener((ActionEvent arg0) -> {
            okClicked();
        });
        super.setLocationRelativeTo(panel);
        int xOffset = 170;
        int yOffset = 60;
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);
        super.pack();
    }

    public static int showDialog(MainController mainCtl, PlayerPanel panel) {
        UtilDiceRoll dialog = new UtilDiceRoll(mainCtl, panel);
        dialog.setVisible(true);
        return dialog.diceValue;
    }
    
    public final void okClicked(){
        this.dispose();
    }

    public final void rollDice() {
        Dice dice = mainCtl.getDice();
        diceValue = dice.getTotal();
        StringBuilder text = new StringBuilder();
        text.append("You rolled " )
                .append(dice.getSingleDice(0))
                .append(", ")
                .append(dice.getSingleDice(1))
                .append(" which totals ")
                .append(dice.getTotal())
                .append(".");
        lblPrompt.setText(text.toString());
        btnDice.setEnabled(false);
        btnOK.setEnabled(true);
    }
}
