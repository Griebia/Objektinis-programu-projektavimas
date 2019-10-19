package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public class UnitFactory extends AbstractEntityFactory {
    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType.toLowerCase()){
            case "strongunit":
                return new StrongUnit(transform);
            case "mediumunit":
                return new MediumUnit(transform);
            case "weakunit":
                return new WeakUnit(transform);
            default:
                return null;
        }
    }
}