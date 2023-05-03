import java.io.FileNotFoundException;
import java.util.*;

public class CampusNavigationBD implements CampusNavigationInterface<BuildingInterface, Double> {

    private ShortPathGraphInterface<BuildingInterface, Double> graph;
    private MapReaderInterface mapReader;
    private int totalBuildings;
    private int totalPaths;

    public CampusNavigationBD(ShortPathGraphInterface<BuildingInterface, Double> graph, MapReaderInterface mapReader) {
        this.graph = graph;
        this.mapReader = mapReader;
    }

    public void loadMap(String filename) throws FileNotFoundException {
        // Using mapReader to get a list of nodes and edges
        List<Building> nodesToAdd = new ArrayList<>();
        List<PathInterface> edgesToAdd = new ArrayList<>();
        nodesToAdd = mapReader.readBuildingsFromFile(filename);
        edgesToAdd = (List<PathInterface>) mapReader.readPathsFromFile(filename);

        // Adding each node to the graph using the addNode() method of ShortPathGraph
        for (int i = 0; i < nodesToAdd.size(); i++) {
            graph.insertNode((Building)nodesToAdd.get(i));
            this.totalBuildings++;
        }

        // Adding each edge to the graph using the addEdge() method of ShortPathGraph
        for (int i = 0; i < edgesToAdd.size(); i++) {
        	Path x = (Path) edgesToAdd.get(i);
        	double dist = x.getDistance();
        	BuildingInterface pred = new Building(x.getPredecessor());
        	BuildingInterface succ = new Building(x.getSuccessor());
        	BuildingInterface pred1 = graph.getNode(pred);
        	BuildingInterface succ1 = graph.getNode(succ);
            graph.insertEdge(pred1, succ1, dist);
            graph.insertEdge(succ1, pred1, dist);
	    this.totalPaths++;
        }
    }

    public void addBuilding(String name, String department) {
        BuildingInterface building = new Building(name, department);
        graph.insertNode(building);
        this.totalBuildings++;
    }

    public void removeBuilding(String name) {
    	BuildingInterface toRemove = new Building(name);
        toRemove = graph.getNode(toRemove);
        graph.removeNode(toRemove);
        this.totalBuildings--;
    }

    public void addPath(String start, String end, double weight) {
    	BuildingInterface startB = new Building(start);
    	BuildingInterface endB = new Building(end);
    	startB = graph.getNode(startB);
    	endB = graph.getNode(endB);
        graph.insertEdge(startB, endB, weight);
	graph.insertEdge(endB, startB, weight);
        this.totalPaths++;
    }

    public void removePath(String start, String end) {
    	BuildingInterface startB = new Building(start);
    	BuildingInterface endB = new Building(end);
    	startB = graph.getNode(startB);
    	endB = graph.getNode(endB);
    	graph.removeEdge(startB, endB);
    	graph.removeEdge(endB, startB);
	this.totalPaths--;
    }

	@Override
	public List<BuildingInterface> getShortestPath(String building1, String building2) {
		BuildingInterface startB = new Building(building1);
		BuildingInterface endB = new Building(building2);
    	startB = graph.getNode(startB);
    	endB = graph.getNode(endB);
    	List<BuildingInterface> toReturn = graph.shortestPathData(startB, endB); 	
		return toReturn;
	}

	@Override
	public double getShortestPathCost(String building1, String building2) {
		BuildingInterface startB = new Building(building1);
		BuildingInterface endB = new Building(building2);
    	startB = graph.getNode(startB);
    	endB = graph.getNode(endB);
    	double toReturn = graph.shortestPathCost(startB, endB); 	
		return toReturn;
	}

	@Override
	public List<BuildingInterface> getShortestPathWithRequiredNodeData(String building1, String building2,
			String requiredNode) {
		BuildingInterface startB = new Building(building1);
		BuildingInterface endB = new Building(building2);
		BuildingInterface midB = new Building(requiredNode);
    	startB = graph.getNode(startB);
    	endB = graph.getNode(endB);
    	midB = graph.getNode(midB);
    	List<BuildingInterface> toReturn = graph.shortestPathDataRequiredNode(startB, endB, midB); 	
		return toReturn;
	}

	@Override
	public double getShortestPathWithRequiredNodeCost(String building1, String building2, String requiredNode) {
		BuildingInterface startB = new Building(building1);
		BuildingInterface endB = new Building(building2);
		BuildingInterface midB = new Building(requiredNode);
    	startB = graph.getNode(startB);
    	endB = graph.getNode(endB);
    	midB = graph.getNode(midB);
    	double toReturn = graph.shortestPathCostRequiredNode(startB, endB, midB); 	
		return toReturn;
	}

	@Override
	public String getData() {
		String dataString = "Total Buildings in this campus map: " + this.totalBuildings + "\n"
				+ "Total Paths in this campus map: " + this.totalPaths; 
		return dataString;
	}

	@Override
	public void clear() {
		this.graph = graph.clear();
		this.totalBuildings = 0;
		this.totalPaths = 0;
		
	}
	
}

    
