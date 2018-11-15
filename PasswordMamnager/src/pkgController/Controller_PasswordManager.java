package pkgController;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pkgData.Database;
import pkgData.WebAccount;
import pkgMisc.ExceptionHandler;

public class Controller_PasswordManager
{

	// ============================================================
	// ============================================================
	// ===================== FXML VARIABLES =======================
	// ============================================================
	// ============================================================

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

	@FXML
	private HBox paneWebAccountEditDelete;

	@FXML
	private JFXButton btnWebAccountOpenInBrowser;

	@FXML
	private VBox paneWebAccountDetails;

	// ============================================================
	// ============================================================
	// =================== NO FXML VARIABLES ======================
	// ============================================================
	// ============================================================

	private ObservableList<WebAccount> listWebAccounts;
	private Database db;
	private WebAccount currentAccount = null;
	private boolean webAccountPasswordVisible;

	// ============================================================
	// ============================================================
	// ====================== FXML METHODS ========================
	// ============================================================
	// ============================================================

	// -------------------------------------------------------------
	// ----------------- web account things ------------------------
	// -------------------------------------------------------------

	@FXML
	void initialize() throws Exception
	{
		// db = Database.newInstance();

		listWebAccounts = FXCollections.observableArrayList();
		listViewWebAccount.setItems(listWebAccounts);
		listWebAccounts.add(new WebAccount("test1", "google.com", "http://www.google.com/", "googleUser",
				"googlePassword", "info for google"));
		listWebAccounts.add(new WebAccount("test2", "facebook.com", "http://www.facebook.com/", "fbUser", "fbPassword",
				"info for fb"));
		listWebAccounts.add(new WebAccount("test3", "twitter.com", "http://www.twitter.com/", "tiwtterUser",
				"twitterPassword", "info for twitter"));
		listWebAccounts.add(new WebAccount("test4", "tumblr.com", "http://www.tumblr.com/", "tumblrUser",
				"tumblrPassword", "info for tumblr"));
	}

	@FXML
	void onSelectButtonWebAccount(ActionEvent event) // Action handler for all kinds in web accounts view
	{
		try
		{
			if (event.getSource().equals(btnWebAccountAdd)) // on add account
			{
				WebAccount tmp = new WebAccount("Name", "example.com", "http://example.com", "max.mustermann", "1234",
						"Info for example.com.");
				listWebAccounts.add(tmp);
				currentAccount = tmp;
				listViewWebAccount.getSelectionModel().select(listWebAccounts.indexOf(currentAccount));
				doFillTextFieldsWebAccount();
				paneWebAccountEditDelete.setVisible(false);
				paneWebAccountSaveCancelEdit.setVisible(true);
				doSetTextFieldsWebAccountEditable(true);
				// TODO add into db
			} else if (event.getSource().equals(btnWebAccountCancelEdit)) //on cancel edit
			{
				doFillTextFieldsWebAccount();
				doSetTextFieldsWebAccountEditable(false);
				paneWebAccountSaveCancelEdit.setVisible(false);
				paneWebAccountEditDelete.setVisible(true);
			} else if (event.getSource().equals(btnWebAccountDelete))  //on delete

			{
				if (doShowDeleteDialogWebAccount())
				{
					doDeleteWebAccount();
				}
			} else if (event.getSource().equals(btnWebAccountEdit)) // on edit
			{
				doSetTextFieldsWebAccountEditable(true);
				paneWebAccountSaveCancelEdit.setVisible(true);
			} else if (event.getSource().equals(btnWebAccountSaveEdit)) //Save edit
			{
				currentAccount.setAdditionalInformation(txtWebAccountAdditionalInformation.getText());
				currentAccount.setName(txtWebAccountName.getText());
				currentAccount.setPassword(pwdWebAccountPassword.getText());
				currentAccount.setUsername(txtWebAccountUsername.getText());
				currentAccount.setWebsiteURL(txtWebAccountURL.getText());

				// TODO save into db
				doFillTextFieldsWebAccount();
				doSetTextFieldsWebAccountEditable(false);
				paneWebAccountEditDelete.setVisible(true);
				paneWebAccountSaveCancelEdit.setVisible(false);
				imgWebAccountThumbnail.setImage(currentAccount.getThumbnail());

			} else if (event.getSource().equals(btnWebAccountShowHidePassword))
			{
				if (webAccountPasswordVisible)
				{
					String pwd = txtWebAccountPassword.getText();
					pwdWebAccountPassword.setVisible(true);
					txtWebAccountPassword.setVisible(false);
					pwdWebAccountPassword.setText(pwd);
					webAccountPasswordVisible = false;
				} else
				{
					String pwd = pwdWebAccountPassword.getText();
					pwdWebAccountPassword.setVisible(false);
					txtWebAccountPassword.setVisible(true);
					txtWebAccountPassword.setText(pwd);
					webAccountPasswordVisible = true;
				}
			} else if (event.getSource().equals(btnWebAccountOpenInBrowser))
			{
				if (Desktop.isDesktopSupported())
				{
					Desktop.getDesktop().browse(new URI(txtWebAccountURL.getText()));
				}
			}
			// copy buttons down here
			else if (event.getSource().equals(btnWebAccountCopyAdditionalInformation))
			{
				pkgMisc.SystemClipboard.copy(txtWebAccountAdditionalInformation.getText());
			} else if (event.getSource().equals(btnWebAccountCopyName))
			{
				pkgMisc.SystemClipboard.copy(txtWebAccountName.getText());
			} else if (event.getSource().equals(btnWebAccountCopyPassword))
			{
				pkgMisc.SystemClipboard.copy(pwdWebAccountPassword.getText());
			} else if (event.getSource().equals(btnWebAccountCopyURL))
			{
				pkgMisc.SystemClipboard.copy(txtWebAccountURL.getText());
			} else if (event.getSource().equals(btnWebAccountCopyUsername))
			{
				pkgMisc.SystemClipboard.copy(txtWebAccountUsername.getText());
			}
		} catch (Exception ex)
		{
			ExceptionHandler.hanldeUnexpectedException(ex);
		}
	}

	// ============================================================
	// ============================================================
	// ===================== NO FXML METHODS ======================
	// ============================================================
	// ============================================================

	// -------------------------------------------------------------
	// ----------------- web account things ------------------------
	// -------------------------------------------------------------

	private void doDeleteWebAccount()
	{
		// db.deleteWebAccount(currentAccount); TODO
		int pos = listWebAccounts.indexOf(currentAccount);
		listWebAccounts.remove(currentAccount);
		currentAccount = listWebAccounts.get(pos - 1);
	}

	@FXML
	void onSelectListView(MouseEvent event)
	{
		if (event.getSource().equals(listViewWebAccount))
		{
			if (listViewWebAccount.getSelectionModel().getSelectedItem() != null)
			{
				paneWebAccountDetails.setVisible(true);
				currentAccount = listViewWebAccount.getSelectionModel().getSelectedItem();
				doFillTextFieldsWebAccount();
				imgWebAccountThumbnail.setImage(currentAccount.getThumbnail());
			}
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

	private boolean doShowDeleteDialogWebAccount()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + currentAccount + " ?", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		return alert.getResult() == ButtonType.YES;

	}

	private void doFillTextFieldsWebAccount()
	{
		if (currentAccount != null)
		{
			txtWebAccountName.setText(currentAccount.getName());
			pwdWebAccountPassword.setText(currentAccount.getPassword());
			txtWebAccountURL.setText(currentAccount.getWebsiteURL().toString());
			txtWebAccountUsername.setText(currentAccount.getUsername());
			txtWebAccountAdditionalInformation.setText(currentAccount.getAdditionalInformation());
		}
	}
}
