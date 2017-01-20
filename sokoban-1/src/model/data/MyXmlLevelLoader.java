package model.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//The class MyXmlLevelLoader

public class MyXmlLevelLoader extends AbstractLevelLoader{
	//Data members
	Level level=null;

	//Load level from XML file
	@Override
	public Level LoadLevel(InputStream in) throws IOException {
		XMLDecoder xd = new XMLDecoder(new BufferedInputStream(in));
		level=(Level) xd.readObject();
		xd.close();
		return level;
	}

	//Save level to file
	@Override
	public void SaveLevel(Level level, String LevelName) {
		
		XMLEncoder e;
		try {
			e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(LevelName)));
			e.writeObject(level);
			e.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
