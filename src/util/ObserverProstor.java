package util;

import logika.Prostor;

public interface ObserverProstor {
    /**
     * Metoda, ve které proběhne aktualizace pozorovatele
     *
     * @param prostor
     */
    void update(Prostor prostor);
}
