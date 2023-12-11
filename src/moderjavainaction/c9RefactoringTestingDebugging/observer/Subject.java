package moderjavainaction.c9RefactoringTestingDebugging.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
