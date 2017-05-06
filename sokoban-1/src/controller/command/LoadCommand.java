package controller.command;

import java.io.IOException;

import model.Model;
import view.View;

//The class LoadCommand
public class LoadCommand extends AbstractCommand {

	//C'tor
	public LoadCommand(Model model,View view) {
		super(model,view);
	}

	//Execute the load command
	@Override
	public void execute() throws IOException {
<<<<<<< HEAD
		model.LoadCommand((String)params.get(0));
=======
		model.LoadCommand(params.get(0));
>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
	}


}
