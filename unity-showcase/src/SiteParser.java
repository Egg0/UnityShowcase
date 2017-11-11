import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
		
		// Get genre
		
		// Get description	
		DomNodeList<DomNode> descriptions = page.querySelectorAll(".mb15.description.clear p");
		
		// Get platforms TODO: iterate over games and store int array of platforms per game
		DomNodeList<DomNode> platforms = page.querySelectorAll(".platform-icon.pa0.m0a.mr1.mb0.tooltip .logo");
		List<Integer> platformsPerGame = new ArrayList<Integer>();
		
		// Printing the titles with their images for debugging
		for (int i = 0; i < titles.size(); i++) {
			System.out.print(titles.get(i).getTextContent() + ": ");
			System.out.println(images.get(i));
			System.out.println(descriptions.get(i).getTextContent());
			// Print platforms (NOT CORRECT, CAN BE MORE THAN 1)
			System.out.println(platforms.get(i));
		}
//		List<HtmlDivision> games = page.getByXPath("//*[@class=\"wrap\"]");
//		for (HtmlDivision game : games) {
//			List<?> titles = game.getByXPath("//h3/text()");
//			System.out.println(titles);
//		}
		
		//HtmlHeading3 title = page.getFirstByXPath("//h3[@class=\"mb10 title\"]");
		//System.out.println(page.asXml());
		
		
	}
}
