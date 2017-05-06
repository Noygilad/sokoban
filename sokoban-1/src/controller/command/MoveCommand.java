package controller.command;

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
<<<<<<< HEAD
		model.MoveCommand((String)params.get(0));
=======
		model.MoveCommand(params.get(0));
>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git

	}

}
