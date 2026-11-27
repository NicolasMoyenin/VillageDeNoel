package village.lycanthropes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import village.model.CategorieAge;
import village.model.RangDomination;
import village.personnages.creatures.Lycanthropes;

public class CoupleAlpha {

    private Lycanthropes male;
    private Lycanthropes femelle;

    public CoupleAlpha(Lycanthropes male, Lycanthropes femelle) {
        this.male = male;
        this.femelle = femelle;
    }

    public void afficherCaracteristiques() {
        System.out.println("Couple Alpha : Mâle = " + (male != null ? male.getNom() : "Aucun") + " | Femelle = " + (femelle != null ? femelle.getNom() : "Aucune"));
    }

    public List<Lycanthropes> reproduire(boolean aDesBeta) {
        List<Lycanthropes> portee = new ArrayList<>();
        if (male == null || femelle == null) {
            return portee;
        }
        Random random = new Random();
        int nbPetits = random.nextInt(7) + 1; // 1 a 7 jeunes

        //On ajoute les petits
        for(int i = 0; i <= nbPetits;i++){
            String sexe = random.nextBoolean() ? "M" : "F";
            Lycanthropes petit = new Lycanthropes("Louveteau_" + (i + 1), sexe, 1, CategorieAge.JEUNE, 20, 1.0);

            if(!aDesBeta){
               petit.setRang(RangDomination.BETA);
            }
            else{
                petit.setRang(RangDomination.GAMMA);
            }
            portee.add(petit);
        }



        return portee;
    }

    public Lycanthropes getMale() { return male; }
    public Lycanthropes getFemelle() { return femelle; }
}