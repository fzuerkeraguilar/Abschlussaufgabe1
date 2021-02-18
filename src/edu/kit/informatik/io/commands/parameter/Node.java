package edu.kit.informatik.io.commands.parameter;

public class Node extends Parameter<Node>{
    Name name;

    public Node(String name) {
        this.name = new Name(name);
    }
}
