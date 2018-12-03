package pkgController;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.YearMonth;
import java.time.ZoneId;
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
import pkgData.ESex;
import pkgData.Passport;
import pkgData.WebAccount;
import pkgMisc.DateUtils;
import pkgMisc.ExceptionHandler;
import pkgMisc.SystemClipboard;

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
	private JFXButton btnPassport;

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
	private VBox panePassportsList;

	@FXML
	private JFXButton btnPassportAdd;

	@FXML
	private JFXListView<Passport> listViewPassport;

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
	private JFXPasswordField pwdCreditCardCVV;

	@FXML
	private HBox paneCreditCardSaveCancelEdit;

	@FXML
	private JFXButton btnCreditCardCancelEdit;

	@FXML
	private JFXButton btnCreditCardSaveEdit;

	@FXML
	private VBox panePassportDetails;

	@FXML
	private Label lblPassportName;

	@FXML
	private ImageView imgPassportThumbnail;

	@FXML
	private HBox panePassportEditDelete;

	@FXML
	private JFXButton btnPassportEdit;

	@FXML
	private JFXButton btnPassportDelete;

	@FXML
	private JFXTextField txtPassportExpirationDate;

	@FXML
	private JFXTextField txtPassportNationality;

	@FXML
	private JFXTextField txtPassportSurname;

	@FXML
	private JFXTextArea txtPassportAdditionalInformation;

	@FXML
	private JFXTextField txtPassportGivenNames;

	@FXML
	private JFXButton btnPassportCopyGivenNames;

	@FXML
	private JFXButton btnPassportCopySurname;

	@FXML
	private JFXButton btnPassportCopyNationality;

	@FXML
	private JFXButton btnPassportCopyExpirationDate;

	@FXML
	private JFXButton btnPassportCopyAdditionalInformation;

	@FXML
	private JFXButton btnPassportCopyDateOfBirth;

	@FXML
	private JFXTextField txtPassportNumber;

	@FXML
	private JFXButton btnPassportShowHideNumber;

	@FXML
	private ImageView imgPassportShowHideNumber;

	@FXML
	private JFXTextField txtPassportDateOfIssue;

	@FXML
	private JFXButton btnPassportCopyDateOfIssue;

	@FXML
	private JFXButton btnPassportCopyNumber;

	@FXML
	private JFXPasswordField pwdPassportNumber;

	@FXML
	private JFXTextField txtPassportDateOfBirth;

	@FXML
	private JFXTextField txtPassportAuthority;

	@FXML
	private JFXComboBox<ESex> cmbxPassportSex;

	@FXML
	private JFXButton btnPassportCopyAuthority;

	@FXML
	private JFXButton btnPassportCopySex;

	@FXML
	private HBox panePassportSaveCancelEdit;

	@FXML
	private JFXButton btnPassportCancelEdit;

	@FXML
	private JFXButton btnPassportSaveEdit;

	@FXML
	private JFXButton btnPassportCopyPlaceOfBirth;

	@FXML
	private JFXTextField txtPassportPlaceOfBirth;

	// ============================================================
	// ============================================================
	// =================== NO FXML VARIABLES ======================
	// ============================================================
	// ============================================================

	private ObservableList<WebAccount> listWebAccounts;
	private ObservableList<ECreditCardsProviders> listCreditCardProviders;
	private ObservableList<CreditCard> listCreditCards;
	private ObservableList<ESex> listSex;
	private ObservableList<Passport> listPassports;

	private Database db;
	private WebAccount currentAccount = null;
	private CreditCard currentCard = null;
	private Passport currentPass = null;

	// ============================================================
	// ============================================================
	// ====================== FXML METHODS ========================
	// ============================================================
	// ============================================================

	@FXML
	void initialize() throws Exception
	{
		db = Database.newInstance();

		listSex = FXCollections.observableArrayList();
		listSex.add(ESex.Female);
		listSex.add(ESex.Male);
		cmbxPassportSex.setItems(listSex);
		cmbxPassportSex.setCellFactory(cmbx -> new ListCell<ESex>() { // TODO find new pictures for male & female
			@Override
			protected void updateItem(ESex item, boolean empty)
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
						iv.setImage(SwingFXUtils.toFXImage(ESex.getSexPicture(item), null));
					} catch (IOException e)
					{
						ExceptionHandler.hanldeUnexpectedException(e);
					}
					iv.setFitHeight(25);
					iv.setFitWidth(25);
					// Add the values from our piece to the HBox
					hBox.getChildren().addAll(iv, new Label("   " + ESex.getSexString(item)));
					// Set the HBox as the display
					setGraphic(hBox);
				}
			}
		});

		listPassports = FXCollections.observableArrayList();
		listViewPassport.setItems(listPassports); 
		listViewPassport.setCellFactory(listView -> new ListCell<Passport>() {
			@Override
			protected void updateItem(Passport pass, boolean empty)
			{
				super.updateItem(pass, empty);

				if (empty)
				{
					setGraphic(null);
				} else
				{
					// Create a HBox to hold our displayed value
					HBox hBox = new HBox(2);
					hBox.setAlignment(Pos.CENTER_LEFT);
					ImageView iv = new ImageView(pass.getThumbnail());
					iv.setFitHeight(25);
					iv.setFitWidth(25);
					// Add the values from our piece to the HBox
					hBox.getChildren().addAll(iv, new Label("   " + pass.getGivenNames() + " "+pass.getSurName()));
					// Set the HBox as the display
					setGraphic(hBox);
				}
			}
		});
		
		
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
		listWebAccounts.add(new WebAccount("test1", "google.com", "https://www.google.com/", "googleUser",
				"googlePassword", "info for google"));
		listWebAccounts.add(new WebAccount("test2", "facebook.com", "https://www.facebook.com/", "fbUser", "fbPassword",
				"info for fb"));
		listWebAccounts.add(new WebAccount("test3", "twitter.com", "https://www.twitter.com/", "tiwtterUser",
				"twitterPassword", "info for twitter"));
		listWebAccounts.add(new WebAccount("test4", "tumblr.com", "https://www.tumblr.com/", "tumblrUser",
				"tumblrPassword", "info for tumblr"));
		listWebAccounts.add(new WebAccount("Pronhub", "pornhub.com", "https://www.pornhub.com/", "pornhubUser",
				"pornhubPassword", "info for pornhub"));

		listCreditCards.add(new CreditCard("Test card 1", "1234 5678", "Owner No1", YearMonth.of(2020, 11),
				ECreditCardsProviders.Visa, "Infooo", "Erste Bank", 123));
	}

	@FXML
	void onSelectButtonMenu(ActionEvent event)
	{
		if (event.getSource().equals(btnWebAccount))
		{
			panePassportDetails.setVisible(false);
			panePassportsList.setVisible(false);
			paneCreditCardDetails.setVisible(false);
			paneCreditCardList.setVisible(false);
			paneWebAccountDetails.setVisible(true);
			paneWebAccountList.setVisible(true);
			if (currentAccount == null)
			{
				paneWebAccountDetails.setVisible(false);
			}
			// TODO do this for later things too
		} else if (event.getSource().equals(btnCreditCard))
		{
			paneCreditCardDetails.setVisible(true);
			paneCreditCardList.setVisible(true);
			paneWebAccountDetails.setVisible(false);
			paneWebAccountList.setVisible(false);
			panePassportDetails.setVisible(false);
			panePassportsList.setVisible(false);
			if (currentCard == null)
			{
				paneCreditCardDetails.setVisible(false);
			}
			// TODO do this for later things too
		} else if (event.getSource().equals(btnIdentities))
		{
			// TODO do this for later things too
		} else if (event.getSource().equals(btnPassport))
		{
			panePassportDetails.setVisible(true);
			panePassportsList.setVisible(true);
			paneCreditCardDetails.setVisible(false);
			paneCreditCardList.setVisible(false);
			paneWebAccountDetails.setVisible(false);
			paneWebAccountList.setVisible(false);
			if (currentPass == null)
			{
				panePassportDetails.setVisible(false);
			}

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
				WebAccount tmp = new WebAccount();
				listWebAccounts.add(tmp);
				currentAccount = tmp;
				listViewWebAccount.getSelectionModel().select(listWebAccounts.indexOf(currentAccount));
				doFillTextFieldsWebAccount();
				paneWebAccountEditDelete.setVisible(false);
				paneWebAccountSaveCancelEdit.setVisible(true);
				doSetTextFieldsWebAccountEditable(true);
				db.addWebAccount(currentAccount);
				paneWebAccountDetails.setVisible(true);
				paneWebAccountList.setDisable(true);
			} else if (event.getSource().equals(btnWebAccountCancelEdit)) // on cancel edit
			{
				doFillTextFieldsWebAccount();
				doSetTextFieldsWebAccountEditable(false);
				paneWebAccountSaveCancelEdit.setVisible(false);
				paneWebAccountEditDelete.setVisible(true);
				paneWebAccountList.setDisable(false);
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
				paneWebAccountEditDelete.setVisible(false);
				paneWebAccountList.setDisable(true);
			} else if (event.getSource().equals(btnWebAccountSaveEdit)) // on Save edit
			{
				currentAccount.setAdditionalInformation(txtWebAccountAdditionalInformation.getText());
				currentAccount.setName(txtWebAccountName.getText());
				if (txtWebAccountPassword.isVisible())
					currentAccount.setPassword(txtWebAccountPassword.getText());
				else
					currentAccount.setPassword(pwdWebAccountPassword.getText());
				currentAccount.setUsername(txtWebAccountUsername.getText());
				currentAccount.setWebsiteURL(txtWebAccountURL.getText());

				db.updateWebAccount(currentAccount);

				doFillTextFieldsWebAccount();
				doSetTextFieldsWebAccountEditable(false);
				paneWebAccountEditDelete.setVisible(true);
				paneWebAccountSaveCancelEdit.setVisible(false);
				imgWebAccountThumbnail.setImage(currentAccount.getThumbnail());
				paneWebAccountList.setDisable(false);
				listViewWebAccount.refresh();
			} else if (event.getSource().equals(btnWebAccountShowHidePassword))
			{
				if (txtWebAccountPassword.isVisible())
				{
					String pwd = txtWebAccountPassword.getText();
					pwdWebAccountPassword.setVisible(true);
					txtWebAccountPassword.setVisible(false);
					pwdWebAccountPassword.setText(pwd);
					imgWebAccountShowHidePassword.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/eye-solid.png")),
							null));
				} else
				{
					String pwd = pwdWebAccountPassword.getText();
					pwdWebAccountPassword.setVisible(false);
					txtWebAccountPassword.setVisible(true);
					txtWebAccountPassword.setText(pwd);
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
				if (txtWebAccountPassword.isVisible())
					SystemClipboard.copy(txtWebAccountPassword.getText());
				else
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
				CreditCard tmp = new CreditCard();
				listCreditCards.add(tmp);
				currentCard = tmp;
				listViewCreditCard.getSelectionModel().select(listCreditCards.indexOf(currentCard));
				doFillTextFieldsCreditCard();
				paneCreditCardEditDelete.setVisible(false);
				paneCreditCardSaveCancelEdit.setVisible(true);
				doSetTextFieldsCreditCardEditable(true);
				db.addCreditCard(currentCard);
				paneCreditCardDetails.setVisible(true);
				paneCreditCardList.setDisable(true);
			} else if (event.getSource().equals(btnCreditCardCancelEdit)) // on cancel editing
			{
				doFillTextFieldsCreditCard();
				doSetTextFieldsCreditCardEditable(false);
				paneCreditCardSaveCancelEdit.setVisible(false);
				paneCreditCardEditDelete.setVisible(true);
				paneCreditCardList.setDisable(false);
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
				paneCreditCardEditDelete.setVisible(false);
				paneCreditCardList.setDisable(true);
			} else if (event.getSource().equals(btnCreditCardSaveEdit)) // on save after editing
			{
				String nm;
				if (txtCreditCardNumber.isVisible())
					nm = txtCreditCardNumber.getText();
				else
					nm = pwdCreditCardNumber.getText();
				String cvv;
				if (txtCreditCardCVV.isVisible())
					cvv = txtCreditCardCVV.getText();
				else
					cvv = pwdCreditCardCVV.getText();
				CreditCard tmp = currentCard;
				try
				{
					tmp = new CreditCard(txtCreditCardName.getText(), nm, txtCreditCardOwner.getText(),
							CreditCard.getExpireDateOfString(txtCreditCardExpireDate.getText()),
							cmbxCreditCardProvider.getValue(), txtCreditCardAdditionalInformation.getText(),
							txtCreditCardBankName.getText(), Integer.valueOf(cvv));
				} catch (Exception ex)
				{
					doFillTextFieldsCreditCard();
					// btnCreditCardCancelEdit.fire();
					throw ex;
				}
				currentCard.setAdditionalInformation(tmp.getAdditionalInformation());
				currentCard.setBankName(tmp.getBankName());
				currentCard.setCardName(tmp.getCardName());

				currentCard.setExpireDateOfString(tmp.getExpireDateAsString());
				currentCard.setOwnerName(tmp.getOwnerName());
				currentCard.setProvider(tmp.getProvider());

				db.updateCreditCard(currentCard);
				doFillTextFieldsCreditCard();
				doSetTextFieldsCreditCardEditable(false);
				paneCreditCardEditDelete.setVisible(true);
				paneCreditCardSaveCancelEdit.setVisible(false);
				imgCreditCardThumbnail.setImage(currentCard.getThumbnail());
				paneCreditCardList.setDisable(false);
				listViewCreditCard.refresh();
			} else if (event.getSource().equals(btnCreditCardShowCVV)) // on show/hide cvv
			{
				if (txtCreditCardCVV.isVisible())
				{
					String pwd = txtCreditCardCVV.getText();
					pwdCreditCardCVV.setVisible(true);
					txtCreditCardCVV.setVisible(false);
					pwdCreditCardCVV.setText(pwd);
					imgCreditCardShowHideCVV.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/eye-solid.png")),
							null));
				} else
				{
					String pwd = pwdCreditCardCVV.getText();
					pwdCreditCardCVV.setVisible(false);
					txtCreditCardCVV.setVisible(true);
					txtCreditCardCVV.setText(pwd);
					imgCreditCardShowHideCVV.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(
									getClass().getResourceAsStream("/pkgMain/ressources/images/eye-slash-solid.png")),
							null));
				}
			} else if (event.getSource().equals(btnCreditCardShowNuber)) // on show/hide number
			{
				if (txtCreditCardNumber.isVisible())
				{
					String pwd = txtCreditCardNumber.getText();
					pwdCreditCardNumber.setVisible(true);
					txtCreditCardNumber.setVisible(false);
					pwdCreditCardNumber.setText(pwd);
					imgCreditCardShowHideNumber.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/eye-solid.png")),
							null));
				} else
				{
					String pwd = pwdCreditCardNumber.getText();
					pwdCreditCardNumber.setVisible(false);
					txtCreditCardNumber.setVisible(true);
					txtCreditCardNumber.setText(pwd);
					imgCreditCardShowHideNumber.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(
									getClass().getResourceAsStream("/pkgMain/ressources/images/eye-slash-solid.png")),
							null));
				}
			}
			// COPY BUTTONS DOWN HERE
			else if (event.getSource().equals(btnCreditCardCopyAdditionalInformation))
			{
				SystemClipboard.copy(txtCreditCardAdditionalInformation.getText());
			} else if (event.getSource().equals(btnCreditCardCopyBankName))
			{
				SystemClipboard.copy(txtCreditCardBankName.getText());
			} else if (event.getSource().equals(btnCreditCardCopyCVV))
			{
				if (txtCreditCardCVV.isVisible())
					SystemClipboard.copy(txtCreditCardCVV.getText());
				else
					SystemClipboard.copy(pwdCreditCardCVV.getText());
			} else if (event.getSource().equals(btnCreditCardCopyExpireDate))
			{
				SystemClipboard.copy(txtCreditCardExpireDate.getText());
			} else if (event.getSource().equals(btnCreditCardCopyName))
			{
				SystemClipboard.copy(txtCreditCardName.getText());
			} else if (event.getSource().equals(btnCreditCardCopyNumber))
			{
				if (txtCreditCardNumber.isVisible())
					SystemClipboard.copy(txtCreditCardNumber.getText());
				else
					SystemClipboard.copy(pwdCreditCardNumber.getText());
			} else if (event.getSource().equals(btnCreditCardCopyOwner))
			{
				SystemClipboard.copy(txtCreditCardOwner.getText());
			} else if (event.getSource().equals(btnCreditCardCopyProvider))
			{
				SystemClipboard.copy(cmbxCreditCardProvider.getValue().toString());
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

	// -------------------------------------------------------------
	// ------------------- Passport things -------------------------
	// -------------------------------------------------------------

	@FXML
	void onSelectButtonPassport(ActionEvent event)
	{
		try
		{
			if (event.getSource().equals(btnPassportAdd)) // on add passport
			{
				Passport tmp = new Passport();
				listPassports.add(tmp);
				currentPass = tmp;
				listViewPassport.getSelectionModel().select(listPassports.indexOf(currentPass));
				doFillTextFieldsPassport();
				panePassportEditDelete.setVisible(false);
				panePassportSaveCancelEdit.setVisible(true);
				doSetTextFieldsPassportEditable(true);
				db.addPassport(currentPass);
				panePassportsList.setDisable(true);
				panePassportDetails.setVisible(true);
			} else if (event.getSource().equals(btnPassportDelete)) // on delete passport
			{
				if (doShowDeleteDialogPassport())
				{
					doDeletePassport();
				}
			} else if (event.getSource().equals(btnPassportCancelEdit)) // on cancel while editing passport
			{
				doFillTextFieldsPassport();
				doSetTextFieldsPassportEditable(false);
				panePassportSaveCancelEdit.setVisible(false);
				panePassportEditDelete.setVisible(true);
				panePassportsList.setDisable(false);
			} else if (event.getSource().equals(btnPassportEdit)) // on edit passport
			{
				doSetTextFieldsPassportEditable(true);
				panePassportSaveCancelEdit.setVisible(true);
				panePassportEditDelete.setVisible(false);
				panePassportsList.setDisable(true);
			} else if (event.getSource().equals(btnPassportSaveEdit)) // on save after edit passport
			{
				String nm;
				if (txtPassportNumber.isVisible())
					nm = txtPassportNumber.getText();
				else
					nm = pwdPassportNumber.getText();

				Passport tmp = null;
				try
				{
					tmp = new Passport(txtPassportGivenNames.getText(), txtPassportSurname.getText(),
							txtPassportNationality.getText(),
							DateUtils.getLocalDateOfString(txtPassportDateOfBirth.getText()),
							txtPassportPlaceOfBirth.getText(),
							DateUtils.getLocalDateOfString(txtPassportDateOfIssue.getText()), DateUtils.getLocalDateOfString(txtPassportExpirationDate.getText()),cmbxPassportSex.getValue(),
							txtPassportAuthority.getText(),nm,txtPassportAdditionalInformation.getText());
				} catch (Exception ex)
				{
					doFillTextFieldsPassport();
					// btnCreditCardCancelEdit.fire();
					throw ex;
				}
				currentPass.setAdditionalInformation(tmp.getAdditionalInformation());
				currentPass.setAuthority(tmp.getAuthority());
				currentPass.setDateOfBirth(tmp.getDateOfBirth());
				currentPass.setDateOfIssue(tmp.getDateOfIssue());
				currentPass.setExirationDate(tmp.getExirationDate());
				currentPass.setGivenNames(tmp.getGivenNames());
				currentPass.setNationality(tmp.getNationality());
				currentPass.setNumber(tmp.getNumber());
				currentPass.setPlaceOfBirth(tmp.getPlaceOfBirth());
				currentPass.setSex(tmp.getSex());
				currentPass.setSurName(tmp.getSurName());

				db.updatePassport(currentPass);
				doFillTextFieldsPassport();
				doSetTextFieldsPassportEditable(false);
				panePassportEditDelete.setVisible(true);
				panePassportSaveCancelEdit.setVisible(false);
				imgPassportThumbnail.setImage(currentPass.getThumbnail());
				panePassportsList.setDisable(false); 
				listViewPassport.refresh();
				
			} else if (event.getSource().equals(btnPassportShowHideNumber)) // on show/hide numner passport
			{
				if (txtPassportNumber.isVisible())
				{
					String pwd = txtPassportNumber.getText();
					pwdPassportNumber.setVisible(true);
					txtPassportNumber.setVisible(false);
					pwdPassportNumber.setText(pwd);
					imgPassportShowHideNumber.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/eye-solid.png")),
							null));
				} else
				{
					String pwd = pwdPassportNumber.getText();
					pwdPassportNumber.setVisible(false);
					txtPassportNumber.setVisible(true);
					txtPassportNumber.setText(pwd);
					imgPassportShowHideNumber.setImage(SwingFXUtils.toFXImage(
							ImageIO.read(
									getClass().getResourceAsStream("/pkgMain/ressources/images/eye-slash-solid.png")),
							null));
				}
			}

			// COPY THINGS DOWN HERE
			else if (event.getSource().equals(btnPassportCopyAdditionalInformation))
			{
				SystemClipboard.copy(txtPassportAdditionalInformation.getText());
			} else if (event.getSource().equals(btnPassportCopyAuthority))
			{
				SystemClipboard.copy(txtPassportAuthority.getText());
			} else if (event.getSource().equals(btnPassportCopyDateOfBirth))
			{
				SystemClipboard.copy(txtPassportDateOfBirth.getText());
			} else if (event.getSource().equals(btnPassportCopyDateOfIssue))
			{
				SystemClipboard.copy(txtPassportDateOfIssue.getText());
			} else if (event.getSource().equals(btnPassportCopyExpirationDate))
			{
				SystemClipboard.copy(txtPassportExpirationDate.getText());
			} else if (event.getSource().equals(btnPassportCopyGivenNames))
			{
				SystemClipboard.copy(txtPassportGivenNames.getText());
			} else if (event.getSource().equals(btnPassportCopyNationality))
			{
				SystemClipboard.copy(txtPassportNationality.getText());
			} else if (event.getSource().equals(btnPassportCopyNumber))
			{
				if (txtPassportNumber.isVisible())
					SystemClipboard.copy(txtPassportNumber.getText());
				else
					SystemClipboard.copy(pwdPassportNumber.getText());
			} else if (event.getSource().equals(btnPassportCopySex))
			{
				SystemClipboard.copy(cmbxPassportSex.getValue().toString());
			} else if (event.getSource().equals(btnPassportCopySurname))
			{
				SystemClipboard.copy(txtPassportSurname.getText());
			}
			else if(event.getSource().equals(btnPassportCopyPlaceOfBirth)) {
				SystemClipboard.copy(txtPassportPlaceOfBirth.getText());
			}
		} catch (Exception e)
		{
			ExceptionHandler.hanldeUnexpectedException(e);
		}

	}

	@FXML
	void onSelectListViewPassport(MouseEvent event)
	{
		if (event.getSource().equals(listViewPassport))
		{
			if (listViewPassport.getSelectionModel().getSelectedItem() != null)
			{
				panePassportDetails.setVisible(true);
				currentPass = listViewPassport.getSelectionModel().getSelectedItem();
				doFillTextFieldsPassport();
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
		int pos = listWebAccounts.indexOf(currentAccount);
		db.deleteWebAccount(currentAccount);
		listWebAccounts.remove(currentAccount);
		if (pos > 0)
		{
			currentAccount = listWebAccounts.get(pos - 1);
			doFillTextFieldsWebAccount();
		} else if (pos == 0 && listWebAccounts.size() > 0)
		{
			currentAccount = listWebAccounts.get(pos);
			doFillTextFieldsWebAccount();
		}
		currentAccount = null;
		paneWebAccountDetails.setVisible(false);
	}

	private void doSetTextFieldsWebAccountEditable(boolean state)
	{
		txtWebAccountName.setEditable(state);
		txtWebAccountPassword.setEditable(state);
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
		cmbxCreditCardProvider.setDisable(!state);
		txtCreditCardExpireDate.setEditable(state);
		txtCreditCardAdditionalInformation.setEditable(state);
		txtCreditCardNumber.setEditable(state);
		pwdCreditCardNumber.setEditable(state);
		txtCreditCardCVV.setEditable(state);
		pwdCreditCardCVV.setEditable(state);
	}

	private boolean doShowDeleteDialogCreditCard()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete Credit Card " + currentCard.getCardName() + " ?",
				ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		return alert.getResult() == ButtonType.YES;

	}

	private void doDeleteCreditCard()
	{
		int pos = listCreditCards.indexOf(currentCard);
		db.delteCreditCard(currentCard);
		listCreditCards.remove(currentCard);
		if (pos > 0)
		{
			currentCard = listCreditCards.get(pos - 1);
			doFillTextFieldsCreditCard();
		} else if (pos == 0 && listCreditCards.size() > 0)
		{
			currentCard = listCreditCards.get(pos);
			doFillTextFieldsCreditCard();
		}
		currentCard = null;
		paneCreditCardDetails.setVisible(false);
	}

	// -------------------------------------------------------------
	// ------------------- Passport things -------------------------
	// -------------------------------------------------------------

	private void doFillTextFieldsPassport()
	{
		txtPassportAdditionalInformation.setText(currentPass.getAdditionalInformation());
		txtPassportAuthority.setText(currentPass.getAuthority());
		txtPassportDateOfBirth.setText(currentPass.getDateOfBirthAsString());
		txtPassportDateOfIssue.setText(currentPass.getDateOfIssueAsString());
		txtPassportExpirationDate.setText(currentPass.getExirationDateAsString());
		txtPassportGivenNames.setText(currentPass.getGivenNames());
		txtPassportNationality.setText(currentPass.getNationality());
		txtPassportNumber.setText(currentPass.getNumber());
		txtPassportPlaceOfBirth.setText(currentPass.getPlaceOfBirth());
		txtPassportSurname.setText(currentPass.getSurName());
		pwdPassportNumber.setText(currentPass.getNumber());
		cmbxPassportSex.setValue(currentPass.getSex());
	}

	private void doSetTextFieldsPassportEditable(boolean state)
	{
		txtPassportAdditionalInformation.setEditable(state);
		txtPassportAuthority.setEditable(state);
		txtPassportDateOfBirth.setEditable(state);
		txtPassportDateOfIssue.setEditable(state);
		txtPassportExpirationDate.setEditable(state);
		txtPassportGivenNames.setEditable(state);
		txtPassportNationality.setEditable(state);
		txtPassportNumber.setEditable(state);
		txtPassportSurname.setEditable(state);
		txtPassportPlaceOfBirth.setEditable(state);
		pwdPassportNumber.setEditable(state);
		cmbxPassportSex.setDisable(!state);
	}

	private boolean doShowDeleteDialogPassport()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete Passport of " + currentPass.getGivenNames() + " ?",
				ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		return alert.getResult() == ButtonType.YES;

	}

	private void doDeletePassport()
	{
		int pos = listPassports.indexOf(currentPass);
		db.deltePassport(currentPass);
		listPassports.remove(currentPass);
		if (pos > 0)
		{
			currentPass = listPassports.get(pos - 1);
			doFillTextFieldsPassport();
		} else if (pos == 0 && listPassports.size() > 0)
		{
			currentPass = listPassports.get(pos);
			doFillTextFieldsPassport();
		}
		currentPass = null;
		panePassportDetails.setVisible(false);
	}
}
