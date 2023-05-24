package assets;

public class TypeProduit {
    private int id;
    private String nom;
    private boolean stockable;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean isStockable() {
        return stockable;
    }
    public void setStockable(boolean stockable) {
        this.stockable = stockable;
    }
}
