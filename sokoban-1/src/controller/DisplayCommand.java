package controller;

import model.Model;
import view.View;

//The class DisplayCommand
public class DisplayCommand extends AbstractCommand {

	//C'tor
	public DisplayCommand(Model model,View view) {
		super(model,view);

	}

	//Execute the display
	@Override
	public void execute() {
		model.DisplayCommand();
	}





}
