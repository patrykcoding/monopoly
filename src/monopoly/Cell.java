package monopoly;

public abstract class Cell {
    private boolean available = true;
    private String name;
    protected Player player;

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }
	
    public int getPrice() {
        return 0;
    }

    public boolean isAvailable() {
        return available;
    }
	
    public abstract void playAction();

    public void setAvailable(boolean available) {
        this.available = available;
    }
	
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
