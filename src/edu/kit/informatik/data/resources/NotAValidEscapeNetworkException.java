package edu.kit.informatik.data.resources;

/**
 * Custom exception to be thrown when a given network is invalid
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class NotAValidEscapeNetworkException extends DatabaseException {
    private static final String NOT_A_VALID_NETWORK = "%s is not a valid network";

    /**
     * Formats given string into a meaningful error message
     * @param identifier identifier of network that is invalid
     */
    public NotAValidEscapeNetworkException(final String identifier) {
        this.message = String.format(NOT_A_VALID_NETWORK, identifier);
    }
}
