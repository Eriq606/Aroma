package assets.report;

import java.sql.Date;
import java.time.LocalDate;

public class Recherche {
    LocalDate date_debut;
    LocalDate date_fin;
    public LocalDate getDate_debut() {
        return date_debut;
    }
    public Date getDate_debutSql(){
        Date date=Date.valueOf(getDate_debut());
        return date;
    }
    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }
    public LocalDate getDate_fin() {
        return date_fin;
    }
    public Date getDate_finSql(){
        Date date=Date.valueOf(getDate_fin());
        return date;
    }
    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    } 
}
