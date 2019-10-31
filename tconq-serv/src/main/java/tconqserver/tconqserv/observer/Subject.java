package tconqserver.tconqserv.observer;

import java.util.ArrayList;
import java.util.Hashtable;

import tconqserver.tconqserv.entities.Player;
import tconqserver.tconqserv.entities.SEntity;
import tconqserver.tconqserv.repositories.PlayerRepository;
import tconqserver.tconqserv.repositories.SEntityRepository;

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
    public void notifyObserverEntity(ArrayList<SEntity> entities, Long playerId, SEntityRepository repository){
        observers.get(playerId).update(entities,repository);
    }

    public void notifyObserverPlayer(Player player, PlayerRepository repository){
        observers.get(player.getId()).updatePlayerNextTurn(player,repository);
    }

    //sets new entity
    public void setEntity(ArrayList<SEntity> entities, Long playerID, SEntityRepository repository){
        notifyObserverEntity(entities, playerID, repository);
    }
}
