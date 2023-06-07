package assets.report;

import java.sql.*;
import java.time.LocalDate;

import utils.Constantes;
import utils.MaConnection;

public class Caisse {
    private LocalDate date;
    private double valeur;

    public static Caisse getDefaultCaisseNow(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        String requete = " select "+Constantes.col_valeur_caisse+" from "+Constantes.view_caisse_now;
        PreparedStatement statemnt=connect.prepareStatement(requete);
        try {
            Caisse caisse=new Caisse();
            caisse.setDate(LocalDate.now());
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                caisse.setValeur(result.getDouble(0));
            }
            return caisse;
        } finally {
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
}
