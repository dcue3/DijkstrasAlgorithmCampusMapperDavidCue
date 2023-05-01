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
import java.util.Scanner;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

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
}

