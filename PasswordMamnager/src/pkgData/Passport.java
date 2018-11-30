package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.text.DateFormatter;

import impl.org.controlsfx.skin.ExpandableTableRowSkin;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

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
			String additionalInformation) throws IOException
	{
		super();
		this.givenNames = givenNames;
		this.surName = surName;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.dateOfIssue = dateOfIssue;
		this.exirationDate = exirationDate;
		this.sex = sex;
		this.authority = authority;
		this.number = number;
		this.additionalInformation = additionalInformation;
		thumbnail= ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/passport-solid.png"));
	}

	public String getGivenNames()
	{
		return givenNames;
	}

	public void setGivenNames(String givenNames)
	{
		this.givenNames = givenNames;
	}

	public String getSurName()
	{
		return surName;
	}

	public void setSurName(String surName)
	{
		this.surName = surName;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}

	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth()
	{
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth)
	{
		this.placeOfBirth = placeOfBirth;
	}

	public LocalDate getDateOfIssue()
	{
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue)
	{
		this.dateOfIssue = dateOfIssue;
	}

	public LocalDate getExirationDate()
	{
		return exirationDate;
	}

	public void setExirationDate(LocalDate exirationDate)
	{
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

	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
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
