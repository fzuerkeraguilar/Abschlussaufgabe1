package edu.kit.informatik.io.resources.exceptions;

/**
 * Custom exception to be thrown when certain value is out of given range
 * @author Fabian Manuel Zürker Aguilar
 * @version 1.0
 */
public class ValueOutOfRangeException extends InputException {
    private static final String VALUE_OUT_OF_EXPECTED_RANGE = "expected value between %1$d and %2$d";

    /**
     * Formats given values into a meaningful error message
     * @param lowerBound the minimum value a given input should be
     * @param upperBound the maximum value a given input should be
     */
    public ValueOutOfRangeException(int lowerBound, int upperBound) {
        this.message = String.format(VALUE_OUT_OF_EXPECTED_RANGE, lowerBound, upperBound);
    }
}
