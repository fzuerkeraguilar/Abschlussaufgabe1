package edu.kit.informatik.model;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.flownetwork.Edge;
import edu.kit.informatik.model.flownetwork.EscapeNetwork;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    HashMap<String, EscapeNetwork> escapeNetworkTable = new HashMap<>();

    public EscapeNetwork getEscapeNetwork(String name){
        return this.escapeNetworkTable.get(name);
    }

    //TODO vielleicht was anderes als ArrayList übergeben,
    public void addEscapeNetwork(String networkIdentifier, ArrayList<Edge> edges){
        assert !this.escapeNetworkTable.containsKey(networkIdentifier);
        EscapeNetwork temp = new EscapeNetwork(edges);
        this.escapeNetworkTable.put(networkIdentifier, temp);
        Terminal.printLine(networkIdentifier);
    }

    public String escapeNetworkToString(String networkName){
        if(!this.escapeNetworkTable.containsKey(networkName)) {
            throw new IllegalArgumentException(networkName + " does not exist");
        }
        return this.escapeNetworkTable.get(networkName).toString();
    }

    public long getCapacity(String networkName, String sourceName, String sinkName){
        if(!this.escapeNetworkTable.containsKey(networkName)) {
            throw new IllegalArgumentException(networkName + " does not exist");
        }
        return this.escapeNetworkTable.get(networkName).computeCapacity(sourceName, sinkName);
    }

    public String toString(String identifier){
        return this.escapeNetworkTable.get(identifier).toString();
    }

}
