package assets;

import java.time.LocalDate;

public class Plantation {
    private int id;
    private ProduitIntermediaire produit;
    private double quantite;
    private LocalDate date;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ProduitIntermediaire getProduit() {
        return produit;
    }
    public void setProduit(ProduitIntermediaire produit) {
        this.produit = produit;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
