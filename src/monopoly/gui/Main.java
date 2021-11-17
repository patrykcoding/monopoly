package monopoly.gui;

import javax.swing.JOptionPane;
import monopoly.*;

public class Main {

    private static final MainController MAIN_CONTROLLER = new MainController();

    /**
     * Taking the valid int number of the players by the user
     * @param window
     * @return the number of the players entered by the user
     */
    private static int inputNumberOfPlayers(MainWindow window) {
        int numPlayers = 0;
        while(numPlayers < 2 || numPlayers > BoardController.MAX_PLAYER) {
            String numberOfPlayers = JOptionPane.showInputDialog(
                window, 
                "How many players"
            );
            if (numberOfPlayers == null) {
                System.exit(0);
            }
            try {
                numPlayers = Integer.parseInt(numberOfPlayers);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Please input a number"
                );
                continue;
            }
            if (numPlayers < 2 || numPlayers > BoardController.MAX_PLAYER) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Please input a number between 2 and 8"
                );
            } else {
                MAIN_CONTROLLER.setNumberOfPlayers(numPlayers);
            }
        }
        return numPlayers;
    }

    /**
     * main function of the project which is doing many things to start the game:
     *  1- Initialize the window and set the gameBoard.
     *  2- Get the number of the players from the user.
     *  3- Get each player name if any.
     *  4- Set the GUI of the game and start it.
     * @param args
     */
    public static void main(String[] args) {
        MainWindow window = new MainWindow(MAIN_CONTROLLER);
        if (args.length > 0) {
            try {
                GameBoard board = (GameBoard) Class.forName(args[1]).newInstance();
                MAIN_CONTROLLER.setGameBoard(board);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Class Not Found.  Program will exit"
                );
                System.exit(0);
            } catch (IllegalAccessException e ) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Illegal Access of Class.  Program will exit"
                );
                System.exit(0);
            } catch (InstantiationException e) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Class Cannot be Instantiated.  Program will exit"
                );
                System.exit(0);
            }
        }
        
        int numPlayers = inputNumberOfPlayers(window);
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(
                window, 
                "Please input name for Player " + (i + 1)
            );
            if (name.equals("") || name.trim().isEmpty()) {
                MAIN_CONTROLLER.getPlayer(i).setName("Player " + (i + 1));
            } else {
                MAIN_CONTROLLER.getPlayer(i).setName(name);
            }
        }
        window.setupGameBoard(MAIN_CONTROLLER.getGameBoard());
        window.setVisible(true);
        MAIN_CONTROLLER.setGUI(window);
        MAIN_CONTROLLER.startGame();
    }
}
