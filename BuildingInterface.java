// Building object interface

public interface BuildingInterface implements Comparable<BuildingInterface> {
    // public BuildingInterface(String name,String department);
// public BuildingInterface(String name);
//private String name;
//private String department;
    public String getName();
    public String getDepartment();
    public int compareTo(BuildingInterface building);
    void addBuilding(Building building);
    void removeBuilding(Building building);
    void modifyBuilding(Building building, String newName, double newX, double newY, String newDepartment);

}
