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
	private static final String DB_USER = "d4b12";
	private static final String DB_PWD = "d4b";
	private static User currentUser;

	// ------------------------------------------------------------
	// ---------------------- ALL SELECTS -------------------------
	// ------------------------------------------------------------

	public void selectWebAccounts()
			throws SQLException, MalformedURLException, IOException, URISyntaxException, InvalidWebAccountException
	{
		String stmtString = "SELECT name, websiteName, websiteURL, username, password, additionalInformation FROM webAccount WHERE programUser LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		accounts.clear();

		while (rs.next())
		{
			accounts.add(new WebAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));
		}
	}

	public void selectCreditCards() throws SQLException, IOException, InvalidCardException
	{
		String stmtString = "SELECT cardName, cardNumber, ownerName, bankName, expireDate, provider, additionalInformation, securityCode FROM creditCard WHERE userName LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		creditCards.clear();

		while (rs.next())
		{
			creditCards.add(new CreditCard(rs.getString(1), rs.getString(2), rs.getString(3),
					DateUtils.getYearMonthOfString(rs.getString(5)), ECreditCardsProviders.getProvider(rs.getString(6)),
					rs.getString(7), rs.getString(4), rs.getInt(8)));
		}
	}

	// ------------------------------------------------------------
	// ---------------- WEB ACCOUNT OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addWebAccount(WebAccount accountToAdd) throws SQLException
	{
		String stmtString = "INSERT INTO webAccount VALUES(?,?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, accountToAdd.getName());
		stmt.setString(2, accountToAdd.getWebsiteName());
		stmt.setString(3, accountToAdd.getWebsiteURL());
		stmt.setString(4, accountToAdd.getUsername());
		stmt.setString(5, accountToAdd.getPassword());
		stmt.setString(6, currentUser.getUsername());
		stmt.setString(7, accountToAdd.getAdditionalInformation());
		stmt.executeUpdate();
	}

	public void deleteWebAccount(WebAccount accountToDelete) throws Exception
	{
		String stmtString = "DELETE FROM webAccount WHERE websiteName LIKE ? and username LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, accountToDelete.getWebsiteName());
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
		String stmtString = "UPDATE webAccount SET name = ?, websiteName = ?, websiteURL = ?, username = ?, password = ?, additionalInformation = ? WHERE programUser LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, accountToUpdate.getName());
		stmt.setString(2, accountToUpdate.getWebsiteName());
		stmt.setString(3, accountToUpdate.getWebsiteURL());
		stmt.setString(4, accountToUpdate.getUsername());
		stmt.setString(5, accountToUpdate.getPassword());
		stmt.setString(6, accountToUpdate.getAdditionalInformation());
		stmt.setString(7, currentUser.getUsername());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such Account with username " + accountToUpdate.getUsername() + " to update.");
		}
	}

	// ------------------------------------------------------------
	// ---------------- CREDIT CARD OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addCreditCard(CreditCard cardToAdd) throws SQLException
	{
		String stmtString = "INSERT INTO creditCard VALUES(?,?,?,?,?,?,?,?,?)";

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
		stmt.executeUpdate();
	}

	public void delteCreditCard(CreditCard cardToDelete) throws Exception
	{
		String stmtString = "DELETE FROM creditCard WHERE cardNumber LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, cardToDelete.getCardNumber());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such CreditCard with cardNumber " + cardToDelete.getCardNumber() + " to delete.");
		}
	}

	/*
	 * public void updateCreditCard(CreditCard cardToUpdate) throws SQLException {
	 * String stmtString =
	 * "UPDATE creditCard SET cardName = ?, cardNumber = ?, ownerName = ?, bankName = ?, expireDate = ?, provider = ?, additionalInformation = ?, securityCode = ? WHERE "
	 * ;
	 * 
	 * PreparedStatement stmt = conn.prepareStatement(stmtString); stmt.setString(1,
	 * cardToUpdate.getCardName()); stmt.setString(2, cardToUpdate.getCardNumber());
	 * stmt.setString(3, cardToUpdate.getOwnerName()); stmt.setString(4,
	 * cardToUpdate.getBankName()); stmt.setString(5,
	 * cardToUpdate.getExpireDateAsString()); stmt.setString(6,
	 * ECreditCardsProviders.getProviderString(cardToUpdate.getProvider()));
	 * stmt.setString(7, cardToUpdate.getAdditionalInformation()); stmt.setInt(8,
	 * cardToUpdate.getSecurityCode()); stmt.setString(9,
	 * cardToUpdate.getCardNumber()); }
	 */ // Funktioniert NOCH nicht, will Sequenz machen die ID vergibt, diese dann
		// primary key machen.

	// ------------------------------------------------------------
	// ----------------- PASSPORT OPERATIONS ----------------------
	// ------------------------------------------------------------

	public void addPassport(Passport currentPass) throws SQLException
	{
		String stmtString = "INSERT INTO passport VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

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

		stmt.executeUpdate();
	}

	public void deltePassport(Passport currentPass) throws Exception
	{
		String stmtString = "DELETE FROM passport WHERE firstName LIKE ? AND surName LIKE ? AND dateOfIssue LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(stmtString);

		stmt.setString(1, currentPass.getGivenNames());
		stmt.setString(2, currentPass.getSurName());
		stmt.setString(3, currentPass.getDateOfIssueAsString());

		int cnt = stmt.executeUpdate();

		if (cnt < 1)
		{
			throw new Exception("No such passport with firstname " + currentPass.getGivenNames() + ", surName "
					+ currentPass.getSurName() + " and date of Issue " + currentPass.getDateOfIssueAsString()
					+ " found.");
		}

	}

	/*
	 * public void updatePassport(Passport currentPass) { String stmtString =
	 * "UPDATE passport SET firstName = ?, surName = ?, nationality = ?, dateOfBirth = ?, placeOfBirth = ?, dateOfIssue = ?, expirationDate = ?, sex = ?, authority = ?, passportNumber = ?, additionalInformation = ?"
	 * 
	 * }
	 */
	// Funktioniert ebenfalls noch nicht

	// ------------------------------------------------------------
	// ------------------- USER OPERATIONS ------------------------
	// ------------------------------------------------------------

	public void addUser(User userToAdd, char[] password) throws NoSuchAlgorithmException
	{
		String salt = PasswordUtils.generateSaltAsString(PasswordUtils.SALT_LENGTH);
		String hashedPwd = PasswordUtils.getSHA512Hash(new String(password), salt);
		// TODO insert into db

		currentUser = userToAdd;
	}

	public void checkUserCredentials(User userToCheck, char[] password) throws NoSuchAlgorithmException
	{

		// TODO check if user & password exist
		// TODO select users salt

		String salt = PasswordUtils.generateSaltAsString(PasswordUtils.SALT_LENGTH);
		String hashedPwd = PasswordUtils.getSHA512Hash(new String(password), salt);
		// TODO check if pwd is correct
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
			// TODO initialize all arraylists
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
		// DriverManager.setLoginTimeout(3);
		// conn = DriverManager.getConnection(connectionString, DB_USER, DB_PWD); //
		// Connects with database user "d4b12"
		// conn.setAutoCommit(true);
		isConnectionSet = true;

	}

	private Database()
	{
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

	public static Collection<WebAccount> getAccounts()
	{
		return accounts;
	}

	public static Collection<CreditCard> getCreditCards()
	{
		return creditCards;
	}

	public static boolean isConnectionSet()
	{
		return isConnectionSet;
	}

	public void login(User usr, char[] pwd) throws NoSuchAlgorithmException
	{
		// TODO check if user exists -> if no throw new exception
		// TODO select salt from db
		String salt = "123"; // TODO JUST FOR TESTING, ELSE SALT = NULL!!!!
		String hashed = PasswordUtils.getSHA512Hash(new String(pwd), salt);
		// TODO check if user and pwd exist, throw an UserException if not --> look
		// below
		// throw new UserException("Invalid username or password");
		usr.setPassword(hashed);
		usr.setSalt(salt);
		currentUser = usr;
	}

	public void createNewUser(User user, char[] pwd) throws NoSuchAlgorithmException
	{
		// TODO check if user exists -> if yes throw new UserException --> look below
		// throw new UserException("Username is already used");
		String salt = new Base64().encodeToString(PasswordUtils.generateSalt(PasswordUtils.SALT_LENGTH));
		String hashedPassword = PasswordUtils.getSHA512Hash(new String(pwd), salt);

		// TODO insert new user with name, salt & hash in db

		currentUser = new User(user.getUsername());
		currentUser.setPassword(hashedPassword);
		currentUser.setSalt(salt);

	}

	@Override
	public void selectPassports()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectIdentities()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCreditCard(CreditCard cardToUpdate) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassport(Passport currentPass) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNote(Note NoteToAdd) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNote(Note noteToDelete) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNote(Note noteToDelete) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addIdentity(Identity idToAdd) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIdentity(Identity idToDelte) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIdentity(Identity idToUpdate) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

}
