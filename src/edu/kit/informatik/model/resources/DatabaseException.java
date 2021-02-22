package edu.kit.informatik.model.resources;

public abstract class DatabaseException extends Exception{
    protected final static String IDENTIFIER_NOT_FOUND = " not found.";
    protected final static String IDENTIFIER_ALREADY_IN_USE = " already in use.";
    protected final static String NOT_A_VALID_NETWORK = " would make this not a valid network";
    protected final static String SAME_ORIGIN_AND_DESTINATION = "Origin and destination may not be the same";
    protected final static String SECTION_IN_OPPOSING_DIRECTION = "Escape section already exists in opposing direction";


    protected String message;

    @Override
    public String getMessage(){
        return this.message;
    }
}
