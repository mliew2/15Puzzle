import javafx.scene.control.Button;

public class GameButton extends Button{
	private int row, col;

	GameButton(String text) {
		this.setText(text);
	}

	void setPosition(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
