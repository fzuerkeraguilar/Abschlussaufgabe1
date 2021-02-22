package edu.kit.informatik.io.commands;

import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;

//TODO das sollte eine ImputException sein
public class CommandNotFound extends Command{
    private String command;
    private ArrayList<String> parameters;


    public CommandNotFound(String command){
        this.command = command;
    }

    public CommandNotFound(String command, ArrayList<String> parameters){
        this.parameters = parameters;
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        return command + "not found";
    }
}
