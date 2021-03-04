package edu.kit.informatik.data.resources;

/**
 * Custom exception to be thrown when an identifier is not found
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class IdentifierNotFoundException extends DatabaseException {
    private static final String IDENTIFIER_NOT_FOUND = "%s not found.";

    /**
     * Formats given string into a meaningful error message
     * @param identifier identifier that was not found
     */
    public IdentifierNotFoundException(final String identifier) {
        this.message = String.format(IDENTIFIER_NOT_FOUND, identifier);
    }

}
