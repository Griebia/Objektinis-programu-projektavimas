package tconq.entity.decorator;

import tconq.entity.Entity;
import tconq.entity.IEntity;

public abstract class UnitDecorator implements IEntity{

    protected IEntity tempUnit;

    public UnitDecorator(IEntity newUnit){
        tempUnit = newUnit;
    }

    public boolean getAttack(IEntity enemyUnit){
        return tempUnit.getAttack(enemyUnit);
    }

    public boolean getDestroyBuilding(IEntity enemyBuilding){
        return tempUnit.getDestroyBuilding(enemyBuilding);
    }

    public int getMovement() {
        return tempUnit.getMovement();
    }



}
