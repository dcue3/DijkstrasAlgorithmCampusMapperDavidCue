import java.util.List;

public interface BuildingInterface extends Comparable<BuildingInterface> {
// public BuildingInterface(String name,String department, String schedule, List<PathInterface> pathsIn, List<PathInterface> pathsOut);
//public String name;
//public String department;
//public string schedule;
//public List<PathInterface> pathsIn;
//public List<PathInterface> pathsOut;
    public String getName();
    public String getDepartment();
//    public String getSchedule();
//    public List<PathInterface> getPathsIn();
//    public List<PathInterface> getPathsOut();
    public int compareTo(BuildingInterface intersection);
}

