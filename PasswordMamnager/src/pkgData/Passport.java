package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.text.DateFormatter;

import impl.org.controlsfx.skin.ExpandableTableRowSkin;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import pkgExceptions.InvalidPassportException;
import pkgMisc.DateUtils;

public class Passport
{
	private String givenNames;
	private String surName;
	private String nationality;
	private LocalDate dateOfBirth;
	private String placeOfBirth;
	private LocalDate dateOfIssue;
	private LocalDate exirationDate;
	private ESex sex;
	private String authority;
	private String number;
	private String additionalInformation;
	private BufferedImage thumbnail;

	public Passport(String givenNames, String surName, String nationality, LocalDate dateOfBirth, String placeOfBirth,
			LocalDate dateOfIssue, LocalDate exirationDate, ESex sex, String authority, String number,
			String additionalInformation) throws IOException, InvalidPassportException
	{
		super();
		this.setGivenNames(givenNames);
		this.setSurName(surName);
		this.setNationality(nationality);
		this.setDateOfBirth(dateOfBirth);
		this.setPlaceOfBirth(placeOfBirth);
		this.setDateOfIssue(dateOfIssue);
		this.setExirationDate(exirationDate);
		this.setSex(sex);
		this.setAuthority(authority);
		this.setNumber(number);
		this.setAdditionalInformation(additionalInformation);
		thumbnail= ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/passport-solid.png"));
	}
	
	public Passport() throws IOException, InvalidPassportException {
		this("John", "Doe", "Utopia", LocalDate.now().minusYears(25),
						"Sample Town", LocalDate.now(), LocalDate.now().plusYears(10),
						ESex.Male, "Passport Office", "12345678", "Some additional Information");
	}

	public String getGivenNames()
	{
		return givenNames;
	}

	public void setGivenNames(String givenNames) throws InvalidPassportException
	{
		if(givenNames == null || givenNames.trim().isEmpty())
			throw new InvalidPassportException("Given names must not be empty.");
		this.givenNames = givenNames;
	}

	public String getSurName()
	{
		return surName;
	}

	public void setSurName(String surName) throws InvalidPassportException
	{
		if(surName == null || surName.trim().isEmpty())
			throw new InvalidPassportException("Surname must not be empty.");
		this.surName = surName;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String nationality) throws InvalidPassportException
	{
		if(nationality == null || nationality.trim().isEmpty())
			throw new InvalidPassportException("Nationality must not be empty.");
		this.nationality = nationality;
	}

	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) throws InvalidPassportException
	{
		if(dateOfBirth == null)
			throw new InvalidPassportException("Date of birth must not be null.");
		else if(dateOfBirth.isAfter(LocalDate.now()))
			throw new InvalidPassportException("Date of birth must not be after today.");
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth()
	{
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) throws InvalidPassportException
	{
		if(placeOfBirth == null || placeOfBirth.trim().isEmpty())
			throw new InvalidPassportException("Place of birth must not be empty.");
		this.placeOfBirth = placeOfBirth;
	}

	public LocalDate getDateOfIssue()
	{
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) throws InvalidPassportException
	{
		if(dateOfIssue == null)
			throw new InvalidPassportException("Date of issue must not be null.");
		else if(dateOfIssue.isAfter(LocalDate.now()))
			throw new InvalidPassportException("Date of issue must not be in the future.");
		this.dateOfIssue = dateOfIssue;
	}

	public LocalDate getExirationDate()
	{
		return exirationDate;
	}

	public void setExirationDate(LocalDate exirationDate) throws InvalidPassportException
	{
		if(exirationDate == null)
			throw new InvalidPassportException("Expiration date must not be null.");
		else if(exirationDate.isBefore(LocalDate.now()))
			throw new InvalidPassportException("Expiration date must not be before today.");
		this.exirationDate = exirationDate;
	}

	public ESex getSex()
	{
		return sex;
	}

	public void setSex(ESex sex)
	{
		this.sex = sex;
	}

	public String getAuthority()
	{
		return authority;
	}

	public void setAuthority(String authority) throws InvalidPassportException
	{
		if(authority == null || authority.trim().isEmpty())
			throw new InvalidPassportException("Authority must not be empty.");
		this.authority = authority;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number) throws InvalidPassportException
	{
		if(number == null || number.trim().isEmpty())
			throw new InvalidPassportException("Passport number must not be empty.");
		this.number = number;
	}

	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

	public String getDateOfBirthAsString()
	{
		return pkgMisc.DateUtils.getStringOfLocalDate(dateOfBirth);
	}

	public String getDateOfIssueAsString()
	{
		return pkgMisc.DateUtils.getStringOfLocalDate(dateOfIssue);
	}

	public String getExirationDateAsString()
	{
		return pkgMisc.DateUtils.getStringOfLocalDate(exirationDate);
	}

	public void setDateOfBirthOfString(String date)
	{
		this.dateOfBirth = pkgMisc.DateUtils.getLocalDateOfString(date);
	}

	public void setDateOfIssueOfString(String date)
	{
		this.dateOfIssue = pkgMisc.DateUtils.getLocalDateOfString(date);
	}

	public void setExpirationDateOfString(String date)
	{
		this.exirationDate = pkgMisc.DateUtils.getLocalDateOfString(date);
	}

	public Image getThumbnail()
	{
		return SwingFXUtils.toFXImage(thumbnail, null);
	}

}
