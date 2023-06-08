package utils.exceptions;

public class QuantiteNegativeException extends Exception{
    public QuantiteNegativeException(){
        super("La quantite entree est negative ou nulle");
    }
}
