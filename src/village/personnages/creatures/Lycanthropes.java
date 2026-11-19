package village.personnages.creatures;

import village.interfaces.EtreFantastique;
import village.personnages.Humanoide;

public class Lycanthropes extends Humanoide implements EtreFantastique {

    public Lycanthropes(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public void agirFantastiquement() {
        System.out.println(getNom() + " s'amuse dans le village de Noël.");
        setMagieDeNoel(getMagieDeNoel()*2);
    }

    @Override
    public void emettreSon() {
        System.out.println("*- AOUUUUUUH -*");
    }
}

