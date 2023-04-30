// --== CS400 File Header Information ==--
// Name: Asish Das
// Email: das38@wisc.edu
// Group and Team: DF, RED
// Group TA: Callie 
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class campusMapperFD<NodeType,EdgeType> implements FrontendInterface{
	private Scanner userInput;
	public CampusNavigationInterface campus;
	
	/**
	 * Contructor for the frontend class that takes a backedn obj as argument
	 */
	public campusMapperFD(Scanner userInput, CampusNavigationInterface campus) {
		//assigning the input
		this.campus = campus;
		this.userInput = userInput;
	}
	
	@Override
	public void runCommandLoop() {
		// TODO Auto-generated method stub
		int choice = -1;
		
		while(choice!=0) {
			System.out.println(menu());
			String input = userInput.nextLine().trim();
			int command = input.charAt(0) - '0';
			
			if(command < 0 || command > 9) {
				System.out.println("Please enter a valid choice");
			}
			
			if(command == 1) {
				getDataFD();
			}
			else if(command == 2) {
				System.out.println("Enter the name of the file: ");
				String name = input;
				loadDataFD(name);
			}
			else if(command == 3) {
				try {
					String b;
					String d;
				
					System.out.println("Enter the name of the building to add: ");
					b = userInput.nextLine();
					System.out.println("Enter the department name: ");
					d = userInput.nextLine();
					addBuildingFD(b,d);
				}catch(Exception e) {
					System.out.println("Please provide inputs in correct order");
				}
			}
			else if(command == 4) {
				try {
					String b;
					String d;
				
					System.out.println("Enter the name of the building 1:");
					b = userInput.nextLine();
					System.out.println("Enter the name of the building 2:");
					d = userInput.nextLine();
					System.out.println("Enter the cost of the Path:");
					Double e = userInput.nextDouble();
					addPathFD(b,d,e);
					}catch(Exception e) {
						System.out.println("Please provide inputs in correct order");
				}
			}
			
			else if(command == 5) {
				String name = input;
				removeBuildingFD(name);
			}
			
			else if(command == 6) {
				try {
					String b;
					String d;
				
					System.out.println("Enter the name of the building 1:");
					b = userInput.nextLine();
					System.out.println("Enter the name of the building 2:");
					d = userInput.nextLine();
					removePathFD(b,d);
					}catch(Exception e) {
						System.out.println("Please provide inputs in correct order");
				}
			}
			else if(command == 7) {
				String b;
				String d;
			
				System.out.println("Enter the name of the building 1:");
				b = userInput.nextLine();
				System.out.println("Enter the name of the building 2:");
				d = userInput.nextLine();
				
				System.out.println("Path:- "+ getShortestPath(b,d)+ " Cost of this Path: " + getShortestPathCost(b,d));
			}
			else if(command == 8) {
				String b;
				String d;
				String f;
			
				System.out.println("Enter the name of the building 1:");
				b = userInput.nextLine();
				System.out.println("Enter the name of the building 2:");
				d = userInput.nextLine();
				System.out.println("Enter the name of the building 3(Required):");
				f = userInput.nextLine();
				
				System.out.println("Path:- "+ getShortestPathRequired(b,d,f)+ " Cost of this Path: " + getShortestPathRequiredCost(b,d,f));

			}
			//exiting the loop
			else if(command == 0) {
				choice = 0;
			}
		}
		System.out.println("some");
	}

	@Override
	public String menu() {
		String menu = "Welcome to our movie rating database, please select an option: \n" ;
		menu += "1. Print Map Data \n";
		menu += "2: Load Campus Mapper \n";
		menu += "3: Add Building by name \n";
		menu += "4: Add Path by building names \n";
		menu += "5: Remove building by name \n";
		menu += "6: Remove path by names \n";
		menu += "7: Find the shortest path between buildings \n";
		menu += "8. Find shortest path between 3 buildings \n";
		menu += "9: Clear \n";
		menu += "0. Quit \n";


		return menu;
	}
	
	/**
	 * Loads the file using the method in backend
	 * @Param filename- the file to be loaded
	 */
	@Override
	public void loadDataFD(String fileName) {
		// TODO Auto-generated method stub
		try {
			campus.loadMap(fileName);
			System.out.println("Loaded Map successfully.");
		}catch(Exception e) {
			System.out.println("Error: invalid file name");
		}
		
	}

	/**
	 * Adds a building to the dataset
	 * @param name- the name of the building
	 * @param department- the department associated with the building
	 */
	@Override
	public void addBuildingFD(String name, String department) {
		// TODO Auto-generated method stub
		try {
			campus.addBuilding(name, department);
			System.out.println("Added building Successfully");
		}catch(Exception e) {
			System.out.println("Adding building failed.");
		}
	}
	
	/**
	 * Adds a path between two buildings
	 * @param a - the name of building a
	 * @param b - the name of the building b
	 * @param z - cost of the path
	 */
	@Override
	public void addPathFD(String a, String b, double z) {
		// TODO Auto-generated method stub
		try {
			campus.addPath(a, b, z);
			System.out.println("Added path successfully");
		}catch(Exception e) {
			System.out.println("Adding path failed");
		}
	}
	
	/**
	 * removes a building from the dataset
	 * @param name- the name of the building to be removed
	 */
	@Override
	public void removeBuildingFD(String name) {
		// TODO Auto-generated method stub
		try {
			campus.removeBuilding(name);
			System.out.println("Removed building successfully");
		}catch(Exception e) {
			System.out.println("Removing building was unsuccessful");
		}
	}
	
	/**
	 * Removes a path between two buildings
	 * @param building1 - the name of the building1
	 * @param building2 - the name of the building2
	 */
	@Override
	public void removePathFD(String building1, String building2) {
		// TODO Auto-generated method stub
		try {
			campus.removePath(building1, building2);
			System.out.println("Removed path successfullly");
		}catch(Exception e) {
			System.out.println("Removing path was unsuccessfull");
		}
	}
	
	/**
	 * Returns the shortes path between two buildings
	 * @param building1 - the name of the building1
	 * @param building2 - the name of the building2
	 * @return the shortest path between two buildings
	 */
	@Override
	public List<NodeType> getShortestPath(String building1, String building2) {
		// TODO Auto-generated method stub
		try{
		LinkedList<NodeType> shortestPath = new LinkedList<>();
		shortestPath = (LinkedList<NodeType>) campus.getShortestPath(building1, building2);
		return shortestPath;
		}catch(Exception e){
			System.out.println("It failed");
		}
	}
	
	/**
	 * Returns the cost of the shortest path between two buildings
	 * @param building1 - the name of the building1
	 * @param building2 - the name of the building2
	 * @return the cost of the shortest path between two buildings
	 */
	@Override
	public double getShortestPathCost(String building1, String building2) {
		// TODO Auto-generated method stub
		return campus.getShortestPathCost(building1, building2);
	}
	
	/**
	 * Returns the shortes path between two buildings
	 * @param building1 - the name of the building1
	 * @param building2 - the name of the building2
	 * @param building3 - the name of the building3 it must include
	 * @return the shortest path between three buildings
	 */
	@Override
	public List<NodeType> getShortestPathRequired(String building1, String building2, String building3) {
		// TODO Auto-generated method stub
		LinkedList<NodeType> shortestPathRequired = new LinkedList<>();
		shortestPathRequired = (LinkedList<NodeType>) campus.getShortestPathWithRequiredNodeData(building1, building2, building3);
		return shortestPathRequired;
	}

	/**
	 * Returns the cost of the shortest path between two buildings
	 * @param building1 - the name of the building1
	 * @param building2 - the name of the building2
	 * @param building3 - the name of the building3 it must include
	 * @return the cost of the shortest path between three buildings
	 */
	@Override
	public double getShortestPathRequiredCost(String building1, String building2, String building3) {
		// TODO Auto-generated method stub
		return campus.getShortestPathWithRequiredNodeCost(building1, building2, building3);		
	}

	/**
	 * Gets the data of the map(number of buildings, no of edges etc)
	 */
	@Override
	public void getDataFD() {
		// TODO Auto-generated method stub
		try {
			System.out.println(campus.getData());
		}catch(Exception e) {
			System.out.println("Load a file.");
		}
	}

	/**
	 * Clears the loaded map
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		try {
		campus.clear();
		System.out.println("The map has been cleared successfully");
		}catch(Exception e) {
			System.out.println("Map could not be cleared");
		}
	}

}

