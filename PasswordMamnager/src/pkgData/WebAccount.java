package pkgData;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import net.sf.image4j.codec.ico.ICODecoder;
import pkgExceptions.InvalidWebAccountException;

/**
 * @author david.jahn
 *
 */
public class WebAccount
{
	private String name;
	private String websiteName;
	private String websiteURL;
	private String username;
	private String password;
	private String additionalInformation;
	private BufferedImage thumbnail;

	public WebAccount(String name, String websiteName, String websiteURL, String username, String password,
			String additionalInformation, BufferedImage thumbnailParam)
			throws MalformedURLException, IOException, URISyntaxException, InvalidWebAccountException
	{
		super();
		setName(name);
		setWebsiteName(websiteName);
		setWebsiteURL(websiteURL);
		setUsername(username);
		setPassword(password);
		this.additionalInformation = additionalInformation;
		if (thumbnailParam == null)
		{
			checkThumbnailDownload();
		} else
			thumbnail = thumbnailParam;

	}

	public WebAccount(String name, String websiteName, String websiteURL, String username, String password,
			String additionalInformation) throws MalformedURLException, IOException, URISyntaxException, InvalidWebAccountException
	{
		this(name, websiteName, websiteURL, username, password, additionalInformation, null);
	}

	private void checkThumbnailDownload() throws IOException //TODO doesnt work everytime
	{
		try
		{
			List<BufferedImage> lstImg = ICODecoder.read(new URL(websiteURL + "favicon.ico").openStream());
			this.thumbnail = lstImg.get(lstImg.size() - 1);
		} catch (Exception e)
		{
			this.thumbnail = ImageIO
					.read(getClass().getResource("/pkgMain/ressources/images/default_icon_web_account.png"));
		}
	}

	@Override
	public String toString()
	{
		return name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) throws InvalidWebAccountException
	{
		if(name == null)
			throw new InvalidWebAccountException("Name must not be null");
		else if(name.trim().isEmpty())
			throw new InvalidWebAccountException("Name must not be empty");
		this.name = name;
	}

	public String getWebsiteName()
	{
		return websiteName;
	}

	public void setWebsiteName(String accountName) throws InvalidWebAccountException
	{
		if(accountName == null)
			throw new InvalidWebAccountException("Name must not be null");
		else if(accountName.trim().isEmpty())
			throw new InvalidWebAccountException("Name must not be empty");
		this.websiteName = accountName;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username) throws InvalidWebAccountException
	{
		if(username == null)
			throw new InvalidWebAccountException("Username must not be null");
		else if(username.trim().isEmpty())
			throw new InvalidWebAccountException("Username must not be empty");
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password) throws InvalidWebAccountException
	{
		if(password == null)
			throw new InvalidWebAccountException("Password must not be null");
		else if(password.trim().isEmpty())
			throw new InvalidWebAccountException("Password must not be empty");
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

	public String getWebsiteURL()
	{
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) throws IOException, InvalidWebAccountException
	{
		if(websiteURL == null)
			throw new InvalidWebAccountException("Website URL must not be null");
		else if(websiteURL.trim().isEmpty())
			throw new InvalidWebAccountException("Website URL must not be empty");
		this.websiteURL = websiteURL;
		checkThumbnailDownload(); 
	}

	public Image getThumbnail()
	{
		return SwingFXUtils.toFXImage(thumbnail, null);
	}

	public void setThumbnail(BufferedImage thumbnail)
	{
		this.thumbnail = thumbnail;
	}

}
