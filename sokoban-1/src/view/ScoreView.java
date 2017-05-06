package view;

import java.net.URL;

import java.util.Observable;
import java.util.ResourceBundle;

import db.Score;
import db.ScoreQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;

public class ScoreView extends Observable implements Initializable {

	private ObservableList<Score> scoreList = FXCollections.observableArrayList();
	ScoreQuery query;

	@FXML
	TableView scoresTable;
	@FXML
	ChoiceBox orderByChoiceBox;
	@FXML
	TextField userNameField;
	@FXML
	TextField levelNameField;
	@FXML
	TableColumn timer;
	@FXML
	Pagination page;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		scoresTable.setItems(scoreList);
		orderByChoiceBox.setItems(FXCollections.observableArrayList("id", "levelName", "userName", "timer", "steps"));
		orderByChoiceBox.setValue("timer");
	}

	//set query- set the query that the user enter
	public void setQuery(ScoreQuery query) {
		userNameField.setText(query.getUserName());
		levelNameField.setText(query.getLevelName());
		orderByChoiceBox.setValue(query.getOrderBy());

	}

	//After the user enter submit- return him the desired request
	public void submitClick() {
		ScoreQuery query = new ScoreQuery();
		query.setUserName(
				userNameField.getText() == null || userNameField.getText().equals("") ? null : userNameField.getText());
		query.setLevelName(levelNameField.getText() == null || levelNameField.getText().equals("") ? null
				: levelNameField.getText());
		query.setOrderBy(orderByChoiceBox.getValue() == null || orderByChoiceBox.getValue().equals("") ? null
				: orderByChoiceBox.getValue().toString());
		System.out.println(page.getCurrentPageIndex());
		query.setPage(page.getCurrentPageIndex());
		setChanged();
		notifyObservers(query);
	}

	public void setScoreItems(Score[] scores) {
		scoreList.clear();
		scoreList.addAll(scores);

	}

}
