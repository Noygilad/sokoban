package controller;

import java.io.PrintWriter;

import controller.server.MyServer;
import model.Model;
import view.View;

//The class DisplayCommand
public class DisplayCommand extends AbstractCommand {

	// C'tor
	public DisplayCommand(Model model, View view) {
		super(model, view);

	}

	public DisplayCommand(Model model, MyServer myServer) {
		super(model, myServer);

	}

	// Execute the display
	@Override
	public void execute() {
		if (getMyServer() != null) {
			PrintWriter printWriter = getMyServer().getOutToClientFromServer();
			printWriter.println(model.DisplayCommand());
			printWriter.flush();
		} else {
			view.displayGui(model.getCurrentLevel());
		}
	}

}
