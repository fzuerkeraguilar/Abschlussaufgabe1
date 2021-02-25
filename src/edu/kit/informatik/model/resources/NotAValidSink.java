package edu.kit.informatik.model.resources;

public class NotAValidSink extends DatabaseException{
    public NotAValidSink(String nodeIdentifier){
        this.message = String.format(DatabaseException.NOT_A_VALID_SINK, nodeIdentifier);
    }
}
