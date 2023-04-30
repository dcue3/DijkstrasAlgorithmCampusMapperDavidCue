public interface CampusNavigationInterface<Nodetype, EdgeType> {
// public CampusNavigationInterface()
// public CampusNavigationInterface(GraphADT graph)

  public void loadMap(String filename) throws FileNotFoundException;
 // Add a new building to the campus map

  public void addBuilding(String  name, String department);

  public void removeBuilding(String name);

  public void removePath(String building1, String building2);
  Public void addPath(String x, String y)
// Find the shortest path between two buildings
    public List<NodeType> getShortestPath(String building1, String building2);
    public double getShortestPathCost(String building 1, String building 2);
// Find the shortest path between two buildings, visiting a specified building along the way
    public List<NodeType> getShortestPathWithRequiredNodeData(String building1, String building2, String requiredNode);
    public double getShortestPathWithRequiredNodeCost(String building1, String building2, String requiredNode); // 
  public String getData(); //
  public void clear(); //

}
