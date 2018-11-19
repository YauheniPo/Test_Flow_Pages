package com.flow;

import com.flow.framework.utils.Options;
import com.google.devtools.common.options.OptionsParser;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        OptionsParser parser = OptionsParser.newOptionsParser(Options.class);
        parser.parseAndExitUponError(args);
        Options options = parser.getOptions(Options.class);

        TestNG testng = new TestNG();

        List<XmlSuite> suite;
        try {
            suite = (List <XmlSuite>)(new Parser(Paths.get("target", "test-classes", options.testngXml).toString()).parse());
            testng.setXmlSuites(suite);
            testng.run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
