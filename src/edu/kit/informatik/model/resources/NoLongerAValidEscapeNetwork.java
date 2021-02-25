package edu.kit.informatik.model.resources;

public class NoLongerAValidEscapeNetwork extends DatabaseException {

    public NoLongerAValidEscapeNetwork(final String identifier){
        this.message = String.format(DatabaseException.NO_LONGER_A_VALID_NETWORK, identifier);
    }
}
