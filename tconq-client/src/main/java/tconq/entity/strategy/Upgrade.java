package tconq.entity.strategy;

import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.worldmap.Map;

public interface Upgrade {
    public void upgrade(TransformTc tc, Map world, Long playerId, Long entityId) ;
}