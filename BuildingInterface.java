
public interface BuildingInterface extends Comparable<BuildingInterface> {
  // public BuildingInterface(String name,String department);
  // public String name;
  // public String department;

  public String getName();

  public String getDepartment();

  public int compareTo(BuildingInterface intersection);
}

