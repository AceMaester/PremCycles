import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Main {

	public static void main(String[] args) {
		
		
		File input = new File("src/Html/09-10_results.html");
		
		try {
			Document doc = Jsoup.parse(input, "UTF-8", "http://premierleague.com/");
			//Elements games = doc.getElementsByClass("contentTable");
			Elements games = doc.getElementsByClass("alt"); 
			
			
			Hashtable<String, Team> teams = getTeams(doc);
			Season<Team> nineTen = new Season<Team>();
			Element test = games.get(0);
			Match m = new Match(test);
			
			
			
			Elements home = test.getElementsByTag("a");
			//System.out.println(home.html());
			
			//Elements home = test.getElementsByClass("clubs rHome");
			//System.out.println("home: " + home.get(0));
			
			

			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

	}
	
	/**
	 * Returns the table of all teams that have ever competed in the Premier league
	 */
	public static Hashtable<String, Team> getTeams(Document doc){
		
		Hashtable<String, Team> teams = new Hashtable<String, Team>();
		Element clublist = doc.getElementById("club");
		Elements clubs = clublist.getElementsByAttribute("value");
		
		for (int i = 1; i < clubs.size(); i++){ // exclude first one as it is just ALLCLUBS in HTML
			teams.put(clubs.get(i).text(), new Team(clubs.get(i).text()));
		}
		
		
		
		
		return teams;
		
	}
	

}
