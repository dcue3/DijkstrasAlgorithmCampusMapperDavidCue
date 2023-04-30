public class Building implements BuildingInterface {

  public String name;
  public String department;



  public Building(String name, String department) {
    this.name = name;
    this.department = department;

  }

  public Building(String name) {
    this.name = name;
    this.department = "N/A";

  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDepartment() {
    return department;
  }

  @Override
  public int compareTo(BuildingInterface building) {
    return name.compareTo(building.getName());
  }
  
  @Override
  public boolean equals(Object building) {
    if (name.compareTo(((Building) building).getName()) == 0) {
        return true;
    }
    return false; 
    }
}
