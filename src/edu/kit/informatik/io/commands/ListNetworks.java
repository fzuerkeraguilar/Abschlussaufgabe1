package edu.kit.informatik.io.commands;

import edu.kit.informatik.data.Database;
import java.util.Objects;

/**
 * extends Command class
 * used if all escape networks need to be listed
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class ListNetworks extends Command {
    /**
     * Identifier of this command
     */
    public static final String REGEX = "list";

    private static final String NO_NETWORK_FOUND = "EMPTY";

    /**
     * Constructor of ListNetwork command
     * "list" has no parameters
     */
    public ListNetworks() { }

    /**
     * Executes the command on given database
     * @param database Database on which the command should be executed
     * @return message of successful execution if command executed correctly
     */
    @Override
    public String execute(Database database) {
        String output = database.allNetworksToString();
        return Objects.requireNonNullElse(output, NO_NETWORK_FOUND);
    }



}
