package hw7;

import hw4.Graph;

import java.io.*;
import java.util.*;


public class CampusDataParser {
	
	/**
	 * Read campus buildings dataset.
	 * Each line of the input contains abbreviated name of a building, 
	 * full name of a building, and x and y coordinate of location of 
	 * the building's entrance. And these four tokens are separated by 
	 * a tab character.
	 * 
	 * @requires file is well-formed, with each line containing exactly four
     *           tokens separated by a tab character, or else starting with
     *           a # symbol to indicate a comment line.
	 * @param buildings file which contains data of campus buildings
	 * @param bNames a map of building's names that maps building's 
	 * 		  abbreviated name to full name (empty initially)
	 * @param bNaems2 a map of building's names that maps building's 
	 * 		  full name to abbreviated name (empty initially)
	 * @param bLocations a map of building's locations that maps 
	 * 		  building's abbreviated name to its location (empty initially)
	 * @throws Exception if the format of the file does not match the 
	 * 		   expected format
	 */
	public static void parseBuildingData(String buildings, 
			Map<String, String> bNames, Map<String, String> bNames2, 
			Map<String, Coordinates> bLocations, Map<String, Coordinates> bLocations2) throws Exception {
		
		BufferedReader reader = null;
	    try {
	    	reader = new BufferedReader(new FileReader(buildings));

	    	// Construct a map of buildings' names (map abbreviated name to full name) 
	    	// and a map of buildings' locations (map abbreviated name to 
	    	// location of the building).
	    	String inputLine;
	    	while ((inputLine = reader.readLine()) != null) {


	    		// Parse the data, stripping out quotation marks and throwing
	    		// an exception for malformed lines.
	    		String[] tokens = inputLine.split(",");
	    		if (tokens.length != 4) {
	    			throw new Exception("Line should contain one tab between " +
	    					"each pair of tokens: " + inputLine);
	    		}

	    		String id = tokens[1];
	    		String full_name = tokens[0];
	    		double x = Double.parseDouble(tokens[2]);
	    		double y = Double.parseDouble(tokens[3]);
	    		
   		// map building's full name to its id
	    		if (full_name.equals("")) {
	    		String tmp = "Intersection "+ id;
	    		bNames.put(id, tmp);
	    		bNames2.put(tmp, id);
	    		}
	    		else {
	    			bNames.put(id, full_name);
	    			bNames2.put(full_name, id);
	    			bLocations2.put(id, new Coordinates(x, y));
	    		}
	    		
	    		// map building's id to its location
	    		bLocations.put(id, new Coordinates(x, y));
	    	}
	    } catch (IOException e) {
	    	System.err.println(e.toString());
	    	e.printStackTrace(System.err);
	    } finally {
	    	if (reader != null) {
	    		reader.close();
	    	}
	    }
	}
	
	/**
	 * Read campus paths dataset and construct a campus paths.
	 * 
	 * @requires File is well-formed. File passed in should start with 
	 * 			 a non-indented line (if file is not empty) with 
	 * 			 coordinates of a point, followed by lines of 
	 * 			 coordinates and the distance between the coordinates of 
	 * 			 point in non-indented line and	coordinates of point in 
	 * 			 this line. Format of non-indented lines should be 
	 * 			 x coordinate of the point followed by a comma and then 
	 * 			 the y coordinate of the point (e.g. x,y). Format of 
	 * 			 indented lines should be like non-indented lines 
	 * 			 and followed by a colon and the distance between those 
	 * 			 two points.
	 * @param paths file which contains data of campus paths
	 * @param campusPaths a graph that contains campus paths (empty initially)
	 * @throws Exception if the format of the file does not match the 
	 * 		   expected format
	 */
	public static void buildCampusPaths(String paths, 
			Graph<Coordinates, Double> campusPaths, 
			Map<String, Coordinates> bLocations) throws Exception {
		
		BufferedReader reader = null;
	    try {
	    	reader = new BufferedReader(new FileReader(paths));
	    	
	    	String inputLine;
	    	while ((inputLine = reader.readLine()) != null) {
	    		
	    		// Ignore comment lines.
	    		if (inputLine.startsWith("#")) {
	    			continue;
	    		}

	    		String[] tokens = inputLine.split(",");
	    		
	    		String id1 = tokens[0];
	    		String id2 = tokens[1];

    			Coordinates coords1 = bLocations.get(id1);
    			Coordinates coords2 = bLocations.get(id2);
    		
    			if (!campusPaths.containsNode(coords1))
    				campusPaths.addNode(coords1);
    			
    			if (!campusPaths.containsNode(coords2))
    				campusPaths.addNode(coords2);
    			
    			double dist = Math.sqrt((coords2.getY() - coords1.getY()) * (coords2.getY() - coords1.getY()) + (coords2.getX() - coords1.getX()) * (coords2.getX() - coords1.getX()));
    			campusPaths.addEdge(coords1, coords2, dist);   
    			campusPaths.addEdge(coords2, coords1, dist); 
	    		
	    	}
	    } catch (IOException e) {
	    	System.err.println(e.toString());
	    	e.printStackTrace(System.err);
	    } finally {
	    	if (reader != null) {
	    		reader.close();
	    	}
	    }
	}
}