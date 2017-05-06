package model.data;

import java.io.Serializable;

@SuppressWarnings("serial")
//The class Position2D
public class Position2D implements Position,Serializable {

	//Data Members
	private int Row;
	private int Column;
	
	//default C'tor
	public Position2D() {
	}
	
	//C'tor
	public Position2D(int row,int column) {
		this.Row=row;
		this.Column=column;
	}
	
	public Position2D(Position pos){
		Row = pos.getRow();
		Column = pos.getColumn();
	}
	
	//Getters and Setters
	public int getRow() {
		return Row;
	}
	
	public void setRow(int row) {
		this.Row = row;
	}
	
	public int getColumn() {
		return Column;
	}
	
	public void setColumn(int column) {
		this.Column = column;
	}
	
	public void SetPosition(Position pos){
		Row = pos.getRow();
		Column = pos.getColumn();
	}
	
	//Check if in the position the is item
	@Override
	public boolean equals(Object arg0) {
		if ((Row == ((Position2D)arg0).getRow()) && (Column == ((Position2D)arg0).getColumn())) {
			return true;
		}
		return false;
	}
	
}
