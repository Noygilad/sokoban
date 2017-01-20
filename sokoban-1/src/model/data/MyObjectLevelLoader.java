package levels;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//The class MyObjectLevelLoader
@SuppressWarnings("serial")
public class MyObjectLevelLoader extends AbstractLevelLoader implements Serializable {

	//Load level from object file
	@Override
	public Level LoadLevel(InputStream LevelLoad) throws IOException {

		Level level=null;
		try {
			level=(Level) Serialization.deserialize(LevelLoad);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}

	//save level to file
	@Override
	public void SaveLevel(Level level,String LevelName) throws IOException {
		FileOutputStream fout=null;
		ObjectOutputStream oos=null;
		fout=new FileOutputStream(LevelName);
		oos=new ObjectOutputStream(fout);
		oos.writeObject(level);
		fout.close();
		oos.close();
		
	}
}
