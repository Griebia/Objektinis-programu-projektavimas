package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.adapter.IMovementAdapter;
import tconq.entity.adapter.UpAdapter;

public class Up implements ICommand {

    //Units info
    private final IEntity unit;
    Vector2f movement = new Vector2f(0,2);

    //Constructor
    public Up(IEntity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
        
        //unit.move(movement);
        moveAdapter();
    }
   
    private void moveAdapter(){
        ICommand down = new Down(unit);
        IMovementAdapter upAdapter = new UpAdapter(down, unit);
        upAdapter.moveAdapter();
    }

    @Override
    public void undo() {
        Vector2f movement = new Vector2f(0,-2);
        unit.move(movement);
    }

    @Override
    public Vector2f getMovement(){
        return movement;
    }
}
