package monopoly;

public class GoToJailCell extends Cell {
	
    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    @Override
    public void playAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        GameMaster.instance().sendToJail(currentPlayer);
    }
}
