package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import monopoly.Dice;

public class UtilityDiceRoll extends JDialog {
    private static final long serialVersionUID = -2985807932807855607L;
    
    public static int showDialog(PlayerPanel panel) {
        UtilityDiceRoll dialog = new UtilityDiceRoll(panel);
        dialog.setVisible(true);
        return dialog.diceValue;
    }
    
    private final JButton diceButton = new JButton("Roll the Dice!");
    private final JButton okButton = new JButton("OK");
    private int diceValue;
    private final JLabel promptLabel = new JLabel();
    
    public UtilityDiceRoll(PlayerPanel panel) {
        super.setModal(true);
        okButton.setEnabled(false);
        okButton.setVisible(false);
        promptLabel.setText("Please roll the dice to determine your utility bill.");
        Container contentPane = super.getContentPane();
        JPanel panelButtons = new JPanel();
        panelButtons.add(diceButton);
        panelButtons.add(okButton);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(promptLabel, BorderLayout.CENTER);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        diceButton.addActionListener((ActionEvent arg0) -> {
            rollDice();
            diceButton.setVisible(false);
            okButton.setVisible(true);
        });
        okButton.addActionListener((ActionEvent arg0) -> {
            okClicked();
        });
        super.setLocationRelativeTo(panel);
        int xOffset = 170;
        int yOffset = 60;
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);
        super.pack();
    }

    public final void okClicked(){
        this.dispose();
    }

    public final void rollDice() {
        Dice dice = new Dice(2);
        diceValue = dice.getTotal();
        StringBuilder text = new StringBuilder();
        text.append("You rolled " )
                .append(dice.getSingleDice(0))
                .append(", ")
                .append(dice.getSingleDice(1))
                .append(" which totals ")
                .append(dice.getTotal())
                .append(".");
        promptLabel.setText(text.toString());
        diceButton.setEnabled(false);
        okButton.setEnabled(true);
    }
}
