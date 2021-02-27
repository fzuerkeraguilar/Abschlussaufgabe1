package edu.kit.informatik.io.input;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.io.commands.*;
import edu.kit.informatik.io.resources.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandParser {


    /** The regular expression of a single parameter*/
    private static final String REGEX_SINGLE_PARAMETER = "[^;\\n\\r]+";
    /** The regular expression of multiple parameters */
    private static final String REGEX_MULTIPLE_PARAMETER
            = REGEX_SINGLE_PARAMETER + "(?:;" + REGEX_SINGLE_PARAMETER + ")*";
    /** The regular expression of a generic command,
     * containing one capturing group for the command and one for all parameters */
    private static final String REGEX_COMMAND = "(\\S+)(?: (" + REGEX_MULTIPLE_PARAMETER + "))?";
    private static final int REGEX_GROUP_COMMAND_POSITION = 1;
    private static final int REGEX_GROUP_COMMAND_PARAMETER = 2;


    public Command output;

    public CommandParser(){}

    public Command parse(final String input) throws InputException {
        final Pattern commandPattern = Pattern.compile(REGEX_COMMAND);
        final Matcher commandMatcher = commandPattern.matcher(input);

        if (!commandMatcher.matches()) {
            throw new CommandNotFoundException(input);
        }

        final String commandName = commandMatcher.group(REGEX_GROUP_COMMAND_POSITION);

        if (commandMatcher.groupCount() < 2 || commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER) == null) {
            return interpretCommand(commandName);
        }

        final String parameterString = commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER);
        final ArrayList<String> parameters = extractParameters(parameterString);


        return interpretCommand(commandName, parameters);
    }

    private static Command interpretCommand(String command) throws CommandNotFoundException {
        switch (command){
            case ListNetworks.REGEX:
                return new ListNetworks();
            default: throw new CommandNotFoundException(command);
        }
    }

    private static Command interpretCommand(String command, ArrayList<String> parameters) throws InputException {
        if(parameters.size() == AddSection.PARAM_COUNT && command.equals(AddSection.REGEX)) return new AddSection(parameters);
        switch (command){
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

        paramMatcher.find();
        String temp = paramMatcher.group();
        Terminal.printLine(temp);

        ArrayList<String> tempParameter = new ArrayList<>(Arrays.asList(temp.split(" ")));
        Terminal.printLine(tempParameter);
        final ArrayList<String> parameters = new ArrayList<>(tempParameter);
        Terminal.printLine(parameters.toString());

        while (paramMatcher.find()) {
            parameters.add(paramMatcher.group());
            Terminal.printLine(parameters.toString());
        }

        return parameters;
    }

}
