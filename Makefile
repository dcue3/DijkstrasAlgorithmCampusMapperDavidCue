runTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java ShortPathGraphAE.class BuildingAE.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

ShortPathGraphAE.class: ShortPathGraphAE.java ShortPathGraphInterface.java
	javac ShortPathGraphAE.java ShortPathGraphInterface.java

BuildingAE.class: BuildingAE.java BuildingInterface.java
	javac BuildingAE.java BuildingInterface.java

clean:
	rm *.class
