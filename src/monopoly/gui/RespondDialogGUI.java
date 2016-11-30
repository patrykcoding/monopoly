package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import monopoly.RespondDialog;
import monopoly.TradeDeal;

public class RespondDialogGUI extends JDialog implements RespondDialog {
    private static final long serialVersionUID = -992184678913164041L;
    
    private boolean response;
    private final JTextArea messageText = new JTextArea();
    
    public RespondDialogGUI(PlayerPanel playerPanel) {
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        messageText.setPreferredSize(new Dimension(300, 200));
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(messageText, BorderLayout.CENTER);
        JPanel panelButtons = new JPanel();
        panelButtons.add(yesButton);
        panelButtons.add(noButton);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        
        super.setLocationRelativeTo(playerPanel);
        int xOffset = 140;
        int yOffset = 130;
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);

        yesButton.addActionListener((ActionEvent e) -> {
            response = true;
            setVisible(false);
        });

        noButton.addActionListener((ActionEvent e) -> {
            response = false;
            setVisible(false);
        });
    
        super.setModal(true);
        super.pack();
    }
    
    public void setDeal(TradeDeal deal) {
        messageText.setText(deal.makeMessage());
    }

    @Override
    public boolean getResponse() {
        return response;
    }
}
