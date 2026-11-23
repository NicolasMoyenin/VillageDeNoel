package village.batiments;

import village.personnages.Personnage;

public class MaisonPereNoel extends Batiment {

    private static MaisonPereNoel instance;

    private MaisonPereNoel() {
        super("Maison du Père Noël",350.0,0,false);
    }

    public static MaisonPereNoel getInstance() { //singleton (qu'une seule maison du pere noel (pas plus, pas moins)
        if(instance == null){
            instance = new MaisonPereNoel();
        }
        return instance;
    }

    @Override
    public boolean peutAccueillir(Personnage p) {
        return false;
    }
}