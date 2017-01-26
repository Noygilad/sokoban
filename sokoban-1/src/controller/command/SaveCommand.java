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
		model.SaveCommand(params.get(0));
	}

}
