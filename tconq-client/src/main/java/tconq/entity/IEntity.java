package tconq.entity;

import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public interface IEntity {
    public void render(Shader shader, Camera camera, Map world);
    public void update(float delta, Window window, Camera camera, Map world);
}