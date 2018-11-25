package com.flow;

import com.flow.framework.listener.TestListener;
import com.flow.framework.util.Options;
import epam.popovich.annotation.time.TrackTime;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import picocli.CommandLine;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class Runner {

    private static final String TESTS_SOURCE = "com.flow.app.test";

    @TrackTime
    public static void main(String[] args) {
        Options options = CommandLine.populateCommand(new Options(), args);

        List<XmlSuite> suites = new ArrayList<>();
        TestNG testNG = new TestNG();

        try {
            if (!(Objects.requireNonNull(options).testngXml == null)) {
                for (String xml : options.testngXml) {
                    InputStream xmlRunnerReader = Objects.requireNonNull(Runner.class.getClassLoader().getResource(xml)).openStream();
                    suites.add((new Parser(xmlRunnerReader)).parse().stream().findFirst().get());

//                    suites.add((new Parser(URLDecoder.decode(getSystemResource(xml).getPath(), "UTF-8"))).parse().stream().findFirst().get());
//
//                    suites.add((new Parser(Paths.get("target", "test-classes", xml).toString()).parse()).stream().findFirst().get());
                }
            }

            XmlSuite suite = new XmlSuite();
            suite.setParallel(XmlSuite.ParallelMode.TESTS);


            XmlTest myTest = new XmlTest(suite);
            if (!(options.parameters == null)) {
                suite.setParameters(options.parameters);
            }

            List<XmlClass> classes = new ArrayList<> ();

            if (!(options.testClasses == null)) {
                for (String cl : options.testClasses) {
                    classes.add(new XmlClass(String.format("%s.%s", TESTS_SOURCE, cl)));
                }
            }
//            XmlInclude testLogin = new XmlInclude("testLogin");
//            List<XmlInclude> includes = new ArrayList<>();
//            includes.add(testLogin);
//            xmlClass.setIncludedMethods(includes);
            myTest.setXmlClasses(classes); //testNG.setTestClasses(new Class[] { TestPage.class });

//            List<XmlTest> myTests = new ArrayList<>();
//            myTests.add(myTest);
//            new XmlSuite().setTests(myTests);

            suites.add(suite);

            ITestNGListener tla = new TestListener();
            testNG.addListener(tla);

            testNG.setXmlSuites(suites);
            testNG.run();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
