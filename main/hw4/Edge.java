package hw4;


// Edge<> represents an outgoing labeled edge of a node and the destination(end) of the edge
// @specfield end: String
// @specfield label: String
public class Edge<T, E extends Comparable<E>> implements Comparable<Edge<T,E>> {
	
	// rep invariant:
	// end != null && label != null
	
	// abstract function
	// AF(this) = a labeled edge without origin le such that 
	// le.end = this.end
	// le.label = this.label
	private final T end;
	private final E label;
	
	
	// creates a labeled edge
	// @param destination of the edge
	// @param l, label of the edge
	// @requires d !+ null && l != null
	// @effects constructs a labeled edge with destination destination and label l
	public Edge(T destination, E l) {
		if (destination == null || l == null)
			throw new IllegalArgumentException("Arguments cannot be null.");
	
	end = destination;
	label = l;
	//checkRep();
	}
	
	// returns the destination of this edge
	// @returns the destination of this edge
	public T getDestination() {
	//	checkRep();
		return end;
	}
	
	// returns the label of this edge
	// @return the label of this edge
	public E getLabel() {
		//checkRep();
		return label;
	}
	
	// returns string representation of this edge
	// @return string representation of this edge
	@Override
	public String toString() {
		//checkRep();
		String result = end.toString() + "(" + label.toString() + ")";
		//checkRep();
		return result;
	}
	// returns true if o represent the same edge as this edge
	// @param o object to be compared 
	// @return true if o represents the same edge (same end and same label) as this edge
	@Override
	public boolean equals(Object o) {
	//	checkRep();
		if (!(o instanceof Edge<?,?>)) {
			return false;}
		
		Edge<?,?> tmp = (Edge<?,?>) o;
		//checkRep();
		return end.equals(tmp.end) && label.equals(tmp.label);
	}
	
	// returns hash code of this edge
	// @return hash code of this edge
	
	@Override
	public int hashCode() {
	//	checkRep();
		return end.hashCode() + label.hashCode();
	}
	
	// compares this object with the specified object for ordering. Returns a neg int, 
	// zero, or a pos int as the object is less than, equal to or greater than the specified object
	// @param le object to be compared
	// @return a neg int, zero or pos int as the object is less than, equal to, or greater than the specified object, respectively.
	public int compareTo(Edge<T, E> le) {
	//	checkRep();
		// compare label first
		if (!(label.equals(le.label))) {
		//	checkRep();
			return label.compareTo(le.label);
		}
				
		// if label is the same as this, compare destination
		// using their hashcode
		if (!(end.equals(le.end))) {
		//	checkRep();
			return end.hashCode() - le.end.hashCode();
		}
				
	//	checkRep();
		return 0;
		
	}
	
/*	private void checkRep() throws RuntimeException {
		if (end == null){
			throw new RuntimeException("Destination cannot be NULL!");
		}
		if (label == null) {
			throw new RuntimeException("Label cannot be NULL!");
		}
	}
	*/
}
