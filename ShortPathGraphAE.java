// --== CS400 File Header Information ==--
// Name: Sudheesh Dabbara
// Email: sdabbara@wisc.edu
// Group and Team: DF Red
// Group TA: Callie Kim
// Lecturer: Florian Heimerl | MWF 3:30-4:20
// Notes to Grader: N/A

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class extends the BaseGraph class and implements the ShortPathGraphInterface, which extends
 * the GraphADT. In doing so, this class models a graph that can have its shortest path computed
 * between two nodes, with cost, and shortest path between two nodes with one required node between
 * with cost, based on weights of edges
 * 
 * @author Sudheesh Dabbara
 *
 * @param <NodeType> The type of Node
 * @param <EdgeType> The type of Edge
 */
public class ShortPathGraphAE<NodeType, EdgeType extends Number> 
extends BaseGraph<NodeType, EdgeType>
implements ShortPathGraphInterface<NodeType, EdgeType> {

	/**
	 * SearchNode class from provided DjikstraGraph class, models a path with a total cost, contains
	 * the path's current node, total cost, and preceding path to the current node
	 *
	 */
	public class SearchNode implements Comparable<SearchNode> {
		public Node node;
		public SearchNode predecessor;
		public double cost;
		
		public SearchNode(Node node, double cost, SearchNode predecessor) {
			this.node = node;
			this.cost = cost;
			this.predecessor = predecessor;			
		}
		
		public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
	}
	
	/**
	 * Computes the shortest path between two provided nodes, returns the SearchNode with its "node"
	 * field as the end NodeType provided
	 * @param start is the Node to start at
	 * @param end is the Node to end at
	 * @return the SearchNode which has its "node" field as the end NodeType provided to the method
	 * @throws NoSuchElementException if either start or end are null, or if no path exists
	 */
	public SearchNode computeShortestPath(NodeType start, NodeType end) 
			throws NoSuchElementException {
		// If either of the provided nodes is null, throw the NoSuchElementException
    	if (this.nodes.get(start) == null || this.nodes.get(end) == null) {
    		throw new NoSuchElementException();
    	}
    	// Creating a HashTable to keep track of paths that have been discovered
    	Hashtable<NodeType, SearchNode> visitedNodes = new Hashtable<NodeType, SearchNode>();
    	// Setting the first SearchNode and making its node field the start node 
    	SearchNode first = new SearchNode(this.nodes.get(start), 0, null);
    	// Creating a priority queue for greedy selection in Dijkstra's algorithm
    	PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();
    	// Adding the first SearchNode to the pq
    	pq.add(first);
    	// Looping until the priority queue is empty meaning no more paths to discover
    	while (!pq.isEmpty()) {
    		// Taking the first SearchNode in the priority queue
    		SearchNode currentSN = pq.remove();
    		// Moving on if the node has already been visited, indicating its shortest path is found
    		if (visitedNodes.get(currentSN.node.data) != null) {
    			if (visitedNodes.get(currentSN.node.data).node == currentSN.node) {
    			}
    		}
    		// Else, visit the node, add the path to the HashTable, add all its outgoing edges to pq
    		else {
    			visitedNodes.put(currentSN.node.data, currentSN);
    			for (int i = 0; i < currentSN.node.edgesLeaving.size(); i++) {
    				Edge currentEdge = currentSN.node.edgesLeaving.get(i);
    				double cost = (double) (currentSN.cost + currentEdge.data.doubleValue());
    				SearchNode toAdd = new SearchNode(currentEdge.successor, cost, currentSN);
    				pq.add(toAdd);
    			}
    		}
    	}
    	// Once loop exists, meaning all paths are stored in HashTable, check if path to end is null
    	if (visitedNodes.get(end) == null) {
    		// If null, it means that there is no valid path to end from start, so throw Exception
    		throw new NoSuchElementException();
    	}
    	// If not, return the SearchNode that is the path to the end Node
        return visitedNodes.get(end);
	}
	
	@Override
	/**
	 * Gets the data of the shortest path from start to end with a required node to be visited
	 * Makes use of the computeShortestPath helper method
	 * @param start is the Node to start at
	 * @param end is the Node to end at
	 * @param required is the node required to be visited before the final node is end
	 * @return Returns List in the order of nodes to be visited with a start, end, and required node
	 * @throws NoSuchElementException if start, end, or required are null, or if no path exists
	 */
	public List<NodeType> shortestPathDataRequiredNode(NodeType start, NodeType end, 
			NodeType required) throws NoSuchElementException {
		// Throwing Exception if any Nodes are null or the path is non-existent
		if ((start == null) || (end == null) || (required == null)) {
			throw new NoSuchElementException("Please enter valid values");
		}
		// Instantiating the List to be returned, which is a LinkedList
		List<NodeType> toReturn = new LinkedList<NodeType>();
		// Getting the SearchNode from start to required, and then required to end
		SearchNode midway = this.computeShortestPath(start, required);
		SearchNode finalPath = this.computeShortestPath(required, end);
		if ((midway == null) || (finalPath == null)) {
			throw new NoSuchElementException("No path exists with provided values and required value");
		}
		
		// Looping backward through path from finalPath SearchNode to get path from start to end
		while (true) {
			// Adding the node to be visited to the front of List since looping backward
			toReturn.add(0, finalPath.node.data);
			// Once the path comes back to required, break
			if (finalPath.predecessor.predecessor == null) {
				break;
			}
			finalPath = this.computeShortestPath(required, finalPath.predecessor.node.data);
		}
		// Looping backward through path from start to required because required must be visited
		// Using same loop to do so as above
		while (true) {
			toReturn.add(0, midway.node.data);
			if (midway.predecessor == null) {
				break;
			}
			midway = this.computeShortestPath(start, midway.predecessor.node.data);
		}
		// Returning the final path as a LinkedList
		return toReturn;
	}

	@Override
	/**
	 * Gets the cost of the shortest path from start to end with a required node to be visited
	 * Makes use of the computeShortestPath helper method
	 * @param start is the Node to start at
	 * @param end is the Node to end at
	 * @param required is the node required to be visited before the final node is end
	 * @return Returns double cost of path with a start, end, and required node
	 * @throws NoSuchElementException if start, end, or required are null, or if no path exists
	 */
	public double shortestPathCostRequiredNode(NodeType start, NodeType end, NodeType required) {
		// Throwing NoSuchElementException if needed
		if ((start == null) || (end == null) || (required == null)) {
			throw new NoSuchElementException("Please enter valid values");
		}
		// Finding SearchNode from start to required, and required to end using computeShortestPath
		SearchNode midway = this.computeShortestPath(start, required);
		SearchNode finalPath = this.computeShortestPath(required, end);
		
		if ((midway == null) || (finalPath == null)) {
			throw new NoSuchElementException("No path exists with provided values");
		}
		
		// Computing total cost by getting cost from start to midway, and midway to end
		double toReturn = midway.cost;
		toReturn += finalPath.cost;
		// Returning total cost
		return toReturn;
	}

	@Override
	/**
	 * Gets the data of the shortest path from a start Node to an end Node
	 * Makes use of the computeShortestPath helper method
	 * @param start is the Node to start at
	 * @param end is the Node to end at
	 * @return Returns List in the order of nodes to be visited from start to end
	 * @throws NoSuchElementException if start or end are null, or if no path exists
	 */
	public List<NodeType> shortestPathData(NodeType start, NodeType end) {
		// Throwing NoSuchElementEception if necessary
		if (this.nodes.get(start) == null || this.nodes.get(end) == null) {
    		throw new NoSuchElementException();
    	}
		// Creating a List to return
    	List<NodeType> toReturn = new LinkedList<NodeType>();
    	// Getting the SearchNode result of computing shortest path from start to end
    	SearchNode path = computeShortestPath(start, end);
    	// Starting from the returned SearchNode, adding Node and then stepping back until done
    	while (true) {
    		// Adding the Node to the beginning of the path since we are stepping backward in path
    		toReturn.add(0, path.node.data);
    		// Break once the path reaches the start node
       		if (path.predecessor == null) {
    			break;
    		}
       		// Else making the path the path from start to predecessor SearchNode's Node
    		path = computeShortestPath(start, path.predecessor.node.data);
    	}
    	// Returning the List which is a LinkedList of the path from start to end
    	return toReturn;
	}

	@Override
	/**
	 * Gets the cost of the shortest path from a start Node to an end Node
	 * Makes use of the computeShortestPath helper method
	 * @param start is the Node to start at
	 * @param end is the Node to end at
	 * @return Returns cost of path from start to end
	 * @throws NoSuchElementException if start or end are null, or if no path exists
	 */
	public double shortestPathCost(NodeType start, NodeType end) {
		// Throwing NoSuchElementException if necessary
		if (this.nodes.get(start) == null || this.nodes.get(end) == null) {
    		throw new NoSuchElementException();
    	}
    	if ((this.computeShortestPath(start, end) == null)) {
    		throw new NoSuchElementException();
    	}
    	// Returning cost of the SearchNode returned from computing shortest path from start to end
        return this.computeShortestPath(start, end).cost;
	}

	/**
	 * This method finds and returns the NodeType in the graph corresponding to the one in parameter
	 * @param x is the target NodeType, which is compared to the nodes in the graph
	 * @return NodeType is the NodeType in the graph
	 */
	public NodeType getNode(NodeType x) {
		Set<NodeType> keys = nodes.keySet(); 
		for (NodeType key : keys) {
			if (key.equals(x)){
				return key;
			}
		}
		throw new NoSuchElementException("Node does not exist");
	}

}
