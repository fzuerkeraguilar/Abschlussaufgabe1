package edu.kit.informatik.io.input;

import edu.kit.informatik.Terminal;

/**
 * Input class that handles getting the inputs from the user
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Input {

    private String nextInput;

    /**
     * constructor of Input class
     */
    public Input() { }

    /**
     * gets the latest input of the user
     */
    public void readInput() {
        nextInput = Terminal.readLine();
    }

    /**
     * Getter of the next input, that need executing
     * @return input given by the user
     */
    public String getNextInput() {
        return nextInput;
    }
}
