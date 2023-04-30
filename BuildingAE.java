/**
 * This is a placeholder class made to bare minimum for testing purposes by the AlgorithmEngineer
 * It implements the BuildingInterface of the DataWrangler
 * @author Sudheesh Dabbara
 *
 */

public class BuildingAE implements BuildingInterface {
	private String name;
	private String department;
	// public List<PathInterface> pathsIn;
	// public List<PathInterface> pathsOut;
	
	public BuildingAE(String name,String department) {
		this.name = name;
		this.department = department;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDepartment() {
		// TODO Auto-generated method stub
		return department;
	}

	@Override
	public boolean equals(Object building) {
		if (name.compareTo(((BuildingAE) building).getName()) == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(BuildingInterface intersection) {
		// TODO Auto-generated method stub
		return name.compareTo(intersection.getName());
	}

	
}
