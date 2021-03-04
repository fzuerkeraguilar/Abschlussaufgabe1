package edu.kit.informatik.data.resources;


/**
 * Custom exception to be thrown when a given node is not a valid source of flow
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class NotAValidSourceException extends DatabaseException {
    private static final String NOT_A_VALID_SOURCE = "%s can't be a source";

    /**
     * Formats given string into a meaningful error message
     * @param nodeIdentifier identifier of the node that is an invalid source
     */
    public NotAValidSourceException(String nodeIdentifier) {
        this.message = String.format(NOT_A_VALID_SOURCE, nodeIdentifier);
    }
}
