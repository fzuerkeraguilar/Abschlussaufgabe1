package edu.kit.informatik.io.commands;

import edu.kit.informatik.io.resources.exceptions.FalseFormattingException;
import edu.kit.informatik.data.Database;
import edu.kit.informatik.data.flownetwork.CapacityResult;
import edu.kit.informatik.data.resources.DatabaseException;

import java.util.Collections;
import java.util.List;

/**
 * extends Command class
 * used if the results of previous flow calculations of a network need to be listed
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class ListResults extends Command {
    /**
     * Identifier of this command
     */
    public static final String REGEX = "list";
    private static final String NO_RESULT_FOUND = "EMPTY";
    private static final int PARAM_COUNT = 1;
    private final String networkIdentifier;

    /**
     * Constructor of ListResult object
     * @param parameters List of parameters
     * @throws FalseFormattingException if parameters do not conform to specification
     */
    public ListResults(List<String> parameters) throws FalseFormattingException {
        if (parameters.size() != PARAM_COUNT) {
            throw new FalseFormattingException(parameters.toString(), "<n>");
        }
        this.networkIdentifier = parameters.get(0);
    }

    /**
     * Executes the command on given database
     * @param database Database on which the command should be executed
     * @return message of successful execution if command executed correctly
     * @throws DatabaseException if the database recognizes a problem with the input
     */
    @Override
    public String execute(Database database) throws DatabaseException {
        List<CapacityResult> capacityResults = database.getResults(this.networkIdentifier);
        if (capacityResults.size() == 0) {
            return NO_RESULT_FOUND;
        }
        Collections.sort(capacityResults);
        StringBuilder resultBuilder = new StringBuilder();
        for (CapacityResult r : capacityResults) {
            resultBuilder.append(r);
            resultBuilder.append(System.lineSeparator());
        }
        return resultBuilder.substring(0, resultBuilder.length() - 1);
    }



}
