
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;



/**
 * Digraph represenation of a premier league season
 */
public class Season {
		
		private String name;
		
	    private Map<Team, List<Team>> neighbors = new HashMap<Team,List<Team>>();
	    
	    /**
	     * Add a team to the graph.  Nothing happens if team is already in graph.
	     */
	    public void add (Team team) {
	        if (neighbors.containsKey(team)) return;
	        neighbors.put(team, new ArrayList<Team>());
	        
	    }
	    
	    /**
	     * True is season contains the team.
	     */
	    public boolean contains (Team team) {
	        return neighbors.containsKey(team);
	    }
	    
	    /**
	     * Add an edge to the graph from winning team to losing team.
	     * No multi-edges allowed.
	     */
	    public void add (Team winner, Team loser) {
	    	
	    	if(winner.toString().equals("draw")){
	    		return;
	    	}
	    	
	        this.add(winner); 
	        this.add(loser);
	        if(neighbors.get(winner).contains(loser)){ //check if edge already exists
	        	return;
	        }else{
	        	neighbors.get(winner).add(loser);
	        }
	    }
	   
	    
	    public String toString () {
	        StringBuffer s = new StringBuffer();
	        for (Team v: neighbors.keySet()) s.append("\n   " + v + " -> " + neighbors.get(v));
	        return s.toString();      
	    }
		
	    //conducts a Breadth First Search over the graph (useful for finding shortest paths and also traversal)
	    public ArrayList<Team> bfs(Team t)
	    {
	    	//BFS uses Queue data structure
	    	Team startTeam = t;
	    	ArrayList<Team> bfsOrder = new ArrayList<Team>();
	    	
	    	Queue<Team> q=new LinkedList<Team>();
	    	q.add(startTeam);
	    	//System.out.println(startTeam);
	    	bfsOrder.add(startTeam);
	    	startTeam.setVisited(true);
	    	
	    	while(!q.isEmpty())
	    	{
	    		Team n=(Team)q.remove();
	    		Team child=null;
	    		while((child=getUnvisitedChildTeam(n))!=null)
	    		{
	    			child.setVisited(true);
	    			//System.out.println(child); For printing out BFS ordering
	    			bfsOrder.add(child);
	    			child.setPrevious(n);
	    			q.add(child);
	    		}
	    	}
	
	    	//System.out.println("--------------------------------------------");
	    	
	    	return bfsOrder;
	    }
	    
	    public void getShortestPath(Team fromTeam, Team toTeam){
	    	ArrayList<Team> bfsOrder = bfs(fromTeam);
	    	Team t = toTeam;
	    	Deque<Team> s = new LinkedList<Team>(); //stack of teams to pop in reverse order
	    	int dist = -1; //distance from team to itself is 0. team to another is 1.
	    	
	    	while(t != null){
	    		s.push(t);
	    		dist++;
	    		t = t.getPrevious();
	    	}
	    	
	    	while(s.size() > 1){ //print out list of teams 
	    		System.out.print(s.pop() + " -> ");
	    	}
	    	System.out.print(s.pop()); //last team (toTeam) is finish 
	    	System.out.print("   Distance: " + dist + " teams");
	    	//Clear visited property of nodes
	    	clearNodes();
	    }
	    
	  //Clear visited property of nodes
	    private void clearNodes() {
			for(Team v: neighbors.keySet()){
				v.setVisited(false);
			}
			
		}

	    

		private Team getUnvisitedChildTeam(Team t) {
			
			List<Team> beatenTeams = neighbors.get(t);
			
			for(Team v: beatenTeams){
				if(!v.isVisited()){
					return v;
				}
			}
			return null;
		}
		
}


