package edu.kit.informatik.model.resources;

public abstract class DatabaseException extends Exception{
    protected final static String IDENTIFIER_NOT_FOUND = "%s not found.";
    protected final static String IDENTIFIER_ALREADY_IN_USE = "%s already in use.";
    protected final static String NO_LONGER_A_VALID_NETWORK = "%s would make this an invalid network";
    protected static final String NOT_A_VALID_NETWORK = "%s is not a valid network";
    protected final static String SAME_ORIGIN_AND_DESTINATION = "Origin and destination may not be the same";
    protected final static String SECTION_IN_OPPOSING_DIRECTION = "Escape section already exists in opposing direction";
    protected static final String NOT_A_VALID_SINK = "%s can't be a sink";
    protected final static String NOT_A_VALID_SOURCE = "%s can't be a source";

    protected String message;

    @Override
    public String getMessage(){
        return this.message;
    }
}
