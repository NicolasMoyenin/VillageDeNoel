package village.batiments;

import village.personnages.Personnage;
import village.personnages.humanoides.ElfePostier;

public class BureauDePoste extends Batiment {

    public BureauDePoste(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,false);
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof ElfePostier;
    }
}