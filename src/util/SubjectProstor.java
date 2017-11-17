package util;

public interface SubjectProstor {
    void registerObserver(ObserverProstor observer);
    void removeObserver(ObserverProstor observer);
    void notifyObservers();
}
