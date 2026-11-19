package village.personnages;

public abstract class Humanoide extends Personnage{

    private boolean estAuTravail = false;

    public Humanoide(String nom, String sexe, int age){
        super(nom, sexe, age);
    }

    public boolean isEstAuTravail(){
        return estAuTravail;
    }

    public void setEstAuTravail(boolean estAuTravail){
            this.estAuTravail = estAuTravail;
    }
}
