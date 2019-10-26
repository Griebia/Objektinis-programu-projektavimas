package tconq.entity;

import tconq.collision.AABB;
import tconq.entity.strategy.Upgrade;
import tconq.io.Window;
import tconq.render.Animation;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public interface IEntity {
    public void render(Shader shader, Camera camera, Map world);
    public void update(float delta, Window window, Camera camera, Map world);



    public void setPlayerId(Long id);
    public Long getPlayerId();

    public void setId(Long id);
    public Long getId();

    public TransformTc getPos();

    public AABB getEntityBoundingBox();

    public void collideWithTiles(Map world) ;

    public void upgrade(Map world, Upgrade newUpgradeStrategy);

    public void collideWithEntity(IEntity entity) ;






    public String getAttack();

    public String getMovement();
}