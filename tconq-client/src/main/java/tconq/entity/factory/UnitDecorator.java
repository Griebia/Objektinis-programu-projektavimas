package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;

public abstract class UnitDecorator implements IEntity{

    protected IEntity tempUnit;

    public UnitDecorator(IEntity newUnit){
        tempUnit = newUnit;
    }

    public String getAttack(){
        return tempUnit.getAttack();
    }

    public String getMovement() {
        return tempUnit.getMovement();
    }

}
