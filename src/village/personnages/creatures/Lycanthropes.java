package village.personnages.creatures;

import village.interfaces.EtreFantastique;
import village.personnages.Creature;

public class Lycanthropes extends Creature implements EtreFantastique {

    public Lycanthropes(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public void agirFantastiquement() {
        System.out.println(getNom() + " se lèche les babines.");
        modifierMagieDeNoel(3);
    }

    @Override
    public void emettreSon() {
        System.out.println("*- AOUUUUUUH -*");
    }
}

