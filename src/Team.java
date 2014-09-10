
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Team {
	
		private String name;
		private Elements fixtures;
		
		public Team(String teamName){
			name = teamName;
			
		}
		
		
		
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Elements getFixtures() {
			return fixtures;
		}
		public void addFixture(Element fixture) {
			fixtures.add(fixture);
		}





		@Override
		public String toString() {
			return name;
		}
		
		
		
	

}


