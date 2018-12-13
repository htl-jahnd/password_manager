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

import pkgExceptions.InvalidWebAccountException;
import pkgExceptions.UserException;
import pkgMisc.PasswordUtils;

public class Database
{
	// "jdbc:oracle:thin:@192.168.128.152:1521:ora11g";
	// "dbc:oracle:thin:@212.152.179.117:1521:ora11g";
	private static Database instance = null;
	private static Connection conn = null;
	private static boolean isConnectionSet = false;
	private static String connectionString = "192.168.128.152"; // TODO insert proper external ip address
	private static final ArrayList<WebAccount> accounts = new ArrayList<WebAccount>();
	private static final ArrayList<ProgramLicense> licenses = new ArrayList<ProgramLicense>();
	private static final ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
	private static final String DB_USER = "d4b12";
	private static final String DB_PWD = "d4b";
	private static User currentUser;

	// ------------------------------------------------------------
	// ---------------------- ALL SELECTS -------------------------
	// ------------------------------------------------------------

	public void selectWebAccounts() throws SQLException, MalformedURLException, IOException, URISyntaxException, InvalidWebAccountException
	{
		String stmtString = "SELECT name, websiteName, websiteURL, username, password, additionalInformation FROM webAccount WHERE programUser LIKE ?"; 
		
		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			accounts.add(new WebAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
	}

	public void selectCreditCards(User userToSelectFrom) throws SQLException
	{
		String stmtString = "SELECT cardName, cardNumber, ownerName, bankName, expireDate, provider, additionalInformation, securityCode FROM creditCard WHERE userName LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(stmtString);
		stmt.setString(1, currentUser.getUsername());
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			creditCards.add(new CreditCard(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), ECreditCardsProviders.getProvider(rs.getString(6)), rs.getString(7), rs.getInt(8)));
		}
	}
		
				

	// ------------------------------------------------------------
	// ---------------- WEB ACCOUNT OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addWebAccount(WebAccount accountToAdd)
	{
		// TODO @rabitsch
	}

	public void deleteWebAccount(WebAccount accountToDelete)
	{
		// TODO @rabitsch
	}

	public void updateWebAccount(WebAccount accountToUpdate)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ------------------ LICENSE OPERATIONS ----------------------
	// ------------------------------------------------------------

	public void addProgramLicense(ProgramLicense licenseToAdd)
	{
		// TODO @rabitsch
	}

	public void deleteProgramLicense(ProgramLicense licenseToDelete)
	{
		// TODO @rabitsch
	}

	public void updateProgramLicense(ProgramLicense licenseToUpdate)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ---------------- CREDIT CARD OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addCreditCard(CreditCard cardToAdd)
	{
		// TODO @rabitsch
	}

	public void delteCreditCard(CreditCard cardToDelete)
	{
		// TODO @rabitsch
	}

	public void updateCreditCard(CreditCard cardToUpdate)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ----------------- PASSPORT OPERATIONS ----------------------
	// ------------------------------------------------------------

	public void addPassport(Passport currentPass)
	{
		// TODO @rabitsch

	}
	
	public void deltePassport(Passport currentPass)
	{
		// TODO @rabitsch

	}

	public void updatePassport(Passport currentPass)
	{
		// TODO @rabitsch

	}
	
	// ------------------------------------------------------------
	// ------------------- USER OPERATIONS ------------------------
	// ------------------------------------------------------------

	public void addUser(User userToAdd, char[] password) throws NoSuchAlgorithmException
	{
		// String hashedPwd = hash(new String(password));
		// TODO @rabitsch
	}

	public void checkUserCredentials(User userToCheck, char[] password) throws NoSuchAlgorithmException
	{

		// TODO select users salt

		// String hashedPwd = hash(new String(password));
		// TODO check if user & password exist
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

	public static ArrayList<ProgramLicense> getListLicenses()
	{
		return licenses;
	}

	public static ArrayList<CreditCard> getListCreditcards()
	{
		return creditCards;
	}

	public static Collection<WebAccount> getAccounts()
	{
		return accounts;
	}

	public static Collection<ProgramLicense> getLicenses()
	{
		return licenses;
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
		String salt = "123";  //TODO JUST FOR TESTING, ELSE SALT = NULL!!!!
		String hashed = PasswordUtils.getSHA512Hash(new String(pwd), salt);
		// TODO check if user and pwd exist, throw an UserException if not --> look below
//		throw new UserException("Invalid username or password");
		usr.setPassword(hashed);
		usr.setSalt(salt);
		currentUser = usr;
	}

	public void createNewUser(User user, char[] pwd) throws NoSuchAlgorithmException
	{
		// TODO check if user exists -> if yes throw new UserException --> look below
//		throw new UserException("Username is already used");
		String salt = new Base64().encodeToString(PasswordUtils.generateSalt(PasswordUtils.SALT_LENGTH));
		String hashedPassword = PasswordUtils.getSHA512Hash(new String(pwd), salt);

		// TODO insert new user with name, salt & hash in db
		
		currentUser = new User(user.getUsername());
		currentUser.setPassword(hashedPassword);
		currentUser.setSalt(salt);

	}

}
