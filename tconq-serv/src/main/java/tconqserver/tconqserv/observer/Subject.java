package tconqserver.tconqserv.observer;

import tconqserver.tconqserv.entities.SEntity;

import java.util.ArrayList;

public class Subject {

    private ArrayList<Observer> observers;          //registered observers

    public Subject() {
        observers = new ArrayList<Observer>();
    }

    //registers new observer
    public void register(Observer newObserver){
        observers.add(newObserver);
    }

    //notify's player about changes

    //???reiktu kviest tik viena pagal tai koks playeris pabaige turetu but lengva padaryt jei observerio id = playerio id???
    public void notifyObserver(ArrayList<SEntity> entities){
        for (Observer observer : observers){
            observer.update(entities);
        }
    }

    //sets new entity
    public void setEntity(ArrayList<SEntity> entities){
        notifyObserver(entities);
    }
}
