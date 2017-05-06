package controller.command;

import java.io.IOException;

import controller.Controller;
import javafx.application.Platform;
import model.Model;
import view.View;

//The Class ExitCommand
public class ExitCommand extends AbstractCommand {
	Controller controller;

	// C'tor
	public ExitCommand(Model model, View view, Controller controller) {
		super(model, view);
		this.controller = controller;
	}

	// Execute exit command
	@Override
	public void execute() throws IOException {
		model.ExitCommand();
		if (controller.getMyServer() == null) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					view.exit();

				}
			});
			controller.Stop();
		}
	}

}
