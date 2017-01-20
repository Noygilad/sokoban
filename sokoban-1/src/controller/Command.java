package controller;

import java.io.IOException;
import java.util.List;

import model.Model;
import view.View;

//The interface Command
public interface Command {
	//execute the command
	public void execute()throws IOException;
	//Getters and Setters
	public Model getModel();
	public void setModel(Model model);
	public View getView();
	public void setView(View view);
	public String[] getCommandLine();
	public void setCommandLine(String[] commandLine);
	public void setParams(List<String> params);

}
