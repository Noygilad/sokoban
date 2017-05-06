package db;

public interface ScoreDataMapper {
	//Save the score
	public void save(Score score);
	//search the desired score
	public Score[] search(ScoreQuery q);
}
