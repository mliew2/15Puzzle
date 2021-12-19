import java.util.Arrays;
import java.util.Random;

public class PuzzleGame {
	private int uniquePuzzles[][];
	private int currPuzzle[];
	private int currPuzzleIndex;
	private int[] winState = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

	PuzzleGame() {
		uniquePuzzles = new int[][] {
			{1, 2, 6, 3, 9, 5, 7, 0, 4, 13, 10, 15, 12, 14, 11, 8},
			{9, 6, 13, 3, 2, 10, 7, 15, 4, 14, 0, 1, 12, 11, 5, 8},
			{11, 2, 8, 4, 10, 5, 15, 3, 12, 9, 7, 6, 1, 14, 13, 0},
			{15, 11, 5, 1, 4, 0, 6, 12, 8, 9, 14, 10, 13, 3, 7, 2},
			{8, 15, 3, 11, 1, 5, 10, 4, 12, 13, 14, 9, 0, 2, 6, 7},
			{8, 5, 10, 4, 15, 1, 2, 7, 0, 6, 3, 9, 13, 12, 14, 11},
			{7, 0, 10, 1, 14, 13, 3, 2, 12, 4, 6, 9, 15, 11, 5, 8},
			{15, 13, 3, 12, 6, 4, 1, 5, 8, 9, 11, 2, 14, 0, 10, 7},
			{14, 12, 2, 0, 10, 13, 9, 1, 3, 4, 15, 7, 5, 8, 11, 6},
			{13, 4, 5, 6, 15, 1, 11, 12, 9, 10, 2, 0, 8, 3, 14, 7},
			{0, 8, 15, 9, 12, 13, 1, 3, 14, 5, 4, 2, 6, 10, 11, 7},
			{6, 4, 5, 13, 10, 8, 9, 1, 3, 15, 11, 12, 2, 14, 0, 7},
			{11, 3, 10, 15, 0, 14, 4, 6, 1, 13, 8, 7, 12, 5, 2, 9},
			{4, 1, 0, 5, 9, 8, 2, 13, 6, 3, 15, 11, 14, 10, 7, 12},
			{2, 8, 14, 1, 6, 5, 9, 0, 11, 13, 15, 12, 3, 7, 4, 10},
			{14, 13, 12, 1, 10, 15, 9, 6, 8, 0, 2, 11, 3, 4, 7, 5}
		};
		currPuzzle = new int[16];
		currPuzzleIndex = -1;
	}

	//generate a new puzzle from the list of unique puzzles
	public void newPuzzle() {
		Random r = new Random();
		int index = currPuzzleIndex;

		while(index == currPuzzleIndex) {
			index = r.nextInt(uniquePuzzles.length);
		}

		currPuzzleIndex = index;
		copyArray(uniquePuzzles[currPuzzleIndex]);
	}

	//check if the current board is a win condition
	public boolean isWin(){
		if(Arrays.equals(currPuzzle, winState))
			return true;
		else
			return false;
	}

	//copy the unique puzzle into the current puzzle user is playing on
	public void copyArray(int[] puzzle){
		for(int i = 0; i < 16; i++)
			currPuzzle[i] = puzzle[i];
	}

	//returns the move based on the index of the tile that was selected
	public Move getMove(int index) {
		Move move = new Move(index);

		//checks if the empty tile is beside the selected tile
		//check top
		if(index > 3) {
			if(isEmpty(index-4)) {
				move.setMoveTo(Movement.TOP);
				return move;
			}
		}
		//check bottom
		if(index < 12) {
			if(isEmpty(index+4)) {
				move.setMoveTo(Movement.BOTTOM);
				return move;
			}
		}
		//check left
		if((index%4) != 0) {
			if(isEmpty(index-1)) {
				move.setMoveTo(Movement.LEFT);
				return move;
			}
		}
		//check right
		if((index%4) != 3) {
			if(isEmpty(index+1)) {
				move.setMoveTo(Movement.RIGHT);
				return move;
			}
		}

		return move;
	}

	//checks if the tile at this index is the empty tile
	public boolean isEmpty(int index) {
		return currPuzzle[index] == 0;
	}

	//move the tiles based on the move object passed in
	public void moveTile(Move move) {
		int tileIndex = move.getIndex();
		int emptyTileIndex = tileIndex;

		switch(move.getMoveTo()) {
		case TOP:
			emptyTileIndex -= 4;
			break;
		case BOTTOM:
			emptyTileIndex += 4;
			break;
		case LEFT:
			emptyTileIndex -= 1;
			break;
		case RIGHT:
			emptyTileIndex += 1;
			break;
		}

		swap(tileIndex, emptyTileIndex);
	}

	//swap the position of two tiles
	public void swap(int index1, int index2) {
		int temp = currPuzzle[index1];
		currPuzzle[index1] = currPuzzle[index2];
		currPuzzle[index2] = temp;
	}

	//returns value at the index passed in
	public int getValue(int index) {
		return currPuzzle[index];
	}

	//returns the current state of the puzzle array
	public int[] getPuzzle() {
		return currPuzzle;
	}

	//returns index of current puzzle from the unique puzzles array
	//used for testing purpose only
	public int getIndex() {
		return currPuzzleIndex;
	}
}
