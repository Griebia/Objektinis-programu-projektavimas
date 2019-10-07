package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.Entity;
import tconq.entity.TransformTc;

public class UnitFactory extends AbstractEntityFactory {
    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType){
            case "StrongUnit":
                return new StrongUnit(transform);
            case "MediumUnit":
                return new MediumUnit(transform);
            case "WeakUnit":
                return new WeakUnit(transform);
            default:
                return null;
        }
    }
}