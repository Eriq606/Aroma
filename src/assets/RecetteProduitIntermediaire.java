package assets;

import java.util.HashMap;

public class RecetteProduitIntermediaire {
    private HashMap<Ressource, Double> recette;
    public HashMap<Ressource, Double> getRecette() {
        return recette;
    }

    public void setRecette(HashMap<Ressource, Double> recette) {
        this.recette = recette;
    }

    public RecetteProduitIntermediaire() {
        setRecette(new HashMap<Ressource, Double>());
    }
    
}
