package edu.kit.informatik.io.resources.exceptions;

public class CommandNotFoundException extends InputException{
    public CommandNotFoundException(String command){
        this.message = String.format(InputException.COMMAND_NOT_FOUND,  command);
    }
}
