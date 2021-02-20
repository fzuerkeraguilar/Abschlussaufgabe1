package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Result;

import java.util.ArrayList;
import java.util.Collections;

public class ListResults extends Command{
    public static final String REGEX = "list";
    String networkIdentifier;


    @Override
    public void execute(Database database) {
        ArrayList<Result> results = database.getResults(this.networkIdentifier);
        Collections.sort(results);
        StringBuilder resultBuilder = new StringBuilder();
        for(Result r : results){
            resultBuilder.append(r);
            resultBuilder.append(System.lineSeparator());
        }
        Terminal.printLine(resultBuilder.substring(0, resultBuilder.length() - 1));
    }

    //TODO Befehl implementieren
    public ListResults(ArrayList<String> parameters){
        assert parameters.size() == 1;
        this.networkIdentifier = parameters.get(0);
    }
    //TODO Setter, Getter
}
