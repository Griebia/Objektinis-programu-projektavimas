package tconq.entity.state;

import tconq.entity.Entity;
import tconq.entity.TransformTc;
import tconq.worldmap.Map;

public class StrongUnitState implements State {
    @Override
    public void upgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        System.out.println("You cannot upgrade more form strong unit state");
    }
}
