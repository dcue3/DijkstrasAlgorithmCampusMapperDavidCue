import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class CampusNavigationFD implements CampusNavigationInterface {

	@Override
	public void loadMap(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBuilding(String name, String department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBuilding(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePath(String building1, String building2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPath(String x, String y, double z) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getShortestPath(String building1, String building2) {
		// TODO Auto-generated method stub
		LinkedList<String> path = new LinkedList<>(); //creating a linked list for the data
        //traversing back through each nodes in the path
        
        path.addFirst(building2);
        path.addFirst("C");
        path.addFirst("B");
        path.addFirst(building1);
        return path;
	}

	@Override
	public double getShortestPathCost(String building1, String building2) {
		// TODO Auto-generated method stub
		return 15.0;
	}

	@Override
	public List getShortestPathWithRequiredNodeData(String building1, String building2, String requiredNode) {
		// TODO Auto-generated method stub
		LinkedList<String> path = new LinkedList<>(); //creating a linked list for the data
        //traversing back through each nodes in the path
        
        path.addFirst(building2);
        path.addFirst("D");
        path.addFirst(requiredNode);
        path.addFirst("B");
        path.addFirst(building1);
        return path;
	}

	@Override
	public double getShortestPathWithRequiredNodeCost(String building1, String building2, String requiredNode) {
		// TODO Auto-generated method stub
		return 20.0;
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		String buildings = "Chemistry Chamberlin Van Vleck Computer Sciences";
		return buildings;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}

