package edu.kit.informatik.io.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.io.resources.exceptions.FalseFormatting;
import edu.kit.informatik.io.resources.exceptions.ValueOutOfRange;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSection extends Command {
    public static final String REGEX = "add";
    public static final int PARAM_COUNT = 2;
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "Added new section %1$s to escape network %2$s.";
    private static final int minCapacity = 1;
    private static final int maxCapacity = Integer.MAX_VALUE;
    private final String networkIdentifier;
    private final String origin;
    private final String dest;
    private final long cap;

    //TODO Befehl implementieren
    public AddSection(ArrayList<String> parameters) throws FalseFormatting, ValueOutOfRange {
        this.networkIdentifier = parameters.remove(0);
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);
        final Matcher edgeMatcher = commandPattern.matcher(parameters.get(0));
        if(!edgeMatcher.find()) throw new FalseFormatting(parameters.get(0),"<e><k><e>" );
        this.origin = edgeMatcher.group();
        if(!edgeMatcher.find()) throw new FalseFormatting(parameters.get(0),"<e><k><e>" );
        this.cap = Long.parseLong(edgeMatcher.group());
        if(cap < minCapacity || cap > maxCapacity){
            throw new ValueOutOfRange(minCapacity, maxCapacity);
        }
        if(!edgeMatcher.find()) throw new FalseFormatting(parameters.get(0),"<e><k><e>" );
        this.dest = edgeMatcher.group();
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        database.addNewEscapeSection(this.networkIdentifier, this.origin, this.dest, (int) this.cap);
        return String.format(SUCCESSFUL_EXECUTION_MESSAGE, this.origin+this.cap+this.dest, this.networkIdentifier);
    }
}
