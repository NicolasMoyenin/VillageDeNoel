package village.batiments;

import java.util.ArrayList;
import java.util.List;

import village.personnages.Personnage;

public class Batiment {

    public enum DegreProprete {
        MAUVAIS,
        CORRECT,
        BON
    }

    private double superficie;
    private int capaciteAccueil;
    private DegreProprete degreProprete;
    private List<Personnage> PersonnagePresents;

    public Batiment(double superficie, int capaciteAccueil, DegreProprete degreProprete) {
        this.superficie = superficie;
        this.capaciteAccueil = capaciteAccueil;
        this.degreProprete = degreProprete;
        this.PersonnagePresents = new ArrayList<>();
    }

    public boolean ajouterPersonnage(Personnage p) {
        if (PersonnagePresents.size() < capaciteAccueil) {
            PersonnagePresents.add(p);
            return true;
        }
        return false;
    }

    public boolean retirerPersonnage(Personnage p) {
        return PersonnagePresents.remove(p);
    }

    public int getNombrePersonnagePresents() {
        return PersonnagePresents.size();
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public int getCapaciteAccueil() {
        return capaciteAccueil;
    }

    public void setCapaciteAccueil(int capaciteAccueil) {
        this.capaciteAccueil = capaciteAccueil;
    }

    public DegreProprete getDegreProprete() {
        return degreProprete;
    }

    public void setDegreProprete(DegreProprete degreProprete) {
        this.degreProprete = degreProprete;
    }

    public List<Personnage> getPersonnagePresents() {
        return PersonnagePresents;
    }
}