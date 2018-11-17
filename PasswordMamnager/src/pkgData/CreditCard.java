package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import pkgExceptions.InvalidCVVCodeException;

public class CreditCard extends Card
{

	private String cardNumber;
	private ECreditCardsProviders provider;
	private Integer securityCode;
	private BufferedImage thumbnail;

	public CreditCard(String cardName, String cardNumber, String cardOwner, YearMonth validationDate,
			ECreditCardsProviders provider, String additionalInformation, String bankName, Integer securityCode,
			BufferedImage thumbnail) throws IOException
	{
		super(cardName, cardOwner, bankName, validationDate, additionalInformation);
		this.cardNumber = cardNumber;
		this.provider = provider;
		this.securityCode = securityCode;
		if (thumbnail == null)
		{
			checkForProviderPicture();
		} else
			this.thumbnail = thumbnail;
	}

	private void checkForProviderPicture() throws IOException
	{
		thumbnail = ECreditCardsProviders.getProviderPicture(provider);

	}

	public CreditCard(String cardName, String cardNumber, String cardOwner, YearMonth validationDate,
			ECreditCardsProviders provider, String additionalInformation, String bankName, Integer securityCode)
			throws IOException
	{
		this(cardName, cardNumber, cardOwner, validationDate, provider, additionalInformation, bankName, securityCode,
				null);
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public ECreditCardsProviders getProvider()
	{
		return provider;
	}

	public void setProvider(ECreditCardsProviders provider)
	{
		this.provider = provider;
	}

	public int getSecurityCode()
	{
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) throws InvalidCVVCodeException
	{
		if (String.valueOf(securityCode).length() != 3)
			throw new InvalidCVVCodeException("ERROR: The security code has to be three digits long");
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

	public void setExpireDateOfString(String expDate)
	{
		this.setExpireDate(YearMonth.parse(expDate, DateTimeFormatter.ofPattern("MM/yy")));
	}

}
