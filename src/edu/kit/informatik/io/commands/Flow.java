package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormatting;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Node;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;

public class Flow extends Command {
    public static final String REGEX = "flow";
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "";
    private static final int parameterNum = 3;
    private String networkIdentifier;
    private Node source;
    private Node sink;

    @Override
    public String execute(Database database) throws DatabaseException {
        return String.valueOf(database.getCapacity(networkIdentifier, source.getIdentifier(), sink.getIdentifier()));
    }

    //TODO Konstruktor implementieren
    public Flow(ArrayList<String> parameters) throws FalseFormatting {
        if(parameters.size() != parameterNum){
            throw new FalseFormatting(parameters.get(0)+parameters.get(1)+parameters.get(2), "<n> <v_1> <v_2>");
        }
        this.networkIdentifier = parameters.get(0);
        this.source = new Node(parameters.get(1));
        this.sink = new Node(parameters.get(2));
    }
    //TODO Setter, Getter
}
