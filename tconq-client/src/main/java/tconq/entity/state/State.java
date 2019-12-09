package tconq.entity.state;

import tconq.entity.TransformTc;
import tconq.worldmap.Map;

public interface State {
    public void upgrade(StateContext context,TransformTc tc, Map world, Long playerId, Long entityId);
    public void downgrade(StateContext context,TransformTc tc, Map world, Long playerId, Long entityId);
}
