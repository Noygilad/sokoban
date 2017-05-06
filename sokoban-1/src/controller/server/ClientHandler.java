package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public interface ClientHandler {

	//start communicate
	public void handleClient (InputStream inFromClient, OutputStream OutToClient);
	public PrintWriter getOutToClientFromServer();

}
