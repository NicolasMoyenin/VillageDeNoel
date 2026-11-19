package village.personnages;
import java.util.List;
import java.util.Random;
import village.personnages.creatures.Lycanthropes;

public abstract class Creature  extends Personnage {

    public Creature(String nom, String sexe, int age) {
        super(nom, sexe, age);
    }

    public void rencontreFunebre(Humanoide cible, List<Personnage> temoins) {
        if (!cible.isEstVivant()) {
            return;
        }
        // chance de dévorer (en %) (d'apres énoncé)
        int risque = 10; // Risque bas ici
        if (cible.isEnRepos()) {
            risque += 50; // Surtout quand ils se reposent
        } else if (cible.isEstBruyant()) {
            risque += 25; // quand ils sont bruyants.
        }
        if (this instanceof Lycanthropes) {
            risque += 20; // principalement par les lycanthropes
        }

        Random random = new Random();
        if (random.nextInt(100) < risque) {
            cible.setEstVivant(false);
            System.out.println(cible.getNom() + " s'est tragiquement fait dévorer...");
            for (Personnage p : temoins) {
                if (p instanceof Humanoide h && p.isEstVivant()) {
                    p.modifierMagieDeNoel(-30);
                }
            }
        }
    }
}
