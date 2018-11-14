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
			// TODO
		} else if (event.getSource().equals(btnWebAccountDelete))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountEdit))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountSaveEdit))
		{
			// TODO
		} else if (event.getSource().equals(btnWebAccountShowHidePassword))
		{
			// TODO
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

	// ------------------------------------------------------------
	// --------------------- NO FXML METHODS ----------------------
	// ------------------------------------------------------------

}
