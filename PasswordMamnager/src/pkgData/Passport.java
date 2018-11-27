package pkgData;

import java.time.LocalDate;

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
	
	public Passport(String givenNames, String surName, String nationality, LocalDate dateOfBirth, String placeOfBirth,
			LocalDate dateOfIssue, LocalDate exirationDate, ESex sex, String authority, String number)
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
	
	
	
}
