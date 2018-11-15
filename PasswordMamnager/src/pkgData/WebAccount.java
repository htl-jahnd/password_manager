package pkgData;

import java.net.URL;

import javafx.scene.image.Image;

/**
 * @author david.jahn
 *
 */
public class WebAccount
{
	private String name;
	private String websiteName;
	private URL websiteURL;
	private String username;
	private String password;
	private String additionalInformation;
	private Image thumbnail;
	private byte[] salt;

	public WebAccount(String name, String websiteName, URL websiteURL, String username, String password,
			String additionalInformation, Image thumbnail)
	{
		super();
		this.name = name;
		this.websiteName = websiteName;
		this.websiteURL = websiteURL;
		this.username = username;
		this.password = password;
		this.additionalInformation = additionalInformation;
		this.thumbnail = thumbnail;
	}

	public WebAccount(String name, String websiteName, URL websiteURL, String username, String password,
			String additionalInformation)
	{
		this(name, websiteName, websiteURL,username, password, additionalInformation, new Image("../pkgMain/ressources/images/default_icon_web_account.png",40,40,false,false));
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getWebsiteName()
	{
		return websiteName;
	}

	public void setWebsiteName(String accountName)
	{
		this.websiteName = accountName;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

	public URL getWebsiteURL()
	{
		return websiteURL;
	}

	public void setWebsiteURL(URL websiteURL)
	{
		this.websiteURL = websiteURL;
	}

	public Image getThumbnail()
	{
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail)
	{
		this.thumbnail = thumbnail;
	}

	public byte[] getSalt()
	{
		return salt;
	}

	public void setSalt(byte[] salt)
	{
		this.salt = salt;
	}

}
