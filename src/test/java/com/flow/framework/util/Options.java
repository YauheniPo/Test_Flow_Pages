package com.flow.framework.util;

import picocli.CommandLine;

import java.util.List;
import java.util.Map;

public class Options {

    @CommandLine.Option(names = {"--xml"}, description = "TestNG xml")
    public List<String> testngXml;

    @CommandLine.Option(names = {"--cl"}, description = "TestNG Classes")
    public List<String> testClasses;

    @CommandLine.Option(names = {"--gr"}, description = "TestNG Groups")
    public List<String> testGroups;

    @CommandLine.Option(names = {"--pack"}, description = "TestNG Packages")
    public List<String> testPackages;

    @CommandLine.Option(names = {"--pr"}, description = "TestNG Parameters")
    public Map<String, String> parameters;
}
