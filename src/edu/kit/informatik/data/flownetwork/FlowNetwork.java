package edu.kit.informatik.data.flownetwork;

import edu.kit.informatik.data.resources.NoLongerAValidEscapeNetworkException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * model of a weighted graph that represents a flow network
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public abstract class FlowNetwork {

    /**
     * For each node i stores all outgoing edges (EscapeSections) at index i
     */
    protected ArrayList<ArrayList<Edge>> adjArrayList;
    /**
     * Number of nodes in network
     */
    protected int nodes;
    /**
     * Node which was assigned the function of the source of the flow
     */
    protected Node source;
    /**
     * Node which was assigned the function of the sink of the flow
     */
    protected Node sink;

    /**
     * Constructor for empty flow network
     */
    public FlowNetwork() {
        this.nodes = 0;
        this.adjArrayList = new ArrayList<>(2);
    }

    /**
     * Calculates the flow from the set source node to the set sink node
     * @return capacity of this flow network
     */
    protected long getCapacity() {
        long capacity = 0;
        ArrayList<ArrayList<Edge>> remainingCapacityNetwork;
        remainingCapacityNetwork = this.cloneAdjList();

        //Finds shortest path with remaining capacity and augments flow through that path
        while (true) {
            Edge[] visited = new Edge[remainingCapacityNetwork.size()];
            Queue<Node> nextNode = new ArrayDeque<>();
            nextNode.add(this.source);
            //BFS to find shortest path with remaining capacity to this.sink
            while (!nextNode.isEmpty()) {
                ArrayList<Edge> currentNode = remainingCapacityNetwork.get(nextNode.remove().getIndex());
                for (Edge e : currentNode) {
                    if (e.getDestination().getIndex() != this.source.getIndex()) {
                        if (visited[e.getDestination().getIndex()] == null) {
                            if (e.getRemainingCapacity() > 0) {
                                if (e.getDestination().getIndex() != this.sink.getIndex()) {
                                    visited[e.getDestination().getIndex()] = e;
                                    nextNode.add(e.getDestination());
                                } else {
                                    visited[e.getDestination().getIndex()] = e;
                                    nextNode.clear();
                                    break;
                                }
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

            for (Edge e = visited[this.sink.getIndex()]; e != null; e = visited[e.getOrigin().getIndex()]) {
                newFlow = Math.min(newFlow, e.getRemainingCapacity());
            }

            for (Edge e = visited[this.sink.getIndex()]; e != null; e = visited[e.getOrigin().getIndex()]) {
                e.increaseFlow(newFlow);
                Edge returnFlow = new Edge(e.getDestination().getIndex(), e.getOrigin().getIndex(), 0, -newFlow);
                remainingCapacityNetwork.get(e.getDestination().getIndex()).add(returnFlow);
            }
            capacity += newFlow;
        }
        return capacity;
    }

    /**
     * Getter for the number of nodes
     * @return number of nodes in this network
     */
    public int getNumberOfNodes() {
        return this.nodes;
    }

    /**
     * Sets the source node to a node with given index.
     * @param index index of source node
     * @return true - if operation was successful; false - if node with given index can't be a source
     */
    protected boolean setSourceIndex(final int index) {
        if (!this.checkSource(index)) return false;
        this.source = new Node(index);
        return true;
    }

    /**
     * Sets the sink node to a node with given index.
     * @param index index of sink node
     * @return true - if operation was successful; false - if node with given index can't be a source
     */
    protected boolean setSinkIndex(final int index) {
        if (!this.checkSink(index)) return false;
        this.sink = new Node(index);
        return true;
    }

    /**
     * Checks if network is invalid.
     * @return true - if network is invalid; false - if network is valid
     */
    protected boolean networkNotValid() {
        boolean sourceFound = false;
        boolean sinkFound = false;
        int i = 0;
        while (!sourceFound && i < this.nodes) {
            sourceFound = this.checkSource(i);
            i++;
        }
        i = 0;
        while (!sinkFound && i < this.nodes) {
            sinkFound = this.checkSink(i);
            i++;
        }
        return !sourceFound || !sinkFound;
    }

    /**
     * Adds edge or updates capacity of existing edge.
     * If resulting network is invalid reverts back to old network.
     * @param newEdge Edge to be added
     * @throws NoLongerAValidEscapeNetworkException - if the edge would make the resulting network would be invalid.
     */
    protected void addEdge(Edge newEdge) throws NoLongerAValidEscapeNetworkException {
        ArrayList<ArrayList<Edge>> adjListBackup;
        adjListBackup = this.cloneAdjList();
        for (Edge e : this.adjArrayList.get(newEdge.getOrigin().getIndex())) {
            if (e.getDestination().getIndex() == newEdge.getDestination().getIndex()) {
                e.setCapacity(newEdge.getCapacity());
                return;
            }
        }
        this.adjArrayList.get(newEdge.getOrigin().getIndex()).add(newEdge);
        if (this.networkNotValid()) {
            this.adjArrayList = adjListBackup;
            throw new NoLongerAValidEscapeNetworkException(newEdge.toString());
        }
    }

    private ArrayList<ArrayList<Edge>> cloneAdjList() {
        ArrayList<ArrayList<Edge>> output = new ArrayList<>();
        for (int i = 0; i < this.adjArrayList.size(); i++) {
            output.add(new ArrayList<>());
            for (Edge e : this.adjArrayList.get(i)) {
                output.get(i).add(e.clone());
            }
        }
        return output;
    }

    private boolean checkSink(final int sinkIndex) {
        if (this.adjArrayList.size() == sinkIndex) {
            return true;
        }
        return this.adjArrayList.get(sinkIndex).isEmpty();
    }

    private boolean checkSource(final int sourceIndex) {
        for (ArrayList<Edge> a : this.adjArrayList) {
            for (Edge e : a) {
                if (e.getDestination().getIndex() == sourceIndex) {
                    return false;
                }
            }
        }
        return true;
    }
}
