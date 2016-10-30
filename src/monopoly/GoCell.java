package monopoly;

public class GoCell extends Cell {
	public GoCell() {
            super.setName("Go");
            setAvailable(false);
	}

        @Override
	public void playAction() {
	}
	
        @Override
	public void setName(String name) {
	}
}
