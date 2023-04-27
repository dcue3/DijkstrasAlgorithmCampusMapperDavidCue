runTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java campusMapperFD.class CampusNavigationFD.class
	javac -cp .:junit5.jar FrontendDeveloperTests.java

campusMapperFD.class: campusMapperFD.java FrontendInterface.java
	javac campusMapperFD.java FrontendInterface.java
	
CampusNavigationFD.class: CampusNavigationFD.java CampusNavigationInterface.java
	javac CampusNavigationFD.java CampusNavigationInterface.java
	
clean:
	rm *.class
