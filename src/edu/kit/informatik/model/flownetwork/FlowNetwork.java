package edu.kit.informatik.model.flownetwork;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public abstract class FlowNetwork implements Comparable<FlowNetwork>{

    public class Edge{
        private final Node origin;
        private final Node dest;
        private final int capacity;
        private int flow;

        public Edge(int origin, int dest, int capacity) {
            assert capacity > 0;
            this.origin = new Node(origin);
            this.dest = new Node(dest);
            this.capacity = capacity;
            this.flow = 0;
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

        private int getOrigin(){
            return this.origin.index;
        }

        public int getDest() {
            return dest.index;
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
            output += this.dest.toString();
            return output;
        }
    }

    public class Node{
        private final int index;
        protected ArrayList<Edge> outgoingEdges;
        public Node(int index) {
            this.index = index;
            this.outgoingEdges = new ArrayList<>();
        }

        public void addEdge(Edge newEdge){
            //TODO add checks
            this.outgoingEdges.add(newEdge.getDest(), newEdge);
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
        //TODO add checks
        this.adjacencyArrayList.get(newEdge.getOrigin()).add(newEdge.getDest(), newEdge);
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
                    if(path.get(e.getDest()) == null && e.getDest() != this.source.index && e.getCapacity() > e.getFlow()) {
                        path.add(e.getDest(), e);
                        for(Edge f : this.adjacencyArrayList.get(e.getDest())){
                            nextNode.add(f.dest);
                        }
                    }
                }
            }

            if(path.get(this.sink.index) == null){
                break;
            }

            int newFlow = Integer.MAX_VALUE;

            for(Edge e = path.get(this.sink.index); e !=  null; e = path.get(e.getOrigin())){
                newFlow = Math.min(newFlow, e.getRemainingCapacity());
            }

            for(Edge e = path.get(this.sink.index); e !=  null; e = path.get(e.getOrigin())){
                e.increaseFlow(newFlow);
            }
        }
    return capacity;
    }

    public int compareTo(FlowNetwork n){
        return Integer.compare(this.nodes, n.nodes);
    }

}
