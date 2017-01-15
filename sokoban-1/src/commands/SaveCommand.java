package commands;


import java.io.IOException;

//The class Save Command 
public class SaveCommand extends AbstractCommand {

	//C'tor
	public SaveCommand(RunCommand run) {
		super(run);	
	}

	//Execute save command
	@Override
	public void execute()throws IOException {
		Run.SaveCommand(CommandLine);
	}

}
