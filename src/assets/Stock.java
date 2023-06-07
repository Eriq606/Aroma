package assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Constantes;
import utils.MaConnection;
import utils.exceptions.RessourceNonExistantException;

public class Stock {
    private Produit produit;
    private double quantite;
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public void getStockByProduit(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_quantite_restant+
                                                            " from "+Constantes.view_stock+
                                                            " where "+Constantes.col_id_ressource+"=?");
        statemnt.setInt(0, getProduit().getId());
        try{
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                setQuantite(result.getDouble(0));
            }else{
                throw new RessourceNonExistantException();
            }
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
}
