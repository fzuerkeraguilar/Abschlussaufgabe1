package edu.kit.informatik.io;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.io.commands.CommandParser;
import edu.kit.informatik.io.commands.controllers.Command;
import edu.kit.informatik.model.Database;

public class Session {
    private final Database database1 = new Database();

    private final CommandParser commandParser = new CommandParser();
    private boolean quit;

    public Session(){}

    //TODO Fehlerbehandlung an ErrorHandler auslagern
    public void run(){
        this.quit = false;
        while(!quit){
            String input = Terminal.readLine();
            if(input.equals("quit")){
                this.quit();
                return;
            }
            try {
                Command nextCommand = commandParser.parse(input);
                nextCommand.execute(this.database1);
            } catch (Exception e){
                Terminal.printLine(e);
                e.printStackTrace();
            }

        }
    }

    private void quit(){
        this.quit = true;
    }

}
