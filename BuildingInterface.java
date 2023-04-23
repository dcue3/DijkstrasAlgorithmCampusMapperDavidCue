import java.util.List;

public interface BuildingInterface extends Comparable<BuildingInterface> {
// public BuildingInterface(String name,String department, String schedule, List<PathInterface> paths);
//private String name;
//private String department;
//private string schedule;
//private List<PathInterface> paths;
    public String getName();
    public String getDepartment();
    public String getSchedule();
    public List<PathInterface> getPaths();
    public int compareTo(BuildingInterface intersection);
}

