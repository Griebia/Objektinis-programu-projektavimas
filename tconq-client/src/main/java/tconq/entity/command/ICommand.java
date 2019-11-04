package tconq.entity.command;

import org.joml.Vector2f;

public interface ICommand {
    public void move();
    public void undo();
    public Vector2f getMovement();
}
