package edu.kit.informatik.model.flownetwork;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EscapeNetwork extends FlowNetwork{
    //HashMap<String, Integer> edgeNameTable;
    HashMap<String, Integer> nodeNameTable;
    HashMap<String, HashMap<String, Long>> calculatedResults;



    public EscapeNetwork() {
        super();
        this.nodeNameTable = new HashMap<>();
        this.calculatedResults = new HashMap<>();
        //if(!this.computeValidity()) throw new IllegalArgumentException("Graph is no valid Flow-Network");
    }

    /**
     * Adds new escape section with given capacity for given source and origin node or updates capacity of
     * existing escape section
     * @param origin name of origin node
     * @param dest  name of destination node
     * @param capacity capacity of Section between origin and destination node
     */
    public void addEscapeSection(String origin, String dest, int capacity){
        //TODO check ob, es auch die capacity eines Edges ändert, wenn es schon existiert
        if(origin.equals(dest)) throw new IllegalArgumentException("Origin and destination may not be the same");
        if(capacity <= 0) throw new IllegalArgumentException("Capacity must be bigger than zero");
        if(!nodeNameTable.containsKey(origin)) throw new IllegalArgumentException("Origin node does not exist");
        if(!nodeNameTable.containsKey(dest)) throw new IllegalArgumentException("Destination node does not exist");
        Edge newEdge = new Edge(this.convertName(origin), origin, this.convertName(dest), dest, capacity);
        this.addEdge(newEdge);
    }

    /**
     * Looks up if result already calculated or needs to be calculated
     * returns capacity of escape network for given source and sink
     * @param sourceName identifier of source
     * @param sinkName identifier of sink
     * @return max capacity of escape network
     */
    public long computeCapacity(String sourceName, String sinkName){
        if(this.calculatedResults.containsKey(sourceName)){
            if(this.calculatedResults.get(sourceName).containsKey(sinkName)){
                return this.calculatedResults.get(sourceName).get(sinkName);
            }
        }
        this.setSource(this.convertName(sourceName));
        this.setSink(this.convertName(sinkName));
        long capacity = this.getCapacity();
        HashMap<String, Long> temp = new HashMap<>();
        temp.put(sinkName, capacity);
        this.calculatedResults.put(sourceName, temp);
        return capacity;
    }

    public String toString(){
        ArrayList<Edge> tempSortEdges = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        for(ArrayList<Edge> a : this.adjacencyArrayList){
            tempSortEdges.addAll(a);
        }
        Collections.sort(tempSortEdges);
        for(Edge e: tempSortEdges){
            output.append(e.toString());
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }



    private int convertName(String name){
        if(this.nodeNameTable.get(name) == null) throw new IllegalArgumentException(name + "does not exist");
        return this.nodeNameTable.get(name);
    }

    private void setSource(int index){
        if(!this.checkSource(index)) throw new IllegalArgumentException("this Node can't be a sink");
        this.source = new Node(index);
        this.calculatedResults.clear();
    }

    private boolean checkSource(int sourceIndex){
        for(ArrayList<Edge> a : this.adjacencyArrayList){
            for(Edge e : a){
                if(e.getDestIndex() == sourceIndex){
                    return false;
                }
            }
        }
        return true;
    }

    private void setSink(int index){
        if(!this.checkSink(index)) throw new IllegalArgumentException("this Node can't be a sink");
        this.sink = new Node(index);
        this.calculatedResults.clear();
    }

    private boolean checkSink(int sinkIndex){
        return this.adjacencyArrayList.get(sinkIndex).size() == 0;
    }

}
