package view;

import java.net.URL;
import java.util.ResourceBundle;

import common.Level;
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
}
