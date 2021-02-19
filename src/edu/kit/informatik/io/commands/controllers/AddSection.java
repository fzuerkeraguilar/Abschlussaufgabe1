package edu.kit.informatik.io.commands.controllers;

import edu.kit.informatik.io.commands.parameter.EscapeSection;
import edu.kit.informatik.model.Database;

import java.util.ArrayList;

public class AddSection extends Command {
    public static final String REGEX = "add";
    private EscapeSection Section;
    //TODO Befehl implementieren
    public AddSection(ArrayList<String> parameters){

    }

    @Override
    public void execute(Database database) {

    }
    //TODO Setter, Getter
}
