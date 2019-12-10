package tconq.mediator;

import javax.swing.text.html.parser.Entity;

import tconq.entity.IEntity;
import tconq.entity.factory.Castle;
import tconq.entity.factory.House;
import tconq.entity.factory.MediumUnit;
import tconq.entity.factory.StrongUnit;
import tconq.entity.factory.Tower;
import tconq.entity.factory.WeakUnit;


public class Mediator{

    public Mediator(){}

    public void executeStrongUnitTemplate(StrongUnit entity){
        entity.processEntity();
    }
    public void executeMediumUnitTemplate(MediumUnit entity){
        entity.processEntity();
    }
    public void executeWeakUnitTemplate(WeakUnit entity){
        entity.processEntity();
    }
    public void executeHouseTemplate(House entity){
        entity.processEntity();
    }
    public void executeTowerTemplate(Tower entity){
        entity.processEntity();
    }
    public void executeCastleTemplate(Castle entity){
        entity.processEntity();
    }
}