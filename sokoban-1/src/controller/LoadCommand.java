package controller;

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
		model.LoadCommand(params.get(0));
	}


}
