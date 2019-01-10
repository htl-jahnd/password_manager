package pkgData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.codec.binary.Base64;

import pkgExceptions.InvalidCardException;
import pkgExceptions.InvalidPassportException;
import pkgExceptions.InvalidWebAccountException;
import pkgExceptions.UserException;
import pkgMisc.DateUtils;
import pkgMisc.PasswordUtils;

public class Database implements IDatabase_Controller
{

	// "jdbc:oracle:thin:@192.168.128.152:1521:ora11g";
	// "dbc:oracle:thin:@212.152.179.117:1521:ora11g";
	private static Database instance = null;
	private static Connection conn = null;
	private static boolean isConnectionSet = false;
	private static String connectionString = "192.168.128.152"; // TODO insert proper external ip address
	private static final ArrayList<WebAccount> accounts = new ArrayList<WebAccount>();
	private static final ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
	private static final ArrayList<Passport> passports = new ArrayList<Passport>();
	private static final ArrayList<Identity> identities = new ArrayList<Identity>();
	private static final ArrayList<Note> notes = new ArrayList<Note>();
	private static final String DB_USER = "d4b21";
	private static final String DB_PWD = "d4b";
	private static User currentUser;

	// ------------------------------------------------------------
	// ---------------------- ALL SELECTS -------------------------
	// ------------------------------------------------------------

	public void selectWebAccounts()
			throws SQLException, MalformedURLException, IOException, URISyntaxException, InvalidWebAccountException
	{
		String stmtString = "SELECT name, websiteName, websiteURL, username, password, additionalInformation, id FROM webAccount WHERE programUser LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		accounts.clear();

		while (rs.next())
		{
			accounts.add(new WebAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getInt("id")));
		}
	}

	public void selectCreditCards() throws SQLException, IOException, InvalidCardException
	{

		String stmtString = "SELECT cardName, cardNumber, ownerName, bankName, expireDate, provider, additionalInformation, securityCode, id FROM creditCard WHERE userName LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		creditCards.clear();

		while (rs.next())
		{
			creditCards.add(new CreditCard(rs.getString(1), rs.getString(2), rs.getString(3),
					DateUtils.getYearMonthOfString(rs.getString(5)), ECreditCardsProviders.getProvider(rs.getString(6)),
					rs.getString(7), rs.getString(4), rs.getInt(8), null, rs.getInt("id")));
		}
	}

	@Override
	public void selectPassports() throws SQLException, IOException, InvalidPassportException
	{
		String stmtString = "SELECT firstName, surName, nationality, dateOfBirth, placeOfBirth, dateOfIssue, expirationDate, sex, authority, passportNumber, additionalInformation, id FROM passport WHERE username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		passports.clear();
		while (rs.next())
		{
			passports.add(new Passport(rs.getString("firstname"), rs.getString("surname"), rs.getString("nationality"),
					DateUtils.getLocalDateOfString(rs.getString("dateofbirth")), rs.getString("placeofbirth"),
					DateUtils.getLocalDateOfString(rs.getString("dateOfIssue")),
					DateUtils.getLocalDateOfString(rs.getString("expirationDate")), ESex.valueOf(rs.getString("sex")),
					rs.getString("authority"), rs.getString("passportNumber"), rs.getString("additionalInformation"),
					rs.getInt("id")));
		}

	}

	@Override
	public void selectIdentities() throws SQLException, IOException
	{
		String select = "Select salutation, firstname, surname, streetadress, cityadress, zipadress,stateadress,birthdate,country,additionalInformation,nr from identity where username = ?";
		PreparedStatement stmt = conn.prepareStatement(select);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		identities.clear();
		while (rs.next())
		{
			identities.add(new Identity(ESalutation.getSalutationString(rs.getString("salutation")),
					rs.getString("firstname"), rs.getString("surname"), rs.getString("streetadress"),
					rs.getString("cityadress"), rs.getInt("zipadress"), rs.getString("stateadress"),
					DateUtils.getLocalDateOfString(rs.getString("birthdate")), rs.getString("country"),
					rs.getString("additionalInformation"), rs.getInt("nr")));
		}

	}

	@Override
	public void selectNotes() throws SQLException
	{
		String stmtString = "Select title, text, id from note where username LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		notes.clear();
		while (rs.next())
		{
			notes.add(new Note(rs.getString("title"), rs.getString("text"), rs.getInt("id")));
		}
	}

	// ------------------------------------------------------------
	// ---------------- WEB ACCOUNT OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addWebAccount(WebAccount accountToAdd) throws SQLException
	{
		String stmtString = "INSERT INTO webAccount VALUES(?,?,?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, accountToAdd.getName());
		stmt.setString(2, accountToAdd.getWebsiteName());
		stmt.setString(3, accountToAdd.getWebsiteURL());
		stmt.setString(4, accountToAdd.getUsername());
		stmt.setString(5, accountToAdd.getPassword());
		stmt.setString(6, currentUser.getUsername());
		stmt.setString(7, accountToAdd.getAdditionalInformation());
		stmt.setInt(8, accountToAdd.getId());
		stmt.executeUpdate();
	}

	public static int getNextWebAccountId() throws SQLException
	{
		int ret = 0;
		String sel = "Select seqWebAccountId.NEXTVAL from dual ";
		PreparedStatement stmt = conn.prepareStatement(sel);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			ret = rs.getInt(1);
		}
		return ret;
	}

	public static int getNextCreditCardId() throws SQLException
	{
		int ret = 0;
		String sel = "Select seqCreditCardId.NEXTVAL from dual";
		PreparedStatement stmt = conn.prepareStatement(sel);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			ret = rs.getInt(1);
		}
		return ret;
	}

	public static int getNextPassportId() throws SQLException
	{
		int ret = 0;
		String sel = "Select seqPassportId.NEXTVAL from dual";
		PreparedStatement stmt = conn.prepareStatement(sel);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			ret = rs.getInt(1);
		}
		return ret;
	}

	public static int getNextIdentityId() throws SQLException
	{
		int ret = 0;
		String sel = "Select seqIdentityId.NEXTVAL from dual";
		PreparedStatement stmt = conn.prepareStatement(sel);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			ret = rs.getInt(1);
		}
		return ret;
	}

	public static int getNextNoteId() throws SQLException
	{
		int ret = 0;
		String sel = "Select seqNoteId.NEXTVAL from dual";
		PreparedStatement stmt = conn.prepareStatement(sel);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			ret = rs.getInt(1);
		}
		return ret;
	}

	public void deleteWebAccount(WebAccount accountToDelete) throws Exception
	{
		String stmtString = "DELETE FROM webAccount WHERE Id = ? and username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setInt(1, accountToDelete.getId());
		stmt.setString(2, accountToDelete.getUsername());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such Account with username " + accountToDelete.getUsername() + " on website + "
					+ accountToDelete.getWebsiteName());
		}
	}

	public void updateWebAccount(WebAccount accountToUpdate) throws Exception
	{
		String stmtString = "UPDATE webAccount SET name = ?, websiteName = ?, websiteURL = ?, username = ?, password = ?, additionalInformation = ? WHERE id = ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, accountToUpdate.getName());
		stmt.setString(2, accountToUpdate.getWebsiteName());
		stmt.setString(3, accountToUpdate.getWebsiteURL());
		stmt.setString(4, accountToUpdate.getUsername());
		stmt.setString(5, accountToUpdate.getPassword());
		stmt.setString(6, accountToUpdate.getAdditionalInformation());
		stmt.setInt(7, accountToUpdate.getId());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such Account with username " + accountToUpdate.getUsername() + " to Update.");
		}
	}

	// ------------------------------------------------------------
	// ---------------- CREDIT CARD OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addCreditCard(CreditCard cardToAdd) throws SQLException
	{
		String stmtString = "INSERT INTO creditCard VALUES(?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, cardToAdd.getCardName());
		stmt.setString(2, cardToAdd.getCardNumber());
		stmt.setString(3, cardToAdd.getOwnerName());
		stmt.setString(4, cardToAdd.getBankName());
		stmt.setString(5, cardToAdd.getExpireDateAsString());
		stmt.setString(6, ECreditCardsProviders.getProviderString(cardToAdd.getProvider()));
		stmt.setString(7, cardToAdd.getAdditionalInformation());
		stmt.setInt(8, cardToAdd.getSecurityCode());
		stmt.setString(9, currentUser.getUsername());
		stmt.setInt(10, cardToAdd.getId());
		stmt.executeUpdate();
	}

	public void delteCreditCard(CreditCard cardToDelete) throws Exception
	{
		String stmtString = "DELETE FROM creditCard WHERE id LIKE ? and username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setInt(1, cardToDelete.getId());
		stmt.setString(2, currentUser.getUsername());
		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such CreditCard with cardNumber " + cardToDelete.getCardNumber() + " to Delete.");
		}
	}

	public void updateCreditCard(CreditCard cardToUpdate) throws SQLException
	{
		String stmtString = "UPDATE creditCard SET cardName = ?, cardNumber = ?, ownerName = ?, bankName = ?, expireDate = ?, provider = ?, additionalInformation = ?, securityCode = ? WHERE id LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, cardToUpdate.getCardName());
		stmt.setString(2, cardToUpdate.getCardNumber());
		stmt.setString(3, cardToUpdate.getOwnerName());
		stmt.setString(4, cardToUpdate.getBankName());
		stmt.setString(5, cardToUpdate.getExpireDateAsString());
		stmt.setString(6, ECreditCardsProviders.getProviderString(cardToUpdate.getProvider()));
		stmt.setString(7, cardToUpdate.getAdditionalInformation());
		stmt.setInt(8, cardToUpdate.getSecurityCode());
		stmt.setInt(9, cardToUpdate.getId());
	}

	// ------------------------------------------------------------
	// ----------------- PASSPORT OPERATIONS ----------------------
	// ------------------------------------------------------------

	public void addPassport(Passport currentPass) throws SQLException
	{
		String stmtString = "INSERT INTO passport VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentPass.getGivenNames());
		stmt.setString(2, currentPass.getSurName());
		stmt.setString(3, currentPass.getNationality());
		stmt.setString(4, currentPass.getDateOfBirthAsString());
		stmt.setString(5, currentPass.getPlaceOfBirth());
		stmt.setString(6, currentPass.getDateOfIssueAsString());
		stmt.setString(7, currentPass.getExirationDateAsString());
		stmt.setString(8, ESex.getSexString(currentPass.getSex()));
		stmt.setString(9, currentPass.getAuthority());
		stmt.setString(10, currentPass.getNumber());
		stmt.setString(11, currentPass.getAdditionalInformation());
		stmt.setString(12, currentUser.getUsername());
		stmt.setInt(13, currentPass.getId());

		stmt.executeUpdate();
	}

	public void deltePassport(Passport currentPass) throws Exception
	{
		String stmtString = "DELETE FROM passport WHERE id =?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setInt(1, currentPass.getId());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such passport with firstname " + currentPass.getGivenNames() + ", surName "
					+ currentPass.getSurName() + " and date of Issue " + currentPass.getDateOfIssueAsString()
					+ " found.");
		}

	}

	public void updatePassport(Passport currentPass) throws Exception
	{
		String stmtString = "UPDATE passport SET firstName = ?, surName = ?, nationality = ?, dateOfBirth = ?, placeOfBirth = ?, dateOfIssue = ?, expirationDate = ?, sex = ?, authority = ?, passportNumber = ?, additionalInformation = ? WHERE id =?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setString(1, currentPass.getGivenNames());
		stmt.setString(2, currentPass.getSurName());
		stmt.setString(3, currentPass.getNationality());
		stmt.setString(4, currentPass.getDateOfBirthAsString());
		stmt.setString(5, currentPass.getPlaceOfBirth());
		stmt.setString(6, currentPass.getDateOfIssueAsString());
		stmt.setString(7, currentPass.getExirationDateAsString());
		stmt.setString(8, ESex.getSexString(currentPass.getSex()));
		stmt.setString(9, currentPass.getAuthority());
		stmt.setString(10, currentPass.getNumber());
		stmt.setString(11, currentPass.getAdditionalInformation());
		stmt.setInt(12, currentPass.getId());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("no row updated");
		}
	}

	// ------------------------------------------------------------
	// ------------------- USER OPERATIONS ------------------------
	// ------------------------------------------------------------

	public void addUser(User userToAdd, char[] password) throws NoSuchAlgorithmException, SQLException
	{
		String salt = PasswordUtils.generateSaltAsString(PasswordUtils.SALT_LENGTH);
		String hashedPwd = PasswordUtils.getSHA512Hash(new String(password), salt);

		String stmtString = "INSERT INTO loggedInUser VALUES(?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setString(1, userToAdd.getUsername());
		stmt.setString(2, hashedPwd);
		stmt.setString(3, salt);
		stmt.executeUpdate();

		currentUser = userToAdd;
	}

	public void checkUserCredentials(User userToCheck, char[] password)
			throws NoSuchAlgorithmException, SQLException, UserException
	{
		// check if user & password exist
		String stmtString = "SELECT username, password FROM loggedInUser WHERE username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, userToCheck.getUsername());
		ResultSet rs = stmt.executeQuery();
		if (!rs.next())
		{
			throw new UserException("Invalid Username or Password");
		}

		// select users salt
		String stmtString2 = "SELECT salt FROM loggedInUser WHERE username LIKE ?";
		String salt = null;

		PreparedStatement stmt2 = conn.prepareStatement(stmtString2);
		ResultSet rs2 = stmt2.executeQuery();
		if (rs2.next())
		{
			salt = rs.getString(1);
		}

		// String salt = PasswordUtils.generateSaltAsString(PasswordUtils.SALT_LENGTH);
		// String hashedPwd = PasswordUtils.getSHA512Hash(new String(password), salt);
	}

	// ------------------------------------------------------------
	// ----------------------- MISC THINGS ------------------------
	// ------------------------------------------------------------

	public static Database newInstance() throws Exception
	{
		if (instance == null)
		{
			instance = new Database();
			createConnection();
		}
		return instance;
	}

	private static void createConnection() throws Exception
	{
		if (conn == null)
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		}
		StringBuilder bu = new StringBuilder("jdbc:oracle:thin:@").append(connectionString).append(":1521:ora11g");
		connectionString = bu.toString();
		DriverManager.setLoginTimeout(3);
		conn = DriverManager.getConnection(connectionString, DB_USER, DB_PWD);
		// Connects with database user "d4b21"
		conn.setAutoCommit(true);
		isConnectionSet = true;

	}

	private Database()
	{
	}

	public void login(User usr, char[] pwd) throws NoSuchAlgorithmException, UserException, SQLException
	{
		String stmtString = "SELECT username, pwd FROM loggedInUser WHERE username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, usr.getUsername());
		ResultSet rs = stmt.executeQuery();
		if (!rs.next())
		{
			throw new UserException("Invalid Username or Password");
		}

		String salt = null;
		String stmtString2 = "SELECT salt FROM loggedInUser WHERE username LIKE ?";
		PreparedStatement stmt2 = conn.prepareStatement(stmtString2);
		stmt2.setString(1, usr.getUsername());
		ResultSet rs2 = stmt2.executeQuery();
		if (rs2.next())
		{
			salt = rs.getString(1);
		}
		String hashed = PasswordUtils.getSHA512Hash(new String(pwd), salt);

		usr.setPassword(hashed);
		usr.setSalt(salt);
		currentUser = usr;
	}

	public void createNewUser(User user, char[] pwd) throws NoSuchAlgorithmException, SQLException, UserException
	{
		String stmtString = "SELECT username FROM loggedInUser WHERE username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, user.getUsername());
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
		{
			throw new UserException("Username is already used");
		}

		String salt = new Base64().encodeToString(PasswordUtils.generateSalt(PasswordUtils.SALT_LENGTH));
		String hashedPassword = PasswordUtils.getSHA512Hash(new String(pwd), salt);

		String stmtString2 = "INSERT INTO loggedInUser VALUES(?,?,?)";
		PreparedStatement stmt2 = conn.prepareStatement(stmtString2);
		stmt2.setString(1, user.getUsername());
		stmt2.setString(2, hashedPassword);
		stmt2.setString(3, salt);
		stmt2.executeUpdate();

		currentUser = new User(user.getUsername());
		currentUser.setPassword(hashedPassword);
		currentUser.setSalt(salt);

	}

	@Override
	public void addNote(Note NoteToAdd) throws SQLException
	{
		String stmtString = "INSERT INTO note VALUES(?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, NoteToAdd.getText());
		stmt.setString(2, currentUser.getUsername());
		stmt.setString(3, NoteToAdd.getTitle());
		stmt.setInt(4, NoteToAdd.getId());

		stmt.executeUpdate();

	}

	@Override
	public void deleteNote(Note noteToDelete) throws Exception
	{
		String stmtString = "DELETE FROM note WHERE id =? AND username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setInt(1, noteToDelete.getId());
		stmt.setString(2, currentUser.getUsername());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception(
					"No such passport with title " + noteToDelete.getTitle() + ", text " + noteToDelete.getText());
		}

	}

	@Override
	public void updateNote(Note noteToDelete) throws SQLException
	{
		String stmtString = "Update note set title =?, text=? where id=? AND username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setString(1, noteToDelete.getTitle());
		stmt.setString(2, noteToDelete.getText());
		stmt.setInt(3, noteToDelete.getId());
		stmt.setString(4, currentUser.getUsername());

		int cnt = stmt.executeUpdate();

	}

	@Override
	public void addIdentity(Identity idToAdd) throws SQLException
	{
		String stmtString = "Insert into identity values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setString(1, currentUser.getUsername());
		stmt.setString(2, ESalutation.getSalutationString(idToAdd.getSalutation()));
		stmt.setString(3, idToAdd.getFirstName());
		stmt.setString(4, idToAdd.getSurName());
		stmt.setString(5, idToAdd.getStreetAddress());
		stmt.setString(6, idToAdd.getCityAddress());
		stmt.setInt(7, Integer.valueOf(idToAdd.getZipAddress()));
		stmt.setString(8, idToAdd.getStateAddress());
		stmt.setString(9, idToAdd.getDateOfBirthAsString());
		stmt.setString(10, idToAdd.getCountry());
		stmt.setString(11, idToAdd.getAdditionalInformation());
		stmt.setInt(12, idToAdd.getNumber());
		stmt.executeQuery();
	}

	@Override
	public void deleteIdentity(Identity idToDelte) throws Exception
	{
		String stmtString = "DELETE FROM identity WHERE nr LIKE ? AND username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setInt(1, idToDelte.getNumber());
		stmt.setString(2, currentUser.getUsername());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such identity with number " + idToDelte.getNumber());
		}

	}

	@Override
	public void updateIdentity(Identity idToUpdate) throws Exception
	{
		String stmtString = "Update identity SET salutation = ?, firstname = ?, surname = ?, streetAdress =?, "
				+ " cityAdress =?, zipAdress=?, stateAdress =?, birthDate=?, country =?, additionalInformation=? WHERE username LIKE ? AND nr = ?";
		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setString(1, ESalutation.getSalutationString(idToUpdate.getSalutation()));
		stmt.setString(2, idToUpdate.getFirstName());
		stmt.setString(3, idToUpdate.getSurName());
		stmt.setString(4, idToUpdate.getStreetAddress());
		stmt.setString(5, idToUpdate.getCityAddress());
		stmt.setInt(6, Integer.valueOf(idToUpdate.getZipAddress()));
		stmt.setString(7, idToUpdate.getStateAddress());
		stmt.setString(8, idToUpdate.getDateOfBirthAsString());
		stmt.setString(9, idToUpdate.getCountry());
		stmt.setString(10, idToUpdate.getAdditionalInformation());
		stmt.setString(11, currentUser.getUsername());
		stmt.setInt(12, idToUpdate.getNumber());
		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("no row updated");
		}
	}

	// ------------------------------------------------------------
	// -------------------- GETTER AND SETTER ---------------------
	// ------------------------------------------------------------

	public static ArrayList<WebAccount> getListAccounts()
	{
		return accounts;
	}

	public static ArrayList<CreditCard> getListCreditcards()
	{
		return creditCards;
	}

	@Override
	public Collection<WebAccount> getAccounts()
	{
		return accounts;
	}

	@Override
	public Collection<CreditCard> getCreditCards()
	{
		return creditCards;
	}

	public static boolean isConnectionSet()
	{
		return isConnectionSet;
	}

	@Override
	public Collection<Passport> getPassports()
	{
		return passports;
	}

	@Override
	public Collection<Identity> getIdentities()
	{
		return identities;
	}

	@Override
	public Collection<Note> getNotes()
	{
		return notes;
	}

	public User getUser()
	{
		return currentUser;
	}

	public void deleteUser() throws SQLException
	{
		String stmtString = "DELETE from loggedInUser where username LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		stmt.executeQuery();
	}
}