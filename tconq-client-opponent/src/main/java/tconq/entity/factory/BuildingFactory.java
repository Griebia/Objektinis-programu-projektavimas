package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public class BuildingFactory extends AbstractEntityFactory {
    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType){
            case "Castle":
                return new Castle(transform);
            case "Tower":
                return new Tower(transform);
            case "House":
                return new House(transform);
            default:
                return null;
        }
    }
}