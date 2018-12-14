package pkgData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import pkgExceptions.InvalidWebAccountException;

public interface IDatabase_Controller
{
	// Selects
	public void selectWebAccounts() throws SQLException, MalformedURLException, IOException, URISyntaxException, InvalidWebAccountException;

	public void selectCreditCards() throws Exception;

	public void selectPassports();

	public void selectIdentities();

	// Web account
	public void addWebAccount(WebAccount accountToAdd) throws SQLException;

	public void deleteWebAccount(WebAccount accountToDelete) throws Exception;

	public void updateWebAccount(WebAccount accountToUpdate) throws Exception;

	// Credit card
	public void addCreditCard(CreditCard cardToAdd) throws SQLException;

	public void delteCreditCard(CreditCard cardToDelete)throws SQLException, Exception;

	public void updateCreditCard(CreditCard cardToUpdate) throws SQLException;

	// Passport
	public void addPassport(Passport passportToAdd) throws SQLException;

	public void deltePassport(Passport passportToDelete) throws SQLException, Exception;

	public void updatePassport(Passport currentPass) throws SQLException;

	// Note
	public void addNote(Note NoteToAdd) throws SQLException;

	public void deleteNote(Note noteToDelete) throws SQLException;

	public void updateNote(Note noteToDelete) throws SQLException;

	// Identity
	public void addIdentity(Identity idToAdd) throws SQLException;

	public void deleteIdentity(Identity idToDelte) throws SQLException;

	public void updateIdentity(Identity idToUpdate) throws SQLException;
}
