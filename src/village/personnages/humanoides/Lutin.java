package village.personnages.humanoides;

import village.interfaces.Divertissable;
import village.interfaces.Travailleur;
import village.interfaces.EtreFantastique;
import village.personnages.Humanoide;
import java.util.List;
import village.model.Farce;
import village.personnages.Personnage;
import village.personnages.Creature;

public class Lutin extends Humanoide implements Divertissable, Travailleur, EtreFantastique{

    private boolean estFarceur;

    public Lutin(String nom, String sexe, int age){
        super(nom,sexe,age);
        this.estFarceur = false;
    }

    @Override
    public void emettreSon(){
        System.out.println("*- Son de grelots et petit rire aigu -*");
    }

    @Override
    public void travailler(){
        System.out.println(getNom() + " se met au travail.");

        modifierMagieDeNoel(5); //selon l'enoncer, travailler augmente magie de noel.
    }

    @Override
    public void seDivertir(){
        System.out.println(getNom() + " se met a sautiller partout.");
        modifierMagieDeNoel(3); //augmente aussi la magie de noel

    }

    @Override
    public void agirFantastiquement(){
        System.out.println(getNom() + " souffle de la neige par sa bouche.");
        modifierMagieDeNoel(3);
    }

    public void setEstFarceur(boolean estFarceur) {
        this.estFarceur = estFarceur;
    }

    public void faireFarce(Farce farce, List<Personnage> temoins) {
        if (!estFarceur) {
            return; // rien s'il n'est pas en mode farceur
        }
        System.out.println(getNom() + " fait la farce : " + farce);

        for (Personnage p : temoins) {
            if (p instanceof Humanoide h && !h.isEstAuTravail()) {
                p.modifierMagieDeNoel(2);
            } else if (p instanceof Creature) {
                p.modifierMagieDeNoel(-2);
            }
        }
    }
}


