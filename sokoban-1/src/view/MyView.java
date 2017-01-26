package view;


import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;
import common.Level;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyView extends Observable implements Initializable, View {

	//Data members
	CLI cli;
	LinkedList<String> params;
	Level level;
	char[][] sokobanData;
	Stage stage;
	boolean levelFinished = false;
	private int count = 0;
	private StringProperty myTimerCounter;
	private Timeline timeLine;
	private SokobanControls sokobanControls;
	@FXML
	Label stepCounter;
	@FXML
	SokobanDisplayer sokobanDisplayer;
	@FXML
	Label timer;

	//Getters and setters
	public Boolean getLevelFinished() {
		return levelFinished;
	}

	public void setLevelFinished(Boolean levelFinished) {
		this.levelFinished = levelFinished;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	@Override
	public void setControls(SokobanControls sokobanControls) {
		this.sokobanControls = sokobanControls;
	}

	//Initialize all variables
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sokobanDisplayer.setSokobanData(sokobanData);
		myTimerCounter = new SimpleStringProperty();
		this.timer.textProperty().bind(myTimerCounter);
		myTimerCounter.set("00:00:00");
		timeLine = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
			int hr, min, sec;
			int currCount = count;
			hr = currCount / 3600;
			currCount = currCount - hr * 3600;
			min = currCount / 60;
			currCount = currCount - min * 60;
			sec = currCount;

			myTimerCounter.set(
					String.format("%02d", hr) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec));
			count++;
		}));
		sokobanDisplayer.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
				(e) -> sokobanDisplayer.requestFocus());

		sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {

				String direction = null;
				if (event.getCode() == sokobanControls.getUpCode()) {
					direction = "up";
				}
				if (event.getCode() == sokobanControls.getDownCode()) {

					direction = "down";
				}
				if (event.getCode() == sokobanControls.getRightCode()) {
					direction = "right";
				}
				if (event.getCode() == sokobanControls.getLeftCode()) {
					direction = "left";
				}
				setChanged();
				params = new LinkedList<String>();
				params.add("move");
				params.add(direction);
				notifyObservers(params);
			}

		});
	}

	//Load level
	public void LoadLevel() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Load level");
		fc.setInitialDirectory(new File("./levels"));

		fc.getExtensionFilters().addAll(new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("XML files", "*.xml"), new ExtensionFilter("Object files", "*.obj"));

		File choosen = fc.showOpenDialog(null);
		if (choosen != null) {
			String filename = choosen.getPath();
			params = new LinkedList<>();
			params.add("load");
			params.add(filename);
			setChanged();
			notifyObservers(params);
			startCount();
		}
	}

	//Save level
	public void SaveLevel() {
		System.out.println("saveLevel-in my view");
		FileChooser fc = new FileChooser();
		fc.setTitle("Save Level");
		fc.setInitialDirectory(new File("./levels"));

		fc.getExtensionFilters().addAll(new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("XML files", "*.xml"), new ExtensionFilter("Object files", "*.obj"));

		File choosen = fc.showSaveDialog(null);
		if (choosen != null) {
			String filename = choosen.getPath();
			params = new LinkedList<>();
			params.add("save");
			params.add(filename);
			setChanged();
			notifyObservers(params);
		}

	}

	//close the window of the game neatly
	public void CloseWindow() {
		params = new LinkedList<>();
		params.add("exit");
		setChanged();
		notifyObservers(params);

	}

	public void exit() {
		stage.close();
	}

	//Display GUI
	@Override
	public void displayGui(Level level) {
		char[][] mat = level.getBoard();
		this.sokobanDisplayer.setSokobanData(mat);
		if (level.isEndOfLevel() == true) {
			setLevelFinished(true);
		}
		LevelFinished();
	}

	//Check if level finished
	public void LevelFinished() {
		if (levelFinished == true) {

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Level Finished");
					alert.setHeaderText("Cinderella and prince together!");
					alert.setContentText("Press OK and load a new level");
					alert.setContentText("Steps: " + stepCounter.getText());
					alert.showAndWait();
				}
			});
		}

	}


	public void Bind(IntegerProperty stepcounter) {
		this.stepCounter.textProperty().bind(stepcounter.asString());
	}

	//Start the count
	public void startCount() {
		timeLine.setCycleCount(Animation.INDEFINITE);
		timeLine.play();
	}


}
