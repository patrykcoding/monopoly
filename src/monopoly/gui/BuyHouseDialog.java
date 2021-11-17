
package monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import monopoly.GameBoard;
import monopoly.MainController;
import monopoly.Player;
import monopoly.cells.PropertyCell;
import monopoly.enums.ColorGroup;

public class BuyHouseDialog extends JDialog {
    private static final long serialVersionUID = -8707274599957567230L;
    private final JComboBox<Integer> housesCombobox;
    private final MainController mainController;
    private JComboBox<ColorGroup> monopolyCombobox;
    private final Player player;

    /**
     * @param mainController
     * @param player
     * @param panel
     */
    public BuyHouseDialog(MainController mainController, Player player, PlayerPanel panel) {
        this.mainController = mainController;
        this.player = player;
        
        Container container = super.getContentPane();
        housesCombobox = new JComboBox<>();
        container.setLayout(new GridLayout(3, 2));
        container.add(new JLabel("Select monopoly"));
        container.add(buildMonopolyComboBox());
        container.add(new JLabel("Number of houses"));
        container.add(housesCombobox);
        container.add(buildOKButton());
        container.add(buildCancelButton());
        container.doLayout();
        super.pack();
        super.setLocationRelativeTo(panel);
        
        updateHousesComboBox(monopolyCombobox.getItemAt(0));
        
        monopolyCombobox.addActionListener((ActionEvent e) -> {
            ColorGroup monopoly = (ColorGroup)monopolyCombobox.getSelectedItem();
            updateHousesComboBox(monopoly);
        });
    }

    /**
     * create the cancel button jbutton with its action
     * @return cancel button
     */
    private JButton buildCancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener((ActionEvent e) -> {
            cancelClicked();
        });
        return button;
    }

    /**
     * build the monopolyCombobox with the player colorGroup
     * @return monopolyCombobox of type JComboBox
     */
    private JComboBox<ColorGroup> buildMonopolyComboBox() {
        monopolyCombobox = new JComboBox<>();
        List<ColorGroup> monopolies = mainController.getMonopolies(player);
        monopolies.stream().forEach((monopoly) -> {
            monopolyCombobox.addItem(monopoly);
        });
        return monopolyCombobox;
    }

    /**
     * create the Ok button with its action
     * @return ok button
     */
    private JButton buildOKButton() {
        JButton button = new JButton("OK");
        button.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        return button;
    }

    /**
     * do the cancel action
     */
    private void cancelClicked() {
        this.dispose();
    }

    /**
     * Make the ok action which is purchasing a house
     */
    private void okClicked() {
        ColorGroup monopoly = (ColorGroup)monopolyCombobox.getSelectedItem();
        int number = housesCombobox.getSelectedIndex() + 1;
        mainController.purchaseHouse(monopoly, number);
        this.dispose();
    }

    /**
     * update the housesComboBox with the given monopoly properties
     * @param monopoly
     */
    private void updateHousesComboBox(ColorGroup monopoly) {
        housesCombobox.removeAllItems();
        GameBoard gameBoard = mainController.getGameBoard();
        List<PropertyCell> properties = gameBoard.getPropertiesInMonopoly(monopoly);
        int numHouses = properties.get(0).getNumHouses();
        int maxHouses = 5;
        int maxPurchase = maxHouses - numHouses;
        for (int i = 1; i <= maxPurchase; i++)
            housesCombobox.addItem(i);
    }
}
