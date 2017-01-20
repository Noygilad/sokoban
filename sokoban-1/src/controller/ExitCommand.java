package commands;

import java.io.IOException;

//The Class ExitCommand
public class ExitCommand extends AbstractCommand  {

	//C'tor
	public ExitCommand(RunCommand run) {
		super(run);	
	}

	//Execute exit command
	@Override
	public void execute() throws IOException {	
		Run.ExitCommand();
	}

}
