package assets;

public class Produit {
    protected int id;
    protected String nom;
    protected Unite unite;
    protected TypeProduit type;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId(String idstring){
        int ids=Integer.parseInt(idstring);
        setId(ids);
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Unite getUnite() {
        return unite;
    }
    public void setUnite(Unite unite) {
        this.unite = unite;
    }
    public TypeProduit getType() {
        return type;
    }
    public void setType(TypeProduit type) {
        this.type = type;
    }
}
