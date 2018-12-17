package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import pkgMisc.DateUtils;

public class Identity
{
	
	private ESalutation salutation; 
	private String firstName;
	private String surName;
	private String streetAddress;
	private String cityAddress;
	private String zipAddress;
	private String stateAddress;
	private LocalDate dateOfBirth; 
	private String country;
	private String additionalInformation;
	private BufferedImage thumbnail;

	public Identity(ESalutation salutation, String firstName, String surName, String streetAddress,
			String cityAddress, String zipAddress, String stateAddress, LocalDate dateOfBirth, String country, String additionalInformation) throws IOException
	{
		super();
		this.salutation = salutation;
		this.firstName = firstName;
		this.surName = surName;
		this.streetAddress = streetAddress;
		this.cityAddress = cityAddress;
		this.zipAddress = zipAddress;
		this.stateAddress = stateAddress;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.additionalInformation = additionalInformation;
		thumbnail= ImageIO.read(getClass().getResourceAsStream("/pkgMain/ressources/images/id-card-regular.png"));;
	}
	
	public Identity() throws IOException {
		this(ESalutation.Other, "James", "Bond", "Bakerstreet 221 B", "London", "WC2N 5DU", "England", DateUtils.getLocalDateOfString("11.11.1920"), "United Kingom"," 007");
	}


	@Override
	public String toString()
	{
		return "Identities [salutation=" + salutation + ", firstName=" + firstName + ", surName=" + surName
				+ ", streetAddress=" + streetAddress + ", cityAddress=" + cityAddress + ", zipAddress=" + zipAddress
				+ ", stateAddress=" + stateAddress + ", dateOfBirth=" + dateOfBirth + ", country=" + country + "]";
	}


	public ESalutation getSalutation()
	{
		return salutation;
	}

	public void setSalutation(ESalutation salutation)
	{
		this.salutation = salutation;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getSurName()
	{
		return surName;
	}

	public void setSurName(String surName)
	{
		this.surName = surName;
	}

	public String getStreetAddress()
	{
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress;
	}

	public String getCityAddress()
	{
		return cityAddress;
	}

	public void setCityAddress(String cityAddress)
	{
		this.cityAddress = cityAddress;
	}

	public String getZipAddress()
	{
		return zipAddress;
	}

	public void setZipAddress(String zipAddress)
	{
		this.zipAddress = zipAddress;
	}

	public String getStateAddress()
	{
		return stateAddress;
	}

	public void setStateAddress(String stateAddress)
	{
		this.stateAddress = stateAddress;
	}

	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
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
		return DateUtils.getStringOfLocalDate(dateOfBirth);
	}
	
	public void setDateOfBirthOfString(String dateOfBirth) {
		setDateOfBirth(DateUtils.getLocalDateOfString(dateOfBirth));
	}

	public Image getThumbnail()
	{
		return SwingFXUtils.toFXImage(thumbnail, null);
	}
	
}
