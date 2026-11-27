package village.model;

public enum RangDomination {
     ALPHA,
    BETA,
    GAMMA,
    DELTA,
    EPSILON,
    ZETA,
    ETA,
    THETA,
    OMEGA;


    //savoir si rang > a un autre
    public boolean estSuperieurA(RangDomination autre) {
        if(this.ordinal()< autre.ordinal()){
            return true;
        }
        return false;
    }
}