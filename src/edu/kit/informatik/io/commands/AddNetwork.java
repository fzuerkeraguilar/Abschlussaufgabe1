package edu.kit.informatik.io.commands;

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
    private static final int minCapacity = 1;
    private static final int maxCapacity = Integer.MAX_VALUE;
    private final String networkIdentifier;
    private final ArrayList<Edge> edges;
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "Added new escape network with identifier %s.";

    public AddNetwork(ArrayList<String> parameters) throws FalseFormatting, ValueOutOfRange {
        edges = new ArrayList<>();
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);

        if(!parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER)){
            throw new IllegalArgumentException(parameters.get(0));
        }
        this.networkIdentifier = parameters.remove(0);
        for(String s : parameters){
            final Matcher edgeMatcher = commandPattern.matcher(s);
            if(!edgeMatcher.find()) throw new FalseFormatting(s,"<e><k><e>" );
            String origin = edgeMatcher.group();
            if(!edgeMatcher.find()) throw new FalseFormatting(s,"<e><k><e>" );
            long cap = Long.parseLong(edgeMatcher.group());
            if(!(cap >= minCapacity && cap <= maxCapacity)){
                throw new ValueOutOfRange(minCapacity, maxCapacity);
            }
            if(!edgeMatcher.find()) throw new FalseFormatting(s,"<e><k><e>" );
            String dest = edgeMatcher.group();
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
