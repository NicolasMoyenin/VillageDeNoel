package village.personnages.creatures;

import village.interfaces.EtreFantastique;
import village.personnages.Creature;

public class EspritDuNord extends Creature implements EtreFantastique {

    public EspritDuNord(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public void emettreSon() {
        // TODO : Afficher un son (ex: souffle glacial / vent du nord)
        System.out.println("*- Vent du nord -*");
    }

    @Override
    public void agirFantastiquement() {
        // TODO : Afficher une action fantastique et augmenter la magie de Noël
        System.out.println(getNom() + " lance un blizzard (mais un tout petit).");
        modifierMagieDeNoel(3);
    }
}