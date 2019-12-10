package tconq.entity.state;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class TowerState implements State {

    @Override
    public void upgrade(StateContext context,TransformTc tc, Map world, Long playerId, Long entityId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(false);     //creates factory for placing buildings

        //deletes tower and places castle in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity castle = entityFactory.getEntity("Castle",tc);
            castle.setId(entityId);
            world.addEntity(castle,playerId);
            context.setState(new CastleState());
            System.out.println("Used state to upgrade the tower to castle");
        }
    }

    @Override
    public void downgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(false);     //creates factory for placing buildings

        //deletes tower and places castle in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity castle = entityFactory.getEntity("House",tc);
            castle.setId(entityId);
            world.addEntity(castle,playerId);
            context.setState(new HouseState());
            System.out.println("Used state to downgrade the tower to house");
        }
    }
}
