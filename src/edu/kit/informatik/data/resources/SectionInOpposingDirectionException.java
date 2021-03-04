package edu.kit.informatik.data.resources;

/**
 * Custom exception to be thrown when an edge is added, but an edge already exists in the opposing direction
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class SectionInOpposingDirectionException extends DatabaseException {
    private static final String SECTION_IN_OPPOSING_DIRECTION = "Escape section already exists in opposing direction";

    /**
     * Sets message that should be displayed to the user
     */
    public SectionInOpposingDirectionException() {
        this.message = SECTION_IN_OPPOSING_DIRECTION;
    }
}
