package controller.command;

import java.io.IOException;

import db.Score;
import model.Model;
import view.View;

//the class score response command
public class ScoreResponeCommand extends AbstractCommand {

	//C'tor
	public ScoreResponeCommand(Model model, View view) {
		super(model, view);
	}

	//Execute the score response command
	@Override
	public void execute() throws IOException {
		view.setScores((Score[]) params.get(0));
	}

}
