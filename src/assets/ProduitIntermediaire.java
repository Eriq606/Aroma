package assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;
import utils.exceptions.RessourceNonExistantException;

public class ProduitIntermediaire extends Produit{
    private RecetteProduitIntermediaire recette;
    public RecetteProduitIntermediaire getRecette() {
        return recette;
    }
    public void setRecette(RecetteProduitIntermediaire recette) {
        this.recette = recette;
    }
    public boolean setRecette(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_id_ressource+", "+Constantes.col_quantite_recette+
                                                            " from "+Constantes.table_recette+" where "+Constantes.col_id_produit_intermediaire+"="+getId());
        try{
            ResultSet results=statemnt.executeQuery();
            RecetteProduitIntermediaire recette=new RecetteProduitIntermediaire();
            while(results.next()){
                Ressource ressource=new Ressource();
                ressource.setId(results.getInt(0));
                ressource.getRessourceById(connect);
                recette.getRecette().put(ressource, results.getDouble(1));
            }
            setRecette(recette);
            return true;
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }

    public static ArrayList<ProduitIntermediaire> getAllProduitIntermediaires(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_id_produit+", "+Constantes.col_nom_produit+", "+
                                                                Constantes.col_id_type+", "+Constantes.col_nom_type+", "+
                                                                Constantes.col_id_unite+", "+Constantes.col_nom_unite+" from "+Constantes.view_produitIntermediaire);
        try{
            ArrayList<ProduitIntermediaire> produits=new ArrayList<ProduitIntermediaire>();
            ResultSet results=statemnt.executeQuery();
            while(results.next()){
                ProduitIntermediaire produit=new ProduitIntermediaire();
                produit.setId(results.getInt(0));
                produit.setNom(results.getString(1));
                TypeProduit type=new TypeProduit();
                type.setId(results.getInt(2));
                type.setNom(results.getString(3));
                produit.setType(type);
                Unite unite=new Unite();
                unite.setId(results.getInt(4));
                unite.setNom(results.getString(5));
                produit.setUnite(unite);
                produit.setRecette(connect);
                produits.add(produit);
            }
            return produits;
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
    public void getProduitIntermediaireById(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_nom_produit+
                                                                ","+Constantes.col_id_type+", "+Constantes.col_nom_type
                                                                +", "+Constantes.col_id_unite+", "+Constantes.col_nom_unite+" from "+Constantes.view_produitIntermediaire+" where id=?");
        statemnt.setInt(0,getId());
        try{
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                setNom(result.getString(0));
                TypeProduit type=new TypeProduit();
                type.setId(result.getInt(1));
                type.setNom(result.getString(2));
                setType(type);
                Unite unite=new Unite();
                unite.setId(result.getInt(3));
                unite.setNom(result.getString(4));
                setUnite(unite);
                setRecette(connect);
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
