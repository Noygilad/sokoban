package boot;

import controller.SokobanController;
import controller.server.MyClientHandler;
import controller.server.MyServer;
import db.SokobanDBManager;
import model.MyModel;
import view.MyView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(MyView.class.getResource("Window.fxml"));
			BorderPane root = fxmlLoader.load();
			Scene scene = new Scene(root, 600, 600);

			MyView mv = fxmlLoader.getController();
			mv.setStage(primaryStage);

			MyModel model = new MyModel(new SokobanDBManager());

			SokobanController controller = new SokobanController(model, mv);

			model.addObserver(controller);
			mv.addObserver(controller);

			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					mv.CloseWindow();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		if (args.length == 2) {
			// -server 18629
			if (args[0].toString().equals("-server")) {
				int portNumber = Integer.valueOf(args[1]);
				System.out.println("Working with server port: " + portNumber);
				MyClientHandler myClientHandler = new MyClientHandler();
				MyServer myServer = new MyServer(portNumber, myClientHandler);

				MyModel model = new MyModel(new SokobanDBManager());
				SokobanController controller = new SokobanController(model, myServer);

				model.addObserver(controller);
				myClientHandler.addObserver(controller);
			}
		} else
			launch(args);
	}
=======

import controller.SokobanController;
import controller.server.MyClientHandler;
import controller.server.MyServer;
import model.MyModel;
import view.MyView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(MyView.class.getResource("Window.fxml"));
			BorderPane root = fxmlLoader.load();
			Scene scene = new Scene(root, 600, 600);

			MyView mv = fxmlLoader.getController();
			mv.setStage(primaryStage);

			MyModel model = new MyModel();

			SokobanController controller = new SokobanController(model, mv);

			model.addObserver(controller);
			mv.addObserver(controller);

			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					mv.CloseWindow();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length == 2) {
			// -server 18629
			if (args[0].toString().equals("-server")) {
				int portNumber = Integer.valueOf(args[1]);
				System.out.println("Working with server port: " + portNumber);
				MyClientHandler myClientHandler = new MyClientHandler();
				MyServer myServer = new MyServer(portNumber, myClientHandler);

				MyModel model = new MyModel();
				SokobanController controller = new SokobanController(model, myServer);

				model.addObserver(controller);
				myClientHandler.addObserver(controller);
			}
		} else
			launch(args);
		}
>>>>>>> branch 'master' of https://github.com/Noygilad/sokoban.git
}
