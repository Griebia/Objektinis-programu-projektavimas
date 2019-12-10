package tconq.entity.state;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class HouseState implements State {

    @Override
    public void upgrade(StateContext context,TransformTc tc, Map world, Long playerId, Long entityId) {

        AbstractEntityFactory entityFactory = EntityProducer.getFactory(false);     //creates factory for placing buildings

        //deletes tower and places castle in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity castle = entityFactory.getEntity("Tower",tc);
            castle.setId(entityId);
            world.addEntity(castle,playerId);

            System.out.println("Used state to upgrade the house to tower");
            context.setState(new MediumUnitState());
        }
    }

    @Override
    public void downgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        world.removeEntity(tc.pos);
    }
}
