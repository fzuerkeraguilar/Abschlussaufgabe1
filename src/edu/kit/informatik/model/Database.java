package edu.kit.informatik.model;

import edu.kit.informatik.model.flownetwork.EscapeNetwork;
import edu.kit.informatik.model.flownetwork.FlowNetwork;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    HashMap<String, EscapeNetwork> escapeNetworkTable = new HashMap<>();

    public EscapeNetwork getEscapeNetwork(String name){
        return this.escapeNetworkTable.get(name);
    }

    //TODO vielleicht was anderes als ArrayList übergeben, vielleicht dafür separate Klasse erstellen?, oder addEscapeSection nutzen
    public void addEscapeNetwork(String name, ArrayList<FlowNetwork.Edge> edges){
        if(this.escapeNetworkTable.containsKey(name)) throw new IllegalArgumentException("Name is already in use");
        EscapeNetwork temp = new EscapeNetwork();
        for(FlowNetwork.Edge e : edges){
            temp.addEdge(e);
        }
        this.escapeNetworkTable.put(name, temp);
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

}
