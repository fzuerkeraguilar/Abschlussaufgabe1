package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.model.Database;

import java.util.ArrayList;

public class Flow extends Command{
    public static final String REGEX = "flow";
    String identifier;
    String node1;
    String node2;

    @Override
    public void execute(Database database) {

    }

    //TODO Konstruktor implementieren
    public Flow(ArrayList<String> parameters){

    }
    //TODO Setter, Getter
}
