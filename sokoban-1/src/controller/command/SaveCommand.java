package controller.command;


import java.io.IOException;

import model.Model;

//The class Save Command
public class SaveCommand extends AbstractCommand {

	//C'tor
	public SaveCommand(Model model) {
		super(model);
	}

	//Execute save command
	@Override
	public void execute()throws IOException {
		System.out.println("save command");
<<<<<<< HEAD
		model.SaveCommand((String)params.get(0));
=======
		model.SaveCommand(params.get(0));
>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
	}

}
