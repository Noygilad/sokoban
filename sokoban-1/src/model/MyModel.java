package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import common.Level;
import db.Score;
import db.ScoreDataMapper;
import db.ScoreQuery;
import model.data.LevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXmlLevelLoader;
import model.data.Position;
import model.data.Position2D;
import model.policy.MySokobanPolicy;
import view.CLI;
import view.MyView;

public class MyModel extends Observable implements Model {

	// Data members
	CLI cli;
	Level level;
	HashMap<String, LevelLoader> LoadMap;
	ScoreDataMapper scoreDataMapper;

	// C'tor
	public MyModel(ScoreDataMapper scoreDataMapper) {
		LoadMap = new HashMap<>();
		LoadMap.put("txt", new MyTextLevelLoader());
		LoadMap.put("obj", new MyObjectLevelLoader());
		LoadMap.put("xml", new MyXmlLevelLoader());
		this.scoreDataMapper = scoreDataMapper;
	}

	// Getters and setters
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

	@Override
	public Level getCurrentLevel() {
		return this.level;
	}

	// Load command
	public void LoadCommand(String CommandLine) throws IOException {
		String fin;
		fin = CommandLine.substring(CommandLine.length() - 3);

		if (LoadMap.containsKey(fin)) {
			InputStream is = new FileInputStream(new File(CommandLine));
			if (is != null) {
				level = LoadMap.get(fin).LoadLevel(is);
			}
		}

		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);
	}

	// Display command
	public String DisplayCommand() {
		String moveable = new String();
		for (int i = 0; i < (level.getUnmoveableMap()).length; i++) {
			for (int j = 0; j < (level.getUnmoveableMap())[0].length; j++) {
				if ((level.getMoveableMap())[i][j] == null) {
					moveable += (level.getUnmoveableMap())[i][j].toString();

				} else {
					moveable += (level.getMoveableMap())[i][j].toString();
				}

			}
			moveable += '\n';
		}
		return moveable;

	}

	// Move command
	public void MoveCommand(String CommandLine) {
		MySokobanPolicy policy = new MySokobanPolicy(level);

		Position position = new Position2D(level.getCharacterList().get(0).getPosition());
		if (level.isEndOfLevel() == false) {
			switch (CommandLine.toLowerCase()) {
			case "up":
				position.setRow(position.getRow() - 1);
				policy.MoveByPolicy(position);
				break;

			case "down":
				position.setRow(position.getRow() + 1);
				policy.MoveByPolicy(position);
				break;

			case "left":
				position.setColumn(position.getColumn() - 1);
				policy.MoveByPolicy(position);
				break;

			case "right":
				position.setColumn(position.getColumn() + 1);
				policy.MoveByPolicy(position);
				break;
			}

			DisplayCommand();
			this.setChanged();
			LinkedList<String> params = new LinkedList<String>();
			params.add("display");
			this.notifyObservers(params);
		}

	}

	// Save command
	public void SaveCommand(String CommandLine) throws IOException {
		System.out.println("save command in my model");
		String fin;
		fin = CommandLine.substring(CommandLine.length() - 3);

		if (LoadMap.containsKey(fin)) {
			OutputStream os = new FileOutputStream(new File(CommandLine));
			if (os != null) {
				LoadMap.get(fin).SaveLevel(level, CommandLine);
			}
			os.close();
		}
	}

	// SaveUserCommand
	/*
	 * public void SaveUserCommand(String CommandLine) { int userId;
	 * SokobanDBManager manager = new SokobanDBManager();
	 * userId=manager.addUser(CommandLine); manager.addScore(userId, levelId,
	 * level.getTime(), level.getSteps()); }
	 */

	public void SaveScoreCommand(String CommandLine) {
		scoreDataMapper.save(new Score(level.getName(), CommandLine, level.getSteps(), level.getTime()));

	}

	// Set CLI
	@Override
	public void setCli(CLI cli) {
		this.cli = cli;
	}

	// Exit command
	@Override
	public void ExitCommand() {
	}

	@Override
	public void requestScoresCommand(ScoreQuery query) {
		Score[] results = scoreDataMapper.search(query);
		setChanged();
		ArrayList<Object> params = new ArrayList();
		params.add("scoreRespone");
		params.add(results);
		notifyObservers(params);
=======
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import common.Level;
import model.data.LevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXmlLevelLoader;
import model.data.Position;
import model.data.Position2D;
import model.policy.MySokobanPolicy;
import view.CLI;

public class MyModel extends Observable implements Model {

	// Data members
	CLI cli;
	Level level;
	HashMap<String, LevelLoader> LoadMap;

	// C'tor
	public MyModel() {
		LoadMap = new HashMap<>();
		LoadMap.put("txt", new MyTextLevelLoader());
		LoadMap.put("obj", new MyObjectLevelLoader());
		LoadMap.put("xml", new MyXmlLevelLoader());
	}

	//Getters and setters
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

	@Override
	public Level getCurrentLevel() {
		return this.level;
	}


	// Load command
	public void LoadCommand(String CommandLine) throws IOException {
		String fin;
		fin = CommandLine.substring(CommandLine.length() - 3);

		if (LoadMap.containsKey(fin)) {
			InputStream is = new FileInputStream(new File(CommandLine));
			if (is != null) {
				level = LoadMap.get(fin).LoadLevel(is);
			}
		}

		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);
	}

	// Display command
	public String DisplayCommand() {
		String moveable = new String();
		for (int i = 0; i < (level.getUnmoveableMap()).length; i++) {
			for (int j = 0; j < (level.getUnmoveableMap())[0].length; j++) {
				if ((level.getMoveableMap())[i][j] == null) {
					moveable += (level.getUnmoveableMap())[i][j].toString();

				} else {
					moveable += (level.getMoveableMap())[i][j].toString();
				}

			}
			moveable += '\n';
		}
		return moveable;

	}

	// Move command
	public void MoveCommand(String CommandLine) {
		MySokobanPolicy policy = new MySokobanPolicy(level);

		Position position = new Position2D(level.getCharacterList().get(0).getPosition());
		if (level.isEndOfLevel() == false) {
			switch (CommandLine.toLowerCase()) {
			case "up":
				position.setRow(position.getRow() - 1);
				policy.MoveByPolicy(position);
				break;

			case "down":
				position.setRow(position.getRow() + 1);
				policy.MoveByPolicy(position);
				break;

			case "left":
				position.setColumn(position.getColumn() - 1);
				policy.MoveByPolicy(position);
				break;

			case "right":
				position.setColumn(position.getColumn() + 1);
				policy.MoveByPolicy(position);
				break;
			}

			DisplayCommand();
			this.setChanged();
			LinkedList<String> params = new LinkedList<String>();
			params.add("display");
			this.notifyObservers(params);
		}

	}

	// Save command
	public void SaveCommand(String CommandLine) throws IOException {
		System.out.println("save command in my model");
		String fin;
		fin = CommandLine.substring(CommandLine.length() - 3);

		if (LoadMap.containsKey(fin)) {
			OutputStream os = new FileOutputStream(new File(CommandLine));
			if (os != null) {
				LoadMap.get(fin).SaveLevel(level, CommandLine);
			}
			os.close();
		}
	}

	// Set CLI
	@Override
	public void setCli(CLI cli) {
		this.cli = cli;
	}

	// Exit command
	@Override
	public void ExitCommand() {
>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
	}
}
