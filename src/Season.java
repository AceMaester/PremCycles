
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;



/**
 * Digraph represenation of a premier league season
 */
public class Season<Team> {
		
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
	     * This implementation allows the creation of multi-edges and self-loops.
	     */
	    public void add (Team winner, Team loser) {
	    	
	    	if(winner.toString().equals("draw")){
	    		return;
	    	}
	    	
	        this.add(winner); this.add(loser);
	        neighbors.get(winner).add(loser);
	    }
	   
	    
	    public String toString () {
	        StringBuffer s = new StringBuffer();
	        for (Team v: neighbors.keySet()) s.append("\n   " + v + " -> " + neighbors.get(v));
	        return s.toString();      
	    }
		
		
}


