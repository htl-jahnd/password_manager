package pkgController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pkgData.Database;
import pkgData.WebAccount;

public class Controller_PasswordManager
{

	// ------------------------------------------------------------
	// --------------------- FXML VARIABLES -----------------------
	// ------------------------------------------------------------

	@FXML
	private JFXButton btnWebAccountAdd;

	@FXML
	private Label lblWebAccountSiteName;

	@FXML
	private ImageView imgWebAccountThumbnail;

	@FXML
	private JFXButton btnWebAccountEdit;

	@FXML
	private JFXButton btnWebAccountDelete;

	@FXML
	private JFXPasswordField pwdWebAccountPassword;

	@FXML
	private JFXTextField txtWebAccountPassword;

	@FXML
	private JFXTextField txtWebAccountUsername;

	@FXML
	private JFXTextField txtWebAccountURL;

	@FXML
	private JFXTextArea txtWebAccountAdditionalInformation;

	@FXML
	private JFXTextField txtWebAccountName;

	@FXML
	private JFXButton btnWebAccountCopyName;

	@FXML
	private JFXButton btnWebAccountCopyURL;

	@FXML
	private JFXButton btnWebAccountCopyUsername;

	@FXML
	private JFXButton btnWebAccountCopyPassword;

	@FXML
	private JFXButton btnWebAccountCopyAdditionalInformation;

	@FXML
	private JFXButton btnWebAccountShowHidePassword;

	@FXML
	private JFXButton btnWebAccountCancelEdit;

	@FXML
	private JFXButton btnWebAccountSaveEdit;

	@FXML
	private HBox paneWebAccountSaveCancelEdit;

	@FXML
	private ImageView imgWebAccountShowHidePassword;

	@FXML
	private JFXListView<WebAccount> listViewWebAccount;

	// ------------------------------------------------------------
	// -------------------- NO FXML VARIABLES ---------------------
	// ------------------------------------------------------------

	private ObservableList<WebAccount> listWebAccounts;
	private Database db;
	private WebAccount currentAccount = null;
	private boolean webAccountPasswordVisible;

	// ------------------------------------------------------------
	// ---------------------- FXML METHODS ------------------------
	// ------------------------------------------------------------

	@FXML
	void initialize()
	{
		listWebAccounts = FXCollections.observableArrayList();
		listViewWebAccount.setItems(listWebAccounts);

	}

	@FXML
	void onSelectButtonWebAccount(ActionEvent event) // Action handler for all kinds in web accounts view
	{
		if (event.getSource().equals(btnWebAccountAdd))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountCancelEdit))
		{
			doFillTextFieldsWebAccount();
			paneWebAccountSaveCancelEdit.setVisible(false);
		} else if (event.getSource().equals(btnWebAccountDelete))
		{
			doShowDeleteDialogWebAccount();
		} else if (event.getSource().equals(btnWebAccountEdit))
		{
			doSetTextFieldsWebAccountEditable(true);
			paneWebAccountSaveCancelEdit.setVisible(true);
		} else if (event.getSource().equals(btnWebAccountSaveEdit))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountShowHidePassword))
		{
			if (webAccountPasswordVisible)
			{
				String pwd = txtWebAccountPassword.getText();
				pwdWebAccountPassword.setVisible(true);
				txtWebAccountPassword.setVisible(false);
				pwdWebAccountPassword.setText(pwd);
				webAccountPasswordVisible=false;
			} else
			{
				String pwd = pwdWebAccountPassword.getText();
				pwdWebAccountPassword.setVisible(false);
				txtWebAccountPassword.setVisible(true);
				txtWebAccountPassword.setText(pwd);
				webAccountPasswordVisible = true;
			}
		}
		// copy buttons down here
		else if (event.getSource().equals(btnWebAccountCopyAdditionalInformation))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountCopyName))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountCopyPassword))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountCopyURL))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountCopyUsername))
		{
			// TODO
		}

	}

	private void doSetTextFieldsWebAccountEditable(boolean state)
	{
		txtWebAccountName.setEditable(state);
		if (webAccountPasswordVisible == true)
		{
			txtWebAccountPassword.setEditable(state);
		} else
			pwdWebAccountPassword.setEditable(state);

		txtWebAccountURL.setEditable(state);
		txtWebAccountUsername.setEditable(state);
		txtWebAccountAdditionalInformation.setEditable(state);
	}

	private void doShowDeleteDialogWebAccount()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + currentAccount + " ?", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES)
		{
			db.deleteWebAccount(currentAccount);
			// TODO set details pane invisible
		}
	}

	private void doFillTextFieldsWebAccount()
	{
		// TODO Auto-generated method stub

	}

	// ------------------------------------------------------------
	// --------------------- NO FXML METHODS ----------------------
	// ------------------------------------------------------------

}
