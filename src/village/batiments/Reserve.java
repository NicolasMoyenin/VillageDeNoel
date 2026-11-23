package village.batiments;

import java.util.HashMap;
import java.util.Map;
import village.model.Nourriture;
import village.personnages.Personnage;

public class Reserve extends Batiment {

    private Map<Nourriture, Integer> stockNourriture;

    public Reserve(String nom, double superficie) {
        super(nom, superficie, 0, false);
        this.stockNourriture = new HashMap<>();
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return false;
    }

    public void ajouterNourriture(Nourriture nourriture, int quantite) {

        if(nourriture != null && quantite > 0){
            int stockActuel = stockNourriture.getOrDefault(nourriture, 0);
            stockNourriture.put(nourriture, stockActuel + quantite);
        }
    }

    public boolean retirerNourriture(Nourriture nourriture, int quantite) {
        if (nourriture == null || quantite <= 0) {
            return false;
        }
        int stockActuel = stockNourriture.getOrDefault(nourriture, 0);
        if (stockActuel >= quantite) {
            stockNourriture.put(nourriture, stockActuel - quantite);
            return true;
        }
        return false;
    }
}