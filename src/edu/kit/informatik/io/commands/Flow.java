package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormattingException;
import edu.kit.informatik.data.Database;
import edu.kit.informatik.data.flownetwork.Node;
import edu.kit.informatik.data.resources.DatabaseException;

import java.util.List;

/**
 * Subclass of Command class
 * used if flow through escape network needs to be calculated
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Flow extends Command {
    /**
     * identifier of this command
     */
    public static final String REGEX = "flow";
    private static final int PARAMETER_NUM = 3;
    private final String networkIdentifier;
    private final Node source;
    private final Node sink;

    /**
     * Constructor of Flow object
     * @param parameters List of parameters
     * @throws FalseFormattingException if parameters do not conform to specification
     */
    public Flow(List<String> parameters) throws FalseFormattingException {
        if (parameters.size() != PARAMETER_NUM) {
            throw new FalseFormattingException(parameters.toString(), "<n> <v_1> <v_2>");
        }
        this.networkIdentifier = parameters.get(0);
        this.source = new Node(parameters.get(1));
        this.sink = new Node(parameters.get(2));
    }

    /**
     * Executes the command on given database
     * @param database Database on which the command should be executed
     * @return message of successful execution if command executed correctly
     * @throws DatabaseException if the database recognizes a problem with the input
     */
    @Override
    public String execute(Database database) throws DatabaseException {
        return String.valueOf(database.getCapacity(networkIdentifier, source.identifier, sink.identifier));
    }
}
