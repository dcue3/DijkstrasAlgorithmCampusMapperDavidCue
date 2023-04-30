import java.io.FileNotFoundException;
import java.util.*;

public class CampusNavigation<Nodetype, EdgeType> implements CampusNavigationInterface<Nodetype, EdgeType> {

    private GraphADT<Nodetype, EdgeType> graph;
    private MapReaderInterface mapReader;

    public CampusNavigation(GraphADT<Nodetype, EdgeType> graph, MapReaderInterface mapReader) {
        this.graph = graph;
        this.mapReader = mapReader;
    }

    public void loadMap(String filename) throws FileNotFoundException {
        // Using mapReader to get a list of nodes and edges
        List<NodeType> nodesToAdd = new ArrayList<>();
        List<EdgeType> edgesToAdd = new ArrayList<>();
        mapReader.readMapFile(filename, nodesToAdd, edgesToAdd);

        if (nodesToAdd.size() == 0 || edgesToAdd.size() == 0) {
            return;
        }

        // Adding each node to the graph using the addNode() method of ShortPathGraph
        for (int i = 0; i < nodesToAdd.size(); i++) {
            graph.addNode(nodesToAdd.get(i));
        }

        // Adding each edge to the graph using the addEdge() method of ShortPathGraph
        for (int i = 0; i < edgesToAdd.size(); i++) {
            EdgeType edge = edgesToAdd.get(i);
            graph.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }
    }

    public void addBuilding(String name, String department, double x, double y) {
        BuildingInterface building = new Building(name, department, x, y);
        graph.addNode((Nodetype) building);
    }

    public void removeBuilding(String name) {
        Set<Nodetype> nodes = graph.getNodes();
        for (Nodetype node : nodes) {
            if (node instanceof BuildingInterface && ((BuildingInterface) node).getName().equals(name)) {
                graph.removeNode(node);
            }
        }
    }

    public void addPath(String start, String end, double weight) {
        PathInterface path = new Path(start, end, weight);
        graph.addEdge((Nodetype) path);
    }

    public void removePath(String start, String end) {
        Set<EdgeType> edges = graph.getEdges();
        for (EdgeType edge : edges) {
            if (edge instanceof PathInterface && ((PathInterface) edge).getStart().equals(start) && ((PathInterface) edge).getEnd().equals(end)) {
                graph.removeEdge(edge);
            }
        }
    }

    public List<Nodetype> getShortestPath(String start, String end) {
        Map<Nodetype, Double> distances = new HashMap<>();
        Map<Nodetype, Nodetype> previous = new HashMap<>();
        Set<Nodetype> unvisited = new HashSet<>();

        for (Nodetype node : graph.getNodes()) {
            if (node instanceof BuildingInterface) {
                distances.put(node, Double.MAX_VALUE);
                previous.put(node, null);
                unvisited.add(node);
            }
        }

        distances.put(getBuilding(start), 0.0);

        while (!unvisited.isEmpty()) {
            Nodetype current = getClosest(unvisited, distances);
            unvisited.remove(current);

            if (current instanceof BuildingInterface && ((BuildingInterface) current).getName().equals(end)) {
                break;
            }

            for (Nodetype neighbor : graph.getNeighbors(current)) {
                double alt = distances.get(current) + graph.getEdgeWeight(current, neighbor);
                if (alt < distances.get(neighbor)) {
                    distances.put(neighbor, alt);
                    previous.put(neighbor, current);
                }
            }
        }

        List<Nodetype> path = new ArrayList<>();
        Nodetype current = getBuilding(end);
        while (previous.get(current) != null) {
            path.add(0, current);
            current = previous.get(current);
        }
        path.add(0, getBuilding(start));

        return path;
    }

    public double getShortestPathCost(String start, String end) {
        List<Nodetype> path = getShortestPath(start, end);
        double cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph.getEdgeWeight(path.get(i), path.get(i + 1));
        }
        return cost;
    }
}
