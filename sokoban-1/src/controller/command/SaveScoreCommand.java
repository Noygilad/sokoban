package controller.command;

import java.io.IOException;

import model.Model;
// the class save score command
public class SaveScoreCommand extends AbstractCommand {

	//C'tor
	public SaveScoreCommand(Model model) {
		super(model);
	}

	//Execute the save score command
	@Override
	public void execute() throws IOException {
		model.SaveScoreCommand((String) params.get(0));
	}

}
