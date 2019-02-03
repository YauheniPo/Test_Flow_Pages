# Test_Flow_Pages

mvn clean -DskipTests=true package


java -jar target\flow-page-1.0-shaded.jar --cl TestWatchCoMainPage --pr=name=JAR

java -jar target\flow-page-1.0-shaded.jar --xml testngQueue.xml