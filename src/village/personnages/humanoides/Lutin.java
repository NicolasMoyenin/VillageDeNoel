package village.personnages.humanoides;

import village.interfaces.Divertissable;
import village.interfaces.Travailleur;
import village.interfaces.EtreFantastique;
import village.personnages.Humanoide;

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
}
