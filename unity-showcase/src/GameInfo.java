import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.DomElement;

/**
 * 
 * @author Marcus Riley
 * This class stores information about a game from the Unity Showcase.
 * It provides functionality to get individual elements, or print it to the console
 *
 */
public final class GameInfo {
	private final String title;
	private final String image;
	private final String gameSite;
	private final String developer;
	private final String devSite;
	private final String genre;
	private final String description;
	private final List<String> platforms;
	
	/**
	 * 
	 * @param title the title of the game
	 * @param image the image associated with the game
	 * @param gameSite the website associated with the game
	 * @param developer the developer of the game
	 * @param devSite the developer's website
	 * @param genre the game's genre
	 * @param description a description of the game
	 * @param platforms the platforms the game is available on
	 */
	public GameInfo(String title, String image, String gameSite, String developer, String devSite,
					String genre, String description, String platforms) {
		this.title = title;
		this.image = image;
		this.gameSite = gameSite;
		this.developer = developer;
		this.devSite = devSite;
		this.genre = genre;
		this.description = description;
		
		this.platforms = new ArrayList<String>();
		String[] platList = platforms.split(" ");
		for (int i = 0; i < platList.length; i++) {
			if (!platList[i].equals("logo")) {
				this.platforms.add(platList[i]);
			}
		}
	}
	
	public String getTitle() {
		return title;
	}
	public String getImage() {
		return image;
	}
	public String getGameSite() {
		return gameSite;
	}
	public String getDeveloper() {
		return developer;
	}
	public String getDevSite() {
		return devSite;
	}
	public String getGenre() {
		return genre;
	}
	public String getDescription() {
		return description;
	}
	public List<String> getPlatforms() {
		return Collections.unmodifiableList(platforms);
	}
	
	public String toString() {
		String res = "";
		res += title + ": " + image + "\n";
		res += " Game site: " + gameSite + "\n";
		res += " Developer: " + developer + ": " + devSite + "\n";
		res += " Genre: " + genre + "\n";
		res += " Description: " + description + "\n";
		res += " Platforms: ";
		for (int i = 0; i < platforms.size(); i++) {
			res += platforms.get(i) + " ";
		}
		return res + "\n";
	}
}
