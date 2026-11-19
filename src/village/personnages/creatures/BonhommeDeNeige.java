package village.personnages.creatures;
import village.interfaces.EtreFantastique;
import village.personnages.Creature;


public class BonhommeDeNeige extends Creature implements EtreFantastique {


    private int toursEnInterieur = 0; //permettra de compter pour suivre la fonte de la créature
    private static final int MAX_TOURS_INTERIEUR = 3;

    public BonhommeDeNeige(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }


    public void fonteNeige(boolean estEnInterieur) {
        if (!isEstVivant()) {
            return;
        }
        if (estEnInterieur) {
            toursEnInterieur++;
            if (toursEnInterieur >= MAX_TOURS_INTERIEUR){
                setEstVivant(false);
                System.out.println("Oh non ! On dirait bien que "+ getNom()+ " a fondu !");
            }
        } else {
            toursEnInterieur = 0;
        }
    }


    @Override
    public void emettreSon() {
        System.out.println("*- Traine son bas du corps sur le sol -*");
    }

    @Override
    public void agirFantastiquement() {
        System.out.println(getNom() + " se divise en plusieurs petit bonhomme de neige puis se réassemble.");
        modifierMagieDeNoel(3);
    }


}

