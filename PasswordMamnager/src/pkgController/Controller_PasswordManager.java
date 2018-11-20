package pkgController;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.YearMonth;
import java.util.Date;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pkgData.CreditCard;
import pkgData.Database;
import pkgData.ECreditCardsProviders;
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
	private JFXButton btnWebAccount;

	@FXML
	private JFXButton btnCreditCard;

	@FXML
	private JFXButton btnIdentities;

	@FXML
	private JFXButton btnSettings;

	@FXML
	private VBox paneWebAccountList;

	@FXML
	private JFXButton btnWebAccountAdd;

	@FXML
	private JFXListView<WebAccount> listViewWebAccount;

	@FXML
	private VBox paneCreditCardList;

	@FXML
	private JFXButton btnCreditCardAdd;

	@FXML
	private JFXListView<CreditCard> listViewCreditCard;

	@FXML
	private VBox paneWebAccountDetails;

	@FXML
	private Label lblWebAccountSiteName;

	@FXML
	private ImageView imgWebAccountThumbnail;

	@FXML
	private HBox paneWebAccountEditDelete;

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
	private ImageView imgWebAccountShowHidePassword;

	@FXML
	private JFXButton btnWebAccountOpenInBrowser;

	@FXML
	private HBox paneWebAccountSaveCancelEdit;

	@FXML
	private JFXButton btnWebAccountCancelEdit;

	@FXML
	private JFXButton btnWebAccountSaveEdit;

	@FXML
	private VBox paneCreditCardDetails;

	@FXML
	private Label lblCreditCardCardName;

	@FXML
	private ImageView imgCreditCardThumbnail;

	@FXML
	private HBox paneCreditCardEditDelete;

	@FXML
	private JFXButton btnCreditCardEdit;

	@FXML
	private JFXButton btnCreditCardDelete;

	@FXML
	private JFXPasswordField pwdCreditCardNumber;

	@FXML
	private JFXTextField txtCreditCardNumber;

	@FXML
	private JFXTextField txtCreditCardBankName;

	@FXML
	private JFXTextField txtCreditCardOwner;

	@FXML
	private JFXTextArea txtCreditCardAdditionalInformation;

	@FXML
	private JFXTextField txtCreditCardName;

	@FXML
	private JFXButton btnCreditCardCopyName;

	@FXML
	private JFXButton btnCreditCardCopyOwner;

	@FXML
	private JFXButton btnCreditCardCopyBankName;

	@FXML
	private JFXButton btnCreditCardCopyNumber;

	@FXML
	private JFXButton btnCreditCardCopyAdditionalInformation;

	@FXML
	private JFXButton btnCreditCardShowNuber;

	@FXML
	private ImageView imgCreditCardShowHideNumber;

	@FXML
	private JFXComboBox<ECreditCardsProviders> cmbxCreditCardProvider;

	@FXML
	private JFXButton btnCreditCardCopyProvider;

	@FXML
	private JFXTextField txtCreditCardCVV;

	@FXML
	private JFXButton btnCreditCardShowCVV;

	@FXML
	private ImageView imgCreditCardShowHideCVV;

	@FXML
	private JFXTextField txtCreditCardExpireDate;

	@FXML
	private JFXButton btnCreditCardCopyExpireDate;

	@FXML
	private JFXButton btnCreditCardCopyCVV;

	@FXML
	private HBox paneCreditCardSaveCancelEdit;

	@FXML
	private JFXButton btnCreditCardCancelEdit;

	@FXML
	private JFXButton btnCreditCardSaveEdit;

	@FXML
	private JFXPasswordField pwdCreditCardCVV;

	// ============================================================
	// ============================================================
	// =================== NO FXML VARIABLES ======================
	// ============================================================
	// ============================================================

	private ObservableList<WebAccount> listWebAccounts;
	private ObservableList<ECreditCardsProviders> listCreditCardProviders;
	private ObservableList<CreditCard> listCreditCards;
	private Database db;
	private WebAccount currentAccount = null;
	private CreditCard currentCard;
	private boolean webAccountPasswordVisible;

	// ============================================================
	// ============================================================
	// ====================== FXML METHODS ========================
	// ============================================================
	// ============================================================

	@FXML
	void initialize() throws Exception
	{
		db = Database.newInstance();

		listCreditCardProviders = FXCollections.observableArrayList();
		listCreditCardProviders.add(ECreditCardsProviders.Visa);
		listCreditCardProviders.add(ECreditCardsProviders.MasterCard);
		listCreditCardProviders.add(ECreditCardsProviders.AmericanExpress);
		listCreditCardProviders.add(ECreditCardsProviders.DinersClub);
		listCreditCardProviders.add(ECreditCardsProviders.Discover);
		listCreditCardProviders.add(ECreditCardsProviders.Other);
		cmbxCreditCardProvider.setItems(listCreditCardProviders);
		cmbxCreditCardProvider.setCellFactory(cmbx -> new ListCell<ECreditCardsProviders>() {
			@Override
			protected void updateItem(ECreditCardsProviders item, boolean empty)
			{
				super.updateItem(item, empty);

				if (empty)
				{
					setGraphic(null);
				} else
				{
					// Create a HBox to hold our displayed value
					HBox hBox = new HBox(2);
					hBox.setAlignment(Pos.CENTER_LEFT);
					ImageView iv = new ImageView();
					try
					{
						iv.setImage(SwingFXUtils.toFXImage(ECreditCardsProviders.getProviderPicture(item), null));
					} catch (IOException e)
					{
						ExceptionHandler.hanldeUnexpectedException(e);
					}
					iv.setFitHeight(25);
					iv.setFitWidth(25);
					// Add the values from our piece to the HBox
					hBox.getChildren().addAll(iv, new Label("   " + ECreditCardsProviders.getProviderString(item)));
					// Set the HBox as the display
					setGraphic(hBox);
				}
			}
		});

		listCreditCards = FXCollections.observableArrayList();
		listViewCreditCard.setItems(listCreditCards);
		listViewCreditCard.setCellFactory(listView -> new ListCell<CreditCard>() {
			@Override
			protected void updateItem(CreditCard card, boolean empty)
			{
				super.updateItem(card, empty);

				if (empty)
				{
					setGraphic(null);
				} else
				{
					// Create a HBox to hold our displayed value
					HBox hBox = new HBox(2);
					hBox.setAlignment(Pos.CENTER_LEFT);
					ImageView iv = new ImageView(card.getThumbnail());
					iv.setFitHeight(25);
					iv.setFitWidth(25);
					// Add the values from our piece to the HBox
					hBox.getChildren().addAll(iv, new Label("   " + card.getCardName()));
					// Set the HBox as the display
					setGraphic(hBox);
				}
			}
		});

		listWebAccounts = FXCollections.observableArrayList();
		listViewWebAccount.setItems(listWebAccounts);
		listViewWebAccount.setCellFactory(listView -> new ListCell<WebAccount>() {
			@Override
			protected void updateItem(WebAccount account, boolean empty)
			{
				super.updateItem(account, empty);

				if (empty)
				{
					setGraphic(null);
				} else
				{
					// Create a HBox to hold our displayed value
					HBox hBox = new HBox(2);
					hBox.setAlignment(Pos.CENTER_LEFT);
					ImageView iv = new ImageView(account.getThumbnail());
					iv.setFitHeight(25);
					iv.setFitWidth(25);
					// Add the values from our piece to the HBox
					hBox.getChildren().addAll(iv, new Label("   " + account.getName()));
					// Set the HBox as the display
					setGraphic(hBox);
				}
			}
		});

		// TEST DATA
		listWebAccounts.add(new WebAccount("test1", "google.com", "http://www.google.com/", "googleUser",
				"googlePassword", "info for google"));
		listWebAccounts.add(new WebAccount("test2", "facebook.com", "http://www.facebook.com/", "fbUser", "fbPassword",
				"info for fb"));
		listWebAccounts.add(new WebAccount("test3", "twitter.com", "http://www.twitter.com/", "tiwtterUser",
				"twitterPassword", "info for twitter"));
		listWebAccounts.add(new WebAccount("test4", "tumblr.com", "http://www.tumblr.com/", "tumblrUser",
				"tumblrPassword", "info for tumblr"));

		listCreditCards.add(new CreditCard("Test card 1", "1234 5678", "Owner No1", YearMonth.of(2020, 11),
				ECreditCardsProviders.Visa, "Infooo", "Erste Bank", 1234));
	}

	@FXML
	void onSelectButtonMenu(ActionEvent event)
	{
		if (event.getSource().equals(btnWebAccount))
		{
			paneCreditCardDetails.setVisible(false);
			paneCreditCardList.setVisible(false);
			paneWebAccountDetails.setVisible(true);
			paneWebAccountList.setVisible(true);
			// TODO do this for later things too
		} else if (event.getSource().equals(btnCreditCard))
		{
			paneCreditCardDetails.setVisible(true);
			paneCreditCardList.setVisible(true);
			paneWebAccountDetails.setVisible(false);
			paneWebAccountList.setVisible(false);
			// TODO do this for later things too
		} else if (event.getSource().equals(btnIdentities))
		{
			// TODO do this for later things too
		} else if (event.getSource().equals(btnSettings))
		{
			// TODO do this for later things too
		}

	}

	// -------------------------------------------------------------
	// ----------------- web account things ------------------------
	// -------------------------------------------------------------

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
				db.addWebAccount(currentAccount);
			} else if (event.getSource().equals(btnWebAccountCancelEdit)) // on cancel edit
			{
				doFillTextFieldsWebAccount();
				doSetTextFieldsWebAccountEditable(false);
				paneWebAccountSaveCancelEdit.setVisible(false);
				paneWebAccountEditDelete.setVisible(true);
			} else if (event.getSource().equals(btnWebAccountDelete)) // on delete
			{
				if (doShowDeleteDialogWebAccount())
				{
					doDeleteWebAccount();
				}
			} else if (event.getSource().equals(btnWebAccountEdit)) // on edit
			{
				doSetTextFieldsWebAccountEditable(true);
				paneWebAccountSaveCancelEdit.setVisible(true);
			} else if (event.getSource().equals(btnWebAccountSaveEdit)) // on Save edit
			{
				currentAccount.setAdditionalInformation(txtWebAccountAdditionalInformation.getText());
				currentAccount.setName(txtWebAccountName.getText());
				currentAccount.setPassword(pwdWebAccountPassword.getText());
				currentAccount.setUsername(txtWebAccountUsername.getText());
				currentAccount.setWebsiteURL(txtWebAccountURL.getText());

				db.updateWebAccount(currentAccount);

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
					imgWebAccountShowHidePassword.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/eye-solid.png")),
							null));
				} else
				{
					String pwd = pwdWebAccountPassword.getText();
					pwdWebAccountPassword.setVisible(false);
					txtWebAccountPassword.setVisible(true);
					txtWebAccountPassword.setText(pwd);
					webAccountPasswordVisible = true;
					imgWebAccountShowHidePassword.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(
									getClass().getResourceAsStream("/pkgMain/ressources/images/eye-slash-solid.png")),
							null));
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

	@FXML
	void onSelectListViewWebAccount(MouseEvent event)
	{
		if (event.getSource().equals(listViewWebAccount))
		{
			if (listViewWebAccount.getSelectionModel().getSelectedItem() != null)
			{
				paneWebAccountDetails.setVisible(true);
				currentAccount = listViewWebAccount.getSelectionModel().getSelectedItem();
				doFillTextFieldsWebAccount();

			}
		}
	}
	// -------------------------------------------------------------
	// ----------------- Credit Card things ------------------------
	// -------------------------------------------------------------

	@FXML
	void onSelectButtonCreditCard(ActionEvent event)
	{
		try
		{
			if (event.getSource().equals(btnCreditCardAdd)) // on add card
			{
				Date now = new Date();
				CreditCard tmp = new CreditCard("Name", "0000 1111 2222 3333 4444", "Card Owner",
						YearMonth.of(now.getYear(), now.getMonth()), ECreditCardsProviders.Other,
						"Additional Information", "Example Bank", Integer.valueOf(123));
				listCreditCards.add(tmp);
				currentCard = tmp;
				listViewCreditCard.getSelectionModel().select(listWebAccounts.indexOf(currentAccount));
				doFillTextFieldsCreditCard();
				paneCreditCardEditDelete.setVisible(false);
				paneCreditCardSaveCancelEdit.setVisible(true);
				doSetTextFieldsCreditCardEditable(true);
				db.addCreditCard(currentCard);
			} else if (event.getSource().equals(btnCreditCardCancelEdit)) // on cancel editing
			{
				doFillTextFieldsCreditCard();
				doSetTextFieldsCreditCardEditable(false);
				paneCreditCardSaveCancelEdit.setVisible(false);
				paneCreditCardEditDelete.setVisible(true);
			} else if (event.getSource().equals(btnCreditCardDelete)) // on delete card
			{
				if (doShowDeleteDialogCreditCard())
				{
					doDeleteCreditCard();
				}
			} else if (event.getSource().equals(btnCreditCardEdit)) // on edit card
			{
				doSetTextFieldsCreditCardEditable(true);
				paneCreditCardSaveCancelEdit.setVisible(true);
			} else if (event.getSource().equals(btnCreditCardSaveEdit)) // on save after editing
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardShowCVV)) // on show/hide cvv
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardShowNuber)) // onshow/hide number
			{
				// TODO
			}

			// COPY BUTTONS DOWN HERE
			else if (event.getSource().equals(btnCreditCardCopyAdditionalInformation))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyBankName))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyCVV))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyExpireDate))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyName))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyNumber))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyOwner))
			{
				// TODO
			} else if (event.getSource().equals(btnCreditCardCopyProvider))
			{
				// TODO
			}
		} catch (Exception e)
		{
			ExceptionHandler.hanldeUnexpectedException(e);
		}

	}

	@FXML
	void onSelectListViewCreditCard(MouseEvent event)
	{
		if (event.getSource().equals(listViewCreditCard))
		{
			if (listViewCreditCard.getSelectionModel().getSelectedItem() != null)
			{
				paneCreditCardDetails.setVisible(true);
				currentCard = listViewCreditCard.getSelectionModel().getSelectedItem();
				doFillTextFieldsCreditCard();
			}
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
		db.deleteWebAccount(currentAccount);
		listWebAccounts.remove(currentAccount);
		if (pos > 0)
			currentAccount = listWebAccounts.get(pos - 1);
		else if (pos == 0)
			currentAccount = listWebAccounts.get(pos);
		doFillTextFieldsWebAccount();
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
			imgWebAccountThumbnail.setImage(currentAccount.getThumbnail());
			lblWebAccountSiteName.setText(currentAccount.getName());
			txtWebAccountName.setText(currentAccount.getName());
			pwdWebAccountPassword.setText(currentAccount.getPassword());
			txtWebAccountURL.setText(currentAccount.getWebsiteURL().toString());
			txtWebAccountUsername.setText(currentAccount.getUsername());
			txtWebAccountAdditionalInformation.setText(currentAccount.getAdditionalInformation());
		}
	}

	// -------------------------------------------------------------
	// ----------------- Credit Card things ------------------------
	// -------------------------------------------------------------

	private void doFillTextFieldsCreditCard()
	{
		imgCreditCardThumbnail.setImage(currentCard.getThumbnail());
		lblCreditCardCardName.setText(currentCard.getCardName());
		txtCreditCardName.setText(currentCard.getCardName());
		txtCreditCardOwner.setText(currentCard.getOwnerName());
		txtCreditCardBankName.setText(currentCard.getBankName());
		cmbxCreditCardProvider.getSelectionModel().select(currentCard.getProvider());
		txtCreditCardExpireDate.setText(currentCard.getExpireDateAsString());
		pwdCreditCardNumber.setText(currentCard.getCardNumber());
		pwdCreditCardCVV.setText(String.valueOf(currentCard.getSecurityCode()));
		txtCreditCardAdditionalInformation.setText(currentCard.getAdditionalInformation());
	}

	private void doSetTextFieldsCreditCardEditable(boolean state)
	{
		txtCreditCardName.setEditable(state);
		txtCreditCardOwner.setEditable(state);
		txtCreditCardBankName.setEditable(state);
		cmbxCreditCardProvider.setEditable(state);
		txtCreditCardExpireDate.setEditable(state);
		txtCreditCardAdditionalInformation.setEditable(state);
		if (txtCreditCardNumber.isVisible())
		{
			txtCreditCardNumber.setEditable(state);
		} else
			pwdCreditCardNumber.setEditable(state);
		if (txtCreditCardCVV.isVisible())
		{
			txtCreditCardCVV.setEditable(state);
		} else
			pwdCreditCardCVV.setEditable(state);

	}

	private boolean doShowDeleteDialogCreditCard()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + currentCard.getCardName() + " ?", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		return alert.getResult() == ButtonType.YES;

	}

	private void doDeleteCreditCard()
	{
		int pos = listCreditCards.indexOf(currentCard);
		db.delteCreditCard(currentCard);
		listCreditCards.remove(currentCard);
		if (pos > 0)
			currentCard = listCreditCards.get(pos - 1);
		else if (pos == 0)
			currentCard = listCreditCards.get(pos);
		doFillTextFieldsCreditCard();
	}

}
