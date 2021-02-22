package edu.kit.informatik.io.commands;


import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;

public abstract class Command {
    protected static final String REGEX_NETWORK_IDENTIFIER = "[A-Z]{1,6}";
    protected static final String REGEX_NODE_IDENTIFIER = "[a-z]{1,6}";
    protected static final String REGEX_POSITIVE_WHOLE_NUMBER = "[0-9]+";
    protected static final String REGEX_EDGE = "(" + REGEX_NODE_IDENTIFIER + ")|(" + REGEX_POSITIVE_WHOLE_NUMBER + ")";


    abstract public String execute(Database database) throws DatabaseException;

    public Command(){}
    public Command(ArrayList<String> parameters){}


}
