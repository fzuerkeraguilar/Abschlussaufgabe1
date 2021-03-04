package edu.kit.informatik.data.resources;


/**
 * Custom exception to be thrown when an edge is added that has the same origin and destination
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class SameOriginAndDestinationException extends DatabaseException {
    private static final String SAME_ORIGIN_AND_DESTINATION = "Origin and destination may not be the same";

    /**
     * Sets message that should be displayed to the user
     */
    public SameOriginAndDestinationException() {
        this.message = SAME_ORIGIN_AND_DESTINATION;
    }
}
