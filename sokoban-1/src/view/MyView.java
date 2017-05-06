package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import common.Level;
import db.Score;
import db.ScoreQuery;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyView extends Observable implements Initializable, View, Observer {

	// Data members
	CLI cli;
	Level level;
	char[][] sokobanData;
	Stage stage;
	boolean levelFinished = false;
	private int count = 0;
	private Timeline timeLine;
	private SokobanControls sokobanControls;
	private ScoreView scoreView;
	private StringProperty myTimerCounter;
	int hr, min, sec;
	boolean isLoad= false;

	@FXML
	Label stepCounter;
	@FXML
	SokobanDisplayer sokobanDisplayer;
	@FXML
	Label timer;
	String musicFile = "./resources/CinderllaSong.mp3";
	Media song = new Media(new File(musicFile).toURI().toString());
	MediaPlayer mp = new MediaPlayer(song);


	//Getters and Setters
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

	// Initialize all variables
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Image Start = null;
		try {
			Start = new Image(new FileInputStream(sokobanDisplayer.getStartFileName()));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		GraphicsContext gc = sokobanDisplayer.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fill();
		gc.drawImage(Start, 0, 0, sokobanDisplayer.getWidth(), sokobanDisplayer.getHeight());

		sokobanDisplayer.setSokobanData(sokobanData);
		myTimerCounter = new SimpleStringProperty();
		this.timer.textProperty().bind(myTimerCounter);
		myTimerCounter.set("00:00:00");

		timeLine = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
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
				LinkedList<Object> params = new LinkedList<Object>();
				params.add("move");
				params.add(direction);
				notifyObservers(params);


			}

		});
	}

	// Load level
	public void LoadLevel() {
		FileChooser fc = new FileChooser();

		fc.setTitle("Load level");
		fc.setInitialDirectory(new File("./levels"));

		fc.getExtensionFilters().addAll(new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("XML files", "*.xml"), new ExtensionFilter("Object files", "*.obj"));

		File choosen = fc.showOpenDialog(null);
		if (choosen != null) {
			levelFinished = false;
			// play music
			mp.play();
			String filename = choosen.getPath();
			LinkedList<Object> params = new LinkedList<>();
			params.add("load");
			params.add(filename);
			setChanged();
			notifyObservers(params);
			startCount();



		}


	}


	// Save level
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
			LinkedList<Object> params = new LinkedList<>();
			params.add("save");
			params.add(filename);
			setChanged();
			notifyObservers(params);
		}

	}

	// close the window of the game neatly
	public void CloseWindow() {
		LinkedList<Object> params = new LinkedList<>();
		params.add("exit");
		setChanged();
		notifyObservers(params);

	}

	public void exit() {
		stage.close();
	}

	// Display GUI
	@Override
	public void displayGui(Level level) {
		char[][] mat = level.getBoard();
		this.sokobanDisplayer.setSokobanData(mat);
		if (level.isEndOfLevel() == true) {
			setLevelFinished(true);
		}
		LevelFinished();
	}

	// Check if level finished
	public void LevelFinished() {
		if (levelFinished == true) {
			Image End = null;
			try {
				End = new Image(new FileInputStream(sokobanDisplayer.getFinishFileName()));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			GraphicsContext gc = sokobanDisplayer.getGraphicsContext2D();
			gc.setFill(Color.WHITE);
			gc.fill();
			gc.drawImage(End, 0, 0, sokobanDisplayer.getWidth(), sokobanDisplayer.getHeight());

			timeLine.stop();
			mp.stop();
			Platform.runLater(new Runnable() {

				@Override
				public void run() {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Level Finished");
					alert.setHeaderText("Cinderella and prince together!");
					ButtonType button = new ButtonType("save score");
					ButtonType button1 = new ButtonType("close");
					alert.setContentText("Press OK and load a new level");
					alert.setContentText("Steps: " + stepCounter.getText() + " Time: " + timer.getText());
					alert.getButtonTypes().setAll(button, button1);
					Optional<ButtonType> res = alert.showAndWait();
					if (res.get() == button) {

						TextInputDialog dialog = new TextInputDialog();
						dialog.setTitle("Level Finished");
						dialog.setHeaderText("Cinderella and prince together!");
						dialog.setContentText("Please enter your name to register in high score table:");

						Optional<String> userName = dialog.showAndWait();

						if (userName.isPresent() && userName.get().length() > 0) {

							String userName1 = userName.get();
							LinkedList<Object> params = new LinkedList();
							params.add("saveScore");
							params.add(userName1);
							setChanged();
							notifyObservers(params);

							ScoreQuery query = new ScoreQuery();
							query.setOrderBy("timer");
							showScoresDialog(query);
						}

					}

				}
			});


		}

	}

	private void showScoresDialog(ScoreQuery query) {
		MyView parent = this;
		if (query == null)
			query = new ScoreQuery();
		ScoreQuery sendQuery = query;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				AnchorPane root = null;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ScoresWindow.fxml"));

				try {
					root = (AnchorPane) fxmlLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ScoreView view = (ScoreView) fxmlLoader.getController();
				view.addObserver(parent);

				Dialog<ButtonType> dialog = new Dialog<ButtonType>();
				dialog.setTitle("Records table");
				dialog.getDialogPane().setContent(root);
				dialog.setWidth(500);
				dialog.setHeight(400);
				dialog.setResizable(false);
				dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
				Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
				closeButton.managedProperty().bind(closeButton.visibleProperty());
				closeButton.setVisible(false);

				ArrayList<Object> list = new ArrayList();
				list.add("scoreRequest");
				list.add(sendQuery);
				setChanged();
				notifyObservers(list);
				scoreView = view;
				scoreView.addObserver(parent);
				dialog.showAndWait();
			}
		});
	}

	public void Bind(IntegerProperty stepcounter) {
		this.stepCounter.textProperty().bind(stepcounter.asString());
	}

	// Start the count
	public void startCount() {
		timeLine.setCycleCount(Animation.INDEFINITE);
		timeLine.play();
	}

	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Object> list = new ArrayList();
		list.add("scoreRequest");
		list.add(arg);
		setChanged();
		notifyObservers(list);
	}

	@Override
	public void setScores(Score[] scores) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				scoreView.setScoreItems(scores);
			}
		});
	}


}
