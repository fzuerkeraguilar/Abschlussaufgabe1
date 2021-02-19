package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.io.commands.parameter.Parameter;
import edu.kit.informatik.model.Database;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    protected static final String REGEX_NETWORK_IDENTIFIER = "[A-Z]{1,6}";
    protected static final String REGEX_NODE_IDENTIFIER = "[a-z]{1,6}";
    protected static final String REGEX_POSITIVE_WHOLE_NUMBER = "[0-9]+";
    protected static final String REGEX_EDGE = "(" + REGEX_NODE_IDENTIFIER + ")|(" + REGEX_POSITIVE_WHOLE_NUMBER + ")";
    List<Parameter> parameterList;

    abstract public void execute(Database database);

    public Command(){}
    public Command(ArrayList<String> parameters){}


}
