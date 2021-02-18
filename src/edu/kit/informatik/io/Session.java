package edu.kit.informatik.io;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.io.commands.Command;

public class Session {
    private String nextCommand;
    private boolean quit;

    public void commandReader(){
        this.quit = false;
        while(!quit){
            nextCommand = Terminal.readLine();
        }
    }
}
