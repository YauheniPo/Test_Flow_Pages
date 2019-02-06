# Test_Flow_Pages

mvn clean -DskipTests=true package


java -jar target\flow-page-1.0-shaded.jar --cl TestWatchCoMainPage --pr=name=JAR

java -jar build\flow-page.jar --xml testngQueue.xml
java -jar target\flow-page-1.0-shaded.jar --xml target\testngQueue.xml

@CommandLine.Option(names = {"--xml"}, description = "TestNG xml")
@CommandLine.Option(names = {"--cl"}, description = "TestNG Classes")
@CommandLine.Option(names = {"--gr"}, description = "TestNG Groups")
@CommandLine.Option(names = {"--pack"}, description = "TestNG Packages")
@CommandLine.Option(names = {"--pr"}, description = "TestNG Parameters")