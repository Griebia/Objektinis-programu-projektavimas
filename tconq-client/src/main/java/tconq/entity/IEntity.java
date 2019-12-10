package tconq.entity;

import org.joml.Vector2f;
import tconq.collision.AABB;
import tconq.entity.strategy.Upgrade;
import tconq.entity.visitor.Visitor;
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

    public boolean move(String direction);
    public void move(Vector2f direction);
    public void undo(boolean isMovement, Map world);
    public void undoMove();

    public AABB getEntityBoundingBox();

    public void collideWithTiles(Map world) ;



    public void collideWithEntity(IEntity entity) ;

    public Class getEntityClass(IEntity entity);


    public boolean getAttack(IEntity enemyUnit);

    public boolean getDestroyBuilding(IEntity enemyBuilding);

    public int getMovement();

    public int getCost();

    public boolean attackChain(IEntity opponent);

    public int accept(Visitor visitor);
}