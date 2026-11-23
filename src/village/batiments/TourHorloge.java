package village.batiments;

import village.personnages.Personnage;

public class TourHorloge extends Batiment {

    private static TourHorloge instance;

    private TourHorloge() {
        super("Tour de l'horloge",36.0,0,false);
    }

    public static TourHorloge getInstance() {
        if(instance == null){
            instance = new TourHorloge();
        }
        return instance;
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return false;
    }
}