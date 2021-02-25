package edu.kit.informatik.model.flownetwork;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.resources.NoLongerAValidEscapeNetwork;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public abstract class FlowNetwork{

    /**
     * For each node i stores all outgoing edges (EscapeSections) at index i
     */
    protected ArrayList<ArrayList<Edge>> adjArrayList;
    protected int nodes;
    protected Node source;
    protected Node sink;

    public FlowNetwork(){
        this.nodes = 0;
        this.adjArrayList = new ArrayList<>(2);
    }

    public void addEdge(Edge newEdge){
        for(Edge e : this.adjArrayList.get(newEdge.getOriginIndex())){
            if(e.getDestIndex() == newEdge.getDestIndex()){
                e.setCapacity(newEdge.getCapacity());
                return;
            }
        }
        this.adjArrayList.get(newEdge.getOriginIndex()).add(newEdge);
    }

    public long getCapacity() {
        long capacity = 0;
        ArrayList<ArrayList<Edge>> remainingCapacityNetwork = new ArrayList<>();
        for(ArrayList<Edge> a : this.adjArrayList){
            remainingCapacityNetwork.add((ArrayList<Edge>) a.clone());
        }

        for (ArrayList<Edge> a : remainingCapacityNetwork) {
            for (Edge e : a) {
                e.resetFlow();
            }
        }
        //Finds shortest path with remaining capacity and augments flow through that path
        while (true) {
            Edge[] visited = new Edge[remainingCapacityNetwork.size()];
            Queue<Node> nextNode = new ArrayDeque<>();
            nextNode.add(this.source);
            //BFS to find shortest path with remaining capacity to this.sink
            while (!nextNode.isEmpty()) {
                ArrayList<Edge> currentNode = remainingCapacityNetwork.get(nextNode.remove().getIndex());
                for (Edge e : currentNode) {
                    if(e.getDestIndex() != this.source.getIndex()){
                        if (visited[e.getDestIndex()] == null) {
                            if (e.getRemainingCapacity() > 0) {
                                if (e.getDestIndex() != this.sink.getIndex()) {
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
            }
            Terminal.printLine(Arrays.toString(visited));
            //Exists while loop, when no augmenting path is found
            if (visited[this.sink.getIndex()] == null) {
                break;
            }

            int newFlow = Integer.MAX_VALUE;

            for (Edge e = visited[this.sink.getIndex()]; e != null; e = visited[e.getOriginIndex()]) {
                newFlow = Math.min(newFlow, e.getRemainingCapacity());
            }

            for (Edge e = visited[this.sink.getIndex()]; e != null; e = visited[e.getOriginIndex()]) {
                e.increaseFlow(newFlow);
                Edge returnFlow = new Edge(e.getDestIndex(), e.getOriginIndex(), 0, -newFlow);
                remainingCapacityNetwork.get(e.getDestIndex()).add(returnFlow);
            }
            capacity += newFlow;
        }
        return capacity;
    }

    public int getNumberOfNodes(){
        return this.nodes ;
    }

    protected boolean setSourceIndex(final int index){
        if(!this.checkSource(index)) return false;
        this.source = new Node(index);
        return true;
    }

    protected boolean checkSource(final int sourceIndex){
        for(ArrayList<Edge> a : this.adjArrayList){
            for(Edge e : a){
                if(e.getDestIndex() == sourceIndex){
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean setSinkIndex(final int index){
        if(!this.checkSink(index)) return false;
        this.sink = new Node(index);
        return true;
    }

    protected boolean checkSink(final int sinkIndex){
        if(this.adjArrayList.size() == sinkIndex){
            return true;
        }
        return this.adjArrayList.get(sinkIndex).isEmpty();
    }

    protected boolean networkNotValid(){
        boolean sourceFound = false;
        boolean sinkFound = false;
        int i = 0;
        while(!sourceFound && i < this.nodes){
            sourceFound = this.checkSource(i);
            i++;
        }
        i = 0;
        while(!sinkFound && i < this.nodes){
            sinkFound = this.checkSink(i);
            i++;
        }
        return !sourceFound || !sinkFound;
    }

    protected void tryToAddEdge(Edge e) throws NoLongerAValidEscapeNetwork {
        ArrayList<ArrayList<Edge>> adjListBackup = new ArrayList<>();
        for(ArrayList<Edge> a : this.adjArrayList){
            adjListBackup.add((ArrayList<Edge>) a.clone());
        }
        //TODO this.addEdge increases this.edge by one
        this.addEdge(e);
        if(this.networkNotValid()){
            this.adjArrayList = adjListBackup;
            throw new NoLongerAValidEscapeNetwork(e.toString());
        }
    }

    private ArrayList<ArrayList<Edge>> cloneAdjList(){
        ArrayList<ArrayList<Edge>> output = new ArrayList<>();
        for(int i = 0; i < this.adjArrayList.size(); i++){
            output.add(new ArrayList<>());
            for(Edge e : this.adjArrayList.get(i)){
                output.get(i).add(e.clone());
            }
        }
        return output;
    }
}
