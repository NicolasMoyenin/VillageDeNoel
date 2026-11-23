package village.batiments;

import village.personnages.Personnage;
import village.personnages.humanoides.Lutin;

public class AtelierFabrication extends Batiment {

    public AtelierFabrication(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,false);
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof Lutin;
    }
}