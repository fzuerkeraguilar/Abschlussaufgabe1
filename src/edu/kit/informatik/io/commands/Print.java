package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormatting;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;

public class Print extends Command {
    public static final String REGEX = "print";
    private final String networkIdentifier;

    public Print(ArrayList<String> parameters) throws FalseFormatting {
        if(!parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER)){
            throw new FalseFormatting(parameters.get(0), REGEX_NETWORK_IDENTIFIER);
        }
        this.networkIdentifier = parameters.remove(0);
    }

    @Override
    public String execute(Database database)throws DatabaseException {
        return database.networkToString(this.networkIdentifier);
    }


}
