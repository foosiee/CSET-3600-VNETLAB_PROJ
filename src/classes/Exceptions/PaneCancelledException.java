package classes.Exceptions;

public class PaneCancelledException extends Exception 
{ 
    public PaneCancelledException(String errorMessage) 
    {
        super(errorMessage);
    }
}