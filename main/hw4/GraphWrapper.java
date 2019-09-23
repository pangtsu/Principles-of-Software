package hw4;


import java.util.*;

public class GraphWrapper{
	
	private Graph<String, String> g;
	public GraphWrapper() {
		g = new Graph<String, String>();
	}
	
	
	public void addNode(String nodeData) {
		g.addNode(nodeData);
	}
	
	public void addEdge(String parentNode, String childNode, String edgeLabel) {
		g.addEdge(parentNode, childNode, edgeLabel);
	}
	
	
	public Iterator<String> listNodes(){
		Set<String> nodes = new TreeSet<String>(g.getNodes());
		Iterator<String> itr = nodes.iterator();
		return itr;
	}
	
	public Iterator<String> listChildren(String parentNode){
		Set<Edge<String, String>> node_edges = new TreeSet<Edge<String, String>>(new Comparator<Edge<String, String>>() {
					public int compare(Edge<String, String> e1, Edge<String, String> e2) {
						if(!(e1.getDestination().equals(e2.getDestination())))
							return e1.getDestination().compareTo(e2.getDestination());
						
						if (!(e1.getLabel().equals(e2.getLabel())))
							return e1.getLabel().compareTo(e2.getLabel());
						
						return 0;
					}
				});
    	
    	node_edges.addAll(g.childrenOf(parentNode));
    	Set<String> out = new LinkedHashSet<String>();
    	for (Edge<String, String> edge : node_edges)
    		out.add(edge.getDestination());
    	return out.iterator();
	}
	
	
	
	
}