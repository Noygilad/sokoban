package commands;

//The class MoveCommand
public class MoveCommand extends AbstractCommand {

	//C'tor
	public MoveCommand(RunCommand run) {
		super(run);
	}

	//Execute the move command
	@Override
	public void execute() {
		Run.MoveCommand(CommandLine);

	}

}
