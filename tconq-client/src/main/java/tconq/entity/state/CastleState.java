package tconq.entity.state;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class CastleState implements State {

    @Override
    public void upgrade(StateContext context,TransformTc tc, Map world, Long playerId, Long entityId) {
        System.out.println("You cannot upgrade more form castle state");
    }
}
