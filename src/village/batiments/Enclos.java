package village.batiments;

import village.personnages.Creature;
import village.personnages.Personnage;

public class Enclos extends Batiment {

    public Enclos(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax, true ); //batiment ouvert
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof Creature;
    }
}