import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DataWranglerTests {

  @Test
  public void testLoadBuildings() { // tests reading buildings from file
    MapReader map = new MapReader(); // creating mapreader
    List<Building> buildings = null; // creating list of buildings
    try {
      buildings = map.readBuildingsFromFile("CampusMap.gv"); // load buildings from file
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      Assertions.fail(); // fail if file cannot be found
    }
    List<Building> buildingsTarget = new LinkedList<>();
    buildingsTarget.add(new Building("Memorial Union"));
    buildingsTarget.add(new Building("Sellery")); // add all buildings that should have been loaded
    buildingsTarget.add(new Building("Witte"));
    buildingsTarget.add(new Building("Lakeshore"));
    buildingsTarget.add(new Building("Van Vleck"));
    buildingsTarget.add(new Building("Capitol Building"));
    buildingsTarget.add(new Building("The Nick"));
    buildingsTarget.add(new Building("Camp Randall"));
    buildingsTarget.add(new Building("Mifflin"));
    buildingsTarget.add(new Building("Picnic Point"));
    buildingsTarget.add(new Building("Union South"));

    for (int i = 0; i < buildings.size(); i++) { // all buildings in lists should be equal
      if (buildings.get(i).compareTo(buildingsTarget.get(i)) != 0) {
        Assertions.fail();
      }
    }

  }

  
  @Test
  public void testLoadPaths() { // tests reading paths from file
    MapReader map = new MapReader(); // create mapreader
    List<PathInterface> paths = null; // create list of paths
    try {
      paths = map.readPathsFromFile("CampusMap.gv"); // load paths from file
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      Assertions.fail(); // fail if file is not found
    }
    List<PathInterface> pathsTarget = new LinkedList<>();
    pathsTarget.add(new Path(1, "Memorial Union", "Sellery")); // add all expected paths
    pathsTarget.add(new Path(1, "Witte", "Sellery")); // that should have been loaded
    pathsTarget.add(new Path(2, "Memorial Union", "Witte"));
    pathsTarget.add(new Path(10, "Memorial Union", "Lakeshore"));
    pathsTarget.add(new Path(13, "Lakeshore", "Sellery"));
    pathsTarget.add(new Path(5, "Memorial Union", "Van Vleck"));
    pathsTarget.add(new Path(5, "Lakeshore", "Van Vleck"));
    pathsTarget.add(new Path(20, "Memorial Union", "Capitol Building"));
    pathsTarget.add(new Path(1, "The Nick", "Sellery"));
    pathsTarget.add(new Path(10, "Mifflin", "Witte"));
    pathsTarget.add(new Path(50, "Picnic Point", "Memorial Union"));
    pathsTarget.add(new Path(100, "Picnic Point", "Capitol Building"));
    pathsTarget.add(new Path(10, "Memorial Union", "Union South"));

    for (int i = 0; i < paths.size(); i++) { // lists should have same contents
      if (paths.get(i).compareTo(pathsTarget.get(i)) != 0) {
        Assertions.fail();
      }
    }

  }

  @Test
  public void testInvalidFile() {
    MapReader map = new MapReader(); // create mapreader
    try {
      map.readBuildingsFromFile("ThisFileDoesNotExist.txt"); //input nonexistent file to readBuildings
      Assertions.fail();
    } catch (FileNotFoundException e) { //should throw filenotfoundexception
    } catch (Exception e) {
      Assertions.fail();
    }
    try {
      map.readPathsFromFile("ThisFileDoesNotExist.txt");//input nonexistent file to readPaths
      Assertions.fail();
    } catch (FileNotFoundException e) { //should throw filenotfoundexception
    } catch (Exception e) {
      Assertions.fail();
    }

  }

  @Test
  public void testInvalidFormat() {
    MapReader map = new MapReader(); // create mapreader
    List<PathInterface> paths = null; // create list of paths
    List<Building> buildings = null; // create list of buildings
    try {
      buildings = map.readBuildingsFromFile("InvalidFormat.gv"); //read in buildings from incorrectly formatted file
    } catch (Exception e) {
      Assertions.fail();
    }
    try {
      paths = map.readPathsFromFile("InvalidFormat.gv"); //read in paths from incorrectly formatted file

    } catch (Exception e) {
      Assertions.fail();
    }

    List<PathInterface> pathsTarget = new LinkedList<>(); //create new linkedlist for paths/buildings
    List<Building> buildingsTarget = new LinkedList<>();
    pathsTarget.add(new Path(8, "Union South", "Witte"));
    buildingsTarget.add(new Building("Sellery"));
    buildingsTarget.add(new Building("Union South")); //add expect paths/buildings to new lists
    buildingsTarget.add(new Building("Witte"));

    for (int i = 0; i < paths.size(); i++) {
      if (paths.get(i).compareTo(pathsTarget.get(i)) != 0) { //compare paths to expected paths
        Assertions.fail();
      }
    }
    for (int i = 0; i < buildings.size(); i++) {
      if (buildings.get(i).compareTo(buildingsTarget.get(i)) != 0) { //compare buildings to expected buildings
        Assertions.fail();
      }
    }

  }

  
  @Test
  public void testDepartment() {
    MapReader map = new MapReader(); // creating mapreader
    List<Building> buildings = null; // creating list of buildings
    try {
      buildings = map.readBuildingsFromFile("CampusMap.gv"); // load buildings from file
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      Assertions.fail(); // fail if file cannot be found
    }
    List<Building> buildingsTarget = new LinkedList<>();
    buildingsTarget.add(new Building("Memorial Union", "Union"));
    buildingsTarget.add(new Building("Sellery", "Housing")); // add all buildings that should have
                                                             // been loaded as well as their expected departments
    buildingsTarget.add(new Building("Witte", "Housing"));
    buildingsTarget.add(new Building("Lakeshore", "Housing"));
    buildingsTarget.add(new Building("Van Vleck", "Math"));
    buildingsTarget.add(new Building("Capitol Building", "Government"));
    buildingsTarget.add(new Building("The Nick", "Athletics"));
    buildingsTarget.add(new Building("Camp Randall", "Football"));
    buildingsTarget.add(new Building("Mifflin"));
    buildingsTarget.add(new Building("Picnic Point", "Nature"));
    buildingsTarget.add(new Building("Union South"));

    for (int i = 0; i < buildings.size(); i++) { // compare expected buildings and their departments to the output from function. 
      if (buildings.get(i).compareTo(buildingsTarget.get(i)) != 0 && buildings.get(i)
          .getDepartment().compareTo(buildingsTarget.get(i).getDepartment()) != 0) {
        Assertions.fail();
      }
    }

  }

  @Test
  public void integrationBD() {
    try {
    ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>();
    MapReader mapReader = new MapReader(); //creating backend and necessary components
    CampusNavigationBD BD = new CampusNavigationBD(graph, mapReader);

    try {
    BD.loadMap("CampusMap.gv"); //loading map through backend
    }catch(Exception e) {
      Assertions.fail();
    }
    String result = BD.getData();  //getting data from buildings/paths successfully loaded
    String expected =  "Total Buildings in this campus map: " + 11 + "\n" +  "Total Paths in this campus map: " + 13;
    if(!result.equals(expected)) {  //check for expected result
      Assertions.fail(); //fail if not equal
    }

    }catch(Exception e) {Assertions.fail();} //fail if any exceptions
  }

  @Test
  public void integrationAE() { //testing integration of DW/AE, also tests backend briefly 
    try {
    ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>();
    MapReader mapReader = new MapReader(); //creating backend/AE class and necessary components
    CampusNavigationBD BD = new CampusNavigationBD(graph, mapReader);

    try {
    BD.loadMap("CampusMap.gv"); //loading map through backend
    }catch(Exception e) {
      Assertions.fail();
    }

    Double result = BD.getShortestPathWithRequiredNodeCost("Picnic Point", "Witte","Sellery"); //getting AE's shortest path cost through backend
    Double expected = 52.0; //AE's code should return length of 52.0
    if(!result.equals(expected)) {  //check for expected result
      System.out.println(result);
      Assertions.fail(); //fail if not equal
    }

    }catch(Exception e) {Assertions.fail();} //fail if any exceptions
  }

  @Test
  public void codeReviewOfAlgorithmEngineer1() { //this method tests adding/removing paths/buildings in AE's code.
    try {
    ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>(); //create graph
    BuildingInterface building = new Building("Sellery", "Housing");
    graph.insertNode(building); //add two buildings
    BuildingInterface building2 = new Building("Witte", "Housing");
    graph.insertNode(building2);

    BuildingInterface startB = new Building("Sellery");
    BuildingInterface endB = new Building("Witte");
    startB = graph.getNode(startB);
    endB = graph.getNode(endB); //add path between two buildings
    graph.insertEdge(startB, endB, 5.0);

    if(graph.getNodeCount()!=2) { //should be two buildings
      Assertions.fail();
    }
    if(graph.getEdgeCount()!=1) { //should be one edge
      Assertions.fail();
    }

    }catch(Exception e) {Assertions.fail();} //fail if any exceptions

  }

  @Test
  public void codeReviewOfAlgorithmEngineer2() { //this method tests getting shortest path in AE's code.
    try {
    ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>(); //create graph
    BuildingInterface building = new Building("Sellery", "Housing");
    graph.insertNode(building); //add all buildings
    BuildingInterface building2 = new Building("Witte", "Housing");
    graph.insertNode(building2);
    BuildingInterface building3 = new Building("Memorial Union", "Union");
    graph.insertNode(building3);
    BuildingInterface building4 = new Building("Capitol", "Government");
    graph.insertNode(building4);


    BuildingInterface startB = new Building("Sellery");
    BuildingInterface endB = new Building("Witte");
    startB = graph.getNode(startB);
    endB = graph.getNode(endB); //add paths between buildings
    graph.insertEdge(startB, endB, 5.0);

    BuildingInterface memu = graph.getNode(building3);
    BuildingInterface cap = graph.getNode(building4);

    graph.insertEdge(startB, memu, 2.0);
    graph.insertEdge(memu, endB, 2.0);
    graph.insertEdge(cap, memu, 10.0);
    graph.insertEdge(endB, cap, 4.0);

    List<BuildingInterface> result = graph.shortestPathData(startB, cap); //getting path from AE
    List<BuildingInterface> target = new LinkedList<>();
    target.add(startB);
    target.add(memu); //creating correct list of buildings that should be returned from AE
    target.add(endB);
    target.add(cap);

    for(int i=0; i<result.size();i++){
    System.out.println(result.get(i).getName());
    }
    for(int i=0; i<target.size(); i++) {
      if(result.get(i).compareTo(target.get(i))!=0) { //checking that lists are equal
        System.out.println(target.get(i).getName());
        System.out.println(result.get(i).getName());
        Assertions.fail();
      }
    }

    }catch(Exception e) {Assertions.fail();} //fail if any exceptions
  }

  public static void main(String args[]) {
//    System.out.println(testLoadBuildings());
//    System.out.println(testLoadPaths());
//    System.out.println(testInvalidFile());
//    System.out.println(testInvalidFormat());
//    System.out.println(testDepartment());
  }


}
