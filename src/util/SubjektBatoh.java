package util;

public interface SubjektBatoh {
    /**
     * Metoda slouží k zaregistrování pozorovatele
     *
     * @param observer registrovaný objekt
     */
    void registerObserver(ObserverBatoh observer);
    /**
     * Metoda slouží k aktualizaci objektů
     */
    void notifyObservers();
}
