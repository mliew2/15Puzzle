import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class PuzzleController implements Initializable{

	private ArrayList<Node> solutionPath;
	private PuzzleGame puzzle;
	private EventHandler<ActionEvent> event;
	private ExecutorService ex;

	@FXML
	GridPane grid;

	@FXML
	Button solutionButton, h1Button, h2Button;

	@FXML
	MenuItem newPuzzleMenu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		puzzle = new PuzzleGame();
		ex = Executors.newFixedThreadPool(5);

		//event handler for game play logic when buttons are pressed
		event  = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				solutionButton.setDisable(true);
				GameButton temp = (GameButton) e.getSource();

				Move move = puzzle.getMove((temp.getRow()*4) + temp.getCol());

				if(move.getMoveTo() != null) {
					makeMove(move, temp);
				}

				//if the user has cleared the puzzle
				if (puzzle.isWin()) {
					modifyAllButtons(true);
					PauseTransition pause;
					pause = new PauseTransition(Duration.millis(500));
					pause.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							Platform.runLater(()->showDialogBox("User"));
						}
					});

					pause.play();
				}
			}
		};

		newPuzzleGame();
	}

	//get a new puzzle from menu
	public void newPuzzleMethod(ActionEvent e) {
		newPuzzleGame();
	}

	//exit the program from menu
	public void exitMethod(ActionEvent e) {
		exit();
	}

	//calls heuristic 1 of A* to solve the puzzle when clicked
	public void h1Method(ActionEvent e) {
		startAISolver("heuristicOne");
	}

	//calls heuristic 2 of A* to solve the puzzle when clicked
	public void h2Method(ActionEvent e) {
		startAISolver("heuristicTwo");
	}

	//shows the solution when clicked
	public void solutionMethod(ActionEvent e) {
		solutionButton.setDisable(true);
		showSolution();
	}

	//generates a new puzzle from the unique puzzles and populate the grid based on it 
	public void newPuzzleGame() {
		grid.getChildren().clear();
		puzzle.newPuzzle();
		modifyAllButtons(false);
		solutionButton.setDisable(true);

		int value;
		GameButton button;
		int index;

		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				index = i*4 + j;
				value = puzzle.getPuzzle()[index];

				if(value == 0)
					continue;

				//custom button class created for the game
				button = new GameButton(value + "");
				button.setOnAction(event);
				button.setPosition(j, i);

				grid.add(button, j, i);
			}
		}
	}

	//exits the program and terminate all threads
	void exit() {
		Platform.exit();
		System.exit(0);
	}

	//move the tiles using animation and updates the puzzle array in the puzzle class instance
	public void makeMove(Move move, GameButton temp) {
		puzzle.moveTile(move);

		TranslateTransition transition = new TranslateTransition(Duration.millis(250));
		transition.setNode(temp);

		int col = move.getCol();
		int row = move.getRow();

		switch(move.getMoveTo()) {
		case TOP:
			transition.setByY(-1 * (temp.getLayoutBounds().getMaxY() + grid.getHgap()));
			row-=1;
			break;
		case BOTTOM:
			transition.setByY(temp.getLayoutBounds().getMaxY() + grid.getHgap());
			row+=1;
			break;
		case LEFT:
			transition.setByX(-1 * (temp.getLayoutBounds().getMaxX() + grid.getVgap()));
			col-=1;
			break;
		case RIGHT:
			transition.setByX(temp.getLayoutBounds().getMaxX() + grid.getVgap());
			col+=1;
			break;
		}

		temp.setPosition(col, row);

		transition.play();
	}

	//starts A* to solve the puzzle, takes in a string to decide which heuristic
	public void startAISolver(String heuristic) {
		modifyAllButtons(true);

		//the solver runs in a different thread
		ex.execute(new AI_15_Solver(puzzle.getPuzzle(), heuristic, ex,
				data->{Platform.runLater(()->{
					solutionPath = new ArrayList<Node>(data);
					solutionButton.setDisable(false);
				});
				}));
	}

	//shows the next 10 or remaining moves if the solution is available
	//if puzzle is solved, show a dialog box to inform user
	public void showSolution() {
		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));

		Move move = Parse_AI_Result.getNextMove(puzzle.getPuzzle(), solutionPath.get(0).getKey());
		GameButton button = getButton(puzzle.getValue(move.getIndex()) + "");

		solutionPath.remove(0);

		pause.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				makeMove(move, button);

				if(solutionPath.size()==0) {
					solutionButton.setDisable(true);
					if(puzzle.isWin()) { //if the AI has solved the puzzle
						PauseTransition endingPause = new PauseTransition(Duration.seconds(1.5));
						endingPause.setOnFinished(e->Platform.runLater(()->showDialogBox("AI")));
						endingPause.play();
					} else {
						modifyAllButtons(false);
					}
				} else {
					showSolution();
				}
			}
		});

		pause.play();
	}

	//show an dialog box when puzzle is solved which allows user to either exit or try another puzzle
	//takes in a string to show different message based on who solved the puzzle
	public void showDialogBox(String solver) {
		ButtonType restart = new ButtonType("New Puzzle");
		ButtonType exit = new ButtonType("Exit");

		Alert alert = new Alert(AlertType.NONE);

		switch(solver) {
		case "User":
			alert = new Alert(AlertType.NONE, "Congratulations!! You Won!", restart, exit);
			alert.setTitle("Win");
			break;
		case "AI":
			alert = new Alert(AlertType.NONE, "AI has finished solving the puzzle", restart, exit);
			alert.setTitle("Done");
			break;
		}

		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == restart)
			newPuzzleGame();
		else if(result.get() == exit) {
			exit();
		}
	}

	//returns the GameButton instance that contains the value passed in
	//used when showing solutions after AI solves the puzzle
	public GameButton getButton(String value) {
		for(javafx.scene.Node e: grid.getChildren()) {
			GameButton button = (GameButton) e;
			if(button.getText().equals(value))
				return button;
		}
		return null;
	}

	//enable or disable all buttons, besides show solution button, and new puzzle in the menu bar
	//used when puzzle is solved, by either user or AI, or when animation for the solution is running
	public void modifyAllButtons(boolean choice) {
		h1Button.setDisable(choice);
		h2Button.setDisable(choice);
		newPuzzleMenu.setDisable(choice);

		for(javafx.scene.Node e: grid.getChildren()) {
			GameButton button = (GameButton) e;
			button.setDisable(choice);
		}
	}
}
