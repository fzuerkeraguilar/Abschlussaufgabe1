package edu.kit.informatik.data.resources;

/**
 * Custom abstract exception class that handles errors resulting in the data package
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public abstract class DatabaseException extends Exception {

    /**
     * Error message that should be displayed to the user
     */
    protected String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
