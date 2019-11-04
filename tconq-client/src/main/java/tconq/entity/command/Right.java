package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.adapter.IMovementAdapter;
import tconq.entity.adapter.RightAdapter;

import java.util.Vector;

public class Right implements ICommand {

    // Units info
    private final IEntity unit;
    Vector2f movement = new Vector2f(2, 0);

    // Constructor
    public Right(IEntity newUnit) {
        unit = newUnit;
    }

    @Override
    public void move() {

        // unit.move(movement);
        moveAdapter();
    }

    private void moveAdapter() {
        ICommand down = new Down(unit);
        IMovementAdapter rightAdapter = new RightAdapter(down, unit);
        rightAdapter.moveAdapter();
    }

    @Override
    public void undo() {
        Vector2f movement = new Vector2f(-2,0);
        unit.move(movement);
    }

    @Override
    public Vector2f getMovement(){
        return movement;
    }
}
