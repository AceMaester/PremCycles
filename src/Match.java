import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class Match {
	
		private String homeTeam;
		private String awayTeam;
		private int[] score;
		private String winner;
		
		public Match(Element html){
			score = new int[2]; // index 0=winner score index 1=loser score
			Elements aTags = html.getElementsByTag("a");
			String info = aTags.html();
			String[] splitInfo = info.split("\n");
			//System.out.println(splitInfo[1]);
			this.homeTeam = splitInfo[0];
			this.awayTeam = splitInfo[2];
			
			
		
			
			
		}

		public String getHomeTeam() {
			return homeTeam;
		}

		public String getAwayTeam() {
			return awayTeam;
		}

		public String getWinner() {
			return winner;
		}
		
		@Override
		public String toString() {
			
			return homeTeam + " " + score[0] + "-" + score[1] + " " + awayTeam; //format Chelsea 6-0 Arsenal
		}

		

}
