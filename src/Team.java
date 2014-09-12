
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Team {
	
		private String name;
		private boolean visited; //for traversal
		private Team previous;  //for traversal
		private Elements fixtures;
		
		
		public Team(String teamName){
			name = teamName;
			setVisited(false);
			setPrevious(null);
			
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

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public Team getPrevious() {
			return previous;
		}

		public void setPrevious(Team previous) {
			this.previous = previous;
		}
		
		
		
	

}


