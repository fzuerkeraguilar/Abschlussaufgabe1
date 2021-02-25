package edu.kit.informatik.model.resources;

public class IdentifierAlreadyInUse extends DatabaseException {

    public IdentifierAlreadyInUse(final String identifier){
        this.message = String.format(DatabaseException.IDENTIFIER_ALREADY_IN_USE, identifier);
    }
}
