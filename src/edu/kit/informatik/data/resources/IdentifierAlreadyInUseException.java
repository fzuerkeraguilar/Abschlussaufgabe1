package edu.kit.informatik.data.resources;

/**
 * Custom exception to be thrown when an identifier is already in use
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class IdentifierAlreadyInUseException extends DatabaseException {
    private static final String IDENTIFIER_ALREADY_IN_USE = "%s already in use.";

    /**
     * Formats given string into a meaningful error message
     * @param identifier identifier that was already assigned
     */
    public IdentifierAlreadyInUseException(final String identifier) {
        this.message = String.format(IDENTIFIER_ALREADY_IN_USE, identifier);
    }
}
