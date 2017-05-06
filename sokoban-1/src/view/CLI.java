package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Observable;

<<<<<<< HEAD
=======
import common.Level;
>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
import controller.Controller;
import controller.command.Command;
import controller.command.DisplayCommand;
import controller.command.ExitCommand;
import controller.command.LoadCommand;
import controller.command.MoveCommand;
import controller.command.SaveCommand;
import model.Model;
//The class CLI
public class CLI extends Observable {

	//Data members
	Model model;
	View view;
	Controller contoller;
	PrintWriter out;
	BufferedReader in;

	//C'tor
	public CLI(Model model) {
		this.model=model;
		model.setCli(this);
	}



	//Start the command that activated
	public void Run() throws IOException
	{

		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out, true);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				String Line;
				String[] Split;
				Command command=null;
				try {
					Line=in.readLine();

				Split=Line.split(" ");

				while(!Line.equals("exit"))
				{
					command=CommaneType(Split);
						if(command!=null)
						{
							command.execute();
						}
					Line=in.readLine();
						if(Line.equals("exit"))
						{
							new ExitCommand(model,view,contoller).execute();
						}
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	//Check which command was activated
	public Command CommaneType(String[] CommandSplit)
	{
		Command command=null;
		switch (CommandSplit[0].toLowerCase()) {
		case "display":
			command=new DisplayCommand(model,view);
			break;

		case "load":
			command=new LoadCommand(model,view);
			command.setCommandLine(CommandSplit);
			break;

		case "move":
			command=new MoveCommand(model);
			command.setCommandLine(CommandSplit);
			break;

		case "save":
			command=new SaveCommand(model);
			command.setCommandLine(CommandSplit);
			break;
		}
		return command;
	}

	//Print to the user
	public void Print(String st)
	{
		out.println(st);
	}

	//Exit
	public void Exit(String st)
	{
		out.println(st);
		System.exit(0);
	}

}
