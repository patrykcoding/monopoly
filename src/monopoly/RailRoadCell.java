package monopoly;

public class RailRoadCell extends Cell {
    static private int baseRent;
    static public String COLOR_GROUP = "RAILROAD";
    static private int price;

    public static void setBaseRent(int baseRent) {
        RailRoadCell.baseRent = baseRent;
    }

    public static void setPrice(int price) {
        RailRoadCell.price = price;
    }

    @Override
    public int getPrice() {
        return RailRoadCell.price;
    }

    public int getRent() {
        return RailRoadCell.baseRent * (int)Math.pow(2, player.numberOfRR() - 1);
    }

    @Override
    public void playAction() {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = GameMaster.instance().getCurrentPlayer();
            if (player != currentPlayer) {
                currentPlayer.payRentTo(player, getRent());
            }
        }
    }
}
