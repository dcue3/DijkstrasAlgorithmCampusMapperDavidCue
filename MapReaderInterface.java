import java.io.FileNotFoundException;
import java.util.List;

public interface MapReaderInterface {
// public MapReaderInterface();
    public List<BuildingInterface> readBuildingsFromFile (String filename)
throws FileNotFoundException;
}
