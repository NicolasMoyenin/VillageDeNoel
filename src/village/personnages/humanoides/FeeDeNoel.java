package village.personnages.humanoides;

import village.interfaces.EtreFantastique;
import village.interfaces.Travailleur;
import village.personnages.Humanoide;

public class FeeDeNoel extends Humanoide implements Travailleur, EtreFantastique{
    public FeeDeNoel (String nom, String sexe, int age){
        super(nom,sexe,age);
    }

    @Override
    public void emettreSon(){
        System.out.println("*- Tintement magique -*");
    }

    @Override
    public void travailler(){
        System.out.println(getNom() + " se met au travail.");

        modifierMagieDeNoel(5); //selon l'enoncer, travailler augmente magie de noel.
    }

    @Override
    public void agirFantastiquement(){
        System.out.println(getNom() + " Fait pleuvoir des étincelles.");
        modifierMagieDeNoel(3);
    }
}
