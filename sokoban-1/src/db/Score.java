package db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Score class
@Entity(name = "Scores")
public class Score implements Serializable {

	//Data members (column table)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "LevelName")
	private String levelName;
	@Column(name = "UserName")
	private String userName;
	@Column(name = "Steps")
	private int steps;
	@Column(name = "Timer")
	private float timer;

	//Default C'tor
	public Score() {
	}

	//C'tor
	public Score(String levelName, String userName, int steps, long timer) {
		this.levelName = levelName;
		this.steps = steps;
		this.userName = userName;
		this.timer = timer;
	}

	//Setters and Getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public float getTimer() {
	 		return timer;
	}


	//Hash code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((levelName == null) ? 0 : levelName.hashCode());
		result = prime * result + steps;
		result = prime * result + Float.floatToIntBits(timer);
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	//Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (id != other.id)
			return false;
		if (levelName == null) {
			if (other.levelName != null)
				return false;
		} else if (!levelName.equals(other.levelName))
			return false;
		if (steps != other.steps)
			return false;
		if (Float.floatToIntBits(timer) != Float.floatToIntBits(other.timer))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	//ToString
	@Override
	public String toString() {

		return "Scores [ID=" + id + ", LevelName+ " + levelName + ", UserName" + userName + ", Steps" + steps
				+ ", Timer" + timer + "]";
	}

}
