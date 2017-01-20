package commands;

//The class AbstractCommand
public abstract class AbstractCommand implements Command {
	
	//Data members
	 RunCommand Run;
	 String[] CommandLine;
	
	 //C'tor
	public AbstractCommand(RunCommand run) {
		Run=run;
	}

	//Getters and Setters
	public RunCommand getRun() {
		return Run;
	}

	public void setRun(RunCommand run) {
		Run = run;
	}

	public String[] getCommandLine() {
		return CommandLine;
	}

	public void setCommandLine(String[] commandLine) {
		CommandLine = commandLine;
	}
	
}
