package village.batiments;

import village.interfaces.Divertissable;
import village.personnages.Personnage;

public class AireDeJeux extends Batiment {

    public AireDeJeux(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,true);
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof Divertissable;
    }
}