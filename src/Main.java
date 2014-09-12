import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Main {

	public static void main(String[] args) {
		
		
		File input = new File("src/Html/13-14_results.html");
		
		try {
			fixHTML(input); //modifies HMTL input file to be JSoup compliant
			
			input = new File("src/Html/13-14_results.html");
			
			Document doc = Jsoup.parse(input, "UTF-8", "http://premierleague.com/");

			Elements games = doc.getElementsByClass("alt"); 

			Hashtable<String, Team> teams = getTeams(doc);
			Season nineTen = new Season();
			Element test = games.get(0);
			Match m = new Match(test);
			
			ArrayList<Match> matches = new ArrayList<Match>();
			
			int matchCount = 0;
			for (Element e : games){
				m = new Match(e);
				System.out.println(m);
				matches.add(m);
				matchCount++;
				
			}
			System.out.println(matchCount);
			
			for (Match t : matches){
				//System.out.println(t.getLoser());
				String winTeam = t.getWinner();
				String loseTeam = t.getLoser();
				if (winTeam.equals("draw")){
					continue;
				}
				Team winner = teams.get(winTeam);
				Team loser = teams.get(loseTeam);
				nineTen.add(winner, loser);
				
				
			}
			System.out.println(nineTen);
			//nineTen.bfs(loser);
			//nineTen.bfs(teams.get("Hull"));
			//nineTen.getShortestPath(teams.get("West Ham"), teams.get("Man City"));
			

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
	
	/**
	 * Jsoup does not allow for searching of empty class tags (ie   <tr class="">) so this is a hack to solve
	 */
	public static void fixHTML(File html){
		try {
			BufferedReader br = new BufferedReader(new FileReader(html));
			File temp = new File("temp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
			while (br.ready()){
				String line = br.readLine();
				
				if (line.trim().equals("<tr class=\"\">") || line.trim().equals("<tr class=\" fixturechangerow\">") || line.trim().equals("<tr class=\"alt fixturechangerow\">")){
					bw.write("\t\t\t<tr class=\"alt\">");
					bw.newLine();
				}else{
					bw.write(line);
					bw.newLine();
				}
			}
			
			
//			BufferedReader br2 = new BufferedReader(new FileReader(temp));
//			 String line = null;
//			 while ((line = br2.readLine()) != null) {
//			   System.out.println(line);
//			 }

			bw.close();
			br.close();
			 
	    		if(html.delete()){
	    			//System.out.println(html.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
			 temp.renameTo(html);
			 
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
