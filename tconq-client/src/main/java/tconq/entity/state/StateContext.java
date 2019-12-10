package tconq.entity.state;

import tconq.entity.TransformTc;
import tconq.worldmap.Map;

public class StateContext {
    private State state;

    public StateContext(State state){
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void upgrade(TransformTc tc, Map world, Long playerId, Long entityId){
        state.upgrade(this,tc,world,playerId,entityId);
    }
    public void downgrade(TransformTc tc, Map world, Long playerId, Long entityId){
        state.downgrade(this,tc,world,playerId,entityId);
    }
}
