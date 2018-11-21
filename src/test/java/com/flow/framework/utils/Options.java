package com.flow.framework.utils;

import picocli.CommandLine;

import java.util.List;
import java.util.Map;

public class Options {

    @CommandLine.Option(names = {"--xml"}, description = "TestNG xml")
    public List<String> testngXml;

    @CommandLine.Option(names = {"--cl"}, description = "TestNG Classes")
    public List<String> testClasses;

    @CommandLine.Option(names = {"--pr"}, description = "TestNG Parameters")
    public Map<String, String> parameters;
}
