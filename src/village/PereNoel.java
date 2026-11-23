package village;

import village.batiments.Batiment;
import village.personnages.Personnage;
import java.util.List;
import java.util.Scanner;

public class PereNoel {

    private static PereNoel instance;

    private PereNoel() {
        // l'énoncé donne aucune caractéristiques
    }

    public static PereNoel getInstance() {//singleton
        if (instance == null) {
            instance = new PereNoel();
        }
        return instance;
    }

    public void examinerBatiment(Batiment b) {
        if (b != null) {
            b.afficherCaracteristiques();
        }
    }

    public void demanderEntretien(Batiment b) {
        if (b != null) {
            b.nettoyer();
        }
    }

    public boolean transfererPersonnage(Personnage p, Batiment source, Batiment destination) {
        if (p == null || source == null || destination == null) {
            System.out.println("Échec de transfert : personnage ou bâtiment invalide.");
            return false;
        }
        if (!source.getPersonnagesPresents().contains(p)) {
            System.out.println("Échec de transfert : " + p.getNom() + " n'est pas dans " + source.getNom() + ".");
            return false;
        }
        if (!destination.peutAccueillir(p)) {
            System.out.println("Échec de transfert : " + destination.getNom() + " n'accepte pas ce type de personnage.");
            return false;
        }
        if (destination.getNombrePersonnagesPresents() >= destination.getCapaciteMax()) {
            System.out.println("Échec de transfert : " + destination.getNom() + " est déjà complet.");
            return false;
        }

        // Si tout bon, alors on fait le transfert :
        destination.ajouterPersonnage(p);
        source.enleverPersonnage(p);
        System.out.println(p.getNom() + " a été transféré vers " + destination.getNom() + ".");
        return true;
    }


    //Menu pour pere noel
    public void jouerTour(List<Batiment> batiments, int maxActions, Scanner scanner) {
        int actionsRestantes = maxActions;

        while (actionsRestantes > 0) {
            System.out.println("\n=== MENU DU PÈRE NOËL (Actions restantes : " + actionsRestantes + ") ===\n");
            System.out.println("1. Examiner un bâtiment");
            System.out.println("2. Demander l'entretien d'un bâtiment");
            System.out.println("3. Transférer un personnage");
            System.out.println("0. Terminer le tour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            if (choix == 0) {
                break;
            }

            // afficher batiments numerotés pour aider utilisateur a choisir
            for (int i = 0; i < batiments.size(); i++) {
                System.out.println("  [" + i + "] " + batiments.get(i).getNom());
            }

            if (choix == 1) {
                int idx = scanner.nextInt();
                if (0 <= idx && idx < batiments.size()) {
                    examinerBatiment((batiments.get(idx)));
                    actionsRestantes--;
                }

            } else if (choix == 2) {
                int idx = scanner.nextInt();
                if (0 <= idx && idx < batiments.size()) {
                    demanderEntretien((batiments.get(idx)));
                    actionsRestantes--;
                }

            } else if (choix == 3) {
                System.out.print("Numéro du bâtiment de départ : ");
                int idxSource = scanner.nextInt();

                if (idxSource >= 0 && idxSource < batiments.size()) {
                    Batiment source = batiments.get(idxSource);
                    List<Personnage> presents = source.getPersonnagesPresents();

                    if (presents.isEmpty()) {
                        System.out.println("Ce bâtiment est vide !");
                    } else {
                        // affiche perso presents dans batiment source
                        for (int i = 0; i < presents.size(); i++) {
                            System.out.println("  [" + i + "] " + presents.get(i).getNom());
                        }
                        System.out.print("Numéro du personnage à transférer : ");
                        int idxPerso = scanner.nextInt();

                        System.out.print("Numéro du bâtiment de destination : ");
                        int idxDest = scanner.nextInt();

                        if(idxPerso >=0 && idxPerso<presents.size()&& idxDest >=0 && idxDest< batiments.size()){
                            transfererPersonnage(presents.get(idxPerso),source, batiments.get(idxDest));
                            actionsRestantes--;
                        }
                    }
                }
            }
        }
    }
}