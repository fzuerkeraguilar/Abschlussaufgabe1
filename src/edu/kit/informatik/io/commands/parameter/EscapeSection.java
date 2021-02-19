package edu.kit.informatik.io.commands.parameter;

public class EscapeSection extends Parameter {
    Node origin;
    Node dest;
    int capacity;
    public EscapeSection(String origin, String dest, int capacity){
        //super(parameterList);
        this.capacity = capacity;
        this.origin = new Node(origin);
        this.dest = new Node(dest);
    }
}
