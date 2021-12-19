import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AITest {
	static int[] puzzle, win;
	static String[] heuristic;

	@BeforeAll
	static void init() {
		/*original puzzle
		   8 12  4  1
		  13  2     6
		   9 10 14  5
		   3  7 11 15
		 */
		puzzle = new int[]{8, 12, 4, 1, 13, 2, 0, 6, 9, 10, 14, 5, 3, 7, 11, 15};
		win = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		heuristic = new String[] {"heuristicOne", "heuristicTwo"};
	}

	@Test
	void firstIndexH1Test() {
		Node startState = new Node(puzzle);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[0]);
		Node solution = aStar.findSolutionPath();
		assertTrue(Arrays.equals(puzzle, aStar.getSolutionPath(solution).get(0).getKey()), "Heuristic one first index test");

	}

	@Test
	void lastIndexH1Test() {
		Node startState = new Node(puzzle);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[0]);
		Node solution = aStar.findSolutionPath();
		ArrayList<Node> solutionPath = aStar.getSolutionPath(solution);
		assertTrue(Arrays.equals(win, solutionPath.get(solutionPath.size()-1).getKey()), "Heuristic one last index test");
	}

	@Test
	void firstIndexH2Test() {
		Node startState = new Node(puzzle);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[1]);
		Node solution = aStar.findSolutionPath();
		assertTrue(Arrays.equals(puzzle, aStar.getSolutionPath(solution).get(0).getKey()), "Heuristic one first index test");
	}

	@Test
	void lastIndexH2Test() {
		Node startState = new Node(puzzle);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[1]);
		Node solution = aStar.findSolutionPath();
		ArrayList<Node> solutionPath = aStar.getSolutionPath(solution);
		assertTrue(Arrays.equals(win, solutionPath.get(solutionPath.size()-1).getKey()), "Heuristic one last index test");
	}

	@Test
	void solvedBoardH1Test() {
		Node startState = new Node(win);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[0]);
		Node solution = aStar.findSolutionPath();
		assertTrue(Arrays.equals(win, aStar.getSolutionPath(solution).get(0).getKey()), "Heuristic one solved puzzle first index test");
	}

	@Test
	void solvedBoardH1LengthTest() {
		Node startState = new Node(win);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[0]);
		Node solution = aStar.findSolutionPath();
		assertEquals(1, aStar.getSolutionPath(solution).size(), "Heuristic one solved puzzle length test");
	}

	@Test
	void solvedBoardH2Test() {
		Node startState = new Node(win);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[1]);
		Node solution = aStar.findSolutionPath();
		assertTrue(Arrays.equals(win, aStar.getSolutionPath(solution).get(0).getKey()), "Heuristic one solved puzzle first index test");
	}

	@Test
	void solvedBoardH2LengthTest() {
		Node startState = new Node(win);
		startState.setDepth(0);
		DB_Solver2 aStar = new DB_Solver2(startState, heuristic[1]);
		Node solution = aStar.findSolutionPath();
		assertEquals(1, aStar.getSolutionPath(solution).size(), "Heuristic one solved puzzle length test");
	}
}
