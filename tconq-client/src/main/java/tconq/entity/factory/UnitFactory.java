package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.DebugHandler;

public class UnitFactory extends AbstractEntityFactory {

    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType.toLowerCase()){
            case "strongunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning strong unit using Unit Factory");
                return new StrongUnit(transform);
            case "mediumunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning medium unit using Unit Factory");
                return new MediumUnit(transform);
            case "weakunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning weak unit using Unit Factory");
                return new WeakUnit(transform);
            default:
                if(DebugHandler.debugmode.factoryDebug) System.out.println("No such unit type");
                return null;
        }
    }
}