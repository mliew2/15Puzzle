import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PuzzleTest {
	static int[] test, win;
	static PuzzleGame testPuzzle;

	@BeforeAll
	static void init() {
		/*original puzzle
		   8 12  4  1
		  13  2     6
		   9 10 14  5
		   3  7 11 15
		 */
		test = new int[]{8, 12, 4, 1, 13, 2, 0, 6, 9, 10, 14, 5, 3, 7, 11, 15};
		testPuzzle = new PuzzleGame();
		testPuzzle.copyArray(test);
		win = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	}

	@Test
	void newConstructorTest() {
		PuzzleGame puzzle = new PuzzleGame();
		assertEquals(16, puzzle.getPuzzle().length, "Constructor Test");
	}

	@Test
	void copyArrayEmptyTest() {
		PuzzleGame puzzle = new PuzzleGame();

		puzzle.copyArray(test);

		assertTrue(Arrays.equals(test, puzzle.getPuzzle()), "Copy puzzle current puzzle empty test");
	}

	@Test
	void copyArrayNotEmptyTest() {
		PuzzleGame puzzle = new PuzzleGame();

		puzzle.newPuzzle();
		puzzle.copyArray(test);

		assertTrue(Arrays.equals(test, puzzle.getPuzzle()), "Copy puzzle current puzzle not empty test");
	}

	@Test
	void newPuzzleTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.newPuzzle();

		int temp[] = Arrays.copyOf(puzzle.getPuzzle(), 16);
		puzzle.newPuzzle();

		assertFalse(Arrays.equals(temp, puzzle.getPuzzle()), "New puzzle test");
	}

	@Test
	void newPuzzleIndexTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.newPuzzle();

		int index = puzzle.getIndex();
		puzzle.newPuzzle();

		assertFalse(index == puzzle.getIndex(), "New puzzle idnex test");
	}

	@Test
	void isNotWinTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.newPuzzle();

		assertFalse(puzzle.isWin(), "Is not a win test");
	}

	@Test
	void isWinTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.copyArray(win);

		assertTrue(puzzle.isWin(), "Win test");
	}

	@Test
	void getMoveTopTest() {
		Move move = testPuzzle.getMove(10);
		assertEquals(Movement.TOP, move.getMoveTo(), "Get move top test");
	}

	@Test
	void getMoveBottomTest() {
		Move move = testPuzzle.getMove(2);
		assertEquals(Movement.BOTTOM, move.getMoveTo(), "Get move bottom test");
	}

	@Test
	void testMoveLeftTest() {
		Move move = testPuzzle.getMove(7);
		assertEquals(Movement.LEFT, move.getMoveTo(), "Get move left test");
	}

	@Test
	void testMoveRightTest() {
		Move move = testPuzzle.getMove(5);
		assertEquals(Movement.RIGHT, move.getMoveTo(), "Get move right test");
	}

	@Test
	void moveTileTopTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.copyArray(test);
		Move move = puzzle.getMove(10);
		puzzle.moveTile(move);
		int testArray[] = {8, 12, 4, 1, 13, 2, 14, 6, 9, 10, 0, 5, 3, 7, 11, 15};
		assertTrue(Arrays.equals(testArray, puzzle.getPuzzle()), "Move tile to top test");
	}

	@Test
	void moveTileBottomTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.copyArray(test);
		Move move = puzzle.getMove(2);
		puzzle.moveTile(move);
		int testArray[] = {8, 12, 0, 1, 13, 2, 4, 6, 9, 10, 14, 5, 3, 7, 11, 15};
		assertTrue(Arrays.equals(testArray, puzzle.getPuzzle()), "Move tile to top test");
	}

	@Test
	void moveTileLeftTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.copyArray(test);
		Move move = puzzle.getMove(7);
		puzzle.moveTile(move);
		int testArray[] = {8, 12, 4, 1, 13, 2, 6, 0, 9, 10, 14, 5, 3, 7, 11, 15};
		assertTrue(Arrays.equals(testArray, puzzle.getPuzzle()), "Move tile to top test");
	}

	@Test
	void moveTileRightTest() {
		PuzzleGame puzzle = new PuzzleGame();
		puzzle.copyArray(test);
		Move move = puzzle.getMove(5);
		puzzle.moveTile(move);
		int testArray[] = {8, 12, 4, 1, 13, 0, 2, 6, 9, 10, 14, 5, 3, 7, 11, 15};
		assertTrue(Arrays.equals(testArray, puzzle.getPuzzle()), "Move tile to top test");
	}
}
