package edu.kit.informatik.io.resources;

public final class Errors {
    //COMMAND PARSER ERRORS
    public static final String NAME_NOT_FOUND = "<n> does not exist";
    public static final String WRONG_NAME_FORMATTING = "<n> is not formatted correctly";
    public static final String NOT_VALID_ESCAPE_NETWORK = "<n> is not a valid escape network";
    public static final String NOT_VALID_SECTION = "<e> is not a valid section";
    public static final String NOT_VALID_NODE = "<v> is not a valid node";
    public static final String NOT_VALID_CAPACITY = "<k> is not a valid capacity";

    //DATABASE ERRORS
    public static final String NOT_VALID_DEST_AND_ORIGIN = "Origin and destination may not be the same";
    public static final String CAPACITY_NOT_POSITIVE = "Capacity must be bigger than zero";
    public static final String NETWORK_NAME_NOT_FOUND = "<n> not found";
    public static final String ORIGIN_NODE_NAME_NOT_FOUND = "<v> not found";
    public static final String DESTINATION_NODE_NAME_NOT_FOUND = "<v> not found";
    public static final String NODE_CANNOT_BE_SOURCE = "<v> cannot be a source node";
    public static final String NODE_CANNOT_BE_SINK = "<v> cannot be a sink node";
    public static final String NETWORK_NAME_ALREADY_IN_USE = "<n> already in use";
    public static final String NODE_NAME_ALREADY_IN_USE = "<v> already in use";

    private Errors(){}
}
