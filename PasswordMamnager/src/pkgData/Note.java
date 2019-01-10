package pkgData;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import pkgExceptions.NoteException;

public class Note
{
	private String text;
	private String title;
	
	public Note(String title, String text)
	{
		super();
		this.text = text;
		this.title = title;
	}
	
	public Note()
	{
		this("Things to buy", "Eggs, Milk, Butter, ...");
	}

	@Override
	public String toString()
	{
		return "Note [text=" + text + ", title=" + title + "]";
	}

	public String getText()
	{
		return text;
	}
	public void setText(String text) throws NoteException
	{
		if(text == null || text.trim().isEmpty())
			throw new NoteException("Text must not be empty");
		this.text = text;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title) throws NoteException
	{
		if(title == null || title.trim().isEmpty())
			throw new NoteException("Title must not be empty");
		this.title = title;
	}
	public static Image getNoteImage() throws IOException {
		return SwingFXUtils.toFXImage(ImageIO.read(Note.class.getResourceAsStream("/pkgMain/ressources/images/sticky-note-solid.png")),null);
	}
	
	
	
}
