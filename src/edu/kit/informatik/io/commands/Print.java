package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormattingException;
import edu.kit.informatik.data.Database;
import edu.kit.informatik.data.resources.DatabaseException;

import java.util.ArrayList;

/**
 * extends Command class
 * used if all escape sections of a network need to be listed
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Print extends Command {
    /**
     * Identifier of this command
     */
    public static final String REGEX = "print";
    private final String networkIdentifier;

    /**
     * Constructor of Command object responsible for "print" command
     * @param parameters List of inputted parameters
     * @throws FalseFormattingException when formatting doesn't follow guidelines
     */
    public Print(ArrayList<String> parameters) throws FalseFormattingException {
        if (!parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER)) {
            throw new FalseFormattingException(parameters.get(0), REGEX_NETWORK_IDENTIFIER);
        }
        this.networkIdentifier = parameters.remove(0);
    }

    /**
     * Executes the command on given database
     * @param database Database on which the command should be executed
     * @return message of successful execution if command executed correctly
     * @throws DatabaseException if the database recognizes a problem with the input
     */
    @Override
    public String execute(Database database)throws DatabaseException {
        return database.networkToString(this.networkIdentifier);
    }


}
