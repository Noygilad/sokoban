package model.data;

import java.io.IOException;
import java.io.InputStream;

import common.Level;

//The interface LevelLoader
public interface LevelLoader {

	//Load level from file
	Level LoadLevel(InputStream LevelLoad)throws IOException;
	
	//save level to file
	public void SaveLevel(Level level,String LevelName) throws IOException;
}
