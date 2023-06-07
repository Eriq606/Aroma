package assets;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Map;

import utils.Constantes;
import utils.MaConnection;
import utils.exceptions.QuantiteNegativeException;

public class Plantation {
    private int id;
    private ProduitIntermediaire produit;
    private double quantite;
    private LocalDate date;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ProduitIntermediaire getProduit() {
        return produit;
    }
    public void setProduit(ProduitIntermediaire produit) {
        this.produit = produit;
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
    public boolean verifierStock(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        try{
            for(Map.Entry<Ressource, Double> entry:getProduit().getRecette().getRecette().entrySet()){
                Stock stock=new Stock();
                stock.setProduit(entry.getKey());
                stock.getStockByProduit(connect);
                if(stock.getQuantite()<entry.getValue()){
                    return false;
                }
            }
            return true;
        }finally{
            if(opened){
                connect.close();
            }
        }
    }
    public void insert(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        try{
            UtilisationRessource utilisation=new UtilisationRessource();
            utilisation.setUtilisationByPlantation(this);
            utilisation.insert(connect);
            if(opened){
                connect.commit();
            }
        }catch(Exception e){
            connect.rollback();
            throw e;
        }finally{
            if(opened){
                connect.close();
            }
        }
    }
}
