package tconq.entity.factory;

import tconq.io.DebugHandler;

public class EntityProducer {
    public static final boolean UNIT = true;
    public static final boolean BUILDING = false;

    /**
     * Get entity factory and create objects.
     * True for Units
     * False for Buildings
     * @param factoryType
     * @return factory
     */
    public static AbstractEntityFactory getFactory(boolean factoryType){
        if(factoryType){
            if(DebugHandler.debugmode.factoryDebug) System.out.println("Initialising UnitFactory");
            return new UnitFactory();
        } else {
            if(DebugHandler.debugmode.factoryDebug) System.out.println("Initialising Building Factory!");
            return new BuildingFactory();
        }
    }
}