package edu.kit.informatik.core.flownetwork;

import java.util.ArrayList;

public abstract class FlowNetwork extends Named{
    public class Edge extends Named{
        private final int origin;
        private final int dest;
        private final double capacity;
        private double usedcapacity;

        private Node origin2;

        public Edge(int origin, int dest, int capacity) {
            this.origin = origin;
            this.dest = dest;
            this.capacity = capacity;
            this.usedcapacity = 0;
        }

        private void setUsedCapacity(int usage){
            if(this.capacity - this.usedcapacity - usage < 0){
                throw new IllegalArgumentException("Usage to high");
            }
            this.usedcapacity = usage;
        }

        private double getRemainingCapacity(){
            return capacity - usedcapacity;
        }

        private int getOrigin(){
            return this.origin;
        }

        public int getDest() {
            return dest;
        }

        public double getCapacity() {
            return capacity;
        }

        public double getUsedCapacity() {
            return usedcapacity;
        }
    }

    public class Node extends Named{
        private final int index;
        private final String name;

        public Node(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }


    /**
     * For each node i stores all outgoing edges (EscapeWays) at index i
     */
    ArrayList<ArrayList<Edge>> adjacencyArrayList;

    int nodes;
    int sourceNode;
    int sinkNode;

    public FlowNetwork(int nodes){
        this.nodes = nodes;
        this.adjacencyArrayList = new ArrayList<>(nodes);
    }

    public void addEdge(int origin, int dest, int capacity){
        if(this.adjacencyArrayList.get(dest).get(origin) != null) throw new IllegalArgumentException("Escape way");
        Edge way = new Edge(origin, dest, capacity);
        this.adjacencyArrayList.get(origin).add(way);
    }
    //TODO getCapacity
    public double getCapacity(){
        for(ArrayList<Edge> a: this.adjacencyArrayList){
            for(Edge e : a){
                e.setUsedCapacity(0);
            }
            //TODO
        }
    return 0;
    }

    //TODO mehrmals findShortstPath? oder mit AdjMatrix?
    boolean computeValidity(){
        return false;
    }


    ArrayList<Edge> findShortestPath(){
        ArrayList<ArrayList<Integer>> possiblePaths = new ArrayList<>(1);
        ArrayList<Edge> shortestPath = new ArrayList<>(0);
        ArrayList<Integer> nextNodes = new ArrayList<>(1);
        nextNodes.add(this.sourceNode);
        for(int i : nextNodes){
            for(Edge e : this.adjacencyArrayList.get(i)){
                if(e.dest == this.sinkNode){
                    shortestPath.add(e);
                    return shortestPath;
                } else {
                    nextNodes.add(e.dest);
                    //TODO Nachdem man Pfad findet, den speichern
                    return shortestPath;
                }
            }
        }
        return null;
    }


}
