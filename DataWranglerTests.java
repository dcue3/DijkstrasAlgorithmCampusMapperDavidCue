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

  public static void main(String args[]) {
//    System.out.println(testLoadBuildings());
//    System.out.println(testLoadPaths());
//    System.out.println(testInvalidFile());
//    System.out.println(testInvalidFormat());
//    System.out.println(testDepartment());
  }


}
