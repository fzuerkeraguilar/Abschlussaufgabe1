package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormattingException;
import edu.kit.informatik.io.resources.exceptions.ValueOutOfRangeException;
import edu.kit.informatik.data.Database;
import edu.kit.informatik.data.resources.DatabaseException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * extends Command class
 * used if a new escape section needs to be added to an existing network
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class AddSection extends Command {
    /**
     * Identifier of this command
     */
    public static final String REGEX = "add";
    /**
     * Amount of parameters this Command needs
     */
    public static final int PARAM_COUNT = 2;
    private static final String SUCCESSFUL_EXECUTION_MESSAGE = "Added new section %1$s to escape network %2$s.";
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private final String networkIdentifier;
    private final String origin;
    private final String dest;
    private final long cap;

    /**
     * Constructor of AddSection Object
     * @param parameters List of parameters
     * @throws FalseFormattingException if the parameters were not formatted correctly
     * @throws ValueOutOfRangeException if the capacity value is out of range
     */
    public AddSection(ArrayList<String> parameters) throws FalseFormattingException, ValueOutOfRangeException {
        this.networkIdentifier = parameters.remove(0);
        final Pattern commandPattern = Pattern.compile(REGEX_EDGE);
        final Matcher edgeMatcher = commandPattern.matcher(parameters.get(0));
        if (!edgeMatcher.matches()) throw new FalseFormattingException(parameters.get(0), "<e><k><e>");

        this.origin = edgeMatcher.group(1);

        this.cap = Long.parseLong(edgeMatcher.group(2));
        if (cap < MIN_CAPACITY || cap > MAX_CAPACITY) {
            throw new ValueOutOfRangeException(MIN_CAPACITY, MAX_CAPACITY);
        }

        this.dest = edgeMatcher.group(3);
    }

    /**
     * Executes the command on given database
     * @param database Database on which the command should be executed
     * @return message of successful execution if command executed correctly
     * @throws DatabaseException if the database recognizes a problem with the input
     */
    @Override
    public String execute(Database database) throws DatabaseException {
        database.addNewEscapeSection(this.networkIdentifier, this.origin, this.dest, (int) this.cap);
        return String.format(SUCCESSFUL_EXECUTION_MESSAGE, this.origin + this.cap + this.dest, this.networkIdentifier);
    }
}
