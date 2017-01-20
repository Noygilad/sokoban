package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


import model.Model;
import view.View;

public class SokobanController implements Observer {

	private Model model;
	private View view;
	private Controller controller;
	private HashMap<String,Command> command;

	public void setModel(Model model) {
		this.model = model;
	}


	public void setView(View view) {
		this.view = view;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}

	public SokobanController(Model model,View view)
	{
		this.model=model;
		this.view=view;

		InitCommands();
		controller = new Controller();
		controller.Start();

	}


	private void InitCommands() {
		command = new HashMap<String,Command>();
		command.put("up",new MoveCommand(model));
		command.put("display",new DisplayCommand(model,view));
		command.put("save",new SaveCommand(model));
		command.put("load",new LoadCommand(model,view));
		command.put("exit",new ExitCommand(model));
	}


	@Override
	public void update(Observable o, Object arg) {
		List <String> params= (List<String>) arg;
		String commandKey = params.remove(0);
		Command c = command.get(commandKey);
		if (c == null) {
			//fix!!!!!!!!111
			System.out.println("Error");
			return;
		}
		c.setParams(params);
		controller.InsertCommand(c);


	}

}
