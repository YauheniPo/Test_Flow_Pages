package popo.flow;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.*;
import picocli.CommandLine;
import popo.flow.framework.helpers.listener.TestListener;
import popo.flow.framework.util.Options;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class Runner {

    private static final String TESTS_SOURCE = "popo.flow.app.test";

    public static void main(String[] args) {
        Options options = CommandLine.populateCommand(new Options(), args);

        List<XmlSuite> suites = new ArrayList<>();
        TestNG testNG = new TestNG();

        try {
            if (Objects.requireNonNull(options).testngXml != null) {
                for (String xml : options.testngXml) {
                    Optional<XmlSuite> parser;
                    try (InputStream xmlRunnerReader = Objects.requireNonNull(Runner.class.getClassLoader().getResource(xml)).openStream()) {
                        parser = (new Parser(xmlRunnerReader)).parse().stream().findFirst();
                        parser.ifPresent(suites::add);
                    } catch (Exception ex) {
                        log.debug("Error for TestNG xml files", ex);
                        try {
                            parser = (new Parser(Paths.get(xml).toString()).parse()).stream().findFirst();
                            parser.ifPresent(suites::add);
                        } catch (Exception e) {
                            log.error("Error for TestNG xml files", e);
                        }
                    }
//                    suites.add((new Parser(URLDecoder.decode(getSystemResource(xml).getPath(), "UTF-8"))).parse().stream().findFirst().get());
//                    suites.add((new Parser(Paths.get("target", "test-classes", xml).toString()).parse()).stream().findFirst().get());

//                    URL url = new URL("jar:file:/absolute/location/of/yourJar.jar!/1.txt");
//                    InputStream is = url.openStream();
                }
            }

            XmlSuite suite = new XmlSuite();
            suite.setParallel(XmlSuite.ParallelMode.METHODS);
            XmlTest myTest = new XmlTest(suite);

            setTestngXmlParameters(options, suite);

            List<XmlClass> classes = new ArrayList<> ();
            setTestngXmlClasses(options, classes);

//            XmlInclude testLogin = new XmlInclude("testLogin");
//            List<XmlInclude> includes = new ArrayList<>();
//            includes.add(testLogin);
//            xmlClass.setIncludedMethods(includes);

            setTestngXmlGroupsPackages(options, myTest);

            myTest.setXmlClasses(classes); //testNG.setTestClasses(new Class[] { TestWatchCoMainPage.class });

//            List<XmlTest> myTests = new ArrayList<>();
//            myTests.add(myTest);
//            new XmlSuite().setTests(myTests);

            suites.add(suite);

            ITestNGListener iTestNGListener = new TestListener();
            testNG.addListener(iTestNGListener);

            testNG.setXmlSuites(suites);
            testNG.run();

        } catch (Throwable e) {
            log.error(e);
        }
    }

    private static void setTestngXmlClasses(Options options, List<XmlClass> classes) {
        if (options.testClasses != null) {
            for (String cl : options.testClasses) {
                classes.add(new XmlClass(String.format("%s.%s", TESTS_SOURCE, cl)));
            }
        }
    }

    private static void setTestngXmlGroupsPackages(Options options, XmlTest myTest) {
        if (options.testGroups != null) {
            for (String gr : options.testGroups) {
                myTest.addIncludedGroup(gr);
            }
            List<XmlPackage> xmlPackages = new ArrayList<>();
            XmlPackage xmlPackage = new XmlPackage();
            if (options.testPackages != null) {
                xmlPackage.setName(options.testPackages.toString());
                xmlPackage.setInclude(options.testPackages.stream().map(
                        (pack) -> String.format("%s.*", pack)).collect(Collectors.toList()));
            } else {
                xmlPackage.setName(String.format("%s.*", TESTS_SOURCE));
                xmlPackage.setInclude(Collections.singletonList(TESTS_SOURCE));
            }
            xmlPackages.add(xmlPackage);
            myTest.setPackages(xmlPackages);
        }
    }

    private static void setTestngXmlParameters(Options options, XmlSuite suite) {
        if (options.parameters != null) {
            suite.setParameters(options.parameters);
        }
    }
}
