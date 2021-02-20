package edu.kit.informatik.model;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.flownetwork.Edge;
import edu.kit.informatik.model.flownetwork.EscapeNetwork;
import edu.kit.informatik.model.flownetwork.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Database {
    HashMap<String, EscapeNetwork> escapeNetworkTable = new HashMap<>();

    public EscapeNetwork getEscapeNetwork(String name){
        return this.escapeNetworkTable.get(name);
    }

    //TODO vielleicht was anderes als ArrayList übergeben,
    public void addEscapeNetwork(String networkIdentifier, ArrayList<Edge> edges){
        if(this.escapeNetworkTable.containsKey(networkIdentifier)){
            throw new IllegalArgumentException("Name already in use");
        }
        EscapeNetwork temp = new EscapeNetwork(edges, networkIdentifier);
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

    public Collection<EscapeNetwork> getNetworks(){
        return this.escapeNetworkTable.values();
    }

    public String getNetworkName(EscapeNetwork network){
        return null;
        //return this.escapeNetworkTable.
    }

    public EscapeNetwork getNetwork(String networkName){
        return this.escapeNetworkTable.get(networkName);
    }

    public ArrayList<Result> getResults(String networkIdentifier){
        return this.escapeNetworkTable.get(networkIdentifier).getResultList();
    }

    public boolean containsNetwork(String networkIdentifier){
        return this.escapeNetworkTable.containsKey(networkIdentifier);
    }

    public void addNewSection(String networkIdentifier, String origin, String dest, int capacity){
        this.escapeNetworkTable.get(networkIdentifier).addEscapeSection(origin, dest, capacity);
    }

}
