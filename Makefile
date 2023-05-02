run: compileAll
	java CampusMapperApp 

runTests: runDataWranglerTests runFrontendDeveloperTests runAlgorithmEngineerTests

compileAll: CampusMapperApp.java campusMapperFD.java CampusNavigationBD.java ShortPathGraphAE.java Building.java Path.java MapReader.java
	javac CampusMapperApp.java campusMapperFD.java CampusNavigationBD.java ShortPathGraphAE.java Building.java Path.java MapReader.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java ShortPathGraphAE.class BuildingAE.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

ShortPathGraphAE.class: ShortPathGraphAE.java ShortPathGraphInterface.java
	javac ShortPathGraphAE.java ShortPathGraphInterface.java

BuildingAE.class: BuildingAE.java BuildingInterface.java
	javac BuildingAE.java BuildingInterface.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java CreateAll
	 javac -cp .:junit5.jar DataWranglerTests.java

CreateAll: Building.java Path.java BuildingInterface.java MapReader.java PathInterface.java DataWranglerTests.java MapReaderInterface.java
	javac Building.java
	javac BuildingInterface.java
	javac Path.java
	javac PathInterface.java
	javac MapReader.java
	javac MapReaderInterface.java
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java campusMapperFD.class CampusNavigationFD.class
	javac -cp .:junit5.jar FrontendDeveloperTests.java

campusMapperFD.class: campusMapperFD.java FrontendInterface.java
	javac campusMapperFD.java FrontendInterface.java
	
CampusNavigationFD.class: CampusNavigationFD.java CampusNavigationInterface.java
	javac CampusNavigationFD.java CampusNavigationInterface.java
	

CampusNavigationBD.class: CampusNavigationBD.java CampusNavigationInterface.java
	javac CampusNavigationBD.java CampusNavigationInterface.java


clean:
	rm *.class
