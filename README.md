# Test_Flow_Pages

mvn clean -DskipTests=true package


java -jar flow-page-tests.jar --xml testng.xml --cl TestPage --cl TestPage2 --pr=name=JAR