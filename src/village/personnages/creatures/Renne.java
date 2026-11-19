package village.personnages.creatures;

import village.personnages.Creature;


public class Renne extends Creature {

    public Renne(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public void emettreSon() {
        System.out.println("*- Rhein, Rhein, Rhein -*");
    }
}

