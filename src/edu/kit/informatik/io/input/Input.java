package edu.kit.informatik.io.input;

import edu.kit.informatik.Terminal;

public class Input {
    String nextInput;

    public Input(){}

    public void readInput(){
        nextInput = Terminal.readLine();
    }

    public String getNextInput(){
        return nextInput;
    }
}
