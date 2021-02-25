package edu.kit.informatik.model.resources;

public class NotAValidEscapeNetwork extends DatabaseException{
    public NotAValidEscapeNetwork(final String identifier){
        this.message = String.format(DatabaseException.NOT_A_VALID_NETWORK, identifier);
    }

}
