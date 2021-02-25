package edu.kit.informatik.model.resources;

public class NotAValidSource extends DatabaseException{
    public NotAValidSource(String nodeIdentifier){
        this.message = String.format(DatabaseException.NOT_A_VALID_SOURCE, nodeIdentifier);
    }
}
