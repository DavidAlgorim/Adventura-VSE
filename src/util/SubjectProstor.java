package util;

public interface SubjectProstor {
    /**
     * Metoda slouží k zaregistrování pozorovatele
     *
     * @param observer registrovaný objekt
     */
    void registerObserver(ObserverProstor observer);
    /**
     * Metoda slouží k zrušení registrace pozorovatele
     *
     * @param observer zrušení objektu
     */
    void removeObserver(ObserverProstor observer);
    /**
     * Metoda slouží k aktualizaci objektů
     */
    void notifyObservers();
}
