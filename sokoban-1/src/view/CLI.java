package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.Command;
import controller.DisplayCommand;
import controller.ExitCommand;
import controller.LoadCommand;
import controller.MoveCommand;
import controller.RunCommand;
import controller.SaveCommand;
import model.Model;
//The class CLI
public class CLI {

	//Data members
	Model model;
	View view;
	PrintWriter out;
	BufferedReader in;

	//C'tor
	public CLI(Model model,View view) {
		this.model=model;
		this.view=view;
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
							new ExitCommand(model).execute();
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
