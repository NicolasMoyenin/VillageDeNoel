package village.batiments;

import village.personnages.Personnage;
import village.personnages.humanoides.EnfantVisiteur;
import village.personnages.humanoides.Lutin;

public class BoutiqueJouets extends Batiment {

    public BoutiqueJouets(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,false);
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof Lutin || p instanceof EnfantVisiteur;
    }
}