package controller;

import java.util.List;


import model.Model;
import view.View;

//The class AbstractCommand
public abstract class AbstractCommand implements Command {

	//Data members
	 Model model;
	 View view;


	 public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	String[] CommandLine;
	 protected List<String> params;


	 public void setParams(List<String> params) {
		this.params = params;
	}

	//C'tor
	public AbstractCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}

	public AbstractCommand(Model model) {
		this.model=model;
	}

	//Getters and Setters
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String[] getCommandLine() {
		return CommandLine;
	}

	public void setCommandLine(String[] commandLine) {
		CommandLine = commandLine;
	}

}
