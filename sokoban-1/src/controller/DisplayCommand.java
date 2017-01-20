package commands;

//The class DisplayCommand
public class DisplayCommand extends AbstractCommand {

	//C'tor
	public DisplayCommand(RunCommand run) {
		super(run);
	}

	//Execute the display
	@Override
	public void execute() {
		Run.DisplayCommand();
	}

}
