package assets.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;

public class RechercheReport extends Recherche{

    Report[] liste;

    public RechercheReport(LocalDate date_debut, LocalDate date_fin) throws Exception {
        super();
        setDate_debut(date_debut);
        setDate_fin(date_fin);
        setListe(getAllReport(null));
    }
    public RechercheReport(LocalDate date_debut, LocalDate date_fin, Connection connex) throws Exception {
        super();
        setDate_debut(date_debut);
        setDate_fin(date_fin);
        setListe(getAllReport(connex));
    }

    //si datefin et datedebut null de toade izy rehetra ny apotra
    public Report[] getAllReport(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        String sql = "select "+Constantes.col_valeur_report+","+Constantes.col_date_report+" from "+Constantes.table_report+" where "+Constantes.col_date_report+" between '?' and '?' order by "+Constantes.col_date_report+" desc";
        PreparedStatement statemnt=connect.prepareStatement(sql);
        statemnt.setDate(0, getDate_debutSql());
        statemnt.setDate(1, getDate_finSql());
        try{
            ArrayList<Report> liste=new ArrayList<>();
            ResultSet result=statemnt.executeQuery();
            while(result.next()){
                Report report=new Report();
                report.setValeur(result.getDouble(0));
                report.setDate(result.getDate(1));
                liste.add(report);
            }
            Report[] reports=(Report[])liste.toArray();
            return reports;
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }

    public Report[] getListe() {
        return liste;
    }

    public void setListe(Report[] liste) {
        this.liste = liste;
    }


}
