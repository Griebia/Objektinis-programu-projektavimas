package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.DebugHandler;

public class BuildingFactory extends AbstractEntityFactory {
    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType.toLowerCase()){
            case "castle":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning castle using Building Factory");
                return new Castle(transform);
            case "tower":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning tower using Building Factory");
                return new Tower(transform);
            case "house":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning house using Building Factory");
                return new House(transform);
            default:
                System.out.println("no such building type");
                return null;
        }
    }
}