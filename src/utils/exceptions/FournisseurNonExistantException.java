package utils.exceptions;

public class FournisseurNonExistantException extends Exception{
    public FournisseurNonExistantException(){
        super("Le fournisseur n'existe pas");
    }
}
