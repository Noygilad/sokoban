package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//The class CLI
public class CLI {
	
	//Data members
	RunCommand Run;
	PrintWriter out;
	BufferedReader in;
	
	//C'tor
	public CLI(RunCommand run) {
		this.Run=run;
		Run.setCli(this);
	}
	
	//Start the command that activated
	public void Run() throws IOException
	{
		in=new BufferedReader(new InputStreamReader(System.in));
		out=new PrintWriter(System.out, true);
		String Line;
		String[] Split;
		Command command=null;
		Line=in.readLine();
		while(!Line.equals("exit"))
		{
			Split=Line.split(" ");
			command=CommaneType(Split);
				if(command!=null)
				{
					command.execute();
				}
			Line=in.readLine();
				if(Line.equals("exit"))
				{
					new ExitCommand(Run).execute();
				}
		}
	}
	
	//Check which command was activated
	public Command CommaneType(String[] CommandSplit)
	{
		Command command=null;
		switch (CommandSplit[0].toLowerCase()) {
		case "display":
			command=new DisplayCommand(Run);
			break;
			
		case "load":
			command=new LoadCommand(Run);
			command.setCommandLine(CommandSplit);
			break;
			
		case "move":
			command=new MoveCommand(Run);
			command.setCommandLine(CommandSplit);
			break;
			
		case "save":
			command=new SaveCommand(Run);
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
