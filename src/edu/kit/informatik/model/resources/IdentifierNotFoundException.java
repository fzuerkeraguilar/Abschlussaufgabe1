package edu.kit.informatik.model.resources;

public class IdentifierNotFoundException extends DatabaseException {

    public IdentifierNotFoundException(final String identifier){
        this.message = String.format(DatabaseException.IDENTIFIER_NOT_FOUND, identifier);
    }

}
