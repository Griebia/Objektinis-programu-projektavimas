package tconqserver.tconqserv.observer;

import tconqserver.tconqserv.entities.SEntity;

import java.util.ArrayList;

public class Observer {

    private ArrayList<SEntity> entities;

    private static int observerIDTracker = 0;

    private int observerID;

    private Subject entityGrabber;

    public Observer(Subject entityGrabber){
        this.entityGrabber = entityGrabber;                 //gets entities for observer

        this.observerID = ++observerIDTracker;              //sets id for observer              ???sita kaskaip reiktu susiet su playerio id manau???

        System.out.println("New Observer " + this.observerID);

        entityGrabber.register(this);           //creates new observer
    }

    //turetu kaskaip informuot kita player kad ivyko kaskokie pakitimai
    public void update(ArrayList<SEntity> entities){
        for (SEntity entity : entities){
            System.out.println(entity.id);
        }

    }
}
