package edu.kit.informatik.data.resources;


/**
 * Custom exception to be thrown when a given node is not a valid sink for the flow
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class NotAValidSinkException extends DatabaseException {
    private static final String NOT_A_VALID_SINK = "%s can't be a sink";

    /**
     * Formats given string into a meaningful error message
     * @param nodeIdentifier identifier of the node that is an invalid sink
     */
    public NotAValidSinkException(String nodeIdentifier) {
        this.message = String.format(NOT_A_VALID_SINK, nodeIdentifier);
    }
}
