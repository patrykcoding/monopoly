package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
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
    private final JTextArea txtMessage = new JTextArea();
    
    public RespondDialogGUI(PlayerPanel playerPanel) {
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        txtMessage.setPreferredSize(new Dimension(300, 200));
        txtMessage.setEditable(false);
        txtMessage.setLineWrap(true);
        
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(txtMessage, BorderLayout.CENTER);
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnYes);
        pnlButtons.add(btnNo);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        
        super.setLocationRelativeTo(playerPanel);
        int xOffset = 140;
        int yOffset = 130;
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);

        btnYes.addActionListener((ActionEvent e) -> {
            response = true;
            setVisible(false);
        });

        btnNo.addActionListener((ActionEvent e) -> {
            response = false;
            setVisible(false);
        });
    
        super.setModal(true);
        super.pack();
    }

    @Override
    public boolean getResponse() {
        return response;
    }
    
    public void setDeal(TradeDeal deal) {
        txtMessage.setText(deal.makeMessage());
    }
}
