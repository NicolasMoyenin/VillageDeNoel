package village.model;

public enum Nourriture {
    BISCUIT(15),
    CHOCOLAT_CHAUD(20),
    GUIMAUVE(100),
    GRUAU_AVOINE(25),
    SUCRE_ORGE(10),
    BONBON(5);


    private final int valeurNutritive;

    Nourriture(int valeurNutritive){
        this.valeurNutritive = valeurNutritive;
    }

    public int getValeurNutritive(){
        return valeurNutritive;
    }
}
