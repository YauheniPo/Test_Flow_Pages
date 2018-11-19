package com.flow.framework.utils;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

import java.util.List;

public class Options extends OptionsBase {

    @Option(name = "testng_xml", defaultValue = "null")
    public String testngXml;

    @Option(name = "test_classes", defaultValue = "null", allowMultiple = true)
    public List<String> testClasses;
}
