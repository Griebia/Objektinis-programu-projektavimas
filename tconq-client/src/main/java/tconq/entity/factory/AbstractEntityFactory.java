package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public abstract class AbstractEntityFactory {
    public abstract IEntity getEntity(String entityType, TransformTc transform);
    public abstract IEntity getEntityFromDb(String entityType, TransformTc transform);
}