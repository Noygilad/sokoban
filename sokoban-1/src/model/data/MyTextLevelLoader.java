package model.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import common.Level;

//The class MyTextLevelLoader
<<<<<<< HEAD
public class MyTextLevelLoader extends AbstractLevelLoader {
	String LevelName;

	// Load Level from text file
	@Override
	public Level LoadLevel(InputStream LevelLoad) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(LevelLoad));
		String line;
		int column = 0;
		int row = 0;
		ArrayList<String> ans = new ArrayList<String>();
		String name = read.readLine();
		while ((line = read.readLine()) != null) {
			if (line.length() > row) {
				row = line.length();
			}
			column++;
			ans.add(line);

		}
		Level level = new Level(column, row);
		level.setName(name);
		column = 0;
		for (String resualt : ans) {
			for (int i = 0; i < resualt.length(); i++) {
				switch (resualt.charAt(i)) {
				case ' ': {
					Item item = new AbstractItems(new Position2D(column, i));
					item.setReprestive(' ');
					level.setUnmoveableMap(column, i, item);
					break;
				}
				case '#': {
					Item item = new AbstractItems(new Position2D(column, i));
					item.setReprestive('#');
					level.setUnmoveableMap(column, i, item);
					break;
				}
				case 'A': {
					Item item = new AbstractItems(new Position2D(column, i));
					item.setReprestive('A');
					level.setMoveableMap(column, i, item);
					level.setUnmoveableMap(column, i, new AbstractItems(new Position2D(column, i), ' '));
					break;
				}
				case '@': {
					Item item = new AbstractItems(new Position2D(column, i));
					item.setReprestive('@');
					level.setMoveableMap(column, i, item);
					level.setUnmoveableMap(column, i, new AbstractItems(new Position2D(column, i), ' '));
					break;
				}
				case 'o': {
					Item item = new AbstractItems(new Position2D(column, i));
					item.setReprestive('o');
					level.setUnmoveableMap(column, i, item);
					break;
				}
				}
			}

			column++;
		}

		return level;
	}

	// Save Level to file
	public void SaveLevel(Level level, String LevelName) {
		String LevelString;
		int i = 0;
		LevelString = new String();
		LevelString = level.toString();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LevelName.toString()));
			writer.write(level.getName());
			while (LevelString.length() != i) {
				for (i = 0; i < LevelString.length(); i++) {
					if (LevelString.charAt(i) != 10) {
						writer.write(LevelString.charAt(i));
					} else {
						writer.newLine();
					}
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
=======
public class MyTextLevelLoader extends AbstractLevelLoader
{
	String LevelName;

	//Load Level from text file
	@Override
	public Level LoadLevel(InputStream LevelLoad)throws IOException
	{

		BufferedReader read = new BufferedReader(new InputStreamReader(LevelLoad));
		String line;
		int column=0;
		int row=0;
		ArrayList<String> ans= new ArrayList<String>();
		while((line = read.readLine()) != null)
		{
			if (line.length() > row)
			{
				row = line.length();
			}
		column++;
		ans.add(line);

		}
		Level level = new Level(column,row);
		column = 0;
		for(String resualt: ans)
		{

			for(int i=0;i<resualt.length();i++)
			{
				switch(resualt.charAt(i))
				{
					case ' ':
					{
						Item item = new AbstractItems(new Position2D(column,i));
						item.setReprestive(' ');
						level.setUnmoveableMap(column,i,item);
							break;
					}
					case '#':
					{
						Item item = new AbstractItems(new Position2D(column,i));
						item.setReprestive('#');
						level.setUnmoveableMap(column,i,item);
							break;
					}
					case 'A':
					{
						Item item = new AbstractItems(new Position2D(column,i));
						item.setReprestive('A');
						level.setMoveableMap(column,i,item);
						level.setUnmoveableMap(column,i, new AbstractItems(new Position2D(column,i), ' '));
							break;
					}
					case '@':
					{
						Item item = new AbstractItems(new Position2D(column,i));
						item.setReprestive('@');
						level.setMoveableMap(column,i,item);
						level.setUnmoveableMap(column,i, new AbstractItems(new Position2D(column,i), ' '));
						break;
					}
					case 'o':
					{
						Item item = new AbstractItems(new Position2D(column,i));
						item.setReprestive('o');
						level.setUnmoveableMap(column,i,item);
						break;
					}
				}
			}

			column++;
		}


		return level;
	}

	//Save Level to file
	public void SaveLevel(Level level,String LevelName)
	{
		String LevelString;
		int i=0;
		LevelString = new String();
		LevelString=level.toString();
		BufferedWriter writer= null;
		try{
			writer = new BufferedWriter(new FileWriter(LevelName.toString()));
		  while(LevelString.length()!=i)
		  {
			  for(i=0;i<LevelString.length();i++)
			  {
				  if(LevelString.charAt(i)!=10)
				  {
					  writer.write(LevelString.charAt(i));
				  }
				  else
				  {
					  writer.newLine();
				  }
			  }
		  }
		  writer.close();
	}
		catch (IOException e)
	{
		e.printStackTrace();
	}


}


}

>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
