package tconq.entity.factory;

import tconq.entity.IEntity;

public abstract class AbstractEntityFactory {
    abstract IEntity getEntity(String entityType);
}