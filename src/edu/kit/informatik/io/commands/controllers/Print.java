package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.Database;

import java.util.ArrayList;

public class Print extends Command{
    public static final String REGEX = "print";
    private final String networkIdentifier;

    @Override
    public void execute(Database database) {
        Terminal.printLine(database.toString(this.networkIdentifier));
    }

    public Print(ArrayList<String> parameters){
        assert parameters.get(0).matches(REGEX_NETWORK_IDENTIFIER);
        this.networkIdentifier = parameters.remove(0);
    }
}
