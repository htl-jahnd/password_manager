package pkgMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pkgMisc.AddressFormatValidator;

public class Password_Manager_MainAppl extends Application
{

	// naming conventions fxml;
	// web accounts:
	// Buttons: btnWebAccount<function>
	// Methods: onSelectButtonWebAccount

	public static void main(String[] args) throws Exception
	{
		// String slt = new Base64().encodeToString(PasswordUtils.generateSalt(16));
		// String pwd = PasswordUtils.getSHA512Hash("my Long UsersadlkfnasjfnPassword",
		// slt);
		// String s = PasswordUtils.encrypt("133", pwd);
		// System.out.println(s +"\n"+s.length());
		//// System.out.println(pwd);
		// System.out.println(PasswordUtils.decrypt(s, pwd));
		//// System.out.println(ICODecoder.read(new
		// URL("http://www.twitter.com/favicon.ico").openStream()).get(1));
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("ressources/PasswordManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("ressources/stylesheet.css").toExternalForm());
			primaryStage.setTitle("Sign In");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e)
		{
			pkgMisc.ExceptionHandler.hanldeUnexpectedException(e);
		}
	}

}
