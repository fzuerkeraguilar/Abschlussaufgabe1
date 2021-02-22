package edu.kit.informatik.io;

import edu.kit.informatik.io.input.CommandParser;
import edu.kit.informatik.io.commands.Command;
import edu.kit.informatik.io.input.Input;
import edu.kit.informatik.io.input.exceptions.InputException;
import edu.kit.informatik.io.ouput.Output;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.resources.DatabaseException;

public class Session {
    private final Database database1 = new Database();

    private final Output outputHandler = new Output();
    private final CommandParser commandParser = new CommandParser();
    private final Input inputHandler = new Input();
    private boolean quit = false;

    public Session(){}

    public void run(){
        while(!quit){
            this.inputHandler.readInput();
            if(inputHandler.getNextInput().equals("quit")){
                this.quit();
                break;
            }
            try {
                Command nextCommand = commandParser.parse(inputHandler.getNextInput());
                outputHandler.print(nextCommand.execute(this.database1));
            } catch (InputException | DatabaseException e){
                outputHandler.printError(e.getMessage());
                e.printStackTrace();
            }

        }
    }

    private void quit(){
        this.quit = true;
    }

}
