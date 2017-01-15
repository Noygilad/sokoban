package levels;

import java.io.IOException;

import commands.CLI;
import commands.MyRunCommand;
import commands.RunCommand;

//The class Run
public class Run {

	public static void main(String[] args) throws IOException {
/*
			FileInputStream in, ino;
	        in = new FileInputStream("level1.txt");
	        Level l1 = new MyTextLevelLoader().LoadLevel(in);
	        FileOutputStream out;
	        out = new FileOutputStream("level1.obj");
	        Serialization.serialize(l1, out);
	        ino = new FileInputStream("level1.obj");
	        Level l2 = new MyObjectLevelLoader().LoadLevel(ino);
	*/	
/*		
 **********************Tests***********************
		LevelLoader load = new MyTextLevelLoader();
		InputStream is = new FileInputStream(new File("level1.txt"));
		Level level=load.LoadLevel(is);
		System.out.println(level);		
		MySokobanPolicy policy=new MySokobanPolicy(level);
		policy.MoveByPolicy(new Position2D(level.getCharacterList().get(0).getPosition().getRow(),level.getCharacterList().get(0).getPosition().getColumn()-1));
		System.out.println(level);
		policy.MoveByPolicy(new Position2D(level.getCharacterList().get(0).getPosition().getRow()+1,level.getCharacterList().get(0).getPosition().getColumn()));
		System.out.println(level);
		policy.MoveByPolicy(new Position2D(level.getCharacterList().get(0).getPosition().getRow()+1,level.getCharacterList().get(0).getPosition().getColumn()));
		System.out.println(level);
		policy.MoveByPolicy(new Position2D(level.getCharacterList().get(0).getPosition().getRow()-1,level.getCharacterList().get(0).getPosition().getColumn()));
		System.out.println(level);
		policy.MoveByPolicy(new Position2D(level.getCharacterList().get(0).getPosition().getRow()-1,level.getCharacterList().get(0).getPosition().getColumn()));
		System.out.println(level);
		
		MyTextLevelLoader save = new MyTextLevelLoader();
		save.SaveLevel(level);
		*/
		
		//check CLI
		RunCommand run=new MyRunCommand();
		CLI cli=new CLI(run);
		cli.Run();
		
		
		
		//test my xml level loader
	
	/*	FileInputStream in1,ino1;
		try{
			in1=new FileInputStream("level1.txt");
			Level l11=new MyTextLevelLoader().LoadLevel(in1);
			XMLEncoder e=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("level1.xml")));
			e.writeObject(l11);ksco
			e.close();
			ino1=new FileInputStream("level1.xml");
			Level l21=new MyXmlLevelLoader().LoadLevel(ino1);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
/*Position2D p=new Position2D();
	p.setColumn(5);
	p.setRow(4);s
	Box box=new Box('@');
	box.setP(p);*/
	
	
	//	temp[i][column] = line.charAt(i);
	
	
	}
}