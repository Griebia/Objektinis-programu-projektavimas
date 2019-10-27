package tconq.entity.strategy;


import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class HouseToTower implements Upgrade {
    @Override
    public void upgrade(TransformTc tc, Map world, Long playerId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(false);     //creates factory for placing buildings

        //deletes house and places tower in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity tower = entityFactory.getEntity("Tower",tc);
            world.addEntity(tower,playerId);
        }
    }

}