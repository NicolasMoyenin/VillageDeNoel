package village.batiments;

import village.personnages.Personnage;
import village.personnages.creatures.EspritDuNord;
import village.personnages.humanoides.FeeDeNoel;

public class EcoleDeMagie extends Batiment {

    public EcoleDeMagie(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,false);
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof FeeDeNoel || p instanceof EspritDuNord;
    }
}