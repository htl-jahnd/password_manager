package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum ECreditCardsProviders
{
	Visa, MasterCard, AmericanExpress, DinersClub, Discover;

	public static ECreditCardsProviders getProvider(String s)
	{
		switch (s)
		{
			case "Visa":
				return Visa;
			case "MasterCard":
				return MasterCard;
			case "AmericanExpress":
				return AmericanExpress;
			case "DinersClub":
				return DinersClub;
			case "Discover":
				return Discover;
		}
		return null;
	}

	public static String getProviderString(ECreditCardsProviders e)
	{
		switch (e)
		{
			case Visa:
				return "Visa";
			case MasterCard:
				return "MasterCard";
			case AmericanExpress:
				return "AmericanExpress";
			case DinersClub:
				return "DinersClub";
			case Discover:
				return "Discover";
		}
		return null;
	}

	public static BufferedImage getProviderPicture(ECreditCardsProviders provider) throws IOException
	{

		if (provider.equals(ECreditCardsProviders.Visa))
		{
			return ImageIO.read(
					ECreditCardsProviders.class.getResourceAsStream("/pkgMain/ressources/images/cc-visa-brands.png"));
		} else if (provider.equals(ECreditCardsProviders.MasterCard))
		{
			return ImageIO.read(ECreditCardsProviders.class
					.getResourceAsStream("/pkgMain/ressources/images/cc-mastercard-brands.png"));
		} else if (provider.equals(ECreditCardsProviders.AmericanExpress))
		{
			return ImageIO.read(
					ECreditCardsProviders.class.getResourceAsStream("/pkgMain/ressources/images/cc-amex-brands.png"));
		} else if (provider.equals(ECreditCardsProviders.Discover))
		{
			return ImageIO.read(ECreditCardsProviders.class
					.getResourceAsStream("/pkgMain/ressources/images/cc-discover-brands.png"));
		} else if (provider.equals(ECreditCardsProviders.DinersClub))
		{
			return ImageIO.read(ECreditCardsProviders.class
					.getResourceAsStream("/pkgMain/ressources/images/cc-diners-club-brands.png"));
		} else
			return ImageIO.read(
					ECreditCardsProviders.class.getResourceAsStream("/pkgMain/ressources/images/credit-card.png"));

	}
}
