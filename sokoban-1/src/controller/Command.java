package commands;

import java.io.IOException;

//The interface Command
public interface Command {
	//execute the command
	public void execute()throws IOException;
	//Getters and Setters
	public RunCommand getRun();
	public void setRun(RunCommand run);
	public String[] getCommandLine();
	public void setCommandLine(String[] commandLine);
	
}
