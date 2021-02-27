package edu.kit.informatik.io.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.io.resources.exceptions.FalseFormatting;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Node;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;

public class Flow extends Command {
    public static final String REGEX = "flow";
    private static final int parameterNum = 3;
    private final String networkIdentifier;
    private final Node source;
    private final Node sink;

    public Flow(ArrayList<String> parameters) throws FalseFormatting {

        if(parameters.size() != parameterNum){
            throw new FalseFormatting(parameters.toString(), "<n> <v_1> <v_2>");
        }
        this.networkIdentifier = parameters.get(0);
        this.source = new Node(parameters.get(1));
        this.sink = new Node(parameters.get(2));
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        return String.valueOf(database.getCapacity(networkIdentifier, source.getIdentifier(), sink.getIdentifier()));
    }
}
