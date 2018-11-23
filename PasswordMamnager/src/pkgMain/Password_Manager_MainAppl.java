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
//		 String slt = new Base64().encodeToString(PasswordUtils.generateSalt(16));
//		 String pwd = PasswordUtils.getSHA512Hash("my Long UsersadlkfnasjfnPassword", slt);
//		 String s = PasswordUtils.encrypt("133", pwd);
//		 System.out.println(s +"\n"+s.length());
////		 System.out.println(pwd);
//		 System.out.println(PasswordUtils.decrypt(s, pwd));
////		System.out.println(ICODecoder.read(new URL("http://www.twitter.com/favicon.ico").openStream()).get(1));
		launch(args);
//		System.out.println(validateCreditCardNumber("670 0651 0470 9950 6950"));
//		System.exit(0);
	}
	
	private static boolean validateCreditCardNumber(String str) {

		int sum = 0;
        boolean alternate = false;
        for (int i = str.length() - 1; i >= 0; i--)
        {
                int n = Integer.parseInt(str.substring(i, i + 1));
                if (alternate)
                {
                        n *= 2;
                        if (n > 9)
                        {
                                n = (n % 10) + 1;
                        }
                }
                sum += n;
                alternate = !alternate;
        }
        return (sum % 10 == 0);
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
