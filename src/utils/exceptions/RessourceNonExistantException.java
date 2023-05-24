package utils.exceptions;

public class RessourceNonExistantException extends Exception{
    public RessourceNonExistantException(){
        super("La ressource n'existe pas");
    }
}
