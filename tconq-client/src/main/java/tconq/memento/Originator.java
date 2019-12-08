package tconq.memento;

import tconq.entity.IEntity;

import java.util.List;

public class Originator {

    private List<IEntity> entities;

    public Originator(){}


    public List<IEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<IEntity> entities) {
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
