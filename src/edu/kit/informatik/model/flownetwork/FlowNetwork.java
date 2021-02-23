package edu.kit.informatik.model.flownetwork;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.model.resources.NotAValidEscapeNetwork;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
        this.nodes++;
    }

    public long getCapacity() {
        long capacity = 0;
        for (ArrayList<Edge> a : this.adjArrayList) {
            for (Edge e : a) {
                e.resetFlow();
            }
        }
        //Finds shortest path with remaining capacity and augments flow through that path
        while (true) {
            Edge[] visited = new Edge[this.adjArrayList.size()];
            Queue<Node> nextNode = new ArrayDeque<>();
            nextNode.add(this.source);
            //BFS to find shortest path with remaining capacity to this.sink
            while (!nextNode.isEmpty()) {
                ArrayList<Edge> currentNode = this.adjArrayList.get(nextNode.remove().getIndex());
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
            }
            capacity += newFlow;
        }
        return capacity;
    }

    public int getNodeNumber(){
        return this.nodes ;
    }

    protected void setSource(final int index){
        if(!this.checkSource(index)) throw new IllegalArgumentException("this Node can't be a source");
        this.source = new Node(index);
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

    protected void setSink(final int index){
        if(!this.checkSink(index)) throw new IllegalArgumentException("this Node can't be a sink");
        this.sink = new Node(index);
    }

    protected boolean checkSink(final int sinkIndex){
        if(this.adjArrayList.size() == sinkIndex){
            return true;
        }
        return this.adjArrayList.get(sinkIndex).isEmpty();
    }

    protected boolean networkNoLongerValid(){
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

    protected void tryToAddEdge(Edge e) throws NotAValidEscapeNetwork {
        ArrayList<ArrayList<Edge>> adjListBackup = new ArrayList<>(this.adjArrayList);
        this.addEdge(e);
        if(this.networkNoLongerValid()){
            this.adjArrayList = adjListBackup;
            throw new NotAValidEscapeNetwork(e.toString());
        }
    }
}
