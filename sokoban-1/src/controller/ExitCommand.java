package controller;

import java.io.IOException;

import model.Model;

//The Class ExitCommand
public class ExitCommand extends AbstractCommand  {

	//C'tor
	public ExitCommand(Model model) {
		super(model);
	}

	//Execute exit command
	@Override
	public void execute() throws IOException {
		model.ExitCommand();
	}

}
