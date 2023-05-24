package assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;
import utils.exceptions.RessourceNonExistantException;

public class Ressource {
    private int id;
    private String nom;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId(String idstring){
        int ids=Integer.parseInt(idstring);
        setId(ids);
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public static ArrayList<Ressource> getAllRessources(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select * from ressource");
        try {
            ArrayList<Ressource> ressources=new ArrayList<Ressource>();
            ResultSet results=statemnt.executeQuery();
            while(results.next()){
                Ressource ressource=new Ressource();
                ressource.setId(results.getInt(0));
                ressource.setNom(results.getString(1));
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
        PreparedStatement statemnt=connect.prepareStatement("select * from ressource where id=?");
        statemnt.setInt(0, id);
        try {
            ResultSet result=statemnt.executeQuery();
            Ressource ressource=new Ressource();
            if(result.next()){
                ressource.setId(result.getInt(0));
                ressource.setNom(result.getString(1));
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
        PreparedStatement statemnt=connect.prepareStatement("select * from produit where id=?");
        statemnt.setInt(0, getId());
        try {
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                setNom(result.getString(1));
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
