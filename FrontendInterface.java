import java.util.List;

public interface FrontendInterface<NodeType,EdgeType>{
	
	//methods for for frontend
	public void runCommandLoop();
	public String menu();
	public void loadDataFD(String fileName);
	public void addBuildingFD(String name, String department);
	public void removeBuildingFD(String name);
	public void removePathFD(String building1, String building2);
	public List<NodeType> getShortestPath(String building1, String building2);
	public double getShortestPathCost(String building1, String building2);
	public List<NodeType> getShortestPathRequired(String building1, String building2, String building3);
	public double getShortestPathRequiredCost(String building1, String building2, String building3);
	public void getDataFD();
	public void clear();
	void addPathFD(String a, String b, double z);

}

