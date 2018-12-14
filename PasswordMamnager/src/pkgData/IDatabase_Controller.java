package pkgData;

public interface IDatabase_Controller
{
	// Selects
	public void selectWebAccounts();

	public void selectCreditCards();

	public void selectPassports();

	public void selectIdentities();

	// Web account
	public void addWebAccount();

	public void deleteWebAccount();

	public void updateWebAccount();

	// Credit card
	public void addCreditCard(CreditCard cardToAdd);

	public void delteCreditCard(CreditCard cardToDelete);

	public void updateCreditCard(CreditCard cardToUpdate);

	// Passport
	public void addPassport(Passport passportToAdd);

	public void deltePassport(Passport passportToDelete);

	public void updatePassport(Passport currentPass);

	// Note
	public void addNote(Note NoteToAdd);

	public void deleteNote(Note noteToDelete);

	public void updateNote(Note noteToDelete);

	// Identity
	public void addIdentity(Identity idToAdd);

	public void deleteIdentity(Identity idToDelte);

	public void updateIdentity(Identity idToUpdate);
}
