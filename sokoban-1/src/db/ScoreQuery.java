package db;

public class ScoreQuery {

	//Data members
	private String levelName;
	private String userName;
	private String orderBy;
	private int page;

	//C'tor
	public ScoreQuery() {
		this.page = 0;
	}

	//Getters and Setters
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
