package village;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import village.batiments.*;
import village.interfaces.*;
import village.model.*;
import village.personnages.*;
import village.personnages.creatures.*;
import village.personnages.humanoides.*;

public class Village {

    private PereNoel pereNoel;
    private double superficieMax;
    private int capaciteMax;
    private List<Batiment> batiments;

    public Village(double superficieMax, int capaciteMax) {
        this.pereNoel = PereNoel.getInstance();
        this.superficieMax = superficieMax;
        this.capaciteMax = capaciteMax;
        this.batiments = new ArrayList<>();
    }

    //ajout bat en verifiant superficie totale
    public boolean ajouterBatiment(Batiment b) {
        if (b == null || batiments.contains(b)) {
            return false;
        }
        double superficieActuelle = 0;
        for (Batiment bat : batiments) {
            superficieActuelle += bat.getSuperficie();
        }
        if (superficieActuelle + b.getSuperficie() <= superficieMax) {
            batiments.add(b);
            return true;
        }
        System.out.println("Superficie max atteinte pour " + b.getNom());
        return false;
    }

    //Affiche nombre total de personnages dans le village
    public void afficherNombrePersonnages() {
        int total = 0;
        for (Batiment bat : batiments) {
            total += bat.getNombrePersonnagesPresents();
        }
        System.out.println("Nombre total de personnages : "+ total);
    }

    //Affiche personnages de tous les batiments
    public void afficherTousLesBatiments() {
        for (Batiment bat : batiments) {
            bat.afficherCaracteristiques();
        }
    }

    //calculer magie totale du village pour victoire/défaite
    public int calculerMagieTotale() {
        int total = 0;
        for (Batiment b : batiments) {
            for (Personnage p : b.getPersonnagesPresents()) {
                total += p.getMagieDeNoel();
            }
        }
        return total;
    }

    //intervalle de temps (événements aléatoires + activités)
    public void passerIntervalleTemps() {
        Random random = new Random();
        System.out.println("\n--- Un intervalle de temps s'écoule dans le village... ---\n");

        for (Batiment b : batiments) {
            //a) Modifier l'état de propreté du batiment au hasard
            if (random.nextInt(100) < 30) {
                EtatProprete[] etats = EtatProprete.values();
                b.setEtatProprete(etats[random.nextInt(etats.length)]);
            }

            //b) Augmenter stocks dans les réserves au pif
            if (b instanceof Reserve reserve && random.nextInt(100) < 50) {
                Nourriture[] nourritures = Nourriture.values();
                reserve.ajouterNourriture(nourritures[random.nextInt(nourritures.length)], random.nextInt(5) + 1);
            }

            // Copie liste pour pouvoir retirer un personnage mort/fondu (sans erreur)
            List<Personnage> presents = new ArrayList<>(b.getPersonnagesPresents());

            for (Personnage p : presents) {
                //c) Fonte bonhomme de neige a l'interieur
                if (p instanceof BonhommeDeNeige bdn) {
                    bdn.fonteNeige(!b.isEstOuvert());
                    if (!bdn.isEstVivant()) {
                        b.enleverPersonnage(bdn);
                        continue;
                    }
                }

                //d) Affamer + fatiguer personnages aléatoirement
                if (random.nextBoolean()) {
                    p.modifierFaim(random.nextInt(10)+1);
                    p.modifierFatigue(random.nextInt(10)+1);
                }

                //e) Générer aléatoirement émission des sons
                if (random.nextInt(100) < 30) {
                    p.emettreSon();
                }

                //f) Faire agir personnage (selon ses propriétés : Travailleur, Divertissable, EtreFantastique...)
                if(p instanceof Travailleur t){
                    t.travailler();
                }
                if(p instanceof Divertissable d){
                    d.seDivertir();
                }
                if(p instanceof EtreFantastique ef){
                    ef.agirFantastiquement();
                }
                if(b instanceof Confiserie c){
                    c.servirNourritureAleatoire(p);
                }
            }

            // g) Generation aléatoire de rencontres funèbres (si Creature + Humanoide dans mrme bat)

            Creature creaturePresente = null;
            Humanoide humanoidePresent = null;

            for (Personnage p : b.getPersonnagesPresents()) {
                if (p instanceof Creature c && creaturePresente == null) {
                    creaturePresente = c;
                } else if (p instanceof Humanoide h && humanoidePresent == null) {
                    humanoidePresent = h;
                }
            }

            // Si les deux sont dans le batiment, on tente une rencontre funebre
            if (creaturePresente != null && humanoidePresent != null) {
                creaturePresente.rencontreFunebre(humanoidePresent, b.getPersonnagesPresents()); //rappel, y'a une liste de témoins possible
                if (!humanoidePresent.isEstVivant()) { //si meurt
                    b.enleverPersonnage(humanoidePresent);
                }
            }
        }
    }

    // Simulation
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Village village = new Village(2000.0, 50);

        // Initialisation de quelques bâtiments (dont les Singletons)
        village.ajouterBatiment(MaisonPereNoel.getInstance());
        village.ajouterBatiment(TourHorloge.getInstance());
        AtelierFabrication atelier = new AtelierFabrication("Atelier Principal", 150.0, 10);
        ChaletDortoir dortoir = new ChaletDortoir("Chalet du Nord", 100.0, 8);
        Enclos enclos = new Enclos("Enclos des Rennes", 300.0, 10);
        Confiserie confiserie = new Confiserie("La Douceur Polaire", 60.0, 6);
        AireDeJeux aireDeJeux = new AireDeJeux("Place Enneigée", 200.0, 15);
        Reserve reserve = new Reserve("Grande Réserve", 80.0);

        village.ajouterBatiment(atelier);
        village.ajouterBatiment(dortoir);
        village.ajouterBatiment(enclos);
        village.ajouterBatiment(confiserie);
        village.ajouterBatiment(aireDeJeux);
        village.ajouterBatiment(reserve);

        //Création personnages
        Lutin lutin1 = new Lutin("Pippin", "M", 120);
        Lutin lutin2 = new Lutin("Trixie", "F", 95);
        FeeDeNoel fee = new FeeDeNoel("Clochette", "F", 210);
        EnfantVisiteur enfant = new EnfantVisiteur("Léo", "M", 8);
        Renne renne = new Renne("Rudolph", "M", 5);
        Lycanthropes lycan = new Lycanthropes("Fenrir", "M", 40);
        BonhommeDeNeige olaf = new BonhommeDeNeige("Olaf", "M", 1);

        // Placement dans batiments compatibles
        atelier.ajouterPersonnage(lutin1);
        atelier.ajouterPersonnage(fee);
        dortoir.ajouterPersonnage(lutin2);
        dortoir.ajouterPersonnage(lycan); // Compatible car le dortoir n'est pas vide
        aireDeJeux.ajouterPersonnage(enfant);
        enclos.ajouterPersonnage(renne);
        enclos.ajouterPersonnage(olaf);


        // Boucle : victoire si magie >= 600, défaite si magie <= 100
        int magieTotale = village.calculerMagieTotale();
        while (magieTotale > 100 && magieTotale < 600) {
            village.afficherNombrePersonnages();
            System.out.println("Magie de Noël totale du village : " + magieTotale);

            village.passerIntervalleTemps();
            village.pereNoel.jouerTour(village.batiments, 2, scanner); // 2 actions max par tour

            magieTotale = village.calculerMagieTotale();
        }

        if (magieTotale >=600){
            System.out.println("VICTOIRE !!!");
        }
        if (magieTotale <=100){
            System.out.println("Défaite...");
        }
        scanner.close();
    }
}