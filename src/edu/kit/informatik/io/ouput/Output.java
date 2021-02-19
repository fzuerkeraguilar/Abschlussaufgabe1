package edu.kit.informatik.io.ouput;

import edu.kit.informatik.Terminal;

public class Output {
    public void print(String output){
        Terminal.printLine(output);
    }

    public void printError(String output){
        Terminal.printError(output);
    }
}
