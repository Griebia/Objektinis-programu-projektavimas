package tconq.memento;

import tconq.entity.IEntity;
import tconq.worldmap.Map;

public class Data {
    private IEntity entity;
    private Boolean isMovement;

    public Data(IEntity entity, boolean isMovement){
        this.entity = entity;
        this.isMovement = isMovement;
    }

    public void undo(Map map){
        entity.undo(isMovement,map);
    }

    public IEntity getEntity() {
        return entity;
    }

    public Boolean getMovement() {
        return isMovement;
    }
}
