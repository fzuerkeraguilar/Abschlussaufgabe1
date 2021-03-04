package edu.kit.informatik.data.flownetwork;

/**
 * Edge class that models a escape section in an escape network
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Edge implements Comparable<Edge> {
    private final Node origin;
    private final Node destination;
    private int capacity;
    private int flow;

    /**
     * Constructor of new Edge, when only the names are given
     * @param originIdentifier identifier of the node this edge originates from
     * @param destinationIdentifier identifier of the node this edge terminates in
     * @param capacity capacity of this edge
     */
    public Edge(String originIdentifier, String destinationIdentifier, int capacity) {
        this.origin = new Node(originIdentifier);
        this.destination = new Node(destinationIdentifier);
        this.capacity = capacity;
    }

    /**
     * Constructor of new Edge, when all parameters are known
     * @param origin index of the node this edge originates from
     * @param originIdentifier identifier of the node this edge originates from
     * @param destination index of the node this edge terminates in
     * @param destinationIdentifier identifier of the node this edge terminates in
     * @param capacity capacity of this edge
     */
    public Edge(int origin, String originIdentifier, int destination, String destinationIdentifier, int capacity) {
        this.origin = new Node(origin, originIdentifier);
        this.destination = new Node(destination, destinationIdentifier);
        this.capacity = capacity;
        this.flow = 0;
    }

    /**
     * Constructor of new Edge, when only the indexes are known and a flow is given
     * @param origin index of the node this edge originates from
     * @param destination index of the node this edge terminates in
     * @param capacity capacity of this edge
     * @param flow flow of this Edge
     */
    public Edge(int origin, int destination, int capacity, int flow) {
        this.origin = new Node(origin);
        this.destination = new Node(destination);
        this.capacity = capacity;
        this.flow = flow;
    }

    @Override
    public int compareTo(Edge e) {
        if (this.origin.getIdentifier().equals(e.origin.getIdentifier())) {
            return this.destination.getIdentifier().compareTo(e.destination.getIdentifier());
        }
        return this.origin.getIdentifier().compareTo(e.origin.getIdentifier());
    }

    /**
     * Increases the flow through this edge by given amount, if new flow would exceed capacity does nothing
     * @param usage amount of flow that should be added to existing flow
     */
    protected void increaseFlow(int usage) {
        if (this.capacity - this.flow - usage < 0) {
            return;
        }
        this.flow += usage;
    }

    /**
     * resets flow of this edge to 0
     */
    protected void resetFlow() {
        this.flow = 0;
    }

    /**
     * Returns how much capacity this edge still has available
     * @return the remaining capacity of this escape section
     */
    public int getRemainingCapacity() {
        return capacity - flow;
    }

    /**
     * Gives the index of the node this edge originates from
     * @return the index of the node this edge originates from; null if no index was given to node
     */
    public int getOriginIndex() {
        return this.origin.getIndex();
    }

    /**
     * Gives the index of the node this edge terminates in
     * @return the index of the node this edge terminates in; null if no index was given to node
     */
    public int getDestIndex() {
        return this.destination.getIndex();
    }

    /**
     * Gives capacity of this edge
     * @return capacity of this edge
     */
    public int getCapacity() {
        return this.capacity;
    }


    /**
     * Gives String representation of given Edge
     * @return this.origin.identifier + this.capacity + this.destination.identifier (<v_1><k><v_2>)
     */
    public String toString() {
        String output = "";
        output += this.origin.toString();
        output += this.capacity;
        output += this.destination.toString();
        return output;
    }

    /**
     * Gives the origin Node of this Edge
     * @return origin Node of this Edge
     */
    public Node getOrigin() {
        return this.origin;
    }

    /**
     * Gives the destination Node of this Edge
     * @return destination Node of this Edge
     */
    public Node getDestination() {
        return this.destination;
    }

    /**
     * Gives the given identifier of the origin Node of this Edge
     * @return the given identifier of the origin Node of this Edge
     */
    public String getOriginIdentifier() {
        return this.origin.getIdentifier();
    }

    /**
     * Gives the given identifier of the destination Node of this Edge
     * @return the given identifier of the destination Node of this Edge
     */
    public String getDestIdentifier() {
        return this.destination.getIdentifier();
    }

    /**
     * Sets new capacity for this edge
     * @param newCapacity new capacity
     */
    public void setCapacity(int newCapacity) { this.capacity = newCapacity; }

    @Override
    public Edge clone() {
        return new Edge(this.getOriginIndex(),
                this.getOriginIdentifier(),
                this.getDestIndex(),
                this.getDestIdentifier(),
                this.getCapacity());
    }
}
