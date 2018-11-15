package pkgController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pkgData.Database;
import pkgData.User;
import pkgExceptions.InvalidEmailAddressException;
import pkgExceptions.InvalidPasswordException;
import pkgMisc.AddressFormatValidator;
import pkgMisc.ExceptionHandler;
import pkgMisc.SceneSwitcher;
import javafx.scene.control.Alert.AlertType;

public class Controller_SignIn
{

	@FXML
	private JFXTextField txtUsername;

	@FXML
	private JFXPasswordField pwdPassword;

	@FXML
	private JFXPasswordField pwdConfirmPassword;

	@FXML
	private JFXButton btnLogin;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private Hyperlink lnkSignIn;

	@FXML
	private Label lblAccountText;

	private boolean isLogin = true;
	private Database db;

	@FXML
	void onSelectButton(ActionEvent event)
	{
		try
		{
			if (event.getSource().equals(btnCancel))
			{
				if (doShowCloseDialog())
				{
					System.exit(0);
				}
			} else if (event.getSource().equals(btnLogin))
			{
				String usr = txtUsername.getText();
				if (usr.trim().isEmpty())
				{
					throw new InvalidEmailAddressException(
							"Please enter an email address to sign " + (isLogin == true ? "in" : "up") + ".");
				} else if (!AddressFormatValidator.isValidEmailAddress(usr))
				{
					throw new InvalidEmailAddressException(
							"Please enter a valid email address to sign " + (isLogin == true ? "in" : "up") + ".");
				} else if (isLogin && !pwdPassword.getText().equals(pwdConfirmPassword.getText()))
				{
					throw new InvalidPasswordException("Passwords do not match.");
				} else if(pwdPassword.getText().length()<6) {
					throw new InvalidPasswordException("Password is too short. It has to be at least 6 characters");
				}
				Database.newInstance();
				if(isLogin) {
					db.login(new User(txtUsername.getText()), pwdPassword.getText().toCharArray());
				}
				else {
					db.createNewUser(new User(txtUsername.getText()),pwdPassword.getText().toCharArray());
				}
			}
		} catch (InvalidEmailAddressException iee)
		{
			ExceptionHandler.hanldeExpectedException("Invalid email address eneterd", iee);
		} catch (InvalidPasswordException ipe)
		{
			ExceptionHandler.hanldeExpectedException("Password error", ipe);
		} catch (Exception ex)
		{
			ExceptionHandler.hanldeUnexpectedException(ex);
		}
	}

	@FXML
	void onSelectHyperlinkLabel(ActionEvent event)
	{
		try
		{
			if (event.getSource().equals(lnkSignIn))
			{
				if (isLogin)
				{
					pwdConfirmPassword.setVisible(true);
					btnLogin.setText("Sign Up!");
					lblAccountText.setText("Already have an account?");
					lnkSignIn.setText("Sign In!");
					isLogin = false;
				} else
				{
					pwdConfirmPassword.setVisible(false);
					btnLogin.setText("Login");
					lblAccountText.setText("Don't have an account?");
					lnkSignIn.setText("Sign Up!");
					isLogin = true;
				}

			}
		} catch (Exception ex)
		{
			ExceptionHandler.hanldeUnexpectedException(ex);
		}

	}

	private boolean doShowCloseDialog()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO,
				ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES)
		{
			return true;
		}
		return false;
	}

}
