package pkgMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Password_Manager_MainAppl extends Application
{

	// naming conventions fxml;
	// web accounts:
	// Buttons: btnWebAccount<function>
	// Methods: onSelectButtonWebAccount

	public static void main(String[] args) throws Exception
	{
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("ressources/SignIn.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("ressources/stylesheet.css").toExternalForm());
			primaryStage.setTitle("Password Manager");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e)
		{
			pkgMisc.ExceptionHandler.hanldeUnexpectedException(e);
		}
	}

}
