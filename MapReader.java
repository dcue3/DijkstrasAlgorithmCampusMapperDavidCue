import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MapReader {



  public MapReader() {
  }

  public List<Building> readBuildingsFromFile(String filename) throws FileNotFoundException {

    LinkedList<Building> buildings = new LinkedList<Building>(); // create list of buildings
    Scanner in = new Scanner(new File(filename)); // create scanner with filename input
    while (in.hasNextLine()) { // go through each line

      String line = in.nextLine();
      if (line.isBlank()) { // skip blank lines
        line = in.nextLine();
        continue;
      }
      if (line.contains("}")) { // skip lines that outline the graph
        break;
      }
      if (line.contains("graph") || line.contains("{")) {
        line = in.nextLine();
        continue;
      }
      if (line.contains("--") && line.contains("distance") && line.contains("[")
          && line.contains("]") && line.contains("=")) { // if the line contains a path
        try {
          line = line.trim(); // get rid of leading/trailing whitespace

          line = line.replaceAll("--", "!"); // replace unnecessary characters with nothing
          line = line.replaceAll("=", "!"); // replace = and -- with ! so it can be split by that
                                            // character
          line = line.replaceAll("\\[", "");
          line = line.replaceAll("\\]", "");
          line = line.replaceAll(";", "");

          String[] parts = line.split("!", -1); // split into parts
          for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim(); // trim to remove any extra whitespace
          }

          parts[1] = parts[1].substring(0, parts[1].length() - 9); // remove word "distance" from
                                                                   // the second building name
          if (parts.length != 3) { // make sure line has correct formatting, should be split into
                                   // three parts
            System.out.println("Warning: found a line without matching parameters " + line);
            continue;
          }

          int zero = containsBuilding(buildings, parts[0]); // see if predecessor building is
                                                            // already stored
          int one = containsBuilding(buildings, parts[1]); // see if successor building is already
                                                           // stored

          if (zero != -1 && one == -1) { // if the predecessor building is already contained and
                                         // successor isn't
            Building newB = new Building(parts[1]);
            buildings.add(newB); // add new building

          } else if (zero == -1 && one != -1) { // if predecessor building is not contained and
                                                // successor is
            Building newB = new Building(parts[0]);
            buildings.add(newB); // add new building

          } else if (zero == -1 && one == -1) { // if neither are contained already add them both
            Building newB1 = new Building(parts[0]);
            Building newB2 = new Building(parts[1]);
            buildings.add(newB1);
            buildings.add(newB2);
          } else if (zero != -1 && one != -1) { // if both are contained do nothing

          }
        } catch (Exception e) { // catch any random exceptions
          System.out.println("Warning: found a line without matching parameters " + line);
          continue;
        }
      } else { // otherwise the line contains information about a building
        try {
          line = line.trim(); // get rid of whitespace

          line = line.replaceAll("--", "!");
          line = line.replaceAll("=", "!"); // filter out unnecessary characters
          line = line.replaceAll("\\[", "!");
          line = line.replaceAll("\\]", "");
          line = line.replaceAll(";", "");

          String[] parts = line.split("!", -1);// split string into parts

          for (int i = 0; i < parts.length; i++) { // trim each part
            parts[i] = parts[i].trim();
          }

          if (parts.length != 3) { // should have three parts after splitting
            System.out.println("Warning: found a line without matching parameters " + line);
            continue;
          }

          int zero = containsBuilding(buildings, parts[0]); // see if building is already stored in
                                                            // list

          if (zero != -1) {
            buildings.get(zero).department = parts[2]; // if it is already contained, set its
                                                       // department to the specified string.
          } else {
            Building newB = new Building(parts[0]); // otherwise add a new building with the name
                                                    // and specified department.
            newB.department = parts[2];
            buildings.add(newB);
          }
        } catch (Exception e) { // catch any exceptions and print error.
          System.out.println("Warning: found a line without matching parameters " + line);
          continue;
        }
      }

    }
    // closing the scanner
    in.close();
    return buildings;// returning the list of buildings


  }



  public List<PathInterface> readPathsFromFile(String filename) throws FileNotFoundException {

    LinkedList<PathInterface> paths = new LinkedList<>(); // creating list of paths
    Scanner in = new Scanner(new File(filename)); // create scanner using filename
    while (in.hasNextLine()) { // go through each line

      String line = in.nextLine();
      if (line.isBlank()) { // skip blank lines
        line = in.nextLine();
      }
      if (line.contains("}")) { // skip any lines defining the graph
        break;
      }
      if (line.contains("graph") || line.contains("{")) {
        line = in.nextLine();
      }
      if (line.contains("--") && line.contains("distance") && line.contains("[")
          && line.contains("]") && line.contains("=")) { // check for correct formatting
        try {
          line = line.trim(); // remove whitespace

          line = line.replaceAll("--", "!");
          line = line.replaceAll("=", "!");
          line = line.replaceAll("\\[", ""); // remove unnecessary characters
          line = line.replaceAll("\\]", "");
          line = line.replaceAll(";", "");

          String[] parts = line.split("!", -1); // split into parts
          for (int i = 0; i < parts.length; i++) { // trim each part
            parts[i] = parts[i].trim();
          }

          parts[1] = parts[1].substring(0, parts[1].length() - 9); // remove word "distance" from
                                                                   // second part
          if (parts.length != 3) { // make sure all required parts are there
            System.out.println("Warning: found a line without matching parameters " + line);
            continue; // skip this line if not
          }

          Path path = new Path(Double.valueOf(parts[2]), parts[0], parts[1]); // create new path
                                                                              // with the line's
                                                                              // parts

          boolean contains = false; // creating boolean to keep track of whether the path is already
                                    // stored

          for (int i = 0; i < paths.size(); i++) {
            if (path.compareTo(paths.get(i)) == 0) {
              contains = true; // if path is already stored, set contains to true
            }
          }

          if (contains == false) { // add path if not already stored
            paths.add(path);
          }

        } catch (Exception e) { // skip lines that cause any exceptions for incorrect formats
          System.out.println("Warning: found a line without matching parameters " + line);
          continue;
        }
      } else { // do nothing to skip lines that do not have paths in them

      }

    }
    // closing the scanner
    in.close();
    return paths;// returning the list of paths


  }



  public int containsBuilding(LinkedList<Building> buildings, String name) {
    for (int i = 0; i < buildings.size(); i++) {
      if (buildings.get(i).getName().equals(name)) { // buildings same if they have same name
        return i;
      }
    }
    return -1;
  }


}
