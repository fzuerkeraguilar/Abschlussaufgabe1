package edu.kit.informatik.io.commands;

import edu.kit.informatik.model.Database;

import java.util.Objects;

public class ListNetworks extends Command {
    public static final String REGEX = "list";
    public static final String NO_NETWORK_FOUND = "EMPTY";


    @Override
    public String execute(Database database) {
        String output = database.allNetworksToString();
        return Objects.requireNonNullElse(output, NO_NETWORK_FOUND);
    }

    public ListNetworks(){}

}
