package hw4;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// Graph represents a mutable, directed graph
// @specfield nodes : Set<<param>String</param>> // node of the graph
// @specfield outgoing edges:
			// Collection<<param>HashSet<<param>LabeledEdge</param>></param>>
			// the edges (with label and destination, but without origin) of the graph


// Graph would be represented in nodes and their corresponding set of edges, all stored in map. 
public class Graph<T, E extends Comparable<E>> {
	// Rep invariant:
	// 	     graph != null
	// 		 Every node and every edge in graph are not null.
	//       graph must contain node n if n is included in any edge in the graph

	// Abstract function:
	// AF(this) = directed graph g such that
	// {} if g is an empty graph
	// if a is a node in g.nodes, then
	// {a=[b(e),c(f),....], b=[....], c=[.....]} o.w.
	// where b, c are destinations of a's outgoing edges and e, f are label of these edges, respectively.
	
	// constant variable for checkRep
//	private final static boolean CHECK = false;
	
	// our directed graph
	private final Map<T, HashSet<Edge<T, E>>> graph;
	
	// creates an empty graph
	// @effect constructs an empty directed graph
	public Graph() {
		graph = new HashMap<T, HashSet<Edge<T, E>>>();
	//	checkRep();
	}
	
	// adds node n to the graph if it is not already present
	// @parameter n node to be added
	// @modifies this.node
	// @effects adds node to n this.node if it's not already there
	// @return true if this graph did not already contain node n
	public boolean addNode(T n) {
		//checkRep();
		
		if (n == null) {
			throw new IllegalArgumentException("Node cannot be NULL!");
		}
		
		boolean success = false;
		if (!(graph.containsKey(n))) {
			graph.put(n, new HashSet<Edge<T, E>>());
			success = true;
		}
		//checkRep();
		return success;
	}
	// Adds edge from "from" to "to" with label to the graph if both nodes exist
	// and if the the edge does not already exist
	// @param from - origin of the edge
	// @param to - destination of the edge
	// @param label - label of the edge
	// @requires from, to, label != null
	// @modifies this.outgoing_edges
	// @effects add edges from "from" to "to" with label "label" to the graph if the same
	// edge is not already in the graph.
	// Adds edge from "from" to "to" with label
	// add to the graph if both nodes exist. 
	public boolean addEdge(T from, T to, E label) {
		//checkRep();
		
		if(from == null || to == null || label == null)
			throw new IllegalArgumentException("Node and label cannot be null");
		
		if (!(graph.containsKey(from)))
			throw new IllegalArgumentException("Node" + from + "passed in is not present in the graph");
		
		if (!(graph.containsKey(to)))
			throw new IllegalArgumentException("Node" + to + "passed in is not present in the graph");
		
		boolean success = false;
		HashSet<Edge<T,E>> from_edges = graph.get(from); // get the from node's set of outward edges
		Edge<T, E> e = new Edge<T,E>(to, label);
		
		if (!(from_edges.contains(e)));{
			from_edges.add(e);
			success = true;
		}
		
		//checkRep();
		return success;

	}
	
	// returns true if node "n" is in the graph
	// @param n , a node
	// @requires n != null
	// @returns true if the node is in this.graph
	public boolean containsNode(T n) {
		//checkRep();
		
		if (n == null)
			throw new IllegalArgumentException("Node cannot be null.");
		
		//checkRep();
		return graph.containsKey(n);
	}
	
	
	// returns a set of nodes
	// @returns a set of nodes
	public Set<T> getNodes() {
		//checkRep();
		return new HashSet<T>(graph.keySet());
	}
	
	// returns number of nodes in the graph
	// @return number of nodes in the graph
	public int size() {
		//checkRep();
		return graph.size();
	}
	
	// return true if the graph is empty
	// @return true if the graph is empty
	public boolean isEmpty() {
		//checkRep();
		return graph.isEmpty();
	}
	
	// returns a set of outgoing edges of node n
	// @param n , a node
	// @requires n!= null
	// @return a set of outgoing edges of node n
	// @throws IllegalArgumentException if node n is not in this.nodes
	public Set<Edge<T, E>> childrenOf(T n) {
		//checkRep();
		
		if (n == null)
			throw new IllegalArgumentException("Argument cannot be null.");
		
		if (!(containsNode(n)))
			throw new IllegalArgumentException("Node " + n + 
					" is not in the graph.");
		
		HashSet<Edge<T, E>> edges = graph.get(n);
		//checkRep();
		return new HashSet<Edge<T, E>>(edges);
	}
	
	
	// returns number of edges from one onode to anotehr node
	// @param from origin of the edge
	// @param to destination of the edge
	// @requires from and to != null
	// @throws IllegalArgumentException if either node from or to is not in this.nodes
	// @returns number of edges from node1 to node2
	public int numberOfEdges(T node1, T node2) {
		//checkRep();
		
		if (node1 == null || node2 == null)
			throw new IllegalArgumentException("Node cannot be null.");
		
		// check if nodes of the edge passed in already exist in the graph
		if (!(graph.containsKey(node1)))
			throw new IllegalArgumentException("Node " + node1 + 
					" passed in is not present in the graph.");
			
		if (!(graph.containsKey(node2)))
			throw new IllegalArgumentException("Node " + node2 + 
					" passed in is not present in the graph.");
		
		Set<Edge<T, E>> children = graph.get(node1);
		int count = 0;
		for (Edge<T, E> e : children) {
			// add 1 to count if the destination of the edge
			// is equal to node2
			if (e.getDestination().equals(node2))
				count++;
		}
		
		//checkRep();
		return count;
	}
	
	
	// removes an edge from to to with label label from the graph and returns the edge. Returns null if the specified edge does not exist
	// @param from, origin of the edge
	// @param to, destination of the edge
	// @param label, label of the edge
	// @requires from, to, label != null
	// @modifies this.outgoing edges
	// @effects removes specified edge from this.outgoing_edges
	// @throws IllegalArgumentException if either node from or to is not in this.nodes
	// @returns the edge removed from the graph, or null if the specified edge does not exist
	public /*@Nullable*/ Edge<T, E> removeEdge(T from, T to, E label) {
	//	checkRep();

		if (from == null || to == null || label == null)
			throw new IllegalArgumentException("Node and label cannot " + "be null.");

// check if nodes of the edge passed in already exist in the graph
		if (!(graph.containsKey(from)))
			throw new IllegalArgumentException("Node " + from + " passed in is not present in the graph.");

		if (!(graph.containsKey(to)))
			throw new IllegalArgumentException("Node " + to + " passed in is not present in the graph.");

		HashSet<Edge<T, E>> from_edges = graph.get(from);
		Edge<T, E> e = new Edge<T, E>(to, label);

// check if the specified edge is in the graph
		if (!(from_edges.contains(e)))
			return null;

		from_edges.remove(e);
	//	checkRep();
		return e;
}
	
	// returns a set view of the graph
	// @returns a set view of the graph
	public Set<Map.Entry<T, HashSet<Edge<T, E>>>> entrySet() {
		//checkRep();
		return Collections.unmodifiableSet(graph.entrySet());
	}
	
	// returns string representation of the graph
	// @return string representation of the graph
	
	public String toString() {
		//checkRep();
		return graph.toString();
	}
	
	//private void checkRep() throws RuntimeException {
	//	if (CHECK) {
			// check if the graph itself is null
	//		if (graph == null)
	//			throw new RuntimeException("The graph cannot be null.");
			
	//		Set</*@KeyFor("graph")*/ T> nodes = graph.keySet();
			
			// check if any node in graph is null
	//		for (/*@KeyFor("graph")*/ T n : nodes) {
	//			if (n == null)
	//				throw new RuntimeException("Node cannot be null.");
				
	//			HashSet<Edge<T, E>> node_edges = graph.get(n);
				// check if any edge in graph is null
	//			for (Edge<T, E> le : node_edges) {
	//				if (le == null)
	//					throw new RuntimeException("Edge cannot be null.");
					
					// check if any destination node of edges 
					// in the graph doesn't exist 
	//				if (!(graph.containsKey(le.getDestination())))
	//					throw new RuntimeException("Destination node of edge " +
	//							"must be in the graph before the edge exists.");
	//			}
	//		}
	//	}
}


 
