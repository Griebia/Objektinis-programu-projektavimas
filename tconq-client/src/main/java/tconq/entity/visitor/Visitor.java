package tconq.entity.visitor;

import tconq.entity.IEntity;

public interface Visitor {

    public int visit(IEntity entity);
}
