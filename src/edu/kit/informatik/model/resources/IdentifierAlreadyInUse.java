package edu.kit.informatik.model.resources;

public class IdentifierAlreadyInUse extends DatabaseException {

    public IdentifierAlreadyInUse(final String identifier){
        this.message = identifier + DatabaseException.IDENTIFIER_ALREADY_IN_USE;
    }
}
