package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Edge;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSection extends Command {
    public static final String REGEX = "add";
    public static final int PARAM_COUNT = 2;
    private String networkIdentifier;
    private String origin;
    private String dest;
    private int cap;

    //TODO Befehl implementieren
    public AddSection(ArrayList<String> parameters){
        Terminal.printLine(parameters.toString());
        this.networkIdentifier = parameters.remove(0);
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);
        final Matcher edgeMatcher = commandPattern.matcher(parameters.get(0));
        edgeMatcher.find();
        this.origin = edgeMatcher.group();
        edgeMatcher.find();
        this.cap = Integer.parseInt(edgeMatcher.group());
        edgeMatcher.find();
        this.dest = edgeMatcher.group();
    }

    @Override
    public void execute(Database database) {
        database.addNewSection(this.networkIdentifier, this.origin, this.dest, this.cap);
    }
    //TODO Setter, Getter
}
