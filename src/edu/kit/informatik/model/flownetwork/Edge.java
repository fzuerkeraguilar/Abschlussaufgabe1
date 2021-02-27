package edu.kit.informatik.model.flownetwork;

public class Edge implements Comparable<Edge> {
    private final Node origin;
    private final Node destination;
    private int capacity;
    private int flow;

    public Edge(String originIdentifier, String destinationIdentifier, int capacity){
        this.origin = new Node(originIdentifier);
        this.destination = new Node(destinationIdentifier);
        this.capacity = capacity;
    }

    public Edge(int origin, String originIdentifier, int destination, String destinationIdentifier, int capacity) {
        this.origin = new Node(origin, originIdentifier);
        this.destination = new Node(destination, destinationIdentifier);
        this.capacity = capacity;
        this.flow = 0;
    }

    public Edge(int origin, int destination, int capacity, int flow){
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

    void increaseFlow(int usage) {
        if (this.capacity - this.flow - usage < 0) {
            throw new IllegalArgumentException("Flow would be to high");
        }
        this.flow += usage;
    }

    void resetFlow(){
        this.flow = 0;
    }


    public int getRemainingCapacity() {
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

// --Commented out by Inspection START (26.02.2021 17:40):
//    public int getFlow() {
//        return flow;
//    }
// --Commented out by Inspection STOP (26.02.2021 17:40)

    /**
     * Returns String representation of given Edge
     * @return this.origin.identifier + this.capacity + this.destination.identifier (<v_1><k><v_2>)
     */
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

    public void setCapacity(int newCapacity){this.capacity = newCapacity;}

    @Override
    public Edge clone(){
        return new Edge(this.getOriginIndex(), this.getOriginIdentifier(), this.getDestIndex(), this.getDestIdentifier(), this.getCapacity());
    }
}
