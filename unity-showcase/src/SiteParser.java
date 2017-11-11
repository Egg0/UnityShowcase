import java.io.IOException;
import java.util.ArrayList;
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

public class SiteParser {

	public static void main(String[] args) throws Exception {
		String url = "https://unity3d.com/showcase/gallery";
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_45);
		HtmlPage page = wc.getPage(url);
		wc.getOptions().setJavaScriptEnabled(true);
		wc.waitForBackgroundJavaScript(3000);
		
		System.out.println("Loaded up " + page.getBaseURL());
		
		// Get all images
		DomNodeList<DomNode> images = page.querySelectorAll(".ic");
		
		// Get all titles
		DomNodeList<DomNode> titles = page.querySelectorAll(".wrap h3");
		
		// Get developer
		DomNodeList<DomNode> developers = page.querySelectorAll(".developer");
		
		// Get genre
		DomNodeList<DomNode> genres = page.querySelectorAll(".mb10.genres");
		
		// Get description	
		DomNodeList<DomNode> descriptions = page.querySelectorAll(".mb15.description.clear p");
		
		// Get platforms TODO: iterate over games and store int array of platforms per game
		DomNodeList<DomNode> platforms = page.querySelectorAll(".platform-icon.pa0.m0a.mr1.mb0.tooltip .logo");
		List<Integer> platformsPerGame = new ArrayList<Integer>();
		final List<HtmlDivision> nodes = page.getByXPath("//div[contains(@class,'platform-small')]");
		for (HtmlDivision node: nodes) {
			final List<HtmlDivision> children = node.getByXPath("div[contains(@class,'platform-icon')]");
			platformsPerGame.add(children.size());   
		}
		
		
		//Printing the titles with their images for debugging
		int totalDistance = 0;
		for (int i = 0; i < titles.size(); i++) {
			System.out.print(titles.get(i).getTextContent() + ": ");
			System.out.println(images.get(i));
			System.out.println(" Developer: " + developers.get(i).getTextContent());
			System.out.println(" Genre: " + genres.get(i).getTextContent());
			System.out.println(" Description: " + descriptions.get(i).getTextContent());
			System.out.print(" Platforms: ");
			for (int j = 0; j < platformsPerGame.get(i); j++) {
				System.out.print(platforms.get(j + totalDistance) + " ");
			}
			totalDistance += platformsPerGame.get(i);
			System.out.println("\n");
		} 

		//System.out.println(page.asXml());
		
		
	}
}
