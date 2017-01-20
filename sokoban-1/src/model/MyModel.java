package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import model.data.Level;
import model.data.LevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXmlLevelLoader;
import model.data.Position;
import model.data.Position2D;
import model.policy.MySokobanPolicy;
import view.CLI;

public class MyModel extends Observable implements Model {

	//Data members
	CLI cli;
	Level level;
	HashMap<String, LevelLoader> LoadMap;

	//C'tor
	public MyModel() {
		LoadMap=new HashMap<>();
		LoadMap.put("txt", new MyTextLevelLoader());
		LoadMap.put("obj", new MyObjectLevelLoader());
		LoadMap.put("xml", new MyXmlLevelLoader());
	}

	//Load command
	public void LoadCommand(String[] CommandLine) throws IOException
	{
		if(CommandLine.length==2)
		{
			String[] Split;
			Split=CommandLine[1].split("\\.");
			if(Split.length==2)
			{
				if(LoadMap.containsKey(Split[1]))
				{
					InputStream is = new FileInputStream(new File(CommandLine[1]));
					if(is!=null)
					{
						level = LoadMap.get(Split[1]).LoadLevel(is);
					}
				}
			}
		}
		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");
		this.notifyObservers(params);
	}

	//Display command
	public void DisplayCommand()
	{
			String moveable = new String();
			for (int i = 0; i < (level.getUnmoveableMap()).length; i++) {
				for (int j = 0; j < (level.getUnmoveableMap())[0].length; j++) {
					if((level.getMoveableMap())[i][j]== null)
					{
						moveable += (level.getUnmoveableMap())[i][j].toString();

					}
					else
					{
						moveable += (level.getMoveableMap())[i][j].toString();
					}

				}
				moveable += '\n';
			}
			cli.Print(moveable);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public HashMap<String, LevelLoader> getLoadMap() {
		return LoadMap;
	}

	public void setLoadMap(HashMap<String, LevelLoader> loadMap) {
		LoadMap = loadMap;
	}

	public CLI getCli() {
		return cli;
	}

	//Move command
	public void MoveCommand(String[] CommandLine)
	{
		MySokobanPolicy policy=new MySokobanPolicy(level);
		if(CommandLine.length==2)
		{
			Position position=new Position2D(level.getCharacterList().get(0).getPosition());
			switch (CommandLine[1].toLowerCase()) {
			case "up":
				position.setRow(position.getRow()-1);
				policy.MoveByPolicy(position);
				break;

			case "down":
				position.setRow(position.getRow()+1);
				policy.MoveByPolicy(position);
				break;

			case "left":
				position.setColumn(position.getColumn()-1);
				policy.MoveByPolicy(position);
				break;

			case "right":
				position.setColumn(position.getColumn()+1);
				policy.MoveByPolicy(position);
				break;
			}
		}
		DisplayCommand();

		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");
		this.notifyObservers(params);
	}

	//Save command
	public void SaveCommand(String[] CommandLine) throws IOException
	{
		if(CommandLine.length==2)
		{
			String[] Split;
			Split=CommandLine[1].split("\\.");
			if(Split.length==2)
			{
				if(LoadMap.containsKey(Split[1]))
				{
					OutputStream os = new FileOutputStream(new File(CommandLine[1]));
					if(os!=null)
					{
						LoadMap.get(Split[1]).SaveLevel(level,CommandLine[1]);
					}
					os.close();
				}
			}
		}

	}

	//Set CLI
	@Override
	public void setCli(CLI cli) {
		this.cli = cli;
	}

	//Exit command
	@Override
	public void ExitCommand() {
		cli.Exit("bye bye :]");
	}

}
