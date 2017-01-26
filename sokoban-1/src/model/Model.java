package model;

import java.io.IOException;

import common.Level;
import view.CLI;

public interface Model {
	//Set CLI
	public void setCli(CLI cli);

	//Load command
	public void LoadCommand(String CommandLine)throws IOException;

	//Display command
	public String DisplayCommand();

	//Move command
	public void MoveCommand(String CommandLine);

	//Save command
	public void SaveCommand(String CommandLine) throws IOException;

	//Exit command
	public void ExitCommand();

	//getcurrentLevel
	public Level getCurrentLevel();
}
