package view;

import javafx.scene.input.KeyCode;

public class SokobanControls {
	//Data members
	private KeyCode upCode;
	private KeyCode downCode;
	private KeyCode leftCode;
	private KeyCode rightCode;


	public SokobanControls(){

	}
	//C'tor
	public SokobanControls(KeyCode upCode, KeyCode downCode, KeyCode leftCode, KeyCode rightCode) {
		super();
		this.upCode = upCode;
		this.downCode = downCode;
		this.leftCode = leftCode;
		this.rightCode = rightCode;
	}

	//Getters and setters
	public KeyCode getUpCode() {
		return upCode;
	}
	public void setUpCode(KeyCode upCode) {
		this.upCode = upCode;
	}
	public KeyCode getDownCode() {
		return downCode;
	}
	public void setDownCode(KeyCode downCode) {
		this.downCode = downCode;
	}
	public KeyCode getLeftCode() {
		return leftCode;
	}
	public void setLeftCode(KeyCode leftCode) {
		this.leftCode = leftCode;
	}
	public KeyCode getRightCode() {
		return rightCode;
	}
	public void setRightCode(KeyCode rightCode) {
		this.rightCode = rightCode;
	}


}
