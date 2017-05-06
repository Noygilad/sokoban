package controller.command;

import java.io.IOException;

import db.ScoreQuery;
import model.Model;

//the class score request command
public class ScoreRequestCommand extends AbstractCommand {

	//C'tor
	public ScoreRequestCommand(Model model) {
		super(model);

	}

	//execute the score request command
	@Override
	public void execute() throws IOException {
		model.requestScoresCommand((ScoreQuery) params.get(0));
	}

}
