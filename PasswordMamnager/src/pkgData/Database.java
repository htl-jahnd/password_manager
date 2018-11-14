package pkgData;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

public class Database
{
	private static Database instance = null;
	private static Connection conn = null;
	private static boolean isConnectionSet = false;
	private static String connectionString;
	private static final ArrayList<WebAccount> accounts = new ArrayList<WebAccount>();
	private static final ArrayList<ProgramLicense> licenses = new ArrayList<ProgramLicense>();
	private static final ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
	private static final String DB_USER = "d4b12";
	private static final String DB_PWD = "d4b";
	private static User currentUser;

	// ------------------------------------------------------------
	// ---------------------- ALL SELECTS -------------------------
	// ------------------------------------------------------------

	public void selectWebAccounts(User userToSelectFrom)
	{
		// TODO @rabitsch
	}

	public void selectCreditCards(User userToSelectFrom)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ---------------- WEB ACCOUNT OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addWebAccount(WebAccount accountToAdd, User user)
	{
		// TODO @rabitsch
	}

	public void deleteWebAccount(WebAccount accountToDelete, User user)
	{
		// TODO @rabitsch
	}

	public void updateWebAccount(WebAccount accountToUpdate, User user)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ------------------ LICENSE OPERATIONS ----------------------
	// ------------------------------------------------------------

	public void addProgramLicense(ProgramLicense licenseToAdd, User user)
	{
		// TODO @rabitsch
	}

	public void deleteProgramLicense(ProgramLicense licenseToDelete, User user)
	{
		// TODO @rabitsch
	}

	public void updateProgramLicense(ProgramLicense licenseToUpdate, User user)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ---------------- CREDIT CARD OPERATIONS --------------------
	// ------------------------------------------------------------

	public void addCreditCard(CreditCard cardToAdd, User user)
	{
		// TODO @rabitsch
	}

	public void delteCreditCard(CreditCard cardToDelete, User user)
	{
		// TODO @rabitsch
	}

	public void updateCreditCard(CreditCard cardToUpdate, User user)
	{
		// TODO @rabitsch
	}

	// ------------------------------------------------------------
	// ------------------- USER OPERATIONS ------------------------
	// ------------------------------------------------------------

	public void checkUserCredentials(User userToCheck, char[] password) throws NoSuchAlgorithmException
	{
		String hashedPwd = hash(new String(password));
		//TODO check if user & password exist
	}

	private String hash(String text) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		String hashedPassword = Base64.getEncoder().encodeToString(hash);
		return hashedPassword;
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
		DriverManager.setLoginTimeout(3);
		conn = DriverManager.getConnection(connectionString, DB_USER, DB_PWD); // Connects with database user "d4b12"
		conn.setAutoCommit(true);
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

}
