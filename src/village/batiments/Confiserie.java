package village.batiments;

import village.model.Nourriture;
import village.personnages.Humanoide;
import village.personnages.Personnage;

public class Confiserie extends Batiment {

    public Confiserie(String nom, double superficie, int capaciteMax) {
        super(nom,superficie,capaciteMax,false);
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return p instanceof Humanoide;  //Seuls les humanoides rentre
    }

    public void servirNourritureAleatoire(Personnage p) {
        if (getPersonnagesPresents().contains(p)) {
            Nourriture[] choix = Nourriture.values();
            Nourriture aleatoire = choix[new java.util.Random().nextInt(choix.length)];
            p.seRestaurer(aleatoire);
        }
    }
}