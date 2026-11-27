package village.lycanthropes;

import village.model.TypeHurlement;
import village.personnages.creatures.Lycanthropes;

public class Hurlement {

    private Lycanthropes emetteur;
    private TypeHurlement type;

    public Hurlement(Lycanthropes emetteur, TypeHurlement type) {
        this.emetteur = emetteur;
        this.type = type;
    }

    public void afficherCaracteristiques() {
        String nomMeute;
        if (emetteur.getMeute() != null) {
            nomMeute = emetteur.getMeute().getNom();
        } else {
            nomMeute = "Solitaire";
        }
        System.out.println("Hurlement [" + type + "] émis par " + emetteur.getNom() + " (" + nomMeute + ")");
    }

    public Lycanthropes getEmetteur() { return emetteur; }
    public TypeHurlement getType() { return type; }
}