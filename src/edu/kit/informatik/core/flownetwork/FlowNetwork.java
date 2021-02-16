package edu.kit.informatik.core.flownetwork;

import java.util.ArrayList;

public abstract class FlowNetwork {
    private class Edge {
        private final int origin;
        private final int dest;
        private final double capacity;
        private double usedcapacity;

        private Edge(int origin, int dest, int capacity) {
            this.origin = origin;
            this.dest = dest;
            this.capacity = capacity;
            this.usedcapacity = 0;
        }

        private void setUsedCapacity(int usage){
            if(this.capacity - this.usedcapacity - usage <= 0){
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
        return 0;
    }

    private boolean computeValidity(){
        return false;
    }

}
