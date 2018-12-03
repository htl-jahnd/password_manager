package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import pkgExceptions.InvalidCardException;

public class CreditCard extends Card
{

	private String cardNumber;
	private ECreditCardsProviders provider;
	private Integer securityCode;
	private BufferedImage thumbnail;

	public CreditCard(String cardName, String cardNumber, String cardOwner, YearMonth expireDate,
			ECreditCardsProviders provider, String additionalInformation, String bankName, Integer securityCode,
			BufferedImage thumbnail) throws IOException, InvalidCardException
	{
		super(cardName, cardOwner, bankName, expireDate, additionalInformation);
		setCardNumber(cardNumber);
		setProvider(provider);
		setSecurityCode(securityCode);
		if (thumbnail == null)
		{
			checkForProviderPicture();
		} else
			this.thumbnail = thumbnail;
	}

	public CreditCard(String cardName, String cardNumber, String cardOwner, YearMonth validationDate,
			ECreditCardsProviders provider, String additionalInformation, String bankName, Integer securityCode)
			throws IOException, InvalidCardException
	{
		this(cardName, cardNumber, cardOwner, validationDate, provider, additionalInformation, bankName, securityCode,
				null);
	}
	
	public CreditCard() throws IOException, InvalidCardException {
		this("Name", "0000 1111 2222 3333 4444", "Card Owner",
						YearMonth.of(LocalDate.now().plusYears(5).getYear(), LocalDate.now().getMonth()), ECreditCardsProviders.Other,
						"Additional Information", "Example Bank", Integer.valueOf(123));
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) throws InvalidCardException
	{
		if (cardNumber.trim().isEmpty())
			throw new InvalidCardException("Credit Card number must not be empty");
//		else if (!validateCreditCardNumber(cardNumber))
//			throw new InvalidCardException("Credit card number is not valid"); //TODO comment in
		this.cardNumber = cardNumber;
	}

	public ECreditCardsProviders getProvider()
	{
		return provider;
	}

	public void setProvider(ECreditCardsProviders provider) throws IOException
	{
		this.provider = provider;
		checkForProviderPicture();
	}

	public int getSecurityCode()
	{
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) throws InvalidCardException
	{
		if (String.valueOf(securityCode).length() != 3)
			throw new InvalidCardException("The security code has to be three digits long");
		this.securityCode = securityCode;
	}

	public Image getThumbnail()
	{
		return SwingFXUtils.toFXImage(thumbnail, null);
	}

	public void setThumbnail(BufferedImage thumbnail)
	{
		this.thumbnail = thumbnail;
	}

	public String getExpireDateAsString()
	{
		String ret = getExpireDate().format(DateTimeFormatter.ofPattern("MM/yy"));
		return ret;
	}

	public void setExpireDateOfString(String expDate) throws InvalidCardException
	{
		try
		{
			this.setExpireDate(YearMonth.parse(expDate, DateTimeFormatter.ofPattern("MM/yy")));
		} catch (DateTimeParseException ex)
		{
			throw new InvalidCardException("Expire date must have the format 'MM/YY'");
		}

	}
	
	public static YearMonth getExpireDateOfString(String in) throws InvalidCardException
	{
		try
		{
			return YearMonth.parse(in, DateTimeFormatter.ofPattern("MM/yy"));
		} catch (DateTimeParseException ex)
		{
			throw new InvalidCardException("Expire date must have the format 'MM/YY'");
		}
	}

	private boolean validateCreditCardNumber(String str)
	{

		int sum = 0;
		boolean alternate = false;
		for (int i = str.length() - 1; i >= 0; i--)
		{
			int n = Integer.parseInt(str.substring(i, i + 1));
			if (alternate)
			{
				n *= 2;
				if (n > 9)
				{
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

	private void checkForProviderPicture() throws IOException
	{
		thumbnail = ECreditCardsProviders.getProviderPicture(provider);
	}

}
