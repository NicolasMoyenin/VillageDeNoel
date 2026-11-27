package village.personnages.creatures;

import village.interfaces.EtreFantastique;
import village.personnages.Creature;
import java.util.Random;
import village.lycanthropes.Hurlement;
import village.model.CategorieAge;
import village.model.RangDomination;
import village.model.TypeHurlement;
import village.lycanthropes.Meute;

public class Lycanthropes extends Creature implements EtreFantastique {
    private CategorieAge categorieAge;
    private int force;
    private int facteurDomination;
    private RangDomination rang; //null si solitaire
    private double facteurImpetuosite;
    private Meute meute;         //null si solitaire

    public Lycanthropes(String nom, String sexe, int age) {
        this(nom, sexe, age,determinerCategorieAge(age),50,1.2 ); //valeur par défaut, 50 = force de base, 1.2 = focteurImpetuosite
    }

    public Lycanthropes(String nom, String sexe, int age, CategorieAge categorieAge, int force, double facteurImpetuosite) {
        super(nom, sexe, age);
        this.categorieAge = categorieAge;
        this.force = force;
        this.facteurDomination = 0;
        this.rang = null;
        this.facteurImpetuosite = facteurImpetuosite;
        this.meute = null;
    }

    public static CategorieAge determinerCategorieAge(int age) {
        if (age < 18) return CategorieAge.JEUNE;
        if (age > 60) return CategorieAge.VIEUX;
        return CategorieAge.ADULTE;
    }

    @Override
    public void agirFantastiquement() {
        System.out.println(getNom() + " se lèche les babines.");
        modifierMagieDeNoel(3);
    }

    @Override
    public void emettreSon() {
        System.out.println("*- AOUUUUUUH -*");
        hurler(TypeHurlement.APPARTENANCE);
    }


public double calculerNiveau() {

    double bonusAge = 0.0;
    double bonusRang = 0.0;
    switch (this.getCategorieAge()) {
        case VIEUX:
            bonusAge = 10.0;
            break;
        case ADULTE:
            bonusAge = 20.0;
            break;
        default:
            break;
    }
    if(rang != null){bonusRang = (10-rang.ordinal())*5;}
    return force + facteurDomination + bonusAge + bonusRang;
}

public void afficherCaracteristiques() {
    System.out.println("Nom : "+ this.getNom()+ ", Sexe : "+ this.getSexe() + ", Force : "+ this.getForce() + ", Facteur de Domination : "+ this.getFacteurDomination() + ", Rang : ");
    if (this.getRang() == null){
        System.out.print("Solitaire");
    }
    else {
        System.out.print(this.getRang());
    }
    System.out.print(", Niveau : "+ this.calculerNiveau() + ", Meute : " + this.getMeute());
}

public Hurlement hurler(TypeHurlement type) {
    Hurlement h = new Hurlement(this, type);
    h.afficherCaracteristiques();
    return h;
}

public void entendreHurlement(Hurlement h) {
    if (this.isEstVivant() && !this.isEnRepos() && h != null && h.getEmetteur() != this){
        System.out.println(this.getNom() + "à entendu le hurlement.");
        if (h.getType() == TypeHurlement.APPARTENANCE && this.getMeute() != null
                && this.getMeute() == h.getEmetteur().getMeute()) // Pour ne pas que le loup réponde aux hurlements d'une autre meute
        {
            hurler(TypeHurlement.APPARTENANCE);
        }
    }
}

public void tenterDomination(Lycanthropes cible) {
    if (cible == null || cible == this) return; //securité
    boolean estFemelleAlpha = cible.getRang() == RangDomination.ALPHA && "F".equals(cible.getSexe());
    if( cible != null && cible != this && !estFemelleAlpha && (this.force * this.facteurImpetuosite)>= cible.getForce()){
        if(this.calculerNiveau() > cible.calculerNiveau() || cible.getRang() == RangDomination.OMEGA){
            System.out.println("Domination réussie ! ");
            this.hurler(TypeHurlement.DOMINATION);
            cible.hurler(TypeHurlement.SOUMISSION);
            this.facteurDomination++;
            cible.facteurDomination--;

            //echange de rang
            RangDomination temp = this.getRang();
            this.setRang(cible.getRang());
            cible.setRang(temp);
        }
        else{
            System.out.println("Échec !");
            cible.hurler(TypeHurlement.AGRESSIVITE);
            this.facteurDomination--;
        }
    }

}

public void seSeparerDeSaMeute() {
    if(this.meute != null){
        meute.enleverLycanthrope(this);
        this.meute = null;
        this.rang = null;
    }
}

public void transformerEnHumain() {
    System.out.println(getNom() + " se transforme en humain !");
    Random de = new Random();
    if(de.nextInt(99) < (calculerNiveau())/2){
        seSeparerDeSaMeute();
        setEstVivant(false);
    }
}

// Getters et Setters
public CategorieAge getCategorieAge() { return categorieAge; }
public void setCategorieAge(CategorieAge categorieAge) { this.categorieAge = categorieAge; }
public int getForce() { return force; }
public void setForce(int force) { this.force = force; }
public int getFacteurDomination() { return facteurDomination; }
public void modifierFacteurDomination(int delta) { this.facteurDomination += delta; }
public RangDomination getRang() { return rang; }
public void setRang(RangDomination rang) { this.rang = rang; }
public double getFacteurImpetuosite() { return facteurImpetuosite; }
public Meute getMeute() { return meute; }
public void setMeute(Meute meute) { this.meute = meute; }
}

