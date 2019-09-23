package hw6;
import hw4.Graph;
import hw4.Edge;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class contains a method to build graph using data 
 * from specified file, and a method to find the 
 * minimum-cost path from one node to another node.
 */


public class MarvelPaths2 {
	
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
	private Graph<String, Double> mgraph;
	public void createNewGraph(String filename) {
		if (filename == null)
			throw new IllegalArgumentException("filename cannot be null.");
		HashMap<String, HashMap<String, Integer>> count_common = new HashMap<String, HashMap<String, Integer>>();
		mgraph = new Graph<String, Double>();
		try{MarvelParser2.readData(filename, count_common);}
		catch(IOException e) {
			System.out.println();
			e.printStackTrace();
		}
		
		// add characters (nodes) to the graph
		for (String node : count_common.keySet())
			mgraph.addNode(node);
		
		// add weighted edges of nodes to the graph, 
	    // where the weight of the edge between two characters 
		// is the inverse of how many comic books two 
		// characters are in together
		for (String node1 : count_common.keySet()) {			
			HashMap<String, Integer> count = count_common.get(node1);
			for (String node2 : count.keySet()) {
				int num = count.get(node2);
				mgraph.addEdge(node1, node2, 1.0 / num);
				mgraph.addEdge(node2, node1, 1.0 / num);
			}			
		}

	}
	
	
	/**
	 * Finds the minimum-cost path from one character to another character.
	 * 
	 * @param graph the graph used to find shortest path from start to end
	 * @param start a character
	 * @param end another character
	 * @requires graph != null && start != null && end != null
	 * @return the minimum-cost path from start to end, or null if 
	 * no path exists from start to end
	 * @throws IllegalArgumentException if either start or end is 
	 * not in the graph
	 */
	public static <T> /*@Nullable*/ List<Edge<T, Double>> findPath(
			Graph<T, Double> graph, T start, T end) {
		if (graph == null)
			throw new IllegalArgumentException("graph cannot be null.");
		
		if (start == null || end == null)
			throw new IllegalArgumentException("start and end cannot be null.");
		
		if (!(graph.containsNode(start)))
			throw new IllegalArgumentException("Characters " + start + 
					"is not in the graph.");
		
		if (!(graph.containsNode(end)))
			throw new IllegalArgumentException("Characters " + end + 
					"is not in the graph.");
		
		// Each element in active is a path from start to a given node.
		// A path's "priority" in the queue is the total cost of that path.
		// Create the priority queue with specified comparator 
		// to order paths by their total costs.
		PriorityQueue<ArrayList<Edge<T, Double>>> active = 
				new PriorityQueue<ArrayList<Edge<T, Double>>>(10, 
						new Comparator<ArrayList<Edge<T, Double>>>() {
							public int compare(ArrayList<Edge<T, Double>> path1, 
											   ArrayList<Edge<T, Double>> path2) {
								Edge<T, Double> dest1 = path1.get(path1.size() - 1);
								Edge<T, Double> dest2 = path2.get(path2.size() - 1);
								if (!(dest1.getLabel().equals(dest2.getLabel())))
									return dest1.getLabel().compareTo(dest2.getLabel());
								
								return path1.size() - path2.size();
							}
						});
		
		// known contains nodes for which we know the minimum-cost path from start
		Set<T> known = new HashSet<T>();
		
		// the path from start to itself has zero cost since it contains no edge,
		// so add the edge with zero cost to active
		ArrayList<Edge<T, Double>> begin = new ArrayList<Edge<T, Double>>();
		begin.add(new Edge<T, Double>(start, 0.0));
		active.add(begin);
		
		while (!active.isEmpty()) {
			// minPath is the lowest-cost path in active and 
			// is the minimum-cost path for some node
			ArrayList<Edge<T, Double>> minPath = active.poll();
			
			// I've checked the PriorityQueue is not empty before calling
			// poll method, so minPath won't be null
			@SuppressWarnings("nullness")
			Edge<T, Double> endOfMinPath = minPath.get(minPath.size() - 1);
			// minDest is destination of minPath
			T minDest = endOfMinPath.getDestination();
			// minCost is the cost of minPath
			double minCost = endOfMinPath.getLabel();
			
			// return minPath if the destination of minPath 
			// is equal to end passed in by client
			if (minDest.equals(end))
				return minPath;
			
			// if the minimum-cost path from start to minDest is already known, 
			// skip this one and examine the next one in active
			if (known.contains(minDest))
				continue;
			
			Set<Edge<T, Double>> children = graph.childrenOf(minDest);
			for (Edge<T, Double> e : children) {
				// If we don't know the minimum-cost path from start to child,
	            // examine the path we've just found
				if (!known.contains(e.getDestination())) {
					double newCost = minCost + e.getLabel();
					ArrayList<Edge<T, Double>> newPath = 
							new ArrayList<Edge<T, Double>>(minPath); 
					newPath.add(new Edge<T, Double>(e.getDestination(), newCost));
					active.add(newPath);
				}
			}
			
			// after examining all the edges begin from minDest, 
			// marked minDest as known
			known.add(minDest);
		}
		
		// no path exists from start to end
		return null;
	}
	
	
}