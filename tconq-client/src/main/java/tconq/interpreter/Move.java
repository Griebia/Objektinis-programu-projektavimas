package tconq.interpreter;

import java.util.Map;

import tconq.entity.IEntity;
import tconq.entity.IEntityUpgrade;

public class Move extends Expression {

    Map map;
    IEntity entity;
    IEntityUpgrade entityupgrade;

    public Move(Map map){
        this.map = map;
    }

    @Override
    public void interpret(Context context) {
        
    }

}