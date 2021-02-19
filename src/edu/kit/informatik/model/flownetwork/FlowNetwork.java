package edu.kit.informatik.model.flownetwork;

import edu.kit.informatik.Terminal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public abstract class FlowNetwork implements Comparable<FlowNetwork>{

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
        Terminal.printLine(this.adjacencyArrayList.toString());
    }

    public void addEdge(Edge newEdge){
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
                ArrayList<Edge> currentNode = this.adjacencyArrayList.get(nextNode.remove().getIndex());
                for(Edge e: currentNode){
                    if(path.get(e.getDestIndex()) == null && e.getDestIndex() != this.source.getIndex() && e.getCapacity() > e.getFlow()) {
                        path.add(e.getDestIndex(), e);
                        for(Edge f : this.adjacencyArrayList.get(e.getDestIndex())){
                            nextNode.add(f.getDestination());
                        }
                    }
                }
            }

            if(path.get(this.sink.getIndex()) == null){
                break;
            }

            int newFlow = Integer.MAX_VALUE;

            for(Edge e = path.get(this.sink.getIndex()); e !=  null; e = path.get(e.getOriginIndex())){
                newFlow = Math.min(newFlow, e.getRemainingCapacity());
            }

            for(Edge e = path.get(this.sink.getIndex()); e !=  null; e = path.get(e.getOriginIndex())){
                e.increaseFlow(newFlow);
            }
        }
    return capacity;
    }

    public int compareTo(FlowNetwork n){
        return Integer.compare(this.nodes, n.nodes);
    }

}
