package edu.kit.informatik.model.resources;

public class SameOriginAndDestinationException extends DatabaseException{

    public SameOriginAndDestinationException(){
        this.message = DatabaseException.SAME_ORIGIN_AND_DESTINATION;
    }
}
