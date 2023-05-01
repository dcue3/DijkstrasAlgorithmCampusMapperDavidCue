import java.io.FileNotFoundException;
import java.util.List;

public interface MapReaderInterface {
  // public MapReaderInterface();
  public List<Building> readBuildingsFromFile(String filename)
      throws FileNotFoundException;

  public List<PathInterface> readPathsFromFile(String filename) throws FileNotFoundException;
}
