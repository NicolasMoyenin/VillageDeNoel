package village.batiments;

import village.personnages.Humanoide;
import village.personnages.Personnage;
import village.personnages.creatures.Lycanthropes;

public class ChaletDortoir extends Batiment {

    public ChaletDortoir(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,false); //batiment fermé donc false
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        if(p instanceof Humanoide || (p instanceof Lycanthropes && !getPersonnagesPresents().isEmpty())){return true;}
        return false;
    }
}