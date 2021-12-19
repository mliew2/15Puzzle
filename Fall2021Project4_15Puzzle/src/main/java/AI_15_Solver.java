import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AI_15_Solver implements Runnable {
	int[] puzzle;
	String heuristic;
	Consumer<ArrayList<Node>> callback;
	ExecutorService ex;

	AI_15_Solver(int[] puzzle, String heuristic, ExecutorService ex, Consumer<ArrayList<Node>> callback) {
		this.puzzle = puzzle;
		this.heuristic = heuristic;
		this.callback = callback;
		this.ex = ex;
	}

	//gets the ArrayList from the Callable class and returns it to the application thread
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Future<ArrayList<Node>> future= ex.submit(new A_Star());
		try {
			ArrayList<Node> solutionPath = future.get();
			callback.accept(solutionPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class A_Star implements Callable<ArrayList<Node>> {
		private DB_Solver2 startAStar;

		A_Star() {
			Node startState = new Node(puzzle);
			startState.setDepth(0);
			startAStar = new DB_Solver2(startState, heuristic);
		}

		//starts the A* solver and returns the ArrayList once it has found a solution
		@Override
		public ArrayList<Node> call() throws Exception {
			// TODO Auto-generated method stub
			Node solution = startAStar.findSolutionPath();
			if(solution != null) {
				ArrayList<Node> solutionPath = startAStar.getSolutionPath(solution);
				solutionPath.remove(0);  //remove first element which is the current board
				return (ArrayList<Node>) solutionPath.stream().limit(10).collect(Collectors.toList());
			}
			return null;
		}
	}
}
