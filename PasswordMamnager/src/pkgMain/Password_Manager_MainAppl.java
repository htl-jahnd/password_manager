package pkgMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Password_Manager_MainAppl extends Application
{

	public static void main(String[] args) throws Exception
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root = FXMLLoader.load(getClass().getResource("ressources/PasswordManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("ressources/stylesheet.css").toExternalForm());
			primaryStage.setTitle("Sign Up");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e)
		{
			pkgMisc.ExceptionHandler.hanldeUnexpectedException(e);
		}
	}

}
