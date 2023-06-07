package it.italiandudes.mymcserver.utils.exceptions;

public class NameTooLongException extends NumberFormatException{
    public NameTooLongException() throws NumberFormatException{
        throw new NumberFormatException();
    }
}
