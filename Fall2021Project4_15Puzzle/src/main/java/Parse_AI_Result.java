
public class Parse_AI_Result {
	//returns a Move object by comparing the current puzzle array
	//to the next puzzle array in the solution path returned by the A* solution
	public static Move getNextMove(int[] currPuzzle, int[] nextMove) {
		int indexOfEmptyTile = -1;
		int indexOfTileMoved = -1;
		for(int i=0; i<16; i++) {
			if(currPuzzle[i] == 0) {
				indexOfEmptyTile = i;
			} else if(currPuzzle[i] != 0 && (currPuzzle[i] != nextMove[i])) {
				indexOfTileMoved = i;
			}
		}

		Move move = new Move(indexOfTileMoved);

		int movement = indexOfTileMoved - indexOfEmptyTile;

		switch (movement) {
		case 1:
			move.setMoveTo(Movement.LEFT);
			break;
		case -1:
			move.setMoveTo(Movement.RIGHT);
			break;
		case 4:
			move.setMoveTo(Movement.TOP);
			break;
		case -4:
			move.setMoveTo(Movement.BOTTOM);
			break;
		}

		return move;
	}
}
