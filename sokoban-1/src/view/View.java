package view;

import java.net.URL;
import java.util.ResourceBundle;

import common.Level;
import db.Score;
import javafx.beans.property.IntegerProperty;

public interface View {
	public void displayGui(Level level);
	public void CloseWindow();
	public void exit();
	public void SaveLevel();
	public void LoadLevel();
	public void initialize(URL location, ResourceBundle resources);
	public void Bind(IntegerProperty stepcounter);
	public void setControls(SokobanControls sokobanControls);
	public void setScores(Score[] scores);
}
