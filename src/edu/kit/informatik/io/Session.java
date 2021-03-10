package edu.kit.informatik.io;

import edu.kit.informatik.io.input.CommandParser;
import edu.kit.informatik.io.input.Input;
import edu.kit.informatik.io.ouput.Output;
import edu.kit.informatik.io.commands.Command;
import edu.kit.informatik.io.resources.exceptions.InputException;
import edu.kit.informatik.data.Database;
import edu.kit.informatik.data.resources.DatabaseException;

/**
 * Session class that coordinates the logic of the application
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Session {
    private static final String EXIT_COMMAND = "quit";
    private final Database database1 = new Database();
    private final Output outputHandler = new Output();
    private final CommandParser commandParser = new CommandParser();
    private final Input inputHandler = new Input();
    private boolean quit = false;

    /**
     * Constructor of new session
     */
    public Session() { }

    /**
     * Main loop of application that handles input and output
     */
    public void run() {
        while (!quit) {
            this.inputHandler.readInput();
            if (this.inputHandler.getNextInput().equals(EXIT_COMMAND)) {
                this.quit = true;
                break;
            }
            try {
                Command nextCommand = this.commandParser.parse(this.inputHandler.getNextInput());
                this.outputHandler.print(nextCommand.execute(this.database1));
            } catch (InputException | DatabaseException e) {
                this.outputHandler.printError(e.getMessage());
            }

        }
    }
}