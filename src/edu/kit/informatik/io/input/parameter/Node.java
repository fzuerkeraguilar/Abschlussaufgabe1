package edu.kit.informatik.io.input.parameter;

public class Node extends Parameter{
    Name name;

    public Node(String name) {
        this.name = new Name(name);
    }
}
