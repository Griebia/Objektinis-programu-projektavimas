package tconq.entity.factory;

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
            return new UnitFactory();
        } else {
            return new BuildingFactory();
        }
    }
}