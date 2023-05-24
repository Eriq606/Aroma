package assets;

import java.time.LocalDate;

import utils.exceptions.QuantiteNegativeException;

public class AjoutRessource {
    private int id;
    private Ressource ressource;
    private Fournisseur fournisseur;
    private double quantite;
    private LocalDate date;
    private double prixUnitaire;
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) throws QuantiteNegativeException {
        if(prixUnitaire<=0){
            throw new QuantiteNegativeException();
        }
        this.prixUnitaire = prixUnitaire;
    }
    public void setPrixUnitaire(String prixString) throws QuantiteNegativeException{
        double realPrix=Double.parseDouble(prixString);
        setPrixUnitaire(realPrix);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Ressource getRessource() {
        return ressource;
    }
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) throws QuantiteNegativeException {
        if(quantite<=0){
            throw new QuantiteNegativeException();
        }
        this.quantite = quantite;
    }
    public void setQuantite(String quantiteString) throws QuantiteNegativeException{
        double realQuantite=Double.parseDouble(quantiteString);
        setQuantite(realQuantite);
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDate(String dateString){
        LocalDate realDate=LocalDate.parse(dateString);
        setDate(realDate);
    }
}
