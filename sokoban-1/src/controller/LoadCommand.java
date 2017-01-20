package commands;

import java.io.IOException;

//The class LoadCommand
public class LoadCommand extends AbstractCommand {

	//C'tor
	public LoadCommand(RunCommand run) {
		super(run);
	}

	//Execute the load command
	@Override
	public void execute() throws IOException {
		Run.LoadCommand(CommandLine);
	}


}
