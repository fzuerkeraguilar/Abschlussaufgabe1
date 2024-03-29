package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormattingException;
import edu.kit.informatik.io.resources.exceptions.ValueOutOfRangeException;
import edu.kit.informatik.data.Database;
import edu.kit.informatik.data.flownetwork.Edge;
import edu.kit.informatik.data.resources.DatabaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extends Command class.
 * Implements functionality if a new escape network should be added.
 * used if a new network needs to be added
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class AddNetwork extends Command {
    /**
     * Identifier of this command
     */
    public static final String REGEX = "add";
    private static final int MIN_PARAMETER_NUM = 3;
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "Added new escape network with identifier %s.";
    private final String networkIdentifier;
    private final List<Edge> edges;

    /**
     * to be used when new network should be added
     * @param parameters list of inputted parameters
     * @throws FalseFormattingException - if parameters are not formatted correctly
     * @throws ValueOutOfRangeException - if a value is out of range
     */
    public AddNetwork(List<String> parameters) throws FalseFormattingException, ValueOutOfRangeException {
        if (parameters.size() < MIN_PARAMETER_NUM) {
            throw new FalseFormattingException(parameters.toString(), "<n> <e>");
        }

        edges = new ArrayList<>();
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);

        if (!parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER)) {
            throw new FalseFormattingException( parameters.get(0), REGEX_NETWORK_IDENTIFIER);
        }
        this.networkIdentifier = parameters.remove(0);
        for (String s : parameters) {
            final Matcher edgeMatcher = commandPattern.matcher(s);
            if (!edgeMatcher.matches()) throw new FalseFormattingException(s, "<e><k><e>" );

            String origin = edgeMatcher.group(1);

            int capacity;
            try {
                capacity = Integer.parseInt(edgeMatcher.group(2));
            } catch (NumberFormatException e) {
                throw new ValueOutOfRangeException(e.getMessage());
            }
            if (capacity < MIN_CAPACITY) {
                throw new ValueOutOfRangeException(Integer.toString(capacity), MIN_CAPACITY, MAX_CAPACITY);
            }
            String dest = edgeMatcher.group(3);
            Edge temp = new Edge(0, origin, 0, dest, capacity);
            this.edges.add(temp);
        }
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        database.addEscapeNetwork(this.networkIdentifier, edges);
        return String.format(SUCCESSFUL_EXECUTION_MESSAGE, this.networkIdentifier);
    }
}
