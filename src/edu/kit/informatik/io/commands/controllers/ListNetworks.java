package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.EscapeNetwork;

import java.util.ArrayList;
import java.util.Collections;

public class ListNetworks extends Command{
    public static final String REGEX = "list";


    @Override
    public void execute(Database database) {
        ArrayList<EscapeNetwork> networks = new ArrayList<>(database.getNetworks());
        Terminal.printLine(networks.toString());
        Collections.sort(networks);
        Terminal.printLine(networks.toString());
        StringBuilder networkLister = new StringBuilder();
        for(EscapeNetwork n : networks){
            networkLister.append(n.getIdentifier());
            networkLister.append(" ");
            networkLister.append(n.getNodeNumber());
            networkLister.append(System.lineSeparator());
        }
        Terminal.printLine(networkLister.substring(0, networkLister.length() - 1));
    }

    public ListNetworks(){
        //TODO
    }
    //TODO Setter, Getter
}
