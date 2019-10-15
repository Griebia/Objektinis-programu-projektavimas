package tconqserver.tconqserv.observer;

import tconqserver.tconqserv.entities.SEntity;

import java.util.ArrayList;



public class Observer {


    private ArrayList<SEntity> entities;

    private Long observerID;

    private Subject entityGrabber;

    public Observer(Subject entityGrabber, Long playerID){
        this.entityGrabber = entityGrabber;                 //gets entities for observer

        this.observerID = playerID;              //sets observer id to player id

        System.out.println("New Observer " + this.observerID);

        entityGrabber.register(this, playerID);           //creates new observer
    }

    public Long getObserverId(){
        return observerID;
    }

    public Subject getSubject(){
        return entityGrabber;
    }

    public void setId(Long id){
        this.observerID = id;  
    }

    public void getSubject(Subject entityGrabber){
        this.entityGrabber = entityGrabber; 
    }

    //turetu kaskaip informuot kita player kad ivyko kaskokie pakitimai
    //kita playeri gauna per sesiona pasiema sezsiona kuriame yra playeris kuris siuncia duomenis ir ziuri kas dar tame sesione yra
    //turi pasalint obijektus kurie buvo pakeisti ir palikt tik naujus arba istrint visus senus playerio obijektus ir palikt tik naujus
    //arba atnaujint senus obijektus
    public void update(ArrayList<SEntity> entities){
        for (SEntity entity : entities){
            System.out.println(entity.getId());
        }

    }
}
