package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public class Down implements ICommand {

    //Units info
    private final IEntity unit;
    Vector2f movement = new Vector2f(0,-2);

    //Constructor
    public Down(IEntity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
        
        unit.move(movement);
    }

    @Override
    public void undo() {
        Vector2f movement = new Vector2f(0,2);
        unit.move(movement);
    }

    @Override
    public Vector2f getMovement(){
        return movement;
    }
}
