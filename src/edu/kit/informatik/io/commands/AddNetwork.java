package edu.kit.informatik.io.commands;

import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Edge;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNetwork extends Command {
    public static final String REGEX = "add";
    private final String networkIdentifier;
    private final ArrayList<Edge> edges;
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "Added new escape network with identifier <n>.";

    public AddNetwork(ArrayList<String> parameters){
        edges = new ArrayList<>();
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);

        if(!parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER)){
            throw new IllegalArgumentException(parameters.get(0));
        }
        this.networkIdentifier = parameters.remove(0);
        for(String s : parameters){
            final Matcher edgeMatcher = commandPattern.matcher(s);
            edgeMatcher.find();
            String origin = edgeMatcher.group();
            edgeMatcher.find();
            int cap = Integer.parseInt(edgeMatcher.group());
            edgeMatcher.find();
            String dest = edgeMatcher.group();
            Edge temp = new Edge(origin, dest, cap);
            this.edges.add(temp);
        }
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        database.addEscapeNetwork(this.networkIdentifier, edges);
        return SUCCESSFUL_EXECUTION_MESSAGE;
    }
}
