package tconqserver.tconqserv.observer;

import tconqserver.tconqserv.entities.SEntity;

import java.util.ArrayList;
import java.util.Hashtable;

public class Subject {

    private Hashtable<Long, Observer> observers;          //registered observers

    public Subject() {
        observers = new Hashtable<Long, Observer>();
    }

    //registers new observer
    public void register(Observer newObserver, Long playerID){
        observers.put(playerID, newObserver);
    }

    //deletes observer
    public void unregister(Long playerID){
        observers.remove(playerID);
    }

    //calls update method for observer which  was updated
    public void notifyObserver(ArrayList<SEntity> entities, Long playerId){
        observers.get(playerId).update(entities);
    }

    //sets new entity
    public void setEntity(ArrayList<SEntity> entities, Long playerID){
        notifyObserver(entities, playerID);
    }
}
