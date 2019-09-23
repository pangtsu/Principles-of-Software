package hw4;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.lang.Object;

import hw4.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class GraphWrapperTest{
	private static final int TIMEOUT = 2000;
	private final String NODE_A = "a";
	private final String NODE_B = "b";
	private final String NODE_H = "h";
	private final String EDGE_AA = "AA";
	private final String EDGE_AB = "AB";
	private final String EDGE_AB2 = "AB2";
	private final String EDGE_BA = "BA";
	private final String EDGE_BB = "BB";
	private final String EDGE_AH = "AH";

	
	private Graph<String, String> dgraph;
	private GraphWrapper graph;
	private Set<String> nodes;
	private Set<Edge<String, String>> edges;

	@Before
	public void setUp() throws Exception {
		graph = new GraphWrapper();
		nodes = new HashSet<String>();
		edges = new HashSet<Edge<String, String>>();
		dgraph = new Graph<String, String>();
	}

	@Test(timeout = TIMEOUT)
	public void testIsEmptyWhenConstructed() {
		boolean same = true;
		Iterator<String> itr = graph.listNodes();
		Iterator<String> itr2 = nodes.iterator();
		while(itr.hasNext()){
		  if(!itr2.hasNext() || !itr.next().equals(itr2.next())){
		    same = false;
		    break;
		  }
		}
		assertEquals(true, same);
	}
	
	@Test(timeout = TIMEOUT)
	public void testSizeWhenConstructed() {
		int count = 0;
		Iterator<String> itr = graph.listNodes();
		while(itr.hasNext()){
			  count ++;
			}
		assertEquals(count, dgraph.size());
	}
	
	
	@Test(timeout = TIMEOUT)
	public void testGetNodesWhenConstructed() {
		int count = 0;
		Iterator<String> itr = graph.listNodes();
		while(itr.hasNext()){
			  count ++;
			}
		assertEquals(count, dgraph.getNodes().size());
	}
	
	@Test(timeout = TIMEOUT)
	public void testToStringWhenConstructed() {
		assertEquals("{}", dgraph.toString());
	}
	
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testAddingNullNode() {
		dgraph.addNode(null);
	}
	
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testContainsNodeWithNullNode() {
		dgraph.containsNode(null);
	}
	
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testChildrenOfWithNullNode() {
		dgraph.childrenOf(null);
	}

	
	@Test(timeout = TIMEOUT)
	public void testContainsNodeOnNodeAWithoutAddingNodeA() {
		assertFalse(dgraph.containsNode(NODE_A));
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testChildrenOfNodeAWithoutAddingNodeA() {
		dgraph.childrenOf(NODE_A);
	}
	
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testAddEdgeWithFromToLabelNull() {
		dgraph.addEdge(null, null, null);
	}
	

	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testRemoveEdgeWithFromToLabelNull() {
		dgraph.removeEdge(null, null, null);
	}
	
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void tesNumberOfEdgesWithNode1Null() {
		dgraph.numberOfEdges(null, null);
	}
	
	
	@Test(timeout = TIMEOUT)
	public void testAddingOneNode() {
		assertTrue(dgraph.addNode(NODE_A));
	}
	
	
	@Test(timeout = TIMEOUT)
	public void testWrapperAddingOneNode() {
		graph.addNode(NODE_A);
		Iterator<String> itr = graph.listNodes();
		Set<String> n = new HashSet<String>();
		 while (itr.hasNext()) {
		        n.add(itr.next());
		    }
		assertEquals(n.size(), 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void testIsEmptyAfterAddingOneNode() {
		testAddingOneNode();
		assertFalse(dgraph.isEmpty());
	}
	
	@Test(timeout = TIMEOUT)
	public void testSizeAfterAddingOneNode() {
		testAddingOneNode();
		assertEquals(1, dgraph.size());
	}
	
	@Test(timeout = TIMEOUT)
	public void testToStringAfterAddingOneNode() {
		testAddingOneNode();
		assertEquals("{a=[]}", dgraph.toString());
	}
	
	
	@Test(timeout = TIMEOUT)
	public void testContainsNodeOnNodeAAfterAddingNodeA() {
		testAddingOneNode();
		assertTrue(dgraph.containsNode("a"));
	}
	
	@Test(timeout = TIMEOUT)
	public void testContainsNodeOnNodeBAfterAddingNodeA() {
		testAddingOneNode();
		assertFalse(dgraph.containsNode(NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeAWithoutAddingEdge() {
		testAddingOneNode();
		assertTrue(dgraph.childrenOf(NODE_A).isEmpty());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetNodesAfterAddingOneNode() {
		testAddingOneNode();
		nodes.add("a");
		assertEquals(nodes, dgraph.getNodes());
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testAddEdgeABWithoutAddingNodeB() {
		testAddingOneNode();
		dgraph.addEdge(NODE_A, NODE_B, EDGE_AB);
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testAddEdgeBAWithoutAddingNodeB() {
		testAddingOneNode();
		dgraph.addEdge(NODE_B, NODE_A, EDGE_BA);
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testNumberOfEdgesFromNodeAToNodeBWithoutAddingNodeB() {
		testAddingOneNode();
		dgraph.numberOfEdges(NODE_A, NODE_B);
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testNumberOfEdgesFromNodeBToNodeAWithoutAddingNodeB() {
		testAddingOneNode();
		dgraph.numberOfEdges(NODE_B, NODE_A);
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testRemoveEdgeWithToLabelNulla() {
		dgraph.removeEdge("zz", NODE_B, EDGE_AA);
	}
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testRemoveEdgeWithToLabelNullb() {
		dgraph.removeEdge(NODE_A, "cc", EDGE_AA);
	}
	
	@Test(timeout = TIMEOUT)
	public void testEdgeee() {
		dgraph.addNode(NODE_A);
		dgraph.addNode(NODE_B);
		dgraph.addEdge(NODE_A, NODE_B, EDGE_AB);
		dgraph.removeEdge(NODE_A, NODE_B, EDGE_AB);
		assertEquals(0, dgraph.numberOfEdges(NODE_A, NODE_B));

	}
	@Test(timeout = TIMEOUT)
	public void testEdge4() {
		graph.addNode(NODE_A);
		graph.addNode(NODE_B);
		graph.addEdge(NODE_A, NODE_B, EDGE_AB);
		Iterator<String> child = graph.listChildren(NODE_A);
		Set<String> n = new HashSet<String>();
		 while (child.hasNext()) {
		        n.add(child.next());
		    }
		assertEquals(n.size(), 1);

	}
	
	@Test(timeout = TIMEOUT)
	public void testEdge() {
		graph.addNode(NODE_A);
		graph.addNode(NODE_B);
		graph.addNode(NODE_H);
		graph.addNode("F");
		graph.addEdge(NODE_A, NODE_B, EDGE_AB);
		graph.addEdge(NODE_A, NODE_H, EDGE_AH);
		graph.addEdge(NODE_A, NODE_H, "one");
		graph.addEdge(NODE_A, "F", EDGE_AH);
		Iterator<String> child = graph.listChildren(NODE_A);
		Set<String> n = new HashSet<String>();
		 while (child.hasNext()) {
		        n.add(child.next());
		    }
		assertEquals(n.size(), 3);

	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void justedge() {
		Edge<String, String> e = new Edge<String, String>(null, null);
	}
	
	@Test(timeout = TIMEOUT)
	public void edgetostring() {
		Edge<String, String> e = new Edge<String, String>("A", "B");
		String result = e.toString();
		assertEquals(result, "A(B)");
	}
	
	@Test(timeout = TIMEOUT)
	public void compareedge() {
		Edge<String, String> e = new Edge<String, String>("A", "B");
		Edge<String, String> o = new Edge<String, String>("C", "Z");
		assertTrue(e.compareTo(o) < 0);	
	}
	/*

	@Test(timeout = TIMEOUT)
	public void testAddingSameNodeTwice() {
		testAddingOneNode();
		assertFalse(dgraph.addNode(NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testSizeAfterAddingSameNodeTwice() {
		testAddingSameNodeTwice();
		assertEquals(1, dgraph.get().size());
	}
	
	@Test(timeout = TIMEOUT)
	public void testToStringAfterAddingSameNodeTwice() {
		testAddingSameNodeTwice();
		assertEquals("{a=[]}", dgraph.get().toString());
	}
	
	@Test(timeout = TIMEOUT)
	public void testAddingTwoDifferentNodes() {
		testAddingOneNode();
		assertTrue(dgraph.get().addNode("b"));
	}
	
	@Test(timeout = TIMEOUT)
	public void testSizeAfterAddingTwoDifferentNodes() {
		testAddingTwoDifferentNodes();
		assertEquals(2, dgraph.get().size());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetNodesAfterAddingTwoDifferentNodes() {
		testAddingTwoDifferentNodes();
		nodes.add(NODE_A);
		nodes.add(NODE_B);
		assertEquals(nodes, dgraph.get().getNodes());
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeBWithoutAddingEdge() {
		testAddingTwoDifferentNodes();
		assertEquals(0, dgraph.get().numberOfEdges(NODE_A, NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeBToNodeAWithoutAddingEdge() {
		testAddingTwoDifferentNodes();
		assertEquals(0, dgraph.get().numberOfEdges(NODE_B, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testAddingReflexiveEdgeOnNodeA() {
		testAddingOneNode();
		assertTrue(dgraph.get().addEdge(NODE_A, NODE_A, EDGE_AA));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeAAfterAddingReflexiveEdgeOnNodeA() {
		testAddingReflexiveEdgeOnNodeA();
		edges.add(new Edge<String, String>("a", "AA"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_A));
		
	}
	
	@Test(timeout = TIMEOUT)
	public void testToStringAfterAddingReflexiveEdgeOnNodeA() {
		testAddingReflexiveEdgeOnNodeA();
		assertEquals("{a=[a(AA)]}", dgraph.get().toString());
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeAAfterAddingReflexiveEdge() {
		testAddingReflexiveEdgeOnNodeA();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_A, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testRemoveReflexiveEdgeOnNodeA() {
		testAddingReflexiveEdgeOnNodeA();
		assertEquals(new Edge<String, String>("a", "AA"), 
				     dgraph.get().removeEdge(NODE_A, NODE_A, EDGE_AA));
	}
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void testRemoveEdgeOnNodeAWithNonExistingEdge() {
		testAddingReflexiveEdgeOnNodeA();
		dgraph.get().removeEdge(NODE_A, NODE_B, EDGE_AA);
	}
	
	@Test(timeout = TIMEOUT)
	public void testRemoveEdgeWithExistingEdgeButDifferentLabel() {
		testAddingReflexiveEdgeOnNodeA();
		assertTrue(dgraph.get().removeEdge(NODE_A, NODE_A, EDGE_AB) == null);
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfAfterRemoveReflexiveEdgeOnNodeA() {
		testRemoveReflexiveEdgeOnNodeA();
		assertEquals(edges, dgraph.get().childrenOf(NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testToStringAfterRemoveReflexiveEdgeOnNodeA() {
		testRemoveReflexiveEdgeOnNodeA();
		assertEquals("{a=[]}", dgraph.get().toString());
	}
	
	@Test(timeout = TIMEOUT)
	public void testAddingOneEdgeBetweenTwoNodes() {
		testAddingTwoDifferentNodes();
		assertTrue(dgraph.get().addEdge(NODE_A, NODE_B, EDGE_AB));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeAAfterAddingOneEdgeBetweenNodeAAndNodeB() {
		testAddingOneEdgeBetweenTwoNodes();
		edges.add(new Edge<String, String>("b", "AB"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeBAfterAddingOneEdgeBetweenNodeAAndNodeB() {
		testAddingOneEdgeBetweenTwoNodes();
		assertEquals(edges, dgraph.get().childrenOf(NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeBAfterAddingOneEdgeBetweenNodeAAndNodeB() {
		testAddingOneEdgeBetweenTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_A, NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeBToNodeAAfterAddingOneEdgeBetweenNodeAAndNodeB() {
		testAddingOneEdgeBetweenTwoNodes();
		assertEquals(0, dgraph.get().numberOfEdges(NODE_B, NODE_A));
	}
	
	

	@Test(timeout = TIMEOUT)
	public void testAddingTwoInverseDirectionsEdgesBetweenNodeAAndNodeB() {
		testAddingOneEdgeBetweenTwoNodes();
		assertTrue(dgraph.get().addEdge(NODE_B, NODE_A, EDGE_BA));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeAAfterAddingTwoInverseDirectionsEdges() {
		testAddingTwoInverseDirectionsEdgesBetweenNodeAAndNodeB();
		edges.add(new Edge<String, String>("b", "AB"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeBAfterAddingTwoInverseDirectionsEdges() {
		testAddingTwoInverseDirectionsEdgesBetweenNodeAAndNodeB();
		edges.add(new Edge<String, String>("a", "BA"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testMakeCompleteGraphWithTwoNodes() {
		// To my understanding, complete graph of n nodes 
		// directed graph has n^2 edges.
		testAddingReflexiveEdgeOnNodeA();
		assertTrue(dgraph.get().addNode(NODE_B));
		assertTrue(dgraph.get().addEdge(NODE_A, NODE_B, EDGE_AB));
		assertTrue(dgraph.get().addEdge(NODE_B, NODE_A, EDGE_BA));
		assertTrue(dgraph.get().addEdge(NODE_B, NODE_B, EDGE_BB));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeAAfterMakingCompleteGraphWithTwoNodes() {
		testMakeCompleteGraphWithTwoNodes();
		edges.add(new Edge<String, String>("a", "AA"));
		edges.add(new Edge<String, String>("b", "AB"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeBAfterMakingCompleteGraphWithTwoNodes() {
		testMakeCompleteGraphWithTwoNodes();
		edges.add(new Edge<String, String>("a", "BA"));
		edges.add(new Edge<String, String>("b", "BB"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeAAfterMakingCompleteGraph() {
		testMakeCompleteGraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_A, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeBAfterMakingCompleteGraph() {
		testMakeCompleteGraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_A, NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeBToNodeAAfterMakingCompleteGraph() {
		testMakeCompleteGraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_B, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeBToNodeBAfterMakingCompleteGraph() {
		testMakeCompleteGraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_B, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testMakingMultigraphWithTwoNodes() {
		testMakeCompleteGraphWithTwoNodes();
		assertTrue(dgraph.get().addEdge(NODE_A, NODE_B, EDGE_AB2));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeAAfterMakingMultigraphWithTwoNodes() {
		testMakingMultigraphWithTwoNodes();
		edges.add(new Edge<String, String>("a", "AA"));
		edges.add(new Edge<String, String>("b", "AB"));
		edges.add(new Edge<String, String>("b", "AB2"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testChildrenOfNodeBAfterMakingMultigraphWithTwoNodes() {
		testMakingMultigraphWithTwoNodes();
		edges.add(new Edge<String, String>("a", "BA"));
		edges.add(new Edge<String, String>("b", "BB"));
		assertEquals(edges, dgraph.get().childrenOf(NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeAAfterMakingMakingMultigraph() {
		testMakingMultigraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_A, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeAToNodeBAfterMakingMakingMultigraph() {
		testMakingMultigraphWithTwoNodes();
		assertEquals(2, dgraph.get().numberOfEdges(NODE_A, NODE_B));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeBToNodeAAfterMakingMakingMultigraph() {
		testMakingMultigraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_B, NODE_A));
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOfEdgesFromNodeBToNodeBAfterMakingMakingMultigraph() {
		testMakingMultigraphWithTwoNodes();
		assertEquals(1, dgraph.get().numberOfEdges(NODE_B, NODE_B));
	}
	*/
}