package util;

public interface SubjektBatoh {
    void registerObserver(ObserverBatoh observer);
    void removeObserver(ObserverBatoh observer);
    void notifyObservers();
}
