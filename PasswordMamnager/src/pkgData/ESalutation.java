package pkgData;

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
}
