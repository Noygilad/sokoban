package view;

import javafx.scene.control.Label;

public class MyTimerThread extends Thread {

	private Label label;
	private boolean go = true;

	public MyTimerThread(Label label) {
		this.label = label;
	}

	@Override
	public void run() {
		int count = 0;
		while (go) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			label.setText("" + count++);
		}
		super.run();
	}

	public void stopTimer(){
		go = false;
	}

}
