package edu.kit.informatik.io.resources.exceptions;

/**
 * Custom exception to be thrown when command is not found
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */

public class FalseFormattingException extends InputException {
    private static final String FALSE_FORMATTING = "\"%1$s\", does not follow the pattern:%2$s";

    /**
     * Formats given strings into a meaningful error message
     * @param input input that doesn't match a given pattern
     * @param format pattern that the input should match
     */
    public FalseFormattingException(String input, String format) {
        this.message = String.format(FALSE_FORMATTING, input, format);
    }
}
