package village.personnages.humanoides;

import village.interfaces.Travailleur;
import village.personnages.Humanoide;


public class ElfePostier extends Humanoide implements Travailleur {

    public ElfePostier(String nom,String sexe, int age){

        super(nom,sexe,age);

    }

    @Override
    public void emettreSon(){
        System.out.println("*- Secoue son baluchons rempli de cadeaux -*");
    }


    @Override
    public void travailler(){
        System.out.println(getNom() + " se met au travail.");

        modifierMagieDeNoel(5);
    }

}
