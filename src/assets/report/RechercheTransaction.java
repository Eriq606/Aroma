package assets.report;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import utils.Constantes;
import utils.MaConnection;

public class RechercheTransaction extends Recherche{
    Transaction[] liste;
    public RechercheTransaction(LocalDate date_debut, LocalDate date_fin) throws Exception {
        super();
        setDate_debut(date_debut);
        setDate_fin(date_fin);
        setListe(getAllTransaction(null));
    }
    public RechercheTransaction(LocalDate date_debut, LocalDate date_fin, Connection connex) throws Exception {
        super();
        setDate_debut(date_debut);
        setDate_fin(date_fin);
        setListe(getAllTransaction(connex));
    }

    //si datefin et datedebut null de toade izy rehetra ny apotra
    public Transaction[] getAllTransaction(Connection connex) throws Exception{
        Connection connect=connex;
        boolean opened=false;
        if(connect==null){
            connect=MaConnection.getConnection(Constantes.database, Constantes.username, Constantes.password);
            opened=true;
        }
        String sql = "select "+Constantes.col_numero_transaction+","+Constantes.col_description_transaction+","+
                                Constantes.col_entree_transaction+","+Constantes.col_sortie_transaction+
                                ", "+Constantes.col_date_transaction+" from "+Constantes.table_transaction;
        String sqlBetween=" where "+Constantes.col_date_transaction+
                                " between '?' and '?'";
        String sqlOrderBy=" order by "+Constantes.col_date_transaction+" desc";
        if(getDate_finSql()==null){
            sqlBetween=" where "+Constantes.col_date_transaction+"<'?'";
        }
        if(getDate_debutSql()!=null){
            sql+=sqlBetween;
        }
        sql+=sqlOrderBy;
        PreparedStatement statemnt=connect.prepareStatement(sql);
        if(getDate_debutSql()!=null){
            statemnt.setDate(0, getDate_debutSql());
            if(getDate_finSql()==null){
                statemnt.setDate(1, Date.valueOf(LocalDate.now()));
            }else{
                statemnt.setDate(1, getDate_finSql());
            }
        }
        try{
            ArrayList<Transaction> liste=new ArrayList<>();
            ResultSet result=statemnt.executeQuery();
            while(result.next()){
                Transaction transaction=new Transaction();
                transaction.setNumero_transaction(result.getInt(0));
                transaction.setDescription(result.getString(1));
                transaction.setEntree(result.getDouble(2));
                transaction.setSortie(result.getDouble(3));
                transaction.setDate(result.getDate(4));
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


    public Transaction[] getListe() {
        return liste;
    }

    public void setListe(Transaction[] liste) {
        this.liste = liste;
    }


}
