import java.io.FileNotFoundException;
import java.util.List;

// Mapreader interface to load files
public interface MapReaderInterface {
    // public MapReaderInterface();
    public List<BuildingInterface> readBuildingsFromFile (String filename)
            throws FileNotFoundException;
    public List<PathInterface> readPathsFromFile (String filename)
            throws FileNotFoundException;

}
