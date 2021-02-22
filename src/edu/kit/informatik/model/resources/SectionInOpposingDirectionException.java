package edu.kit.informatik.model.resources;

public class SectionInOpposingDirectionException extends DatabaseException{

    public SectionInOpposingDirectionException(){
        this.message = DatabaseException.SECTION_IN_OPPOSING_DIRECTION;
    }
}
