package edu.kit.informatik.io.input;

import edu.kit.informatik.io.commands.*;
import edu.kit.informatik.io.resources.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This file is modified form the IPDCommandParser.java of the solution of the santorini game by Lucas Alber
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class CommandParser {

    /** The regular expression of a single parameter*/
    private static final String REGEX_SINGLE_PARAMETER = "[^;\\n\\r ]+";
    /** The regular expression of multiple parameters */
    private static final String REGEX_MULTIPLE_PARAMETER
            = "[^;\\n\\r]+" + "(?:;" + REGEX_SINGLE_PARAMETER + ")*";
    /** The regular expression of a generic command,
     * containing one capturing group for the command and one for all parameters */
    private static final String REGEX_COMMAND = "(\\S+)(?: (" + REGEX_MULTIPLE_PARAMETER + "))?";
    private static final int REGEX_GROUP_COMMAND_INDEX = 1;
    private static final int REGEX_GROUP_COMMAND_PARAMETER_INDEX = 2;

    /**
     * Constructs a new command parser.
     */
    public CommandParser() { }

    /**
     * Parses the given String and returns a command object based on the given input
     * @param input the input string
     * @return Command object that corresponds with the given command
     * @throws InputException if problem is detected during the construction of the Command object
     */
    public Command parse(final String input) throws InputException {
        final Pattern commandPattern = Pattern.compile(REGEX_COMMAND);
        final Matcher commandMatcher = commandPattern.matcher(input);

        if (!commandMatcher.matches()) {
            throw new CommandNotFoundException(input);
        }

        final String commandName = commandMatcher.group(REGEX_GROUP_COMMAND_INDEX);

        if (commandMatcher.groupCount() < 2 || commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER_INDEX) == null) {
            return interpretCommand(commandName);
        }

        final String parameterString = commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER_INDEX);
        final List<String> parameters = extractParameters(parameterString);


        return interpretCommand(commandName, parameters);
    }

    private static Command interpretCommand(String command) throws CommandNotFoundException {
        switch (command) {
            case ListNetworks.REGEX:
                return new ListNetworks();
            default: throw new CommandNotFoundException(command);
        }
    }

    private static Command interpretCommand(String command, List<String> parameters) throws InputException {
        if (parameters.size() == AddSection.PARAM_COUNT && command.equals(AddSection.REGEX)) {
            return new AddSection(parameters);
        }
        switch (command) {
            case AddNetwork.REGEX:
                return new AddNetwork(parameters);
            case Print.REGEX:
                return new Print(parameters);
            case Flow.REGEX:
                return new Flow(parameters);
            case ListResults.REGEX:
                return new ListResults(parameters);
            default: throw new CommandNotFoundException(command);
        }
    }


    private static ArrayList<String> extractParameters(final String parameterString) {

        final Pattern singleParam = Pattern.compile(REGEX_SINGLE_PARAMETER);
        final Matcher paramMatcher = singleParam.matcher(parameterString);

        final ArrayList<String> parameters = new ArrayList<>();

        while (paramMatcher.find()) {
            parameters.add(paramMatcher.group());
        }

        return parameters;
    }

}
