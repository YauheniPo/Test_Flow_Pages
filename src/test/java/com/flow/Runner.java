package com.flow;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Runner {

    public static void main(String[] args) {
//        CommandLineOptions options = new CommandLineOptions();
//        JCommander jCommander = new JCommander(options, args);
//
//        XmlSuite suite = new XmlSuite();
//        suite.setName("MyTestSuite");
//        suite.setParameters(options.convertToMap());
        TestNG testng = new TestNG();

        List<XmlSuite> suite;
        try {
            suite = (List <XmlSuite>)(new Parser("testng.xml").parse());
            testng.setXmlSuites(suite);
            testng.run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
