package edu.kit.informatik.io.resources.exceptions;

/**
 * Custom abstract exception class that handles errors resulting in the io package
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public abstract class InputException extends Exception {
    //TODO nutzen?
    //protected static final String WRONG_NUMBER_OF_PARAMETERS = "expected number of parameters between %1$d and %2$d";
    /**
     * Error message that should be displayed to the user
     */
    protected String message;

    @Override
    public String getMessage() {
        return this.message;
    }

}
