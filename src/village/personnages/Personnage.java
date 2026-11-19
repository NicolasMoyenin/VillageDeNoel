package village.personnages;


//NOTE : les LLM (tel que Gemini) ont m'ont grandement aidé surtout au niveau de certains calculs ou des explications de certaines fonctions.
import village.model.Nourriture;

public abstract class Personnage {
    //caractéristiques des personnages.
    private String nom;
    private String sexe;
    private int age;
    private int faim;          // Indicateur de faim
    private int fatigue;       // Indicateur de fatigue
    private boolean enRepos;   // Permet de savoir s'il se repose ou non
    private int magieDeNoel;   // Indicateur de magie de Noel

    public Personnage(String nom, String sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.faim = 0;         // 0 = pas faim
        this.fatigue = 0;      // 0 = pas fatigué
        this.enRepos = false;  // True par défaut
        this.magieDeNoel = 50; // Valeur de départ
    }

    // 1) Emettre son (abstrait) : chaque sous-classe criera ou chantera différemment
    public abstract void emettreSon();

    // 2) Se déplacer (si le personnage pas en repos)
    public void seDeplacer() {
        if (!enRepos) {
            System.out.println(nom + " se déplace dans le village.");
        } else {
            System.out.println(nom + " se repose et ne peut pas se déplacer.");
        }
    }

    // 3) Se restaurer : Seulement si pas en repos (faim augmente par la meme occasion)
    public void seRestaurer(Nourriture nourriture) {
        if (!enRepos && nourriture != null) {
            this.faim = Math.max(0, this.faim - nourriture.getValeurNutritive());
            System.out.println(nom + " a mangé et sa faim réduit.");
        } else {
            System.out.println(nom + " ne peut pas manger (en train de se reposer ou nourriture invalide).");
        }
    }

    // 4) Se reposer/se mettre en activité
    public void changerEtatRepos(boolean enRepos) {
        this.enRepos = enRepos;
        if (enRepos) {
            System.out.println(nom + " va se reposer.");
        } else {
            System.out.println(nom + " se remet en activité.");
        }
    }

    // 5) Baisser/augmenter magie de Noël
    public void modifierMagieDeNoel(int delta) {
        this.magieDeNoel = Math.max(0, this.magieDeNoel + delta);
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }
    public String getSexe() {
        return sexe;
    }
    public int getAge() {
        return age;
    }
    public int getFaim() {
        return faim;
    }
    public int getFatigue() {
        return fatigue;
    }
    public boolean isEnRepos() {
        return enRepos;
    }
    public int getMagieDeNoel() {
        return magieDeNoel;
    }
}