package atj;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	
	public static final String appName = "Sockets-JavaFX-MVC";
	//private static Scene scene;
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws IOException{
		/*
		 * FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Chat.fxml"));
		 * scene = new Scene(fxmlLoader.load(),450,600); stage.setScene(scene);
		 * stage.show();
		 */
        ViewLoader<AnchorPane, ChatController> viewLoader = new ViewLoader<>("Chat.fxml");
        viewLoader.getController().setUserName(getUserName());
        viewLoader.getController().setHost("localhost");
        viewLoader.getController().setPort(10001);
        viewLoader.getController().run();
        Scene scene = new Scene(viewLoader.getLayout(),450,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle(appName);
        primaryStage.setOnHiding( e -> primaryStage_Hiding(e, viewLoader.getController()));
        primaryStage.show();
	}
	private String getUserName() {
		TextInputDialog textInputDialog = new TextInputDialog("Anonymous");
		textInputDialog.setTitle("");
		textInputDialog.setHeaderText("");
		textInputDialog.setContentText("Twoje imię");
		Optional<String> result = textInputDialog.showAndWait();
		return result.orElse("Anonymous");
		}
	
	private void primaryStage_Hiding(WindowEvent e, ChatController controller)
	{try {
		controller.close();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}}
	
	/*
	 * public void mainWindow() { FXMLLoader loader = new FXMLLoader(
	 * Main.class.getResource("Chat.fxml")); try { AnchorPane pane=loader.load();
	 * primaryStage.setMinWidth(820); primaryStage.setMinHeight(620); Scene
	 * scene=new Scene(pane); ChatController ChatController=loader.getController();
	 * //ChatController.setMain(this);
	 * //mainWindowController.setPrimaryStage(primaryStage);
	 * primaryStage.setTitle("Chat"); primaryStage.setScene(scene);
	 * primaryStage.show(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } }
	 */
	
	public static void main(String[] args) {
		launch(args);
	}
}