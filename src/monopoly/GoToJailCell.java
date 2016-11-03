package monopoly;

public class GoToJailCell extends Cell {
	
    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    @Override
    public void playAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        JailCell jail = 
                (JailCell)(GameMaster.instance().getGameBoard().queryCell("Jail"));
        GameMaster.instance().sendToJail(currentPlayer);
    }
}
