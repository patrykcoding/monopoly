package monopoly.gui;

import javax.swing.JOptionPane;

import monopoly.*;

public class Main {

    private static final GameMaster master = new GameMaster();
    
    private static int inputNumberOfPlayers(MainWindow window) {
        int numPlayers = 0;
        while(numPlayers < 2 || numPlayers > PlayerController.MAX_PLAYER) {
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
            }
            if (numPlayers < 2 || numPlayers > PlayerController.MAX_PLAYER) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Please input a number between 2 and 8"
                );
            } else {
                master.setNumberOfPlayers(numPlayers);
            }
        }
        return numPlayers;
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow(master);
        if (args.length > 0) {
            if (args[0].equals("test")) {
                master.setTestMode(true);
            }
            try {
                Class c = Class.forName(args[1]);
                master.setGameBoard((GameBoard)c.newInstance());
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
            master.getPlayer(i).setName(name);
        }
        window.setupGameBoard(master.getGameBoard());
        window.setVisible(true);
        master.setGUI(window);
        master.startGame();
    }
}
