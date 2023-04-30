run:

runTests: runDataWranglerTests

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

clean:
	rm *.class
