package view;

import javafx.scene.control.Label;

public class MyTimerThread extends Thread {

	//Data members
	private Label label;
	private boolean go = true;

	//C'tor
	public MyTimerThread(Label label) {
		this.label = label;
	}

	//Start the thread of timer
	@Override
	public void run() {
		int count = 0;
		while (go) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			label.setText("" + count++);
		}
		super.run();
	}

	//Set the flag=false and stop the thread of timer
	public void stopTimer(){
		go = false;
	}

}
