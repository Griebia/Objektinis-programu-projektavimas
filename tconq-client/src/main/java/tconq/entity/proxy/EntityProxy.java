package tconq.entity.proxy;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.IEntityUpgrade;
import tconq.server.ServerHandler;
import tconq.worldmap.Map;

public class EntityProxy implements IEntityUpgrade {

    IEntityUpgrade entity;

    public EntityProxy(IEntityUpgrade entity){
        this.entity = entity;
    }

    @Override
    public void upgrade(Map world, Long entityId) {
        IEntity e = (IEntity) entity;
        if(e.getPlayerId() == ServerHandler.instance.playerID){
            entity.upgrade(world, entityId);
            System.out.println("Proxy: Entity upgraded");
        } else {
            System.out.println("Proxy: Cant upgrade enemy entities!"); 
        }
    }

}