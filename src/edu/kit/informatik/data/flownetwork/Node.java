package edu.kit.informatik.data.flownetwork;

/**
 * Node class that models a connection point of sections in an escape network
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class Node {
    /**
     * identifier that is given to this Nod
     */
    public final String identifier;
    private int index;

    /**
     * Constructor node when both index and identifier are given
     * @param index index of new node
     * @param identifier identifier of new node
     */
    public Node(int index, String identifier) {
        this.index = index;
        this.identifier = identifier;
    }

    /**
     * Constructor node when only identifier is given
     * @param identifier identifier of new node
     */
    public Node(String identifier) {
        this.identifier = identifier;
        this.index = 0;
    }

    /**
     * Constructor node when identifier is not needed
     * @param index index of new node
     */
    public Node(int index) {
        this.index = index;
        this.identifier = "";
    }

    /**
     * Sets index of this node
     * @param index index of this node
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gives index of this node
     * @return index of this node
     */
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
