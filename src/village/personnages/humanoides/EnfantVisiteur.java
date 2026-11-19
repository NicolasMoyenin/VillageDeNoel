package village.personnages.humanoides;
import village.interfaces.Divertissable;
import village.personnages.Humanoide;

public class EnfantVisiteur extends Humanoide implements Divertissable{
    
    public EnfantVisiteur(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public void seDivertir() {
        System.out.println(getNom() + " s'amuse dans le village de Noël.");
        setMagieDeNoel(getMagieDeNoel()*2);
    }

    @Override
    public void emettreSon() {
        System.out.println("*- S'émerveille devant les décorations de Noël -*");
    }

}
