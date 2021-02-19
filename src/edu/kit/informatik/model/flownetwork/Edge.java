package edu.kit.informatik.model.flownetwork;

import edu.kit.informatik.Terminal;

public class Edge implements Comparable<Edge> {
    private final Node origin;
    private final Node destination;
    private final int capacity;
    private int flow;

    public Edge(String originIdentifier, String destinationIdentifier, int capacity){
        assert capacity > 0;
        this.origin = new Node(originIdentifier);
        this.destination = new Node(destinationIdentifier);
        this.capacity = capacity;
    }

    public Edge(int origin, String originIdentifier, int destination, String destinationIdentifier, int capacity) {
        assert capacity > 0;
        this.origin = new Node(origin, originIdentifier);
        this.destination = new Node(destination, destinationIdentifier);
        this.capacity = capacity;
        this.flow = 0;
    }

    @Override
    public int compareTo(Edge e) {
        if (this.origin.getIdentifier().equals(e.origin.getIdentifier())) {
            return this.destination.getIdentifier().compareTo(e.destination.getIdentifier());
        }
        return this.origin.getIdentifier().compareTo(e.origin.getIdentifier());
    }

    void increaseFlow(int usage) {
        if (this.capacity - this.flow - usage < 0) {
            throw new IllegalArgumentException("Flow would be to high");
        }
        this.flow += usage;
    }

    int getRemainingCapacity() {
        return capacity - flow;
    }

    public int getOriginIndex() {
        return this.origin.getIndex();
    }

    public int getDestIndex() {
        return destination.getIndex();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public String toString() {
        String output = "";
        output += this.origin.toString();
        output += this.capacity;
        output += this.destination.toString();
        return output;
    }

    public Node getOrigin(){
        return this.origin;
    }

    public Node getDestination(){
        return this.destination;
    }
    public String getOriginIdentifier() {
        return this.origin.getIdentifier();
    }

    public String getDestIdentifier() {
        return this.destination.getIdentifier();
    }
}
