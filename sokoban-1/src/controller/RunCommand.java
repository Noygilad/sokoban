package commands;


import java.io.IOException;

//The interface RunCommand
public interface RunCommand {
	//Set CLI
	public void setCli(CLI cli);
	
	//Load command
	public void LoadCommand(String[] CommandLine)throws IOException;
	
	//Display command
	public void DisplayCommand();
	
	//Move command
	public void MoveCommand(String[] CommandLine);
	
	//Save command
	public void SaveCommand(String[] CommandLine) throws IOException;
	
	//Exit command
	public void ExitCommand();
	
}
