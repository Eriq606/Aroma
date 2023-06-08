package assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;
import utils.exceptions.RessourceNonExistantException;

public class Ressource extends Produit {
    public static ArrayList<Ressource> getAllRessources(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_id_produit+", "+Constantes.col_nom_produit+", "+
                                                                        Constantes.col_id_type+", "+Constantes.col_nom_type+", "+
                                                                        Constantes.col_id_unite+", "+Constantes.col_nom_unite+" from "+Constantes.view_ressource);
        try {
            ArrayList<Ressource> ressources=new ArrayList<Ressource>();
            ResultSet results=statemnt.executeQuery();
            while(results.next()){
                Ressource ressource=new Ressource();
                ressource.setId(results.getInt(0));
                ressource.setNom(results.getString(1));
                TypeProduit type=new TypeProduit();
                type.setId(results.getInt(2));
                type.setNom(results.getString(3));
                ressource.setType(type);
                Unite unite=new Unite();
                unite.setId(results.getInt(4));
                unite.setNom((results.getString(5)));
                ressource.setUnite(unite);
                ressources.add(ressource);
            }
            return ressources;
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
    public static Ressource getRessourceById(Connection connex, int id) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_id_produit+", "+Constantes.col_nom_produit+
                                                                Constantes.col_id_type+", "+Constantes.col_nom_type+", "+
                                                                Constantes.col_id_unite+", "+Constantes.col_nom_unite+
                                                                " from "+Constantes.view_ressource+" where id=?");
        statemnt.setInt(0, id);
        try {
            ResultSet result=statemnt.executeQuery();
            Ressource ressource=new Ressource();
            if(result.next()){
                ressource.setId(result.getInt(0));
                ressource.setNom(result.getString(1));
                TypeProduit type=new TypeProduit();
                type.setId(result.getInt(2));
                type.setNom(result.getString(3));
                ressource.setType(type);
                Unite unite=new Unite();
                unite.setId(result.getInt(4));
                unite.setNom((result.getString(5)));
                ressource.setUnite(unite);
                return ressource;
            }else{
                throw new RessourceNonExistantException();
            }
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
    public void getRessourceById(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select "+Constantes.col_nom_produit+
                                                                    Constantes.col_id_type+", "+Constantes.col_nom_type+", "+
                                                                    Constantes.col_id_unite+", "+Constantes.col_nom_unite+" from "+Constantes.view_ressource+" where id=?");
        statemnt.setInt(0, getId());
        try {
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                setNom(result.getString(0));
                TypeProduit type=new TypeProduit();
                type.setId(result.getInt(1));
                type.setNom(result.getString(2));
                setType(type);
                Unite unite=new Unite();
                unite.setId(result.getInt(3));
                unite.setNom((result.getString(4)));
                setUnite(unite);
            }else{
                throw new RessourceNonExistantException();
            }
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
}
