
public class Move {
	private int index, row, col;
	private Movement moveTo;

	Move(int index) {
		this.index = index;
		row = index/4;
		col = index%4;
	}

	public Movement getMoveTo() {
		return moveTo;
	}

	public void setMoveTo(Movement moveTo) {
		this.moveTo = moveTo;
	}

	public int getIndex() {
		return index;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
