import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CampusNavigationTest {

    @Test
    public void testLoadMap() throws FileNotFoundException {
        // Setup
        GraphADT<String, Double> graph = new ShortPathGraph<>();
        MapReaderInterface mapReader = new MapReader();
        CampusNavigation<String, Double> campusNav = new CampusNavigation<>(graph, mapReader);

        // Test loading valid map file
        campusNav.loadMap("valid_map.txt");
        Set<String> expectedNodes = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        Set<Double> expectedEdges = new HashSet<>(Arrays.asList(1.0, 2.0, 3.0));
        assertEquals(expectedNodes, graph.getNodes());
        assertEquals(expectedEdges, graph.getEdgeWeights());

        // Test loading empty map file
        campusNav.loadMap("empty_map.txt");
        assertTrue(graph.getNodes().isEmpty());
        assertTrue(graph.getEdges().isEmpty());

        // Test loading invalid map file
        campusNav.loadMap("invalid_map.txt");
        assertEquals(expectedNodes, graph.getNodes());
        assertEquals(expectedEdges, graph.getEdgeWeights());
    }

    @Test
    public void testAddBuilding() {
        // Setup
        GraphADT<BuildingInterface, Double> graph = new ShortPathGraph<>();
        CampusNavigation<BuildingInterface, Double> campusNav = new CampusNavigation<>(graph, null);

        // Test adding a building
        String name = "Building A";
        String department = "Department A";
        double x = 0.0;
        double y = 0.0;
        campusNav.addBuilding(name, department, x, y);
        BuildingInterface building = new Building(name, department, x, y);
        assertTrue(graph.getNodes().contains(building));
    }

    @Test
    public void testRemoveBuilding() {
        // Setup
        GraphADT<BuildingInterface, Double> graph = new ShortPathGraph<>();
        CampusNavigation<BuildingInterface, Double> campusNav = new CampusNavigation<>(graph, null);

        // Test removing a building
        BuildingInterface buildingA = new Building("Building A", "Department A", 0.0, 0.0);
        BuildingInterface buildingB = new Building("Building B", "Department B", 1.0, 1.0);
        graph.addNode(buildingA);
        graph.addNode(buildingB);
        campusNav.removeBuilding("Building A");
        assertFalse(graph.getNodes().contains(buildingA));
        assertTrue(graph.getNodes().contains(buildingB));
    }

    @Test
    public void testAddPath() {
        // Setup
        GraphADT<String, PathInterface> graph = new ShortPathGraph<>();
        CampusNavigation<String, PathInterface> campusNav = new CampusNavigation<>(graph, null);

        // Test adding a path
        String start = "A";
        String end = "B";
        double weight = 1.0;
        campusNav.addPath(start, end, weight);
        PathInterface path = new Path(start, end, weight);
        assertTrue(graph.getEdges().contains(path));
    }

    @Test
    public void testRemovePath() {
        // Setup
        GraphADT<String, PathInterface> graph = new ShortPathGraph<>();
        CampusNavigation<String, PathInterface> campusNav = new CampusNavigation<>(graph, null);

        // Test removing a path
        PathInterface pathA = new Path("A", "B", 1.0);
        PathInterface pathB = new Path("B", "C", 2.0);
       
