package assets.report;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;

public class Report{
    LocalDate date;
    double valeur;
    Transaction[] liste_Transactions;

    public static Report dernierReport( Connection connex ) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        String requete = "  select "+Constantes.col_valeur_report+" ,  "+Constantes.col_date_report+" from "+Constantes.view_dernier_report;
        PreparedStatement statemnt=connect.prepareStatement(requete);
        try{
            Report dernier_report = new Report();
            ResultSet result=statemnt.executeQuery();
            if(result.next()){
                dernier_report.setValeur(result.getDouble(0));
                dernier_report.setDate(result.getDate(1));
            }
            dernier_report.setListe_Transactions(dernier_report.getListeTransation(connect));
            return dernier_report;
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }

    public Transaction[] getListeTransation(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        String requete = " select "+Constantes.col_numero_transaction+" , "+Constantes.col_description_transaction+" , "+
                                    Constantes.col_date_transaction+" , "+Constantes.col_entree_transaction+" , "+
                                    Constantes.col_sortie_transaction+" from "+Constantes.table_transaction+
                                    " where  "+Constantes.col_date_transaction+" between '?' and  now() order by "+Constantes.col_date_transaction+" desc ";
        PreparedStatement statemnt=connect.prepareStatement(requete);
        statemnt.setDate(0, getDateSql());
        try{
            ArrayList<Transaction> liste=new ArrayList<>();
            ResultSet result=statemnt.executeQuery();
            while(result.next()){
                Transaction transaction=new Transaction();
                transaction.setNumero_transaction(result.getInt(0));
                transaction.setDescription(result.getString(1));
                transaction.setDate(result.getDate(2));
                transaction.setEntree(result.getDouble(3));
                transaction.setSortie(result.getDouble(4));
                liste.add(transaction);
            }
            Transaction[] transactions=(Transaction[])liste.toArray();
            return transactions;
        }finally{
            statemnt.close();
            if(opened){
                connect.close();
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }
    public Date getDateSql(){
        return Date.valueOf(getDate());
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDate(Date date){
        setDate(date.toLocalDate());
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Transaction[] getListe_Transactions() {
        return liste_Transactions;
    }

    public void setListe_Transactions(Transaction[] liste_Transactions) {
        this.liste_Transactions = liste_Transactions;
    }

}