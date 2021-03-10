package edu.kit.informatik.io.ouput;

import edu.kit.informatik.Terminal;

/**
 * Output class that handles getting the outputs to the user
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Output {
    /**
     * Displays message to user when command is executed successfully
     * @param output output that should be displayed to the user
     */
    public void print(String output) {
        Terminal.printLine(output);
    }

    /**
     * Displays error message to user when execution encountered a problem
     * @param output error message that should be displayed to the user
     */
    public void printError(String output) {
        Terminal.printError(output);
    }
}
