package tconq.entity.strategy;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class TowerToCastle implements Upgrade {

    @Override
    public void upgrade(TransformTc tc, Map world, Long playerId, Long entityId)  {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(false);     //creates factory for placing buildings

        //deletes tower and places castle in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity castle = entityFactory.getEntity("Castle",tc);
            castle.setId(entityId);
            world.addEntity(castle,playerId);

            System.out.println("Used strategy TowerToCastle");
        }

    }

}