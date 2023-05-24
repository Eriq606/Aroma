package assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;

public class ProduitIntermediaire extends Produit{
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
        String[] colonnes={"nom", "contact"};
        PreparedStatement statemnt=connect.prepareStatement("select nom,  from "+Constantes.view_produitIntermediaire+" where id=?");
        statemnt.setInt(0,getId());
        try{
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                setNom(result.getString(1));
            }
        }finally{

        }
    }
}
