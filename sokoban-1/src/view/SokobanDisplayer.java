package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SokobanDisplayer extends Canvas {

	// Data members
	char[][] sokobanData;
	private StringProperty wallFileName;
	private StringProperty boxFileName;
	private StringProperty characterFileName;
	private StringProperty targetFileName;
	private StringProperty startFileName;
	private StringProperty finishFileName;
	private StringProperty floorFileName;
	int row, col;

	// C'tor
	public SokobanDisplayer() {
		wallFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		characterFileName = new SimpleStringProperty();
		targetFileName = new SimpleStringProperty();
		startFileName = new SimpleStringProperty();
		finishFileName = new SimpleStringProperty();
		floorFileName= new SimpleStringProperty();
	}

	// Getters and setters
	public String getStartFileName() {
		return startFileName.get();
	}

	public void setStartFileName(String startFileName) {
		this.startFileName.set(startFileName);
		;
	}

	public String getWallFileName() {
		return wallFileName.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	public String getBoxFileName() {
		return boxFileName.get();
	}

	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);
	}

	public String getCharacterFileName() {
		return characterFileName.get();
	}

	public void setCharacterFileName(String characterFileName) {
		this.characterFileName.set(characterFileName);
	}

	public String getTargetFileName() {
		return targetFileName.get();
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName.set(targetFileName);
	}

	public void setSokobanData(char[][] sokobanData) {
		this.sokobanData = sokobanData;
		redraw();
	}
	public String getFinishFileName() {
		return finishFileName.get();
	}

	public void setFinishFileName(String finishFileName) {
		this.finishFileName.set(finishFileName);
	}

	public String getFloorFileName() {
		return floorFileName.get();
	}

	public void setFloorFileName(String floorFileName) {
		this.floorFileName.set(floorFileName);
	}


	// Draw the board of the level
	public void redraw() {
		if (sokobanData != null) {
			double H = getHeight();
			double W = getWidth();
			double h = H / sokobanData.length;
			double w = W / sokobanData[0].length;

			GraphicsContext gc = getGraphicsContext2D();

			Image wall = null, box = null, character = null, target = null, floor=null;
			try {
				wall = new Image(new FileInputStream(wallFileName.get()));
				box = new Image(new FileInputStream(boxFileName.get()));
				character = new Image(new FileInputStream(characterFileName.get()));
				target = new Image(new FileInputStream(targetFileName.get()));
				floor = new Image(new FileInputStream(floorFileName.get()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			gc.clearRect(0, 0, W, H);

			for (int i = 0; i < sokobanData.length; i++)
				for (int j = 0; j < sokobanData[0].length; j++) {
					gc.drawImage(floor, j * w, i * h, w, h);
					if (sokobanData[i][j] == '#') {
						gc.fillRect(j * w, i * h, w, h);
						gc.drawImage(wall, j * w, i * h, w, h);
					}

					else if (sokobanData[i][j] == '@') {
						gc.drawImage(floor, j * w, i * h, w, h);
						gc.fillRect(j * w, i * h, w, h);
						gc.drawImage(box, j * w, i * h, w, h);

					} else if (sokobanData[i][j] == 'A') {
						gc.drawImage(floor, j * w, i * h, w, h);
						gc.fillRect(j * w, i * h, w, h);
						gc.drawImage(character, j * w, i * h, w, h);

					} else if (sokobanData[i][j] == 'o') {
						gc.drawImage(floor, j * w, i * h, w, h);
						gc.fillRect(j * w, i * h, w, h);
						gc.drawImage(target, j * w, i * h, w, h);

					} else {
						gc.drawImage(floor, j * w, i * h, w, h);

					}
				}
		}

	}
}