package tconq.memento;

import tconq.entity.IEntity;

public class Data {
    private IEntity entity;
    private Boolean isMovement;

    public Data(IEntity entity, boolean isMovement){
        this.entity = entity;
        this.isMovement = isMovement;
    }

    public IEntity getEntity() {
        return entity;
    }

    public Boolean getMovement() {
        return isMovement;
    }
}
