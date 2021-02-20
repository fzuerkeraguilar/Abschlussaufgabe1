package edu.kit.informatik.model.flownetwork;

import edu.kit.informatik.Terminal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public abstract class FlowNetwork{

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
        this.adjacencyArrayList = new ArrayList<>(2);
    }

    public void addEdge(Edge newEdge){
        for(Edge e : this.adjacencyArrayList.get(newEdge.getOriginIndex())){
            if(e.getDestIndex() == newEdge.getDestIndex()){
                e.setCapacity(newEdge.getCapacity());
                return;
            }
        }
        this.adjacencyArrayList.get(newEdge.getOriginIndex()).add(newEdge);
    }


    public long getCapacity() {
        long capacity = 0;
        for (ArrayList<Edge> a : this.adjacencyArrayList) {
            for (Edge e : a) {
                e.setFlow(0);
            }
        }
        while (true) {
            Edge[] visited = new Edge[this.adjacencyArrayList.size()];
            Queue<Node> nextNode = new ArrayDeque<>();
            nextNode.add(this.source);
            while (!nextNode.isEmpty()) {
                ArrayList<Edge> currentNode = this.adjacencyArrayList.get(nextNode.remove().getIndex());
                for (Edge e : currentNode) {
                    if (visited[e.getDestIndex()] == null) {
                        if (e.getCapacity() > e.getFlow()) {
                            if (e.getDestIndex() != this.source.getIndex()) {
                                visited[e.getDestIndex()] = e;
                                nextNode.add(e.getDestination());
                            } else {
                                visited[e.getDestIndex()] = e;
                                nextNode.clear();
                                break;
                            }
                        }
                    }
                }
            }

            if (visited[this.sink.getIndex()] == null) {
                break;
            }

            int newFlow = Integer.MAX_VALUE;

            for (Edge e = visited[this.sink.getIndex()]; e != null; e = visited[e.getOriginIndex()]) {
                newFlow = Math.min(newFlow, e.getRemainingCapacity());
            }

            capacity += newFlow;

            for (Edge e = visited[this.sink.getIndex()]; e != null; e = visited[e.getOriginIndex()]) {
                e.increaseFlow(newFlow);
            }
        }
        return capacity;
    }

    public int compareTo(FlowNetwork n){
        return Integer.compare(this.nodes, n.nodes);
    }

}
