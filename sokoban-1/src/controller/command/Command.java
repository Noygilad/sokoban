package controller.command;

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
<<<<<<< HEAD
	public void setParams(List<Object> params);
=======
	public void setParams(List<String> params);

>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
}
