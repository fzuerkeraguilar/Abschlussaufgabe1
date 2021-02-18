package edu.kit.informatik.model.flownetwork;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public abstract class FlowNetwork implements Comparable<FlowNetwork>{

    public class Edge implements Comparable<Edge>{
        private final Node origin;
        private final Node destination;
        private final int capacity;
        private int flow;

        public Edge(int origin, String originIdentifier, int destination, String destinationIdentifier, int capacity) {
            assert capacity > 0;
            this.origin = new Node(origin, originIdentifier);
            this.destination = new Node(destination, destinationIdentifier);
            this.capacity = capacity;
            this.flow = 0;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.getOriginIndex() == o.getOriginIndex()){
                return this.destination.identifier.compareTo(o.destination.identifier);
            }
            return this.origin.identifier.compareTo(o.origin.identifier);
        }

        private void increaseFlow(int usage){
            if(this.capacity - this.flow - usage < 0){
                throw new IllegalArgumentException("Flow would be to high");
            }
            this.flow += usage;
        }

        private int getRemainingCapacity(){
            return capacity - flow;
        }

        private int getOriginIndex(){
            return this.origin.index;
        }

        public int getDestIndex() {
            return destination.index;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getFlow() {
            return flow;
        }

        public String toString(){
            String output = "";
            output += this.origin.toString();
            output += this.capacity;
            output += this.destination.toString();
            return output;
        }

        public String getOriginIdentifier(){
            return this.origin.identifier;
        }

        public String getDestIdentifier(){
            return this.destination.identifier;
        }
    }

    public class Node{
        public String identifier;
        private final int index;
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


        public void addEdge(Edge newEdge){
            //TODO add checks
            this.outgoingEdges.add(newEdge.getDestIndex(), newEdge);
        }

        public String getIdentifier(){
            return identifier;
        }
    }

    /**
     * For each node i stores all outgoing edges (EscapeSections) at index i
     *      at index j in second layer the edge to node j is stored
     * Vielleicht in ein "ArrayList<Node>" machen? und dann die outgoing Edges in den Nodes speichern?
     */
    ArrayList<ArrayList<Edge>> adjacencyArrayList;
    int nodes;
    Node source;
    Node sink;

    public FlowNetwork(){
        this.nodes = 0;
        this.adjacencyArrayList = new ArrayList<>(nodes);
    }

    public void addEdge(Edge newEdge){
        if(newEdge.origin.equals(newEdge.destination)) throw new IllegalArgumentException("Origin and destination may not be the same");
        if(newEdge.getCapacity() <= 0) throw new IllegalArgumentException("Capacity must be bigger than zero");
        this.adjacencyArrayList.get(newEdge.getOriginIndex()).add(newEdge.getDestIndex(), newEdge);
        this.nodes++;
    }


    public long getCapacity(){
        long capacity = 0;
        for(ArrayList<Edge> a: this.adjacencyArrayList){
            for(Edge e : a){
                e.increaseFlow(0);
            }
        }
        while(true){
            ArrayList<Edge> path = new ArrayList<>(this.adjacencyArrayList.size());
            Queue<Node> nextNode = new ArrayDeque<>();
            nextNode.add(this.source);
            while (!nextNode.isEmpty()){
                ArrayList<Edge> currentNode = this.adjacencyArrayList.get(nextNode.remove().index);
                for(Edge e: currentNode){
                    if(path.get(e.getDestIndex()) == null && e.getDestIndex() != this.source.index && e.getCapacity() > e.getFlow()) {
                        path.add(e.getDestIndex(), e);
                        for(Edge f : this.adjacencyArrayList.get(e.getDestIndex())){
                            nextNode.add(f.destination);
                        }
                    }
                }
            }

            if(path.get(this.sink.index) == null){
                break;
            }

            int newFlow = Integer.MAX_VALUE;

            for(Edge e = path.get(this.sink.index); e !=  null; e = path.get(e.getOriginIndex())){
                newFlow = Math.min(newFlow, e.getRemainingCapacity());
            }

            for(Edge e = path.get(this.sink.index); e !=  null; e = path.get(e.getOriginIndex())){
                e.increaseFlow(newFlow);
            }
        }
    return capacity;
    }

    public int compareTo(FlowNetwork n){
        return Integer.compare(this.nodes, n.nodes);
    }

}
