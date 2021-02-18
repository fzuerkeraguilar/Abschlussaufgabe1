package edu.kit.informatik.io.resources;

public class ErrorFormatter extends Exception{
    //TODO das richtig
    public static void nodeErrorFormatting(String v, String Error) throws Exception {
        throw  new Exception(v + Error);
    }

    public static void graphErrorFormatting(String n, String Error) throws Exception {
        throw  new Exception(n + Error);
    }

    public static void sectioErrorFormatting(String e, String Error) throws Exception {
        throw new Exception(e+Error);
    }

}
