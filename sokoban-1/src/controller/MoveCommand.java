package controller;

import model.Model;

//The class MoveCommand
public class MoveCommand extends AbstractCommand {

	//C'tor
	public MoveCommand(Model model) {
		super(model);
	}

	//Execute the move command
	@Override
	public void execute() {
		model.MoveCommand(CommandLine);

	}

}
