package village.lycanthropes;

import java.util.ArrayList;
import java.util.List;
import village.model.CategorieAge;
import village.model.RangDomination;
import village.personnages.creatures.Lycanthropes;

public class Meute {

    private String nom;
    private CoupleAlpha coupleAlpha;
    private List<Lycanthropes> membres;

    public Meute(String nom, List<Lycanthropes> fondateurs) {
        this.nom = nom;
        this.membres = new ArrayList<>();
        creerHierarchie(fondateurs);
    }

    public void creerHierarchie(List<Lycanthropes> groupe) {
        if (groupe == null) return;
        for (Lycanthropes l : groupe) {
            ajouterLycanthrope(l);
        }
        Lycanthropes meilleurMale = null;
        for (Lycanthropes l : membres) {
            if (l.getCategorieAge() == CategorieAge.ADULTE && l.getSexe() == "M") {
                if (meilleurMale == null || l.getForce() > meilleurMale.getForce()) {
                    meilleurMale = l;
                }
            }
        }
        if (meilleurMale != null) {
            constituerCoupleAlpha(meilleurMale, RangDomination.BETA);
        }
    }

    public void constituerCoupleAlpha(Lycanthropes nouveauMaleAlpha, RangDomination ancienRangDuMale) {
        if (nouveauMaleAlpha == null) return;

        if (coupleAlpha != null && coupleAlpha.getFemelle() != null && coupleAlpha.getFemelle() != nouveauMaleAlpha) {
            coupleAlpha.getFemelle().setRang(ancienRangDuMale != null ? ancienRangDuMale : RangDomination.BETA);
        }

        nouveauMaleAlpha.setRang(RangDomination.ALPHA);

        //On fait pareil que dans creerHierarchie mais pour la femelle
        Lycanthropes meilleureFemelle = null;
        for (Lycanthropes l : membres) {
            if (l.getCategorieAge() == CategorieAge.ADULTE && "F".equals(l.getSexe())) {
                if (meilleureFemelle == null || l.calculerNiveau() > meilleureFemelle.calculerNiveau()) {
                    meilleureFemelle = l;
                }
            }
        }

        if (meilleureFemelle != null) {
            meilleureFemelle.setRang(RangDomination.ALPHA);
        }
        this.coupleAlpha = new CoupleAlpha(nouveauMaleAlpha, meilleureFemelle);

    }

    public void lancerReproduction() {
        if (coupleAlpha == null) return;
        boolean aDesBeta = false;
        for (Lycanthropes l : membres) {
            if (l.getRang() == RangDomination.BETA) {
                aDesBeta = true;
                break;
            }
        }
        List<Lycanthropes> jeunes = coupleAlpha.reproduire(aDesBeta);
        for (Lycanthropes j : jeunes) {
            j.setMeute(this);
            membres.add(j);
        }
        System.out.println("La meute " + nom + " accueille " + jeunes.size() + " nouveau(x) jeune(s) !");
    }

    public void declarerOmegas() {
        if (membres.isEmpty()) return;
        double sommeForce = 0;
        for (Lycanthropes l : membres) {
            sommeForce += l.getForce();
        }
        double moyenne = sommeForce / membres.size();

        for (Lycanthropes l : membres) {
            if (l.getCategorieAge() == CategorieAge.ADULTE && l.getRang() != RangDomination.ALPHA && l.getForce() < moyenne) {
                l.setRang(RangDomination.OMEGA);
            }
        }
    }

    public void decroitreRangsNaturellement() {
        RangDomination[] rangs = RangDomination.values();
        for (Lycanthropes l : membres) {
            if (l.getFacteurDomination() < -3 && l.getRang() != null && l.getRang() != RangDomination.OMEGA) {
                boolean autreMembreMemeSexe = false;
                for (Lycanthropes m : membres) {
                    if (m != l && m.getSexe() == l.getSexe() && m.getRang() == l.getRang()) {
                        autreMembreMemeSexe = true;
                        break;
                    }
                }
                if (autreMembreMemeSexe) {
                    l.setRang(rangs[l.getRang().ordinal() + 1]);
                }
            }
        }
    }

    public void ajouterLycanthrope(Lycanthropes l) {
        if (l != null && !membres.contains(l)) {
            membres.add(l);
            l.setMeute(this);
            if (l.getRang() == null) {
                l.setRang(RangDomination.BETA);
            }
        }
    }

    public void enleverLycanthrope(Lycanthropes l) {
        if (membres.remove(l)) {
            l.setMeute(null);
            l.setRang(null);
        }
    }

    public void afficherCaracteristiques() {
        System.out.println("\n=== Meute : " + nom + " (" + membres.size() + " membres) ===");
        if (coupleAlpha != null) coupleAlpha.afficherCaracteristiques();
        for (Lycanthropes l : membres) {
            l.afficherCaracteristiques();
            System.out.println();
        }
    }

    public String getNom() { return nom; }
    public CoupleAlpha getCoupleAlpha() { return coupleAlpha; }
    public List<Lycanthropes> getMembres() { return membres; }
}