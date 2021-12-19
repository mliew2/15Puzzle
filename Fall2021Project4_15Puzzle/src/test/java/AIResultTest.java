import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AIResultTest {
	static int [] original, movedTop, movedBottom, movedLeft, movedRight;

	@BeforeAll
	static void init() {
		/*original puzzle
		   8 12  4  1
		  13  2     6
		   9 10 14  5
		   3  7 11 15
		 */
		original = new int[]{8, 12, 4, 1, 13, 2, 0, 6, 9, 10, 14, 5, 3, 7, 11, 15};

		/*move 14 up
		   8 12  4  1
		  13  2 14  6
		   9 10     5
		   3  7 11 15
		 */
		movedTop = new int[]{8, 12, 4, 1, 13, 2, 14, 6, 9, 10, 0, 5, 3, 7, 11, 15};

		/*move 4 down
		   8 12     1
		  13  2  4  6
		   9 10 14  5
		   3  7 11 15
		 */
		movedBottom = new int[]{8, 12, 0, 1, 13, 2, 4, 6, 9, 10, 14, 5, 3, 7, 11, 15};

		/*move 6 left
		   8 12  4  1
		  13  2  6   
		   9 10 14  5
		   3  7 11 15
		 */
		movedLeft = new int[]{8, 12, 4, 1, 13, 2, 6, 0, 9, 10, 14, 5, 3, 7, 11, 15};

		/*move 2 right
		   8 12  4  1
		  13     2  6
		   9 10 14  5
		   3  7 11 15
		 */
		movedRight = new int[]{8, 12, 4, 1, 13, 0, 2, 6, 9, 10, 14, 5, 3, 7, 11, 15};
	}

	@Test
	void testMoveTopIndexTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedTop);
		assertEquals(10, move.getIndex(), "Move to top check index Test");
	}

	@Test
	void testMoveTopMovementTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedTop);
		assertEquals(Movement.TOP, move.getMoveTo(), "Move to top check movement Test");
	}

	@Test
	void testMoveBottomIndexTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedBottom);
		assertEquals(2, move.getIndex(), "Move to bottom check index Test");
	}

	@Test
	void testMoveBottomMovementTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedBottom);
		assertEquals(Movement.BOTTOM, move.getMoveTo(), "Move to bottom check movement Test");
	}

	@Test
	void testMoveLeftIndexTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedLeft);
		assertEquals(7, move.getIndex(), "Move to left check index Test");
	}

	@Test
	void testMoveLeftMovementTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedLeft);
		assertEquals(Movement.LEFT, move.getMoveTo(), "Move to left check movement Test");
	}

	@Test
	void testMoveRightIndexTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedRight);
		assertEquals(5, move.getIndex(), "Move to right check index Test");
	}

	@Test
	void testMoveRightMovementTest() {
		Move move = Parse_AI_Result.getNextMove(original, movedRight);
		assertEquals(Movement.RIGHT, move.getMoveTo(), "Move to right check movement Test");
	}
}
