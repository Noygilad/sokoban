package levels;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//The class Serialization
public class Serialization {
	
	// Deserialize the given object and save it to file
	public static Object deserialize(InputStream in) throws IOException,ClassNotFoundException {
		Object obj=null;
		ObjectInputStream ois = new ObjectInputStream(in);
		obj = ois.readObject();
		ois.close();
		return obj;
	}

// Serialize the given object and save it to file
	public static void serialize(Object obj, FileOutputStream out)throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(obj);
		oos.close();

		out.close();
	}
}
