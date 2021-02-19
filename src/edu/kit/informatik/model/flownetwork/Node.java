package edu.kit.informatik.model.flownetwork;

import java.util.ArrayList;

public class Node {
    public String identifier;
    private int index;
    protected ArrayList<Edge> outgoingEdges;

    public Node(int index) {
        this.index = index;
        this.outgoingEdges = new ArrayList<>();
    }

    public Node(int index, String identifier) {
        this.index = index;
        this.outgoingEdges = new ArrayList<>();
        this.identifier = identifier;
    }

    public Node(String identifier){
        this.identifier = identifier;
        this.index = 0;
        this.outgoingEdges = new ArrayList<>();
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void addEdge(Edge newEdge) {
        //TODO add checks
        this.outgoingEdges.add(newEdge.getDestIndex(), newEdge);
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
