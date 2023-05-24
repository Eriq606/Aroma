package assets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import utils.Constantes;
import utils.MaConnection;
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

    public double getPrixAchat(){
        return getPrixUnitaire()*getQuantite();
    }

    public LocalDate getDate() {
        return date;
    }
    public Date getUtilsDate(){
        Date realDate=Date.valueOf(getDate());
        return realDate;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDate(String dateString){
        LocalDate realDate=LocalDate.parse(dateString);
        setDate(realDate);
    }
    public boolean insert(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("insert into mouvementproduit values(default,?,'?',?,0,?,null,?)");
        statemnt.setInt(0,getRessource().getId());
        statemnt.setDate(1, getUtilsDate());
        statemnt.setDouble(2, getQuantite());
        statemnt.setDouble(3, getPrixAchat());
        statemnt.setInt(4, getFournisseur().getId());
        try {
            statemnt.executeUpdate();
            if(opened){
                connect.commit();
            }
            return true;
        } catch (Exception e) {
            connect.rollback();
            throw e;
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
}
