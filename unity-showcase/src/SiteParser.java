import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.dom.NodeList;
import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * 
 * @author Marcus
 * SiteParser searches unity3d for games and populates a list of GameInfo with the data.
 *
 */

public class SiteParser {
	private List<GameInfo> allGames;
	
	public static void main(String[] args) throws Exception {
		SiteParser sp = new SiteParser();
		sp.parse();
		sp.printGames();
	}
	
	public SiteParser() {
		allGames = new ArrayList<GameInfo>();
	}
	
	/**
	 * Parses the unity showcase and populates allGames with data.
	 * @throws Exception
	 * @modifies this.allGames
	 * @effects this.allGames now contains a list of games from the unity showcase
	 */
	public void parse() throws Exception {
		String url = "https://unity3d.com/showcase/gallery";
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_45);
		HtmlPage page = wc.getPage(url);
		wc.getOptions().setJavaScriptEnabled(true);
		wc.waitForBackgroundJavaScript(3000);
		
		System.out.println("Loaded up " + page.getBaseURL());
		
		// Get all images
		DomNodeList<DomNode> images = page.querySelectorAll(".ic");
		
		// Get all developer sites
		DomNodeList<DomNode> sites = page.querySelectorAll(".images a");
		
		// Get all titles
		DomNodeList<DomNode> titles = page.querySelectorAll(".wrap h3");
		
		// Get developer
		DomNodeList<DomNode> developers = page.querySelectorAll(".developer");
		
		// Get genre
		DomNodeList<DomNode> genres = page.querySelectorAll(".mb10.genres");
		
		// Get description	
		DomNodeList<DomNode> descriptions = page.querySelectorAll(".mb15.description.clear p");
		
		// Get platforms, and store number of each in the platformsPerGame list
		DomNodeList<DomNode> platforms = page.querySelectorAll(".platform-icon.pa0.m0a.mr1.mb0.tooltip .logo");
		List<Integer> platformsPerGame = new ArrayList<Integer>();
		final List<HtmlDivision> nodes = page.getByXPath("//div[contains(@class,'platform-small')]");
		for (HtmlDivision node: nodes) {
			final List<HtmlDivision> children = node.getByXPath("div[contains(@class,'platform-icon')]");
			platformsPerGame.add(children.size());   
		}
		
		// For each one, populate a GameInfo with the data, then print, storing all in a list		
		int totalDistance = 0;
		for (int i = 0; i < titles.size(); i++) {
			String title = titles.get(i).getTextContent();
			String image = ((DomElement) images.get(i)).getAttribute("src");
			String gameSite = ((DomElement) sites.get(i)).getAttribute("href");
			String developer = developers.get(i).getTextContent();
			String devSite = ((DomElement) developers.get(i)).getAttribute("href");
			String genre = genres.get(i).getTextContent();
			String description = descriptions.get(i).getTextContent();
			String platformString = "";
			for (int j = 0; j < platformsPerGame.get(i); j++) {
				platformString += (((DomElement) platforms.get(j + totalDistance)).getAttribute("class") + " ");
			}
			
			GameInfo game = new GameInfo(title, image, gameSite, developer, 
										devSite, genre, description, platformString);
			allGames.add(game);
			totalDistance += platformsPerGame.get(i);
		} 
	}
	
	/**
	 * Prints out all the games in allGames
	 */
	public void printGames() {
		// Printing the list of games
		for (int i = 0; i < allGames.size(); i++) {
			System.out.println(allGames.get(i).toString());
		}
	}
	
	/**
	 * Gets an unmodifiable list of all the GameInfos that this parsed.
	 * @return an unmodifiable list of the games associated with this
	 * @return null if allGames is empty, i.e. parse hasn't been called yet
	 */
	public List<GameInfo> getGames() {
		if (allGames.isEmpty()) {
			return null;
		}
		return Collections.unmodifiableList(allGames);
	}
}
