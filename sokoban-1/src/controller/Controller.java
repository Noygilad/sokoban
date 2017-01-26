package controller;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.server.MyServer;

public class Controller {
	private BlockingQueue<Command> queue;
	private boolean isStopped = false;
	private MyServer myServer;


	public Controller() {
		queue = new ArrayBlockingQueue<Command>(10);
	}

	public void InsertCommand(Command command)
	{
		try {
			queue.put(command);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void Start()
	{
		if(myServer != null)
			myServer.Start();
		Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isStopped)
				{
					try {
						Command cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null)
							cmd.execute();
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}

				}

			}
		});
		thread.start();
	}

	public void Stop()
	{
		if(myServer != null)
			myServer.Stop();
		isStopped=true;
		Thread.currentThread().stop();
	}

	public MyServer getMyServer() {
		return myServer;
	}

	public void setMyServer(MyServer myServer) {
		this.myServer = myServer;
	}
}
