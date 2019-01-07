# Test_Flow_Pages

mvn clean -DskipTests=true package


java -jar build\flow-page-tests.jar --xml testng.xml --cl TestPage --cl TestPage2 --pr=name=JAR

java -jar target\flow-page-1.0-shaded.jar --xml testng.xml