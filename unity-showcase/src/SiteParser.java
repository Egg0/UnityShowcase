import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SiteParser {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("https://unity3d.com/showcase/gallery").get();
		//System.out.println(doc.data());
		Elements games = doc.getAllElements();
		for (Element game : games) {
			System.out.println(game.data());
		}
		
	}
}
