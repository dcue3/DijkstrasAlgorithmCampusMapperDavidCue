// --== CS400 File Header Information ==--
// Name: Asish Das
// Email: das38@wisc.edu
// Group and Team: DF, RED
// Group TA: Callie 
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import java.util.Scanner;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import java.util.List;

public class FrontendDeveloperTests {
	
	/**
	 * Using TextUITester to check the output of the commands
	 * checking the add building instruction in the runCommandLoop
	 */
	
	@Test
	public void test1() {
		//starting the input with 3 will promt the add building method of the FD, which calls for
		//the name of the building to add
		TextUITester tester = new TextUITester("3\nAsish\nDas\n0\n");
		//scanner
		Scanner sc = new Scanner(System.in);
		//instance of the backend placeholder
		CampusNavigationInterface backend = new CampusNavigationFD();
		FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
		fd.runCommandLoop();
		String output = tester.checkOutput();
		// it will ask for the name of building to add and the department.
		assertThat(output, containsString("Enter the name of the building to add:"));
		assertThat(output, containsString("Enter the department name:"));
	}
	
	/**
	 * Testing the option 2 from the menu and see if it correctly calls the loadData from backend
	 */
	@Test
	public void test2() {
		//the command starts with 2, then a file name and then 0 - ends
		TextUITester tester = new TextUITester("2\nfile.txt\n0\n");
		//scanner
		Scanner sc = new Scanner(System.in);
		//instance of the backend placeholder
		CampusNavigationInterface backend = new CampusNavigationFD();
		FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
		fd.runCommandLoop();
		String output = tester.checkOutput();
		assertThat(output, containsString("Enter the name of the file"));
	}
	
	/**
	 * Checking the output of the shortestPath and the shortestPathCost between two buildings
	 */
	@Test
	public void test3() {
		TextUITester tester = new TextUITester("4\nA\n2\nC\n0\n");
		//scanner
		Scanner sc = new Scanner(System.in);
		//instance of the backend placeholder
		CampusNavigationInterface backend = new CampusNavigationFD();
		FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
		try{
		fd.runCommandLoop();
		String output = tester.checkOutput();
		//assertThat(output, containsString("Please provide inputs in correct order"));
		// this is supposed to throw exception due to wrong input types
		}catch (Exception e){
			return;
		}
	}
	
	/**
	 * Checking the output of the getShortestPathRequired and its cost while 
	 * also checking the if the correct output is displayed on the screen
	 */
	@Test
	public void test4() {
		//three arguments for the three buildings
		// 8 is the option for the getting shortes path with a required node
		TextUITester tester = new TextUITester("-12\n0\n");
		//scanner
		Scanner sc = new Scanner(System.in);
		//instance of the backend placeholder
		CampusNavigationInterface backend = new CampusNavigationFD();
		FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
		fd.runCommandLoop();
		String output = tester.checkOutput();
		assertThat(output, containsString("Please enter a valid choice"));
	}
	
	/**
	 * Checking the output of the command 1, which called the getData() from backend
	 */
	@Test
	public void test5() {
		//One argument for printing buildings and then quitting the program
		TextUITester tester = new TextUITester("1\n0\n");
		//scanner
		Scanner sc = new Scanner(System.in);
		//instance of the backend placeholder
		CampusNavigationInterface backend = new CampusNavigationFD();
		FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
		fd.runCommandLoop();
		String output = tester.checkOutput();
		assertThat(output, containsString("Chemistry Chamberlin Van Vleck Computer Sciences"));
	}
	
	/**
	 *This integration test tests code of datawrangler, frontend, AE and backend's loadMap method and get data method
	 *This also uses TextUITester to select input argument from the console
	 */
	@Test
	public void IntegrationTest1(){
		//at first it loads the map with command 2, then uses 1 to get map data.
		TextUITester tester = new TextUITester("2\nCampusMap.gv\n1\n0\n");
		Scanner sc = new Scanner(System.in);
		//instantiating the AE,DW,and BD
		ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>(); // instance of AE
		MapReader map = new MapReader(); // map reader
		CampusNavigationInterface backend = new CampusNavigationBD(graph, map); // instance of backend
		FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
		fd.runCommandLoop();
                String output = tester.checkOutput();
		// the file contains 11 buildings and 13 paths
		assertThat(output, containsString("Total Buildings in this campus map: 11\nTotal Paths in this campus map: 13\n"));

	}

	/**
	 *This Integration test tests the working of add a building function of the frontend with the other roles
	 */
        @Test
	public void IntegrationTest2(){
		//This demonstrates adding a building to the graph.
		//The TextUITester accepts the input as 2- loads the map, 3- command for adding the building and takes building name
		//and department name as inputs and prints the data before quitting
		TextUITester tester = new TextUITester("2\nCampusMap.gv\n3\ntestBuilding\ntestDepartment\n1\n0\n");
                Scanner sc = new Scanner(System.in);
                //instantiating the AE,DW,and BD
                ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>(); // instance of AE
                MapReader map = new MapReader(); // map reader
                CampusNavigationInterface backend = new CampusNavigationBD(graph, map); // instance of backend
                FrontendInterface fd = new campusMapperFD(sc, backend); //the frontend
                fd.runCommandLoop();
                String output = tester.checkOutput();
		//the output should have the following message if the building was added successfully
		assertThat(output, containsString("Added building Successfully\n"));
		// the map should now contain 12 buildings and 13 paths
                assertThat(output, containsString("Total Buildings in this campus map: 12\nTotal Paths in this campus map: 13\n"));
	}
	
	/**
	 *This method tests the backend's getShortestPathWithRequiredNodeCost.
	 */
	@Test
	public void CodeReviewOfBackend(){
		ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>(); // instance of AE
                MapReader map = new MapReader(); // map reader
                CampusNavigationInterface backend = new CampusNavigationBD(graph, map); // instance of backend
		System.out.println("A");
		try{
			backend.loadMap("CampusMap.gv");
		}catch(Exception e){
			Assertions.fail();
		}
		Double result = backend.getShortestPathWithRequiredNodeCost("Memorial Union", "Witte", "Sellery");
		assertEquals(Double.valueOf(result), Double.valueOf(2.0));
	}

	/**
	 *This method the readBuildings from file method of the DataWrangler 
	 */
	@Test
	public void CodeReviewOfDataWrangler(){
		MapReader map = new MapReader(); // Instantiating the mapReader
		List<Building> buildings = null;
  		try {
      	 		buildings = map.readBuildingsFromFile("CampusMap.gv"); // loading buildings from file
   		}catch (FileNotFoundException e) {
     			e.printStackTrace();
     			Assertions.fail(); // fail if file cannot be found
   		}
	}	
}

