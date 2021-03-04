package edu.kit.informatik.data.resources;

/**
 * Custom exception to be thrown when an identifier is not found
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class NoLongerAValidEscapeNetworkException extends DatabaseException {
    private static final String NO_LONGER_A_VALID_NETWORK = "%s would make this an invalid network";

    /**
     * Formats given string into a meaningful error message
     * @param identifier identifier that would result in an invalid network
     */
    public NoLongerAValidEscapeNetworkException(final String identifier) {
        this.message = String.format(NO_LONGER_A_VALID_NETWORK, identifier);
    }
}
