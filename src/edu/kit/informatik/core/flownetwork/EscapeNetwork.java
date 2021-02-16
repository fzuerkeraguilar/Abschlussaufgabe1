package edu.kit.informatik.core.flownetwork;

import java.util.HashMap;

public class EscapeNetwork extends FlowNetwork {
    HashMap<String, Integer> edgeNameTable;

    public EscapeNetwork(int vertices) {
        super(vertices);
        this.edgeNameTable = new HashMap<>();
    }

    public void addEscapeWay(String origin, String dest, int capacity){
        if(origin.equals(dest)) throw new IllegalArgumentException("Origin and destination may not be the same");
        this.addEdge(this.convertName(origin), this.convertName(dest), capacity);
    }




    private void setSourceAndSink(String source, String sink) {
        this.sourceNode = this.convertName(source);
        this.sinkNode = this.convertName(sink);
    }

    private int convertName(String name){
        if(this.edgeNameTable.get(name) == null) throw new IllegalArgumentException("No such Edge exists");
        return this.edgeNameTable.get(name);
    }
}
