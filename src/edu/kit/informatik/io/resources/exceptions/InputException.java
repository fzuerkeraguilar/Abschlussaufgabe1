package edu.kit.informatik.io.resources.exceptions;

public abstract class InputException extends Exception{
    protected static final String COMMAND_NOT_FOUND = "\"%s\" is not a valid command";
    protected static final String WRONG_NUMBER_OF_PARAMETERS = "expected number of parameters between %1$d and %2$d";
    protected static final String VALUE_OUT_OF_EXPECTED_RANGE = "expected value between %1$d and %2$d";
    protected static final String FALSE_FORMATTING = "\"%1$s\", does not follow the pattern:%2$s";

    protected String message;

    @Override
    public String getMessage(){
        return this.message;
    }

}
