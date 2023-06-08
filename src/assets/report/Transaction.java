package assets.report;

import java.sql.*;

public class Transaction {
    int numero_transaction;
    String description;
    Date date;
    double entree;
    double sortie;

    
    public int getNumero_transaction() {
        return numero_transaction;
    }
    public void setNumero_transaction(int numero_transaction) {
        this.numero_transaction = numero_transaction;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getEntree() {
        return entree;
    }
    public void setEntree(double entree) {
        this.entree = entree;
    }
    public double getSortie() {
        return sortie;
    }
    public void setSortie(double sortie) {
        this.sortie = sortie;
    }
}
