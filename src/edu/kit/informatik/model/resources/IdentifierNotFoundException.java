package edu.kit.informatik.model.resources;

public class IdentifierNotFoundException extends DatabaseException {

    public IdentifierNotFoundException(final String identifier){
        this.message = identifier + DatabaseException.IDENTIFIER_NOT_FOUND;
    }

}
