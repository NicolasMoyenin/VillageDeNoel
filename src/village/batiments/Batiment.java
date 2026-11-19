package village.batiments;

import java.util.ArrayList;
import java.util.List;
import village.model.EtatProprete;
import village.personnages.Personnage;

public abstract class Batiment {
    private String nom;
    private double superficie;
    private int capaciteMax;
    private EtatProprete etatProprete;
    private List<Personnage> personnagesPresents;
    private boolean estOuvert; //true exterieur ou ouvert, false interieur ou fermé

    public Batiment(String nom, double superficie, int capaciteMax, boolean estOuvert) {
        this.nom = nom;
        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.estOuvert = estOuvert;
        this.personnagesPresents = new ArrayList<>();
        this.etatProprete = EtatProprete.BON; // propre par défaut
    }

    public abstract boolean peutAccueillir(Personnage p);

    public boolean ajouterPersonnage(Personnage p) {
        if (p != null && personnagesPresents.size() < capaciteMax && !personnagesPresents.contains(p) && peutAccueillir(p) == true) {
            personnagesPresents.add(p);
            return true;
        }
        return false;
    }

    public boolean enleverPersonnage(Personnage p) {
        return personnagesPresents.remove(p);
    }

    public void nettoyer() {
        this.etatProprete = EtatProprete.BON;
        System.out.println("Le bâtiment " + nom + " a été nettoyé.");
    }


    // Getters et Setters utiles
    public String getNom() {
        return nom;
    }

    public double getSuperficie() {
        return superficie;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public int getNombrePersonnagesPresents() {
        return personnagesPresents.size();
    }

    public List<Personnage> getPersonnagesPresents() {
        return personnagesPresents;
    }

    public EtatProprete getEtatProprete() {
        return etatProprete;
    }

    public void setEtatProprete(EtatProprete etatProprete) {
        this.etatProprete = etatProprete;
    }

    public boolean isEstOuvert() {
        return estOuvert;
    }

    public void afficherCaracteristiques() {
        System.out.println("Nom : " +getNom() + ", Superficie : " + getSuperficie() + ", Nb de personnes/capacité : "+ getNombrePersonnagesPresents()+"/"+getCapaciteMax() +
                ", Etat du batiment : " +getEtatProprete() + ", Personnes présentes : "+ getPersonnagesPresents());

    }
}