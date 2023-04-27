import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapReader implements MapReaderInterface {
    private GraphADT graph;

    public MapReader(GraphADT graph) {
        this.graph = graph;
    }

    public void readMapFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // read the buildings from the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String name = parts[0];
            String department = parts[1];
            double x = Double.parseDouble(parts[2]);
            double y = Double.parseDouble(parts[3]);
            Building building = new Building(name, department, x, y);
            graph.addVertex(building);
        }

        scanner.close();
    }

   
}
