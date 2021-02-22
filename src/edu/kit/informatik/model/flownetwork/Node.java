package edu.kit.informatik.model.flownetwork;


public class Node {
    public String identifier;
    private int index;

    public Node(int index) {
        this.index = index;
    }

    public Node(int index, String identifier) {
        this.index = index;
        this.identifier = identifier;
    }

    public Node(String identifier){
        this.identifier = identifier;
        this.index = 0;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getIndex() {
        return this.index;
    }

    /**
     * Returns String representation of given Node
     * @return this.identifier (<v>)
     */
    @Override
    public String toString() {
        return this.identifier;
    }
}
