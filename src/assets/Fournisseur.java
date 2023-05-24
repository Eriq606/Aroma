package assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;
import utils.exceptions.FournisseurNonExistantException;

public class Fournisseur {
    private int id;
    private String description;
    private String contact;
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId(String idString){
        int realid=Integer.parseInt(idString);
        setId(realid);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public static ArrayList<Fournisseur> getAllFournisseurs(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select * from fournisseur");
        try {
            ArrayList<Fournisseur> fournisseurs=new ArrayList<Fournisseur>();
            ResultSet results=statemnt.executeQuery();
            while(results.next()){
                Fournisseur fournisseur=new Fournisseur();
                fournisseur.setId(results.getInt(0));
                fournisseur.setDescription(results.getString(1));
                fournisseur.setContact(results.getString(2));
                fournisseurs.add(fournisseur);
            }
            return fournisseurs;
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
    public static Fournisseur getFournisseurById(Connection connex, int id) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select * from fournisseur where id=?");
        statemnt.setInt(0, id);
        try {
            ResultSet result=statemnt.executeQuery();
            Fournisseur fournisseur=new Fournisseur();
            if(result.next()){
                fournisseur.setId(result.getInt(0));
                fournisseur.setDescription(result.getString(1));
                fournisseur.setContact(result.getString(2));
                return fournisseur;
            }else{
                throw new FournisseurNonExistantException();
            }
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
    public void getFournisseurById(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=connect.prepareStatement("select * from fournisseur where id=?");
        statemnt.setInt(0, getId());
        try {
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                setDescription(result.getString(1));
                setContact(result.getString(2));
            }else{
                throw new FournisseurNonExistantException();
            }
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }
}
