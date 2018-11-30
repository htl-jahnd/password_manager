package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum ESex
{
	Male, Female;

	public static BufferedImage getSexPicture(ESex sex) throws IOException
	{
		BufferedImage img = null;
		switch (sex)
		{
			case Male:
				img = ImageIO.read(ESex.class.getResourceAsStream("/pkgMain/ressources/images/mars-solid.png"));
				break;
			case Female:
				img = ImageIO.read(ESex.class.getResourceAsStream("/pkgMain/ressources/images/venus-solid.png"));
				break;
		}
		return img;
	}

	public static String getSexString(ESex sex)
	{
		String ret;
		switch (sex)
		{
			case Male:
				ret = "Male";
				break;
			case Female:
				ret = "Female";
				break;
			default:
				ret = "Other";
				break;
		}
		return ret;
	}
}
