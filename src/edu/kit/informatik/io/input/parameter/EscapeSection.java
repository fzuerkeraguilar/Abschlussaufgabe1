package edu.kit.informatik.io.input.parameter;

public class EscapeSection extends Parameter {
    Node origin;
    Node dest;
    int capacity;
    public EscapeSection(String origin, String dest, int capacity){
        this.capacity = capacity;
        this.origin = new Node(origin);
        this.dest = new Node(dest);
    }
}
