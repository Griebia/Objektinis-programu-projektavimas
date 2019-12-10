package tconq.memento;

import tconq.entity.IEntity;

import java.util.List;

public class Originator {

    private Data entities;

    public Originator(){}


    public Data getData() {
        return entities;
    }

    public void setData(Data entities) {
        this.entities = entities;
    }
    //Puts in a memento class the current object
    public Memento save(){
        return new Memento(entities);
    }

    //Sets the current state to a memento
    public void restore(Memento m){
        this.entities = m.getState();
    }
}
