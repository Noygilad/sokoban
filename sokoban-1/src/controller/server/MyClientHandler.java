package controller.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;

public class MyClientHandler extends Observable implements ClientHandler {

	private PrintWriter outToClientFromServer;
	//start communicate
	@Override
	public void handleClient(InputStream inFromClient, OutputStream OutToClient) {
		BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter out = new PrintWriter(OutToClient);
		outToClientFromServer = out;

		out.println("Welcome to Sokoban Game!");
		out.println("You can use:");
		out.println("load");
		out.println("move");
		out.println("save");
		out.println("when you finish - exit");
		out.println("good luck!");
		out.flush();

		String Cinput;
		Scanner scanner= new Scanner(new BufferedReader(new InputStreamReader(inFromClient)));

		while (!scanner.hasNext("exit")) {
			Cinput = scanner.nextLine();
			String[] cmds = Cinput.split(" ");
			LinkedList<String> linkedList = new LinkedList<>();
			linkedList.add(cmds[0]);
			if (cmds.length == 2)
				linkedList.add(cmds[1]);
			setChanged();
			notifyObservers(linkedList);
		}
		Cinput = scanner.nextLine();
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add("exit");
		setChanged();
		notifyObservers(linkedList);
		out.println("bye");
		out.flush();
	}

	@Override
	public PrintWriter getOutToClientFromServer() {
		return outToClientFromServer;
	}

}
