package edu.kit.informatik.core;

import edu.kit.informatik.core.flownetwork.EscapeNetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Database {
    HashMap<String, EscapeNetwork> escapeNetworkTable = new HashMap<>();

    public EscapeNetwork getEscapeNetwork(String name){
        return this.escapeNetworkTable.get(name);
    }

    //TODO change type of escapeRoutes to Graph
    public void addEscapeNetwork(String name, String[] escapeRoutes){
        if(this.escapeNetworkTable.containsKey(name)) throw new IllegalArgumentException("Name is already in use");
        EscapeNetwork temp = new EscapeNetwork(escapeRoutes.length);
        //TODO add escapeRoutes to EscapeNetwork temp
        this.escapeNetworkTable.put(name, temp);
    }

}
