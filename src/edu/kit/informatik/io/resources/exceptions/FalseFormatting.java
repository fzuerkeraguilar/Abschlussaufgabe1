package edu.kit.informatik.io.resources.exceptions;

public class FalseFormatting extends InputException{
    public FalseFormatting(String input, String format){
        this.message = String.format(InputException.FALSE_FORMATTING, input, format);
    }
}
