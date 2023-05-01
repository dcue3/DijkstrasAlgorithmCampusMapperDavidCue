import java.util.LinkedList;
import java.util.Scanner;

public class CampusMapperApp {

	public static void main(String[] args) {
		ShortPathGraphAE<BuildingInterface, Double> graph = new ShortPathGraphAE<BuildingInterface, Double>();
		MapReader mapReader = new MapReader();
		CampusNavigationBD BD = new CampusNavigationBD(graph, mapReader);
		Scanner scan = new Scanner(System.in);
		campusMapperFD<Building, Double> Frontend = new campusMapperFD<Building, Double>(scan, BD);
		System.out.println();
		Frontend.runCommandLoop();
	}
}
