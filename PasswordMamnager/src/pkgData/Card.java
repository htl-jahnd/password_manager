package pkgData;

import java.time.YearMonth;
import java.util.Date;

import pkgExceptions.InvalidCardException;

abstract public class Card
{

	private String cardName;
	private String ownerName;
	private String bankName;
	private YearMonth expireDate;
	private String additionalInformation;

	public Card(String cardName, String ownerName, String bankName, YearMonth expireDate, String additionalInformation)
	{
		super();
		this.cardName = cardName;
		this.ownerName = ownerName;
		this.bankName = bankName;
		this.expireDate = expireDate;
		this.additionalInformation = additionalInformation;
	}

	public String getCardName()
	{
		return cardName;
	}

	public void setCardName(String cardName) throws InvalidCardException
	{
		if(cardName.trim().isEmpty())
			throw new InvalidCardException("Card name must not be empty");
		this.cardName = cardName;
	}

	public String getOwnerName()
	{
		return ownerName;
	}

	public void setOwnerName(String ownerName) throws InvalidCardException
	{
		if(ownerName.trim().isEmpty())
			throw new InvalidCardException("Card owners name must not be empty");
		this.ownerName = ownerName;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public YearMonth getExpireDate()
	{
		return expireDate;
	}

	public void setExpireDate(YearMonth expireDate) throws InvalidCardException
	{
		if(expireDate == null)
			throw new InvalidCardException("Expire date must not be null");
		this.expireDate = expireDate;
	}

	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

}
