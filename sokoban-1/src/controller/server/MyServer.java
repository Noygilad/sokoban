package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	private int port;
	private ClientHandler ch;
	private volatile boolean stop;
	private boolean firstLogin = true;

	public MyServer(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		this.stop = false;
	}

	private void runServer() throws IOException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Server alive");
		server.setSoTimeout(1000);
		while (!stop) {
			try {
				if (firstLogin) {
					System.out.println("Waiting for user to login");
					firstLogin = false;
				}
				Socket aClient = server.accept();
				System.out.println("User login");
				InputStream inFronClient = aClient.getInputStream();
				OutputStream outToClient = aClient.getOutputStream();

				ch.handleClient(inFronClient, outToClient);
				aClient.getInputStream().close();
				// aClient.getOutputStream().close();
				aClient.close();
				System.out.println("User left");

			} catch (IOException e) {
			}
		}
		server.close();
		System.out.println("Server is down");
	}

	public void Start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void Stop() {
		stop = true;
	}

	public PrintWriter getOutToClientFromServer() {
		return ch.getOutToClientFromServer();
	}
}
