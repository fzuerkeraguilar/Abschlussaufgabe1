package edu.kit.informatik.io.resources.exceptions;

/**
 * Custom exception to be thrown when command is not found
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class CommandNotFoundException extends InputException {

    private static final String COMMAND_NOT_FOUND = "\"%s\" is not a valid command";

    /**
     * Formats given string into a meaningful error message
     * @param command string that could not be interpreted as a command
     */
    public CommandNotFoundException(String command) {
        this.message = String.format(COMMAND_NOT_FOUND,  command);
    }
}
