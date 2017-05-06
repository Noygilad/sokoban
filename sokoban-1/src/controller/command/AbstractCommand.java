package controller.command;

import java.util.List;

import controller.server.MyServer;
import model.Model;
import view.View;

//The class AbstractCommand
public abstract class AbstractCommand implements Command {

	// Data members
	Model model;
	View view;
	MyServer myServer;
	String[] CommandLine;
	protected List<Object> params;

	// C'tor
	public AbstractCommand(Model model, MyServer myServer) {
		this.model = model;
		this.myServer = myServer;
	}

	public AbstractCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public AbstractCommand(Model model) {
		this.model = model;
	}

	// Getters and Setters
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

	public MyServer getMyServer() {
		return myServer;
	}

	public void setMyServer(MyServer myServer) {
		this.myServer = myServer;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

}
