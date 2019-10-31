package tconq.entity.strategy;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class MediumToStrong implements Upgrade {

    @Override
    public void upgrade(TransformTc tc, Map world, Long playerId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes medium unit and places strong unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity strong = entityFactory.getEntity("StrongUnit",tc);
            world.addEntity((Entity)strong, playerId);
        }

    }

}