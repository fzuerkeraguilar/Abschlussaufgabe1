package edu.kit.informatik.io.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.io.resources.exceptions.FalseFormatting;
import edu.kit.informatik.io.resources.exceptions.ValueOutOfRange;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Edge;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNetwork extends Command {
    public static final String REGEX = "add";
    private static final int minParameterNum = 3;
    private static final int minCapacity = 1;
    private static final int maxCapacity = Integer.MAX_VALUE;
    private final String networkIdentifier;
    private final ArrayList<Edge> edges;
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "Added new escape network with identifier %s.";

    public AddNetwork(ArrayList<String> parameters) throws FalseFormatting, ValueOutOfRange {
        if(parameters.size() < minParameterNum){
            throw new FalseFormatting(parameters.toString(),"<n> <e>");
        }

        edges = new ArrayList<>();
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);
        final Pattern nodePatter = Pattern.compile(REGEX_NODE_IDENTIFIER);

        if(!parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER)){
            throw new FalseFormatting( parameters.get(0),REGEX_NETWORK_IDENTIFIER);
        }
        this.networkIdentifier = parameters.remove(0);
        for(String s : parameters){
            final Matcher edgeMatcher = commandPattern.matcher(s);
            if(!edgeMatcher.matches()) throw new FalseFormatting(s,"<e><k><e>" );

            String origin = edgeMatcher.group(1);

            //TODO vielleicht zu BigInteger?
            long cap = Long.parseLong(edgeMatcher.group(2));
            if(cap < minCapacity || cap > maxCapacity){
                throw new ValueOutOfRange(minCapacity, maxCapacity);
            }
            String dest = edgeMatcher.group(3);
            Edge temp = new Edge(origin, dest, (int) cap);
            this.edges.add(temp);
        }
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        database.addEscapeNetwork(this.networkIdentifier, edges);
        return String.format(SUCCESSFUL_EXECUTION_MESSAGE, this.networkIdentifier);
    }
}
