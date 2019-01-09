package pkgData;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum ESalutation
{
	Mister, Miss, Other;
	
	
	public static String getSalutationString(ESalutation sal) {
		switch(sal) {
			case Mister:
				return "Mister";
			case Miss:
				return "Miss";
			default:
				return "Other";
		}
	}
	
	
	public static ESalutation getSalutationString(String sal) {
		switch(sal) {
			case "Mister":
				return Mister;
			case "Miss":
				return Miss;
			default:
				return Other;
		}
	}
	
	public static BufferedImage getSalutationPicture(ESalutation sal) throws IOException {
		if(sal.equals(Mister)) {
			return ImageIO.read(ESalutation.class.getResourceAsStream("/pkgMain/ressources/images/mars-solid.png"));
		}
		else if(sal.equals(Miss)) {
			return ImageIO.read(ESalutation.class.getResourceAsStream("/pkgMain/ressources/images/venus-solid.png"));
		}
		else {
			return ImageIO.read(ESalutation.class.getResourceAsStream("/pkgMain/ressources/images/transgender-alt-solid.png"));
		}
	}
}
