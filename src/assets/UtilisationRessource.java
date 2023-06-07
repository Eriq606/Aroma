package assets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import utils.Constantes;
import utils.MaConnection;

public class UtilisationRessource {
    LocalDate date;
    HashMap<Ressource, Double> utilisation;
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
    public HashMap<Ressource, Double> getUtilisation() {
        return utilisation;
    }
    public void setUtilisation(HashMap<Ressource, Double> utilisation) {
        this.utilisation = utilisation;
    }
    public UtilisationRessource() {
        setUtilisation(new HashMap<Ressource,Double>());
    }
    public void setUtilisationByPlantation(Plantation plantation){
        setDate(plantation.getDate());
        for(Map.Entry<Ressource,Double> entry:plantation.getProduit().getRecette().getRecette().entrySet()){
            getUtilisation().put(entry.getKey(), entry.getValue()*plantation.getQuantite());
        }
    }
    public boolean insert(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        PreparedStatement statemnt=null;
        try {
            for(Map.Entry<Ressource,Double> entry:getUtilisation().entrySet()){
                statemnt=connect.prepareStatement("insert into "+Constantes.table_mouvement_produit+" values(default,?,'?',0,?,null,null,null)");
                statemnt.setInt(0, entry.getKey().getId());
                statemnt.setDate(1, getUtilsDate());
                statemnt.setDouble(2, entry.getValue());
                statemnt.executeUpdate();
            }
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
