package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public class BuildingFactory extends AbstractEntityFactory {
    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType.toLowerCase()){
            case "castle":
                return new Castle(transform);
            case "tower":
                return new Tower(transform);
            case "house":
                return new House(transform);
            default:
                return null;
        }
    }
}