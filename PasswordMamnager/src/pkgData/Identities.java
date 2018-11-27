package pkgData;

import java.time.LocalDate;

public class Identities
{
	
	private ESalutation salutation;
	private String firstName;
	private String surName;
	private String streetAddress;
	private String cityAddress;
	private int zipAddress;
	private String stateAddress;
	private LocalDate dateOfBirth;
	private String country;

	public Identities(ESalutation salutation, String firstName, String surName, String streetAddress,
			String cityAddress, int zipAddress, String stateAddress, LocalDate dateOfBirth, String country)
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

	public int getZipAddress()
	{
		return zipAddress;
	}

	public void setZipAddress(int zipAddress)
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

}
