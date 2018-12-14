package pkgMain.ressources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DashboardObject extends Pane
{

	@FXML
	private AnchorPane parentPane;
	
	@FXML
	private Label lblAccountName;

	@FXML
	private ImageView imgThumbnail;

	@FXML
	private Label lblUser;

	@FXML
	private Label lblCategory;

	private Object objectToReference;
	private String category;
	private String name;
	private String user;
	private Image thumbnail;

	

	@FXML
	void initialize() throws IOException
	{
		lblAccountName.setText(name);
		lblCategory.setText(category);
		lblUser.setText(user);
		imgThumbnail.setImage(thumbnail);
		this.getStyleClass().add("dashboardTile");
		parentPane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		       //TODO
		    }
		});
	}

	public DashboardObject(Object objectToReference, String category, String name, String user, Image thumbnail)
	{
		super();
		this.objectToReference = objectToReference;
		this.category = category;
		this.name = name;
		this.user = user;
		this.thumbnail = thumbnail;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardObject.fxml"));
		try
		{
			loader.setController(this);
			loader.load();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		this.getChildren().add(parentPane);
	}

	public Object getObjectToReference()
	{
		return objectToReference;
	}

	public void setObjectToReference(Object objectToReference)
	{
		this.objectToReference = objectToReference;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

}
