package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.Node;

import java.util.ArrayList;

public class Flow extends Command{
    public static final String REGEX = "flow";
    String networkIdentifier;
    Node node1;
    Node node2;

    @Override
    public void execute(Database database) {
        Terminal.printLine(database.getCapacity(networkIdentifier, node1.identifier, node2.identifier));
    }

    //TODO Konstruktor implementieren
    public Flow(ArrayList<String> parameters){
        assert parameters.size() == 3;
        this.networkIdentifier = parameters.get(0);
        this.node1 = new Node(parameters.get(1));
        this.node2 = new Node(parameters.get(2));
    }
    //TODO Setter, Getter
}
