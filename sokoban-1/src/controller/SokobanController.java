package controller;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import controller.command.Command;
import controller.command.DisplayCommand;
import controller.command.ExitCommand;
import controller.command.LoadCommand;
import controller.command.MoveCommand;
import controller.command.SaveCommand;
import controller.server.MyServer;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Model;
import view.SokobanControls;
import view.View;

public class SokobanController implements Observer {

	//Data members
	private Model model;
	private View view;
	private Controller controller;
	private HashMap<String, Command> command;
	private MyServer myServer;
	IntegerProperty counter;

	//Getters and setters
	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	//C'tor
	public SokobanController(Model model, View view) {
		this.model = model;
		this.view = view;

		controller = new Controller();
		InitCommands();
		command.put("display", new DisplayCommand(model, view));

		counter = new SimpleIntegerProperty();
		view.Bind(counter);
		setSokobanControls(view);
		controller.Start();
	}

	public void setSokobanControls(View view) {
		XMLDecoder xd = null;
		try {
			xd = new XMLDecoder(new BufferedInputStream(new FileInputStream("SokobanControls.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		view.setControls((SokobanControls) xd.readObject());
		xd.close();
	}

	public SokobanController(Model model, MyServer myServer) {
		this.model = model;
		this.myServer = myServer;

		controller = new Controller();
		InitCommands();
		command.put("display", new DisplayCommand(model, myServer));

		counter = new SimpleIntegerProperty();

		controller.setMyServer(myServer);
		controller.Start();
	}

	//Create new commands
	private void InitCommands() {
		command = new HashMap<String, Command>();
		command.put("move", new MoveCommand(model));
		command.put("save", new SaveCommand(model));
		command.put("load", new LoadCommand(model, view));
		command.put("exit", new ExitCommand(model, view, controller));

	}

	//notify to observers that change happened
	@Override
	public void update(Observable o, Object arg) {
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey = params.removeFirst();

		Command c = command.get(commandKey);
		if (c == null) {
			System.out.println("Error");
			return;
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (model.getCurrentLevel() != null)
					counter.set(model.getCurrentLevel().getSteps());
			}
		});

		c.setParams(params);
		controller.InsertCommand(c);

	}

}
