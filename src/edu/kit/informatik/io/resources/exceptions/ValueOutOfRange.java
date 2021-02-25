package edu.kit.informatik.io.resources.exceptions;


public class ValueOutOfRange extends InputException{
    public ValueOutOfRange(int lowerBound, int upperBound){
        this.message = String.format(InputException.VALUE_OUT_OF_EXPECTED_RANGE, lowerBound, upperBound);
    }
}
