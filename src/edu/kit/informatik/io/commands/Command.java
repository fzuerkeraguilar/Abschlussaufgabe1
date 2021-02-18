package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.commands.parameter.Parameter;

import java.util.List;

public abstract class Command {
    String rawCommand;
    List<Parameter<?>> parameterList;

}
