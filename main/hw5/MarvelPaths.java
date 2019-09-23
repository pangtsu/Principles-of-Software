package hw5;
import hw4.Graph;
import hw4.Edge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MarvelPaths {
	/**
	 * Builds graph using the data from the file.
	 * 
	 * @param filename file to be used to build the graph
	 * @requires filename != null
	 * @throws Exception if fail to read data from the specified 
	 * file or the format of the file does not match the 
	 * expected format
	 * @throws IllegalArgumentException if filename == null
	 */
	private Graph<String, String> mgraph;
	public void createNewGraph(String filename) {
		if (filename == null)
			throw new IllegalArgumentException("filename cannot be null.");
		
		mgraph = new Graph<String, String>();
		HashSet<String> characters = new HashSet<String>();
		HashMap<String, List<String>> books = new HashMap<String, List<String>>();
		try{MarvelParser.readData(filename, books, characters);}
		catch(IOException e) {
			System.out.println();
			e.printStackTrace();
		}
		
		// add characters (nodes) to the graph
		for (String character : characters)
			mgraph.addNode(character);
		
		// connect characters (nodes) with books (labels of edges)
		for (String book : books.keySet()) {
			// If [a, b, c, d] in book_1, first connect a with b, c, and d. 
			// Since it's directed graph, so connect both direction 
			// (both a to b and b to a via book_1). After adding all 
			// in and outgoing edges of a via book_1, connect b with c, d. 
			// And keep doing this until no edge with label book_1 to be added.
			List<String> chars = books.get(book);
			int i = 1;
			for (String c1 : chars) {
				List<String> chars_sublist = chars.subList(i, chars.size());
		
				for (String c2 : chars_sublist) {
					// don't allow adding reflexive edges
					if (!(c1.equals(c2))) {
						mgraph.addEdge(c1, c2, book);
						mgraph.addEdge(c2, c1, book);
					}
				}
				i++;
			}
		}

	}
	
	/**
	 * Finds the shortest path from one character to another character.
	 * 
	 * @param graph the graph used to find shortest path from start to end
	 * @param start a character
	 * @param end another character
	 * @requires graph != null && start != null && end != null
	 * @return the shortest path from start to end, or null if 
	 * no path exists from start to end
	 * @throws IllegalArgumentException if either start or end is 
	 * not in the graph
	 */
	public String findPath(String node1, String node2) {

		String s = new String("");
		boolean appear = false;
		if (!(mgraph.containsNode(node1))) {
			appear = true;
			s += "unknown character ";
			s += node1;
			s += "\n";
		}
		if (!(mgraph.containsNode(node2))) {
			if (node1 != node2) {
			appear = true;
			s += "unknown character ";
			s += node2;
			s += "\n";
			}
		}
		if (appear == true) {
			return s;
		}

		
		// nodes to be visited
		LinkedList</*@KeyFor("paths")*/ String> worklist = 
				new LinkedList</*@KeyFor("paths")*/ String>();
		
		// Each key in paths is a visited node and each value is 
		// a path from start to that node.
		HashMap<String, List<Edge<String, String>>> paths = 
				new HashMap<String, List<Edge<String, String>>>();
		
		paths.put(node1, new ArrayList<Edge<String, String>>());
		// start is a key of paths since the above code put it in paths
		@SuppressWarnings("keyfor")
		/*@KeyFor("paths")*/ String start2 = node1;
		worklist.add(start2);
		
		while (!(worklist.isEmpty())) {
			/*@KeyFor("paths")*/ String character = worklist.removeFirst();
			if (character.equals(node2)) {
				List<Edge<String, String>> path = paths.get(character);
				String result = new String("path from "+node1+" to "+node2+":"+"\n");
				if (path == null) {
					result += "no path found";
					result += "\n";
					return result;
				}
				else {
				String currentNode = node1;
				for (Edge<String,String> edge : path) {
					result += currentNode + " to " + edge.getDestination() + " via " + edge.getLabel() + "\n";
					currentNode = edge.getDestination();
				}
				return result;
			}
			}
			
			// use special comparator to get edge in alphabetical order
			// comparator compare the alphabetical order of destination of edge first,
			// then compare the alphabetical order of label of edge
			Set<Edge<String, String>> edges = 
					new TreeSet<Edge<String, String>>(new Comparator<Edge<String, String>>() {
						public int compare(Edge<String, String> e1, Edge<String, String> e2) {
							if(!(e1.getDestination().equals(e2.getDestination())))
								return e1.getDestination().compareTo(e2.getDestination());
							
							if (!(e1.getLabel().equals(e2.getLabel())))
								return e1.getLabel().compareTo(e2.getLabel());
							
							return 0;
						}
					});
			
			edges.addAll(mgraph.childrenOf(character));
			
			for (Edge<String, String> edge : edges) {
				String dest = edge.getDestination();
				
				if (!(paths.containsKey(dest))) {
					// if the node is not already visited, then map the path
					// to this node by appending edge from character to this node 
					// to path from start to character
					List<Edge<String, String>> path = paths.get(character);
					List<Edge<String, String>> path_post = 
							new ArrayList<Edge<String, String>>(path);
					path_post.add(edge);
					paths.put(dest, path_post);
					// dest is a key of paths since the above code put it in paths
					@SuppressWarnings("keyfor")
					/*@KeyFor("paths")*/ String dest2 = dest;
					worklist.add(dest2);  // mark this node as visited
				}
			}
		}
		
		// no path exists from start to end
		String result2 = new String("path from "+node1+" to "+node2+":"+"\n");
			result2 += "no path found";
			result2 += "\n";
			return result2;
		
	}
}
	