// --== CS400 File Header Information ==--
// Name: Sudheesh Dabbara
// Email: sdabbara@wisc.edu
// Group and Team: DF Red
// Group TA: Callie Kim
// Lecturer: Florian Heimerl | MWF 3:30-4:20
// Notes to Grader: N/A

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains junit tests for the AlgorithmEngineer's code, testing its functionality
 * @author Sudheesh Dabbara
 *
 */
class AlgorithmEngineerTests {
	// Creating a new instance of the ShortPathGraphAE class to test on
	protected ShortPathGraphAE<BuildingAE, Integer> test = null;
	// Creating new buildings that are of a hard-coded placeholder class made for testing purposes
	protected BuildingAE CS = new BuildingAE("Computer Science Building", "Computer Sciences");
	protected BuildingAE Chemistry = new BuildingAE("Chemistry Building", "Biological Sciences");
	protected BuildingAE DeLuca = new BuildingAE("Biochemistry Building", "Biological Sciences");
	protected BuildingAE Mosse = new BuildingAE("Hummanities Building", "Hummanities");
	protected BuildingAE VanVleck = new BuildingAE("Math Building", "Mathematics");
	protected BuildingAE Smith = new BuildingAE("Smith Residence Hall", "Residencey");
	protected BuildingAE UnionSouth = new BuildingAE("Union South", "Union Student Center");
	protected BuildingAE Sewell = new BuildingAE("Sewell Social Science Building","Social Science");
	
	@BeforeEach
	public void createInstances() {
		// Instantiating the ShortPathGraphAE object to test on
		test = new ShortPathGraphAE<BuildingAE, Integer>();
		// Filling the test graph with the placeholder class Buildings
		// Inserting new buildings to test graph, using Prof Florian's graph: 004-25-Shortest-Paths
		// However, instead of Characters, using buildings and keeping the edges as double still
		test.insertNode(CS);
    	test.insertNode(Chemistry);
    	test.insertNode(DeLuca);
    	test.insertNode(Mosse);
    	test.insertNode(VanVleck);
    	test.insertNode(Smith);
    	test.insertNode(UnionSouth);
    	test.insertNode(Sewell);
    	// Inserting the edges with Integers, the NodeTypes are buildings
    	// These edges the same format as the graph from Prof Florian's notes: 004-25-Shortest-Paths
    	test.insertEdge(CS, DeLuca, 2);
    	test.insertEdge(CS, Chemistry, 4);
    	test.insertEdge(CS, VanVleck, 15);
    	test.insertEdge(Chemistry, Mosse, 1);
    	test.insertEdge(Chemistry, VanVleck, 10);
    	test.insertEdge(DeLuca, Mosse, 5);
    	test.insertEdge(Mosse, VanVleck, 3);
    	test.insertEdge(Mosse, Smith, 0);
    	test.insertEdge(Smith, Sewell, 4);
    	test.insertEdge(Smith, Mosse, 2);
    	test.insertEdge(UnionSouth, Sewell, 4);
    	test.insertEdge(DeLuca, Chemistry, 1);
	}
	
	@Test
	/**
	 * Testing proper functionality of the shortestPathDataRequiredNode() method in the AE's code
	 * using a known path and making sure that the returned List from calling the method has the 
	 * same as the known path. Using the start node Deluca, end node Sewell, and node required to be
	 * visited at some point between start (DeLuca) and end (Sewell) as Smith. Making sure that the
	 * path expected from DeLuca to Sewell with Smith in between is the same as the one returned
	 */
	void RequiredNodeDataStraightPathtest1() {
		// Making a call to a known path and checking if returned path is as expected
		// Path is: DeLuca -(1)> Chem -(1)> Mosse -(0)> Smith -(4)> Sewell
		try {
			// Making a String of this expected path
			String expectedPath = "Biochemistry Building -> Chemistry Building -> Hummanities "
					+ "Building -> Smith Residence Hall -> Sewell Social Science Building";
			// Getting the LinkedList returned from calling shortestPathDataRequiredNode()
			LinkedList<BuildingAE> returnedPath = 
			      (LinkedList<BuildingAE>) test.shortestPathDataRequiredNode(DeLuca, Sewell, Smith);
			String returnedPathFinal = "";
			// Turning the LinkedList into a String representation of the returned data
			for (int i = 0; i < returnedPath.size(); i++) {
				returnedPathFinal += returnedPath.get(i).getName() + " -> ";
			}
			// Substring to remove extra "->" added to the returned String representation in loop
			returnedPathFinal = returnedPathFinal.substring(0, returnedPathFinal.length() - 4);
			// Checking that the returnedPath is as expected and failing otherwise
			assertEquals(expectedPath, returnedPathFinal);
		} catch (Exception e) {
			// Failing if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}
	}
	
	/**
	 * Tests the proper functionality of the shortestPathCostRequiredNode() method in the AE's code
	 * by using an expected path in the graph and checking that the returned cost of the path from
	 * the method is the same as the cost from the known path in the graph. Using the start node
	 * DeLuca, end node Sewell, and node required to be visisted between the start and end at some
	 * point as Smith. The expected cost to be returned is 6.0
	 */
	@Test
	void RequiredNodeCostStraightPathTest2() {
		// Testing a known path: DeLuca to Sewell, and Smith must be visited between the two
		// The expected cost for this call is 6.0
		// Path is: DeLuca -(1)> Chem -(1)> Mosse -(0)> Smith -(4)> Sewell, total is 6.0
		try {
			// Setting an expectedCost as 6.0
			double expectedCost = 6.0;
			// Getting the cost returned from calling the method
			double returnedCost = test.shortestPathCostRequiredNode(DeLuca, Sewell, Smith);
			// Continuing if the expectedCost and returnedCost are equal
			assertEquals(expectedCost, returnedCost);
		} catch (Exception e) {
			// Failing if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}
		// Passing if method works as expected
	}
	
	@Test
	/**
	 * Testing for the expected Exceptions to be thrown: when a path does not exist, or any of the
	 * provided nodes are null
	 */
	void ExpectedExceptionsTest3() {
		// Making UnionSouth null for test purposes, there are no paths to it so it is insignificant
		UnionSouth = null;
		// Testing a null node in the method call to make sure the NoSuchElementException is thrown
		try {
			// Testing Cost with required Node method when a provided node is null
			test.shortestPathCostRequiredNode(UnionSouth, CS, DeLuca);
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			// Failing if the expected NoSuchElementException is not thrown
			fail("Wrong Exception was thrown");
		}
		// Testing with an impossible path to make sure the NoSuchElementException is thrown
		try {
			// Since DeLuca must be visited at some point between the first node (CS), and the final
			// node (Mosse), which is impossible since no paths go from DeLuca to Mosse, this call
			// should throw a NoSuchElementException
			test.shortestPathDataRequiredNode(CS, Mosse, DeLuca);
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			// Failing if the expected NoSuchElementException is not thrown
			fail("Wrong Exception was thrown");
		}
	}
	
	
	@Test
	/**
	 * This tests the functionality of the shortestPath() with required node methods when the required
	 * node can only be visited by visiting the end node, meaning the path would visit end two times
	 * however, required would still be in between the first and last node visiting, the path would
	 * be similar to this: start > end > required > end. This can be modeled by having CS as start,
	 * Mosse as end, and Smith as a required node. This would require the path to go from start to
	 * end to required to end, which complies with rule of the end being visited once AFTER
	 * the required node is visited, even if the end node is visited before the required node to get
	 * to the required node.
	 */
	void RequiredNodeRevistEndTest4() {
		// Testing a known path: CS > Mosse with Mosse being the last node in the path and Smith 
		// being visited at least once before Mosse being visited as the last node. In this path,
		// it is expected that Mosse (end) is visited twice, once to get to the required node, and 
		// once again at the end because it must be the last node in the path
		// The expected data to be returned from a call with these parameters is:
		// Path is: CS -(2)> DeLuca -(1)> Chemistry -(1)> Mosse -(0)> Smith -(2)> Mosse
		try {
			// Making a String of this expected path
			String expectedPath = "Computer Science Building -> Biochemistry Building -> Chemistry "
					+ "Building -> Hummanities Building -> Smith Residence Hall -> Hummanities"
					+ " Building";
			// Getting the LinkedList returned from calling shortestPathDataRequiredNode()
			LinkedList<BuildingAE> returnedPath = 
			      (LinkedList<BuildingAE>) test.shortestPathDataRequiredNode(CS, Mosse, Smith);
			String returnedPathFinal = "";
			// Turning the LinkedList into a String representation of the returned data
			for (int i = 0; i < returnedPath.size(); i++) {
				returnedPathFinal += returnedPath.get(i).getName() + " -> ";
			}
			// Substring to remove extra "->" added to the returned String representation in loop
			returnedPathFinal = returnedPathFinal.substring(0, returnedPathFinal.length() - 4);
			// Checking that the returnedPath is as expected and failing otherwise
			assertEquals(expectedPath, returnedPathFinal);
		} catch (Exception e) {
			// Failing if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}

		// Testing a known path: CS > Mosse with Mosse being the last node in the path and Smith 
		// being visited at least once before Mosse being visited as the last node. In this path,
		// it is expected that Mosse (end) is visited twice, once to get to the required node, and 
		// once again at the end because it must be the last node in the path
		// The expected cost for this call is 6.0:
		// Path is: CS -(2)> DeLuca -(1)> Chemistry -(1)> Mosse -(0)> Smith -(2)> Mosse
		// Total cost is 6.0
		try {
			// Setting an expectedCost as 6.0
			double expectedCost = 6.0;
			// Getting the cost returned from calling the method
			double returnedCost = test.shortestPathCostRequiredNode(CS, Mosse, Smith);
			// Continuing if the expectedCost and returnedCost are equal
			assertEquals(expectedCost, returnedCost);
		} catch (Exception e) {
			// Failing if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}
		// Pass test if the returned List of data and cost is right and no Exception is thrown
	}
	
	@Test
	/**
	 * Tests the proper functionality of the getNode() method in the ShortPathGraphAE class. Making
	 * sure that two Buildings that have the same name are equal even if they are of two different
	 * building objects, and making sure an Exception is thrown when the building does not exist in
	 * the Graph. Using the hard-coded building objects to test this.
	 */
	void getNodeTest5() {
		// Creating 3 building objects to test with; two have the same name, the third does not
		BuildingAE test1 = new BuildingAE("Name is equal", "N/A");
		BuildingAE test2 = new BuildingAE("Name is equal", "N/A");
		BuildingAE test3 = new BuildingAE("Name is not equal", "N/A");
		// Adding test1 to the graph, it will be the only node in the graph so the test can check
		// that getNode() returns test1 when passed test2 (a object that is equal to it) and throws
		// a NoSuchElementException when passed test3 (a object that does not exist in the graph.
		test.insertNode(test1);
		// Checking if getNode(test2) returns test1
		try {
			// Calling the method
			BuildingAE returned = test.getNode(test2);
			// Checking if returned is equal to test1 (which is should be)
			assertEquals(returned, test1);
		} catch (Exception e) {
			// Failing if an Exception is thrown
			fail("Unexpected Exception was thrown");
		}
		
		// Now checking if getNode(test3) throws a NoSuchElementException
		try {
			// Calling the method
			BuildingAE returnedFail = test.getNode(test3);
			// Failing if no Exception is thrown
			fail("NoSuchElementException was not thrown even though it was expected to be thrown");
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			// Failing if the wrong Exception is thrown
			fail("Wrong Exception was thrown");
		}
	}
}
