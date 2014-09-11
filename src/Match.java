import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class Match {
	
		private String homeTeam;
		private String awayTeam;
		private int[] score;
		private String winner;
		private String loser;
		
		public Match(Element html){
			score = new int[2]; // index 0=home score index 1=away score
			Elements aTags = html.getElementsByTag("a");
			String info = aTags.html();
			String[] splitInfo = info.split("\n");
			this.homeTeam = splitInfo[0];
			this.awayTeam = splitInfo[2];
			String scoreString = splitInfo[1];
			score[0] = Integer.parseInt(scoreString.split(" ")[0]);
			score[1] = Integer.parseInt(scoreString.split(" ")[2]);
			findWinner();
			findLoser();

			
			
		}
		
		private void findLoser() {
			if (score[0] < score[1]){
				this.loser = this.homeTeam;
			}else if(score[1] < score[0]){
				this.loser = this.awayTeam;
			}else{
				this.loser = "draw"; //case where scores are equal so there is no technical winner (assuming no team is called 'draw' god forbid)
			}
			
		}

		private void findWinner(){
			if (score[0] > score[1]){
				this.winner = this.homeTeam;
			}else if(score[1] > score[0]){
				this.winner = this.awayTeam;
			}else{
				this.winner = "draw"; //case where scores are equal so there is no technical winner (assuming no team is called 'draw' god forbid)
			}
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
		
		public String getLoser() {
			return loser;
		}
		@Override
		public String toString() {
			
			return homeTeam + " " + score[0] + "-" + score[1] + " " + awayTeam; //format Chelsea 6-0 Arsenal
		}

		

}
