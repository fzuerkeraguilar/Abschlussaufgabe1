package edu.kit.informatik.io.commands;

import edu.kit.informatik.model.Database;
import edu.kit.informatik.model.flownetwork.CapacityResult;
import edu.kit.informatik.model.resources.DatabaseException;

import java.util.ArrayList;
import java.util.Collections;

public class ListResults extends Command {
    public static final String REGEX = "list";
    public static final String NO_RESULT_FOUND = "EMPTY";
    public static final int PARAM_COUNT = 1;
    String networkIdentifier;

    public ListResults(ArrayList<String> parameters) {
        assert parameters.size() == PARAM_COUNT;
        this.networkIdentifier = parameters.get(0);
    }

    @Override
    public String execute(Database database) throws DatabaseException {
        ArrayList<CapacityResult> capacityResults = database.getResults(this.networkIdentifier);
        if(capacityResults.size() == 0){
            return NO_RESULT_FOUND;
        }
        Collections.sort(capacityResults);
        StringBuilder resultBuilder = new StringBuilder();
        for(CapacityResult r : capacityResults){
            resultBuilder.append(r);
            resultBuilder.append(System.lineSeparator());
        }
        return resultBuilder.substring(0, resultBuilder.length() - 1);
    }



}
